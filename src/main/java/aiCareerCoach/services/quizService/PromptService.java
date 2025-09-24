package aiCareerCoach.services.quizService;

import aiCareerCoach.services.llmModelService.GeminiService;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


/* Create a prompt using the PromptService and pass the parameters to it
      The prompt will be used to generate a response from the chat client*/

@Service
public class PromptService {
    private final GeminiService geminiService;
    private final ResourceLoader loader;
    private final QuizResponseService quizResponseService;

    @Autowired
    public PromptService(GeminiService geminiService, ResourceLoader loader,
                          QuizResponseService quizResponseService) {
        this.loader = loader;
        this.geminiService = geminiService;
        this.quizResponseService = quizResponseService;
    }

    public List<?> getPromptForCollege(String interest, String skills, String goals, String comfort, String lifestyle) {
        Resource resource = loader.getResource("classpath:/customPrompts/promptForHSC.st");
        PromptTemplate promptTemplate = new PromptTemplate(resource);

      Prompt prompt = promptTemplate.create(Map.of(
                "interest",interest,"skills", skills,
                "goals", goals,"comfort", comfort,"lifeStyle", lifestyle
        ));
        System.out.println("GetContent :"+prompt.getContents());

       return geminiService.generateResponseForStudent(prompt);

    }

    public List<?> getPromptForJuniorCollege(String interest, String skills, String goals, String comfort) {

        Resource resource = loader.getResource("classpath:/customPrompts/promptForSSC.st");

        PromptTemplate promptTemplate = new PromptTemplate(resource);

        Prompt prompt= promptTemplate.create(Map.of(
                "interest",interest,"skills", skills,
                "goals", goals,"comfort", comfort
        ));
        System.out.println("GetContent :"+prompt.getContents());

        return geminiService.generateResponseForStudent(prompt);
    }

    public List<?> getPromptForFreshGraduate(String interest, String skills,String goals,
                                            String comfort,String status,String exploration) {

        Resource resource = loader.getResource("classpath:/customPrompts/promptForFreshgraduate.st");
        PromptTemplate promptTemplate = new PromptTemplate(resource);

        Prompt prompt= promptTemplate.create(Map.of(
                "interest",interest,"skills", skills, "goals", goals,
                "comfort", comfort,"status",status,"exploration",exploration
        ));
        System.out.println("GetContent :"+prompt.getContents());

       return geminiService.generateResponseForGraduate(prompt);
    }


    // This method can be implemented to handle user responses for feedback to llm response
    public List<?> userFeedbackForLlm(String feedback,String quizId) {
        Prompt prompt;
        String useCase = quizResponseService.getGrade(quizId);
        if (!feedback.equalsIgnoreCase("more")) {
                Resource resource = loader.getResource("classpath:/customPrompts/userFeedback.st");
                PromptTemplate promptTemplate = new PromptTemplate(resource);
                prompt = promptTemplate.create(Map.of(
                    "careerPath", feedback));
        }
        else {
            String template = """ 
                    You are a career advisor bot designed to help users explore various career paths based on their feedback and specific requests.
                    Provide more career paths matching to the  same user's profile for student of grade: {useCase}""";
            PromptTemplate promptTemplate = new PromptTemplate(template);
            prompt = promptTemplate.create(Map.of("useCase",useCase));
        }
        if (useCase.equalsIgnoreCase("GRADUATE"))
                return geminiService.generateResponseForGraduate(prompt);
        else
                return geminiService.generateResponseForStudent(prompt);

    }

}

