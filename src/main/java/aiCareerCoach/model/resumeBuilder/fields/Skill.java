package aiCareerCoach.model.resumeBuilder.fields;


public class Skill {
    private String title;
    private String level;
    // Getters & Setters

    public String getLevel() { return level;}

    public void setLevel(String level) { this.level = level;}

    public String getTitle() { return title;}

    public void setTitle(String title) { this.title = title;}

    @Override
    public String toString() {
        return "Skill{"+ title + '(' + level + ')'+"}";
    }
}