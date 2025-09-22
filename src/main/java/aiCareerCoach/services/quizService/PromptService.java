package aiCareerCoach.services.quizService;

import aiCareerCoach.model.quizResponse.QuizDataInput;
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
    private QuizDataInput quizDataInput;

    private final GeminiService geminiService;
    private final ResourceLoader resourceLoader;

    @Autowired
    public PromptService(GeminiService geminiService, ResourceLoader resourceLoader,
                         QuizResponseService quizResponseService) {
        this.resourceLoader = resourceLoader;
        this.geminiService = geminiService;

    }

    public List<?> getPromptForCollege(String interest, String skills, String goals, String comfort, String lifestyle) {
        Resource resource = resourceLoader.getResource("classpath:/customPrompts/promptForHSC.st");
        PromptTemplate promptTemplate = new PromptTemplate(resource);

      Prompt prompt = promptTemplate.create(Map.of(
                "interest",interest,"skills", skills,
                "goals", goals,"comfort", comfort,"lifeStyle", lifestyle
        ));
        System.out.println("GetContent :"+prompt.getContents());

       return geminiService.generateResponseForStudent(prompt);

    }

    public List<?> getPromptForJuniorCollege(String interest, String skills, String goals, String comfort) {

        Resource resource = resourceLoader.getResource("classpath:/customPrompts/promptForSSC.st");

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

        Resource resource = resourceLoader.getResource("classpath:/customPrompts/promptForFreshgraduate.st");
        PromptTemplate promptTemplate = new PromptTemplate(resource);

        Prompt prompt= promptTemplate.create(Map.of(
                "interest",interest,"skills", skills, "goals", goals,
                "comfort", comfort,"status",status,"exploration",exploration
        ));
        System.out.println("GetContent :"+prompt.getContents());

       return geminiService.generateResponseForGraduate(prompt);
    }


    // This method can be implemented to handle user responses for feedback to llm response
    public List<?> userFeedbackForLlm(String feedback) {
        Prompt prompt;
        String useCase = getUseCase();
        if (!feedback.equalsIgnoreCase("more")) {
                Resource resource = resourceLoader.getResource("classpath:/customPrompts/userFeedback.st");
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


    // Get the use case (grade) from the quiz data input
    public String getUseCase(){
        return quizDataInput.getGrade();
    }
}

