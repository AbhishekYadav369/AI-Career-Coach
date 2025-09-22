package aiCareerCoach.model.resumeBuilder;

import aiCareerCoach.model.resumeBuilder.fields.*;

import java.util.List;

public class ResumeDTO {

    private PersonalInformation personalInformation;
    private String userDescription; // dynamic description entered by user
    private List<Skill> skills;
    private List<Experience> experience;
    private List<Education> education;
    private List<Certification> certifications;
    private List<Project> projects;
    private List<Achievement> achievements;
    private List<Language> languages;
    private List<Interest> interests;

    // Getters & Setters

    public List<Achievement> getAchievements() { return achievements;}

    public void setAchievements(List<Achievement> achievements) {  this.achievements = achievements;}

    public List<Certification> getCertifications() {  return certifications;}

    public void setCertifications(List<Certification> certifications) { this.certifications = certifications;}

    public List<Education> getEducation() { return education;}

    public void setEducation(List<Education> education) { this.education = education;}

    public List<Experience> getExperience() { return experience;}

    public void setExperience(List<Experience> experience) { this.experience = experience;}

    public List<Interest> getInterests() { return interests;}

    public void setInterests(List<Interest> interests) { this.interests = interests;}

    public List<Language> getLanguages() { return languages;}

    public void setLanguages(List<Language> languages) { this.languages = languages;}

    public PersonalInformation getPersonalInformation() { return personalInformation;}

    public void setPersonalInformation(PersonalInformation personalInformation) { this.personalInformation = personalInformation;}

    public List<Project> getProjects() { return projects;}

    public void setProjects(List<Project> projects) { this.projects = projects;}

    public List<Skill> getSkills() { return skills;}

    public void setSkills(List<Skill> skills) { this.skills = skills;}

    public String getUserDescription() { return userDescription;}

    public void setUserDescription(String userDescription) { this.userDescription = userDescription;}

}
