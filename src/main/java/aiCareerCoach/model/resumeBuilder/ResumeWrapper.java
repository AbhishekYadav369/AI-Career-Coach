package aiCareerCoach.model.resumeBuilder;

public class ResumeWrapper {

    private ResumeDTO resumeDTO;

    // Store ATS-friendly resume DOCX (binary)
    private byte[] atsDocx;

    private String careerPath; // e.g., "Full Stack Developer"


    // Getters & Setters

    public byte[] getAtsDocx() { return atsDocx;}

    public void setAtsDocx(byte[] atsDocx) { this.atsDocx = atsDocx;}

    public String getCareerPath() { return careerPath;}

    public void setCareerPath(String careerPath) { this.careerPath = careerPath;}

    public ResumeDTO getUserProfile() { return resumeDTO;}

    public void setUserProfile(ResumeDTO resumeDTO) { this.resumeDTO = resumeDTO;}
}

