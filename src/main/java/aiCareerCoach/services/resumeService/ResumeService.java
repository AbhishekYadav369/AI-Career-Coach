package aiCareerCoach.services.resumeService;

import aiCareerCoach.model.resumeBuilder.ResumeDTO;
import aiCareerCoach.model.resumeBuilder.ResumeWrapper;
import aiCareerCoach.repository.resumeData.ResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Service
public class ResumeService {
    private final ResumeRepository repository;
    @Autowired
    public ResumeService(ResumeRepository repository) {
        this.repository = repository;
    }

    public String getId(ResumeWrapper resumeWrapper) {
       return resumeWrapper.getId();
    }

    public byte[] getResume(String id) {
        ResumeWrapper resumeWrapper = repository.findById(id).orElse(null);
        if(resumeWrapper!= null) {
            return resumeWrapper.getAtsDocx();
        }
       return new byte[0];

    }

            public String generateAndSaveResume(ResumeDTO profile, String careerPath) throws Exception {

                // 2. Convert to ATS DOCX
                byte[] docxBytes = convertProfileToDocx(profile);

                // 3. Save in Mongo
                ResumeWrapper wrapper = new ResumeWrapper();
                wrapper.setUserProfile(profile);
                wrapper.setAtsDocx(docxBytes);
                wrapper.setCareerPath(careerPath);
                wrapper.setCreatedAt(LocalDateTime.now().toString());

                return getId(repository.save(wrapper));
            }

            private byte[] convertProfileToDocx(ResumeDTO profile) throws Exception {

                XWPFDocument doc = new XWPFDocument();

                // Header
                XWPFParagraph header = doc.createParagraph();
                header.setAlignment(ParagraphAlignment.CENTER);
                header.createRun().setText(profile.getPersonalInformation().getFullName());
                header.createRun().addBreak();
                header.createRun().setText(profile.getPersonalInformation().getEmail() + " | " + profile.getPersonalInformation().getPhoneNumber());

                // Summary
                XWPFParagraph summary = doc.createParagraph();
                summary.setAlignment(ParagraphAlignment.LEFT);
                summary.createRun().setBold(true);
                summary.createRun().setText("Summary");
                summary.createRun().addBreak();
                summary.createRun().setText(profile.getUserDescription());

                // Skills
                XWPFParagraph skillsPara = doc.createParagraph();
                skillsPara.createRun().setBold(true);
                skillsPara.createRun().setText("Skills");
                skillsPara.createRun().addBreak();
                if (profile.getSkills() != null) {
                    profile.getSkills().forEach(skill ->
                            skillsPara.createRun().setText("• " + skill.getTitle() + " (" + skill.getLevel() + ")")
                    );
                }

                // Experience
                XWPFParagraph expPara = doc.createParagraph();
                expPara.createRun().setBold(true);
                expPara.createRun().setText("Experience");
                expPara.createRun().addBreak();
                if (profile.getExperience() != null) {
                    profile.getExperience().forEach(exp -> {
                        expPara.createRun().setText(exp.getJobTitle() + " - " + exp.getCompany());
                        expPara.createRun().addBreak();
                        expPara.createRun().setText(exp.getDuration() + " | " + exp.getLocation());
                        expPara.createRun().addBreak();
                        expPara.createRun().setText("Responsibilities: " + exp.getResponsibility());
                        expPara.createRun().addBreak();
                    });
                }

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                doc.write(bos);
                doc.close();
                return bos.toByteArray();
            }
    }
