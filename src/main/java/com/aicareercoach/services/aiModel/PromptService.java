package com.aicareercoach.services.aiModel;

import com.aicareercoach.model.initialQuiz.Questions;
import com.aicareercoach.model.initialQuiz.QuizData;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Create a prompt using the PromptService and pass the parameters to it
        The prompt will be used to generate a response from the chat client*/

@Service
public class PromptService {
    private final GeminiService geminiService;
    private final ResourceLoader resourceLoader;

    @Autowired
    public PromptService(GeminiService geminiService,
                         ResourceLoader resourceLoader) {
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

    public String fetchAnswerFromResponse(String key,QuizData quizData) {
        List<Questions> response = quizData.getSections().get(key);
        StringBuilder sb = new StringBuilder();
        for (Questions q : response) {
            sb.append(q.getAnswer()).append(", ");
        }
        return sb.toString();
    }

    public List<?> processQuizData(QuizData quizData,String useCase) {
        String interest=null;
        String skills=null;
        String goals=null;
        String comfort=null;
        String status=null;
        String lifeStyle=null;
        String exploration=null;

        Set<String> keys = quizData.getSections().keySet();
       for(String key : keys){

           if (key.toLowerCase().contains("interest"))
                interest =fetchAnswerFromResponse(key, quizData);

           else if (key.toLowerCase().contains("skills"))
                skills=fetchAnswerFromResponse(key, quizData);

           else if (key.toLowerCase().contains("goals"))
                goals=fetchAnswerFromResponse(key, quizData);

           else if (key.toLowerCase().contains("comfort"))
                comfort=fetchAnswerFromResponse(key, quizData);

           else if (key.toLowerCase().contains("status"))
                 status=fetchAnswerFromResponse(key, quizData);

           else if (key.toLowerCase().contains("lifestyle"))
               lifeStyle=fetchAnswerFromResponse(key, quizData);

           else if(key.toLowerCase().contains("exploration"))
               exploration=fetchAnswerFromResponse(key, quizData);
       }

        // Provide safe defaults in case data is missing
        interest = interest != null ? interest : "Not Provided";
        skills = skills != null ? skills : "Not Provided";
        goals = goals != null ? goals : "Not Provided";
        comfort = comfort != null ? comfort : "Not Provided";
        status = status != null ? status : "Not Provided";
        lifeStyle = lifeStyle != null ? lifeStyle : "Not Provided";
        exploration = exploration != null ? exploration : "Not Provided";

        if(useCase.equalsIgnoreCase("HSC/Diploma"))
            return getPromptForCollege( interest,skills,goals,comfort,lifeStyle);

        else if (useCase.equalsIgnoreCase("SSC"))
            return  getPromptForJuniorCollege(interest,skills,goals,comfort);

        else
            return getPromptForFreshGraduate(interest, skills, goals, comfort, status,exploration);

    }
}

