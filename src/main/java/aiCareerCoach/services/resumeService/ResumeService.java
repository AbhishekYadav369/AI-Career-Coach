package aiCareerCoach.services.resumeService;

import aiCareerCoach.model.resumeBuilder.ResumeDTO;
import aiCareerCoach.model.resumeBuilder.ResumeWrapper;
import aiCareerCoach.model.resumeBuilder.fields.*;
import aiCareerCoach.services.userServiceApp.UserDataWrapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.poi.xwpf.usermodel.*;
import java.io.*;
import java.util.stream.Collectors;

@Service
public class ResumeService {
     private final UserDataWrapper userData;

    @Autowired
    public ResumeService(UserDataWrapper userData)    { this.userData=userData;}


    public byte[] getResume(String userId) {
        ResumeWrapper resumeWrapper = userData.getUser(userId).getResumeWrapper();
        if(resumeWrapper!= null) {
            return resumeWrapper.getAtsDocx();
        }
       return new byte[0];

    }

    public String generateAndSaveResume(ResumeDTO profile, String careerPath,String userId) throws Exception {

                // 2. Convert to ATS DOCX
                byte[] docxBytes = convertProfileToDocx(profile);

                // 3. Save in Mongo
                ResumeWrapper wrapper = new ResumeWrapper();
                wrapper.setUserProfile(profile);
                wrapper.setAtsDocx(docxBytes);
                wrapper.setCareerPath(careerPath);


               userData.saveResume(wrapper,userId);
                return "Resume Saved in Database !";
            }

