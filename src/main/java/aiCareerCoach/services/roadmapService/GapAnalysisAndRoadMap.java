package aiCareerCoach.services.roadmapService;

import aiCareerCoach.model.careerPath.CareerOptionForGraduates;
import aiCareerCoach.model.careerPath.CareerOptionForStudent;
import aiCareerCoach.model.quizResponse.QuizDataInput;
import aiCareerCoach.model.roadmap.SkillsRoadmapResponse;
import aiCareerCoach.services.quizService.ExtractingQuizData;
import aiCareerCoach.services.llmModelService.GeminiService;
import aiCareerCoach.services.userServiceApp.UserDataWrapper;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class GapAnalysisAndRoadMap {
    @Value("classpath:customPrompts/personalizedRoadMap.st")
    private Resource resource;
    private final ExtractingQuizData extractingQuizData;
    private final UserDataWrapper userData;
    private final GeminiService geminiService;

    @Autowired
    public GapAnalysisAndRoadMap(GeminiService geminiService,ExtractingQuizData
            extractingQuizData,UserDataWrapper userData) {
        this.geminiService = geminiService;
        this.extractingQuizData = extractingQuizData;
        this.userData = userData;

    }

  /* This method is responsible for fetching QuizResponse and PathResponse and extract skills
      and build a dynamic prompt to analyse skills gaps and generate road map with resources
   * */
    public String analyzeGapsAndGenerateRoadMap(String timeline,String userId) {

        QuizDataInput quizInput = userData.getUser(userId).getQuizResponse();
        String oldSkillsKey = quizInput.getSections()
                .keySet().stream()
                .filter(key -> key.toLowerCase().contains("skills"))
                .findFirst()
                .orElse(null);
        String oldSkills= extractingQuizData.fetchAnswerFromResponse
                (oldSkillsKey,quizInput).toString();
        String requiredSkills;
        String careerPath;
    if(!quizInput.getGrade().equalsIgnoreCase("GRADUATE")) {
        CareerOptionForStudent student = userData.getUser(userId).getCareerPath()
                .getPathForStudent().getLast();
         requiredSkills = student.getRequiredSkills();
         careerPath = student.getCareerPath();
    }
    else{
        CareerOptionForGraduates graduate = userData.getUser(userId).getCareerPath()
                .getPathForGraduate().getLast();
        requiredSkills = graduate.getRequiredSkills();
        careerPath = graduate.getCareerPath();
    }
        PromptTemplate promptTemplate = new PromptTemplate(resource);
        Prompt prompt = promptTemplate.create(Map.of(
                "skills", oldSkills,
                "requiredSkills", requiredSkills,
                "careerPath", careerPath,
                "timeFrame", timeline
        ));
            SkillsRoadmapResponse roadmap=geminiService.generateRoadmap(prompt);
        // Persist to MongoDB
        userData.saveRoadmap(roadmap,userId);
         return "Added Roadmap to database successfully!";
    }

      public SkillsRoadmapResponse getRoadmapById(String userId) {
        return userData.getUser(userId).getRoadmapResponse();
    }

    }

