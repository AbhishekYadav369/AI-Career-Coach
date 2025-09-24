package aiCareerCoach.services.roadmapService;

import aiCareerCoach.model.careerPath.CareerOptionForGraduates;
import aiCareerCoach.model.careerPath.CareerOptionForStudent;
import aiCareerCoach.model.quizResponse.QuizDataInput;
import aiCareerCoach.model.roadmap.SkillsRoadmapResponse;
import aiCareerCoach.repository.roadmapRepo.RoadmapRepository;
import aiCareerCoach.services.careerPathService.CareerPathService;
import aiCareerCoach.services.quizService.ExtractingQuizData;
import aiCareerCoach.services.llmModelService.GeminiService;
import aiCareerCoach.services.quizService.QuizResponseService;
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
    private final RoadmapRepository repository;
    private final QuizResponseService quizResponse;
    private final ExtractingQuizData extractingQuizData;
    private final CareerPathService service;

    private final GeminiService geminiService;

    @Autowired
    public GapAnalysisAndRoadMap(QuizResponseService quizResponse,GeminiService geminiService,
           CareerPathService service,ExtractingQuizData extractingQuizData,
                                 RoadmapRepository repository) {
        this.geminiService = geminiService;
        this.quizResponse = quizResponse;
        this.service = service;
        this.extractingQuizData = extractingQuizData;
        this.repository = repository;

    }

  /* This method is responsible for fetching QuizResponse and PathResponse and extract skills
      and build a dynamic prompt to analyse skills gaps and generate road map with resources
   * */
    public SkillsRoadmapResponse analyzeGapsAndGenerateRoadMap(String timeline,String quizId,String pathId) {
        QuizDataInput quizInput = quizResponse.getQuizResponseById(quizId);
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
        CareerOptionForStudent student = service.getCareerOptions(pathId).getPathForStudent().getLast();
         requiredSkills = student.getRequiredSkills();
         careerPath = student.getCareerPath();
    }
    else{
        CareerOptionForGraduates graduate = service.getCareerOptions(pathId).getPathForGraduate().getLast();
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
         repository.save(roadmap);
         return roadmap;
    }

    public String getRoadmapId(SkillsRoadmapResponse roadmap) {
        return roadmap.getId();
    }

    public SkillsRoadmapResponse getRoadmapById(String id) {
        return repository.findById(id).orElse(null);
    }

    }

