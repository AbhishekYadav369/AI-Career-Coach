package aiCareerCoach.services.userServiceApp;

import aiCareerCoach.model.careerPath.CareerPathWrapper;
import aiCareerCoach.model.quizResponse.QuizDataInput;
import aiCareerCoach.model.resumeBuilder.ResumeWrapper;
import aiCareerCoach.model.roadmap.SkillsRoadmapResponse;
import aiCareerCoach.model.userInfo.Users;
import aiCareerCoach.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataWrapper {

    private final UserRepository repository;

    @Autowired
    public UserDataWrapper(UserRepository repository) {
        this.repository = repository;
    }

    public boolean checkUserById(String id){
        return repository.existsById(id);
    }

    public String getUserId(String username) {
        Users user = repository.findByUsername(username);
        return (user != null) ? user.getId() : null;
    }

    public Users getUser(String userId) {
        return repository.findById(userId).orElse(null);
    }

    private void updateUser(Users user) {
        repository.save(user); // full user document updated (with embedded objects)
    }

    public void saveQuizData(QuizDataInput quizData, String userId) {
        Users user = getUser(userId);
        if (user != null) {
            user.setQuizResponse(quizData);
            updateUser(user);
        }
    }

    public void saveCareerPath(CareerPathWrapper wrapper, String userId) {
        Users user = getUser(userId);
        if (user != null) {
            user.setCareerPath(wrapper);
            updateUser(user);
        }
    }

    public void saveRoadmap(SkillsRoadmapResponse response, String userId) {
        Users user = getUser(userId);
        if (user != null) {
            user.setRoadmapResponse(response);
            updateUser(user);
        }
    }

    public void saveResume(ResumeWrapper resume, String userId) {
        Users user = getUser(userId);
        if (user != null) {
            user.setResumeWrapper(resume);
            updateUser(user);
        }
    }


}
