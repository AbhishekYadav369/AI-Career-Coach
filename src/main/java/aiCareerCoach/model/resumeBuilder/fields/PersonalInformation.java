package aiCareerCoach.model.resumeBuilder.fields;



public class PersonalInformation {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String location;
    private String linkedIn;
    private String gitHub;
    private String portfolio;

    //Getters & Setters

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email;}

    public String getFullName() { return fullName;}

    public void setFullName(String fullName) { this.fullName = fullName;}

    public String getGitHub() { return gitHub;}

    public void setGitHub(String gitHub) { this.gitHub = gitHub;}

    public String getLinkedIn() { return linkedIn;}

    public void setLinkedIn(String linkedIn) { this.linkedIn = linkedIn;}

    public String getLocation() { return location;}

    public void setLocation(String location) { this.location = location;}

    public String getPhoneNumber() { return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}

    public String getPortfolio() { return portfolio;}

    public void setPortfolio(String portfolio) { this.portfolio = portfolio;}

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "email= " + email +
                ", fullName= " + fullName +
                ", phoneNumber= " + phoneNumber +
                ", location= " + location +
                ", linkedIn= " + linkedIn  +
                ", gitHub= " + gitHub +
                ", portfolio= " + portfolio +
                '}';
    }
}
