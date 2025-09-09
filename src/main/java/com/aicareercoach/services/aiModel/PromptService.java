package com.aicareercoach.services.aiModel;

import com.aicareercoach.model.initialQuiz.Questions;
import com.aicareercoach.model.initialQuiz.QuizData;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Create a prompt using the PromptService and pass the parameters to it
        The prompt will be used to generate a response from the chat client*/
@Service
public class PromptService {
    private final OllamaService ollamaService;
    @Autowired
    public PromptService(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    public String getPromptForJuniorCollege(String interest, String skills, String goals, String comfort) {
        String temp = """
                  Interested in {interest},skilled in {skills},
                  and comfortable with {comfort} and goal {goals}.
 
                  suggest suitable junior college streams or career paths.
                   By including these aspects :
                    1.future scope
                    2.eligibility
                    3.lifestyle
                    4.preparation methods and course duration
                    5.competitive exams.
             
                """;
        PromptTemplate promptTemplate = new PromptTemplate(temp);

      Prompt prompt = promptTemplate.create(Map.of(
                "interest",interest,"skills", skills,
                "goals", goals,"comfort", comfort
        ));
       return ollamaService.generateResponse(prompt);

    }

    public String getPromptForCollege(String interest, String skills, String goals, String comfort) {
        String temp = """
                  Interested in {interest}, passionate about {passions},
                  skilled in {skills}, and comfortable with {comfort} and goal {goals}.
 
                  suggest suitable junior college streams or career paths.
                   By including these aspects :
                    1.future scope
                    2.eligibility
                    3.lifestyle
                    4.preparation methods and course duration
                    5.competitive exams.
             
                """;
        PromptTemplate promptTemplate = new PromptTemplate(temp);

        Prompt prompt= promptTemplate.create(Map.of(
                "interest",interest,"skills", skills,
                "goals", goals,"comfort", comfort
        ));
        return ollamaService.generateResponse(prompt);
    }

    public String getPromptForFreshGraduate(String interest, String skills,
                                            String goals,String comfort,String status) {
        String temp = """
                Interested in {interest}, skilled in {skills},having
                career goal {goals}, digital comfort {comfort} and Your Current Status &
                Motivation {status}.Please suggest suitable career paths, for each career paths,
                include each points in short in pointer form :
                
                1. Future scope
                2. Eligibility criteria
                3. Lifestyle and work environment
                4. Salary expectations
                5. Required skills
                6. Job security
                """;
        PromptTemplate promptTemplate = new PromptTemplate(temp);

        Prompt prompt= promptTemplate.create(Map.of(
                "interest",interest,"skills", skills,
                "goals", goals,"comfort", comfort,"status",status
        ));
       return ollamaService.generateResponse(prompt);
    }
    public String fetchAnswerFromResponse(String key,QuizData quizData) {
        List<Questions> response = quizData.getSections().get(key);
        StringBuilder sb = new StringBuilder();
        for (Questions q : response) {
            sb.append(q.getAnswer()).append(", ");
        }
        return sb.toString();
    }

    public String processQuizData(QuizData quizData,String useCase) {
        String interest="";
        String skills="";
        String goals="";
        String comfort="";
        String status="";

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

       }
        System.out.println("Prompt generated successfully for " + useCase);
        System.out.println("Interest: " + interest+ ", Skills: " + skills +
                ", Goals: " + goals + ", Comfort: " + comfort + ", Status: " + status);


        if(useCase.equalsIgnoreCase("HSC")){
            return getPromptForJuniorCollege( interest,skills,goals,comfort);

        }

        else if (useCase.equalsIgnoreCase("SSC/Diploma")){
              return  getPromptForCollege(interest,skills,goals,comfort);
        }

        else {
            return getPromptForFreshGraduate(interest, skills, goals, comfort, status);
        }
    }
}

