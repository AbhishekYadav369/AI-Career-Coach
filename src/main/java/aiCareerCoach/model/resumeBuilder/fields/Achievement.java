package aiCareerCoach.model.resumeBuilder.fields;


public  class Achievement{
    private String title;
    private String year;
    private String extraInformation;
    // Getters & Setters


    public String getExtraInformation() { return extraInformation;}

    public void setExtraInformation(String extraInformation) { this.extraInformation = extraInformation;}

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    public String getYear() { return year;}

    public void setYear(String year) { this.year = year;}

    @Override
    public String toString() {
        return "Achievement{" + "extraInformation= " + extraInformation +
                ", title= " + title + ", year= " + year + '}';
    }
}