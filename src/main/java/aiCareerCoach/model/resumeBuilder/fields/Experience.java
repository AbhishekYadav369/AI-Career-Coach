package aiCareerCoach.model.resumeBuilder.fields;


public class Experience{
    private String jobTitle;
    private String company;
    private String location;
    private String duration;
    private String responsibility;
    // Getters & Setters

    public String getCompany() { return company;}

    public void setCompany(String company) { this.company = company;}

    public String getDuration() {  return duration;}

    public void setDuration(String duration) { this.duration = duration;}

    public String getJobTitle() { return jobTitle;}

    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle;}

    public String getLocation() { return location;}

    public void setLocation(String location) { this.location = location;}

    public String getResponsibility() { return responsibility;}

    public void setResponsibility(String responsibility) { this.responsibility = responsibility;}

    @Override
    public String toString() {
        return "Experience{" + "company= " + company + ", jobTitle= " + jobTitle +
                ", location= " + location +", duration= " + duration  +
                ", responsibility= " + responsibility + '}';
    }
}