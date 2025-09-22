package aiCareerCoach.services.careerPathService;


import aiCareerCoach.model.llmResponseFormat.CareerOptionForGraduates;
import aiCareerCoach.repository.careerpaths.CareerOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareerOptionForGraduateService {
    private final CareerOptionRepository repository;
    @Autowired
    public CareerOptionForGraduateService(CareerOptionRepository repository) {
        this.repository = repository;
    }

    /**
     * Save a new CareerOptionForGraduates document
     * @param careerOption Career option to save
     * @return saved document with generated ID
     */
    public String saveCareerOption(List<?> careerOption) {
        CareerOptionForGraduates response=null;
        for(Object option : careerOption){
           response= repository.save((CareerOptionForGraduates) option);
        }
        if(response!=null)
            return response.getId();
        return "Career Path not saved to Repository";
    }

    /**
     * Fetch all career options
     * @return List of CareerOptionForGraduates
     */
    public List<CareerOptionForGraduates> getAllCareerOptions() {
        return repository.findAll();
    }

    /**
     * Fetch a career option by its MongoDB ID
     * @param id document ID
     * @return CareerOptionForGraduates or null if not found
     */
    public CareerOptionForGraduates getCareerOptionById(String id) {
        Optional<CareerOptionForGraduates> option = repository.findById(id);
        return option.orElse(null);
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
