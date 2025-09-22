package aiCareerCoach.services.roadmapService;

import aiCareerCoach.model.llmResponseFormat.CareerOptionForGraduates;
import aiCareerCoach.model.llmResponseFormat.CareerOptionForStudent;
import aiCareerCoach.model.quizResponse.QuizDataInput;
import aiCareerCoach.model.roadmap.SkillsRoadmapResponse;
import aiCareerCoach.repository.roadmapRepo.RoadmapRepository;
import aiCareerCoach.services.careerPathService.CareerOptionForGraduateService;
import aiCareerCoach.services.careerPathService.CareerOptionForStudentService;
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
    private final CareerOptionForGraduateService careerGraduateService;
    private final CareerOptionForStudentService careerStudentService;
    private final ExtractingQuizData extractingQuizData;

    private final GeminiService geminiService;

    @Autowired
    public GapAnalysisAndRoadMap(QuizResponseService quizResponse,GeminiService geminiService,
           CareerOptionForStudentService careerStudentService, CareerOptionForGraduateService
           careerGraduateService,ExtractingQuizData extractingQuizData,
                                 RoadmapRepository repository) {
        this.geminiService = geminiService;
        this.quizResponse = quizResponse;
        this.careerStudentService = careerStudentService;
        this.careerGraduateService = careerGraduateService;
        this.extractingQuizData = extractingQuizData;
        this.repository = repository;

    }
  /* This method is responsible for fetching QuizResponse and PathResponse and extract skills
      and build a dynamic prompt to analyse skills gaps and generate road map with resources
   * */
    public SkillsRoadmapResponse analyzeGapsAndGenerateRoadMap(String timeline,String quizId,String responseId) {
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
        CareerOptionForStudent student = careerStudentService.getCareerOptionById(responseId);
         requiredSkills = student.getRequiredSkills();
         careerPath = student.getCareerPath();
    }
    else{
        CareerOptionForGraduates graduate = careerGraduateService.getCareerOptionById(responseId);
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

