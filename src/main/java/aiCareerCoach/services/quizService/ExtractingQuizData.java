package aiCareerCoach.services.quizService;

import aiCareerCoach.model.quizResponse.Questions;
import aiCareerCoach.model.quizResponse.QuizDataInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExtractingQuizData {

    private final PromptService promptService;
    private StringBuilder interest=new StringBuilder();
    private StringBuilder skills=new StringBuilder();
    private StringBuilder goals=new StringBuilder();
    private StringBuilder comfort=new StringBuilder();
    private StringBuilder status=new StringBuilder();
    private StringBuilder lifeStyle=new StringBuilder();
    private StringBuilder exploration=new StringBuilder();

    @Autowired
    public ExtractingQuizData(PromptService promptService) { this.promptService = promptService;}

    // Process the quiz data and call the appropriate prompt generator
    // based on the user's grade
    public List<?> processQuizData(QuizDataInput quizData) {
        String useCase = quizData.getGrade();
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

        if(useCase.equalsIgnoreCase("HSC/Diploma"))
            return promptService.getPromptForCollege( interest.toString(),skills.toString(),goals.toString()
                    ,comfort.toString(),lifeStyle.toString());

        else if (useCase.equalsIgnoreCase("SSC"))
            return promptService.getPromptForJuniorCollege(interest.toString(),skills.toString(),
                    goals.toString(),comfort.toString());

        else
            return promptService.getPromptForFreshGraduate(interest.toString(), skills.toString(),goals.toString(),
                    comfort.toString(), status.toString(),exploration.toString());

    }

    // Fetch answers from the quiz response based on the provided key SectionWise
    public StringBuilder fetchAnswerFromResponse(String key,QuizDataInput dataInput) {
        List<Questions> response = dataInput.getSections().get(key);
        StringBuilder sb = new StringBuilder();
        for (Questions q : response) {
            sb.append(q.getAnswer()).append(", ");
        }
        return sb;
    }


}
