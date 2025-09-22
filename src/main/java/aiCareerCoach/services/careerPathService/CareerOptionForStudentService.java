package aiCareerCoach.services.careerPathService;

import aiCareerCoach.model.llmResponseFormat.CareerOptionForStudent;
import aiCareerCoach.repository.careerpaths.CareerOptionStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CareerOptionForStudentService {
    private final CareerOptionStudentRepository repository;
    @Autowired
    public CareerOptionForStudentService(CareerOptionStudentRepository repository) {
        this.repository = repository;
    }

    /**
     * Save a new CareerOptionForStudent document
     * @param careerOption Career option to save
     * @return saved document with generated ID
     */
    public String saveCareerOption(List<?> careerOption) {
        CareerOptionForStudent response = null;
        for(Object option:careerOption){
            response=repository.save((CareerOptionForStudent) option);
        }
        if(response!=null)
            return response.getId();
        return "Career Option not saved to Repository";
    }

    /**
     * Fetch all career options for students
     * @return List of CareerOptionForStudent
     */
    public List<CareerOptionForStudent> getAllCareerOptions() {
        return repository.findAll();
    }

    /**
     * Fetch a career option by its MongoDB ID
     * @param id document ID
     * @return CareerOptionForStudent or null if not found
     */
    public CareerOptionForStudent getCareerOptionById(String id) {
        Optional<CareerOptionForStudent> option = repository.findById(id);
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

