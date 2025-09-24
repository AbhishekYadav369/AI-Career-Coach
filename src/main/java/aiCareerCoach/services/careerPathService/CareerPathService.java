package aiCareerCoach.services.careerPathService;


import aiCareerCoach.model.careerPath.CareerOptionForGraduates;
import aiCareerCoach.model.careerPath.CareerPathWrapper;
import aiCareerCoach.repository.careerPaths.CareerPathRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareerPathService {
    private CareerPathWrapper response;
    private final CareerPathRepo repository;
    @Autowired
    public CareerPathService(CareerPathRepo repository) {
        this.repository = repository;
    }

    public String saveCareerOption(CareerPathWrapper wrapper) {
          response= repository.save(wrapper);
         return response.getId();
    }


    public CareerPathWrapper getCareerOptions(String id) {
        if(existsById(id)) { return repository.findById(id).orElse(null);}
        return new CareerPathWrapper();
    }

    /**
     * Check if a career option exists by ID
     * @param id document ID
     * @return true if exists, false otherwise
     */
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

}
