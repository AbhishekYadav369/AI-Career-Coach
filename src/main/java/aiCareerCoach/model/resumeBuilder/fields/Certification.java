package aiCareerCoach.model.resumeBuilder.fields;


public  class Certification {
    private String title;
    private String issuingOrganization;
    private String year;
    // Getters & Setters

    public String getIssuingOrganization() {  return issuingOrganization;}

    public void setIssuingOrganization(String issuingOrganization) { this.issuingOrganization = issuingOrganization;}

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    public String getYear() { return year;}

    public void setYear(String year) {  this.year = year;}

    @Override
    public String toString() {
        return "Certification{" +"issuingOrganization= " + issuingOrganization +
                ", title= " + title + ", year= " + year +  '}';
    }
}