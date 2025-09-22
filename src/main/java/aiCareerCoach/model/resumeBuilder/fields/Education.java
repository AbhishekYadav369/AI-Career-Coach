package aiCareerCoach.model.resumeBuilder.fields;

public  class Education {
    private String degree;
    private String university;
    private String location;
    private String graduationYear;
    // Getters & Setters

    public String getDegree() { return degree;}

    public void setDegree(String degree) { this.degree = degree;}

    public String getGraduationYear() { return graduationYear;}

    public void setGraduationYear(String graduationYear) { this.graduationYear = graduationYear;}

    public String getLocation() { return location;}

    public void setLocation(String location) { this.location = location;}

    public String getUniversity() { return university;}

    public void setUniversity(String university) { this.university = university;}

    @Override
    public String toString() {
        return "Education{" + "degree= " + degree + ", university= " + university +
                ", location= " + location +", graduationYear= " + graduationYear+ '}';
    }
}