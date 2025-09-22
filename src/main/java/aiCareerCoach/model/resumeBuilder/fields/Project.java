package aiCareerCoach.model.resumeBuilder.fields;

import java.util.List;


public class Project {
    private String title;
    private String description;
    private List<String> technologiesUsed;
    private String githubLink;
    // Getters & Setters

    public String getDescription() { return description;}

    public void setDescription(String description) { this.description = description;}

    public String getGithubLink() { return githubLink;}

    public void setGithubLink(String githubLink) { this.githubLink = githubLink;}

    public List<String> getTechnologiesUsed() { return technologiesUsed;}

    public void setTechnologiesUsed(List<String> technologiesUsed) { this.technologiesUsed = technologiesUsed;}

    public String getTitle() { return title;}

    public void setTitle(String title) {  this.title = title;}

    @Override
    public String toString() {
        return "Project{" +"description= " + description + ", title= " + title +
                ", technologiesUsed= " + technologiesUsed + ", githubLink= " + githubLink + '}';
    }
}