    public byte[] convertProfileToDocx(ResumeDTO profile) throws Exception {

        XWPFDocument doc = new XWPFDocument();

        // ===== HEADER (Name & Job Title) =====
        XWPFParagraph header = doc.createParagraph();
        header.setAlignment(ParagraphAlignment.LEFT);

        XWPFRun nameRun = header.createRun();
        nameRun.setBold(true);
        nameRun.setFontSize(20);
        nameRun.setText(profile.getPersonalInformation().getFullName());
        nameRun.addBreak();

        XWPFRun titleRun = header.createRun();
        titleRun.setFontSize(12);
        titleRun.setText(profile.getPersonalInformation().getJobTitle());

        // ===== CONTACT INFO (Right-aligned, each on new line) =====
        XWPFParagraph contact = doc.createParagraph();
        contact.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun contactRun = contact.createRun();
        contactRun.setFontSize(10);

        if (profile.getPersonalInformation().getEmail() != null)
            contactRun.setText(profile.getPersonalInformation().getEmail() + "\n");
        if (profile.getPersonalInformation().getPortfolio() != null)
            contactRun.setText(profile.getPersonalInformation().getPortfolio() + "\n");
        if (profile.getPersonalInformation().getPhoneNumber() != null)
            contactRun.setText(profile.getPersonalInformation().getPhoneNumber() + "\n");
        if (profile.getPersonalInformation().getLocation() != null)
            contactRun.setText(profile.getPersonalInformation().getLocation());

        // ===== SUMMARY =====
        if (profile.getPersonalInformation().getSummary() != null) {
            XWPFParagraph summaryPara = doc.createParagraph();
            summaryPara.setSpacingBefore(200);
            XWPFRun summaryRun = summaryPara.createRun();
            summaryRun.setFontSize(11);
            summaryRun.setText(profile.getPersonalInformation().getSummary());
        }

        // ===== WORK EXPERIENCE =====
        if (profile.getExperience() != null && !profile.getExperience().isEmpty()) {
            addSectionHeader(doc, "Work Experience");

            for (Experience exp : profile.getExperience()) {
                XWPFParagraph jobPara = doc.createParagraph();
                XWPFRun jobTitleRun = jobPara.createRun();
                jobTitleRun.setBold(true);
                jobTitleRun.setText(exp.getJobTitle());

                XWPFRun companyRun = jobPara.createRun();
                companyRun.setItalic(true);
                companyRun.setText(" | " + exp.getCompany());

                if (exp.getLocation() != null)
                    companyRun.setText(" | " + exp.getLocation());

                XWPFParagraph durationPara = doc.createParagraph();
                durationPara.setAlignment(ParagraphAlignment.RIGHT);
                durationPara.createRun().setText(exp.getDuration());

                if (exp.getResponsibility() != null && !exp.getResponsibility().trim().isEmpty()) {
                    // split the single string into multiple lines/points
                    String[] respPoints = exp.getResponsibility().split("\\.|\\n|;");

                    for (String resp : respPoints) {
                        if (!resp.trim().isEmpty()) {
                            XWPFParagraph respPara = doc.createParagraph();
                            respPara.setIndentationLeft(400); // indent bullets
                            XWPFRun respRun = respPara.createRun();
                            respRun.setText("• " + resp.trim());
                        }
                    }
                }

            }
        }

        // ===== PROJECTS =====
        if (profile.getProjects() != null && !profile.getProjects().isEmpty()) {
            addSectionHeader(doc, "Projects");

            for (Project project : profile.getProjects()) {
                XWPFParagraph projectTitlePara = doc.createParagraph();
                XWPFRun projectTitleRun = projectTitlePara.createRun();
                projectTitleRun.setBold(true);
                projectTitleRun.setText(project.getTitle());

                if (project.getDescription() != null) {
                    doc.createParagraph().createRun().setText(project.getDescription());
                }
                if (project.getTechnologiesUsed() != null && !project.getTechnologiesUsed().isEmpty()) {
                    doc.createParagraph().createRun()
                            .setText("Technologies: " + String.join(", ", project.getTechnologiesUsed()));
                }
                if (project.getGithubLink() != null) {
                    doc.createParagraph().createRun().setText("GitHub: " + project.getGithubLink());
                }
            }
        }

        // ===== CORE SKILLS =====
        if (profile.getSkills() != null && !profile.getSkills().isEmpty()) {
            addSectionHeader(doc, "Core Skills");

            String skillsList = profile.getSkills().stream()
                    .map(Skill::getTitle)
                    .collect(Collectors.joining(", "));
            doc.createParagraph().createRun().setText(skillsList);
        }

        // ===== EDUCATION =====
        if (profile.getEducation() != null && !profile.getEducation().isEmpty()) {
            addSectionHeader(doc, "Education");

            for (Education edu : profile.getEducation()) {
                XWPFParagraph eduPara = doc.createParagraph();
                XWPFRun eduRun = eduPara.createRun();
                eduRun.setBold(true);
                eduRun.setText(edu.getUniversity());

                XWPFRun degreeRun = eduPara.createRun();
                degreeRun.setText(" | " + edu.getDegree());

                XWPFParagraph gradPara = doc.createParagraph();
                gradPara.setAlignment(ParagraphAlignment.RIGHT);
                gradPara.createRun().setText(edu.getGraduationYear());
            }
        }

        // ===== CERTIFICATIONS =====
        if (profile.getCertifications() != null && !profile.getCertifications().isEmpty()) {
            addSectionHeader(doc, "Certifications");

            for (Certification cert : profile.getCertifications()) {
                doc.createParagraph().createRun()
                        .setText("• " + cert.getTitle() + " - " + cert.getIssuingOrganization()
                                + " (" + cert.getYear() + ")");
            }
        }

        // ===== ACHIEVEMENTS =====
        if (profile.getAchievements() != null && !profile.getAchievements().isEmpty()) {
            addSectionHeader(doc, "Achievements");

            for (Achievement ach : profile.getAchievements()) {
                XWPFParagraph achPara = doc.createParagraph();
                achPara.setIndentationLeft(400);
                XWPFRun achRun = achPara.createRun();
                achRun.setText("• " + ach.getTitle() + " (" + ach.getYear() + ")");
                if (ach.getExtraInformation() != null) {
                    achRun.addBreak();
                    achPara.createRun().setText(ach.getExtraInformation());
                }
            }
        }

        // ===== LANGUAGES =====
        if (profile.getLanguages() != null && !profile.getLanguages().isEmpty()) {
            addSectionHeader(doc, "Languages");

            String langs = profile.getLanguages().stream()
                    .map(Language::getName)
                    .collect(Collectors.joining(", "));
            doc.createParagraph().createRun().setText(langs);
        }

        // ===== INTERESTS =====
        if (profile.getInterests() != null && !profile.getInterests().isEmpty()) {
            addSectionHeader(doc, "Interests");

            String ints = profile.getInterests().stream()
                    .map(Interest::getName)
                    .collect(Collectors.joining(", "));
            doc.createParagraph().createRun().setText(ints);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        doc.write(bos);
        doc.close();
        return bos.toByteArray();
    }

    // === Helper method for uniform section headers ===
    private void addSectionHeader(XWPFDocument doc, String title) {
        XWPFParagraph sectionHeader = doc.createParagraph();
        sectionHeader.setSpacingBefore(300);
        XWPFRun headerRun = sectionHeader.createRun();
        headerRun.setBold(true);
        headerRun.setFontSize(14);
        headerRun.setText(title);
    }
    /**
     * Generate a DOCX file instead of byte[]
     */
    public File generateResumeAsFile(String userId) {
        var fileName="resume";
        ResumeWrapper resumeWrapper = userData.getUser(userId).getResumeWrapper();
        if( resumeWrapper!= null) {
        byte[] docxBytes= resumeWrapper.getAtsDocx();

        File file = new File(fileName.endsWith(".docx") ? fileName : fileName + ".docx");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(docxBytes);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
            return file; // returns the File object you can use
    }
    return null;
    }


}
