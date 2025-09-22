package aiCareerCoach.services.resumeService;

import aiCareerCoach.model.resumeBuilder.ResumeDTO;
import aiCareerCoach.model.resumeBuilder.fields.*;
import aiCareerCoach.services.llmModelService.GeminiService;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ResumePromptBuilderService {
    private final GeminiService geminiService;
    private final ResumeService resumeService;
    @Value("classpath:customPrompts/personalizedResume.st")
    private Resource resource;
    @Autowired
    public ResumePromptBuilderService(GeminiService geminiService,
                                      ResumeService resumeService) {
        this.geminiService = geminiService;
        this.resumeService = resumeService;
    }

    private String convertIntoString(List<?> obj){
        StringBuilder sb=new StringBuilder();
        for(Object str: obj){
            sb.append(str.toString()).append(",");
        }
        return sb.toString();
    }

        public String generateResumePrompt(ResumeDTO dto, String careerPath) {
            PersonalInformation personalInfo = dto.getPersonalInformation();
            List<Achievement> achievement = dto.getAchievements();
            List<Certification> certification = dto.getCertifications();
            List<Education> education = dto.getEducation();
            List<Interest> interests = dto.getInterests();
            List<Language> languages = dto.getLanguages();
            List<Skill> skills = dto.getSkills();
            List<Project> projects = dto.getProjects();
            List<Experience> experiences = dto.getExperience();

            PromptTemplate promptTemplate = new PromptTemplate(resource);
            // Create Prompt
            Prompt prompt = promptTemplate.create(Map.of(
                    "careerPath",careerPath,
                    "personalInformation", personalInfo.toString(),
                    "Interests", interests.toString(),
                    "Achievement", convertIntoString(achievement),
                    "Certification",convertIntoString(certification),
                    "Education",convertIntoString(education),
                    "Skills",convertIntoString(skills),
                    "Project", convertIntoString(projects),
                    "Experiences",convertIntoString(experiences),
                    "Languages",convertIntoString(languages)
            ));

            // Call LLM → directly mapped to ResumeDTO using BeanOutputConverter
            try {
               return resumeService.generateAndSaveResume(geminiService.generateResumeJSON(prompt), careerPath);
            } catch (Exception e) {
                return "Resume generation failed"+e.getMessage();
            }
        }


    }






