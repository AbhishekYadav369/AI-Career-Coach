package aiCareerCoach.model.resumeBuilder;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "resumes")
public class ResumeWrapper {

    @Id
    private String id;

    private ResumeDTO resumeDTO;

    // Store ATS-friendly resume DOCX (binary)
    private byte[] atsDocx;

    private String careerPath; // e.g., "Full Stack Developer"
    private String createdAt;

    // Getters & Setters

    public byte[] getAtsDocx() { return atsDocx;}

    public void setAtsDocx(byte[] atsDocx) { this.atsDocx = atsDocx;}

    public String getCareerPath() { return careerPath;}

    public void setCareerPath(String careerPath) { this.careerPath = careerPath;}

    public String getCreatedAt() { return createdAt;}

    public void setCreatedAt(String createdAt) { this.createdAt = createdAt;}

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public ResumeDTO getUserProfile() { return resumeDTO;}

    public void setUserProfile(ResumeDTO resumeDTO) { this.resumeDTO = resumeDTO;}
}

