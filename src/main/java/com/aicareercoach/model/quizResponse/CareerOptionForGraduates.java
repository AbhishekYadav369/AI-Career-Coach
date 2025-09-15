package com.aicareercoach.model.quizResponse;

import org.springframework.stereotype.Component;

@Component
public class CareerOptionForGraduates {
    private String careerPath;
    private String jobProfiles;
    private String futureScope;
    private String eligibility;
    private String lifestyle;
    private String competitiveExams;
    private String PreparationMethodAndCourseDuration;
    private String courses;
    private String requiredSkills;
    private  String financialFactors;

    public String getCareerPath() {
        return careerPath;
    }

    public void setCareerPath(String careerPath) {
        this.careerPath = careerPath;
    }

    public String getCompetitiveExams() {
        return competitiveExams;
    }

    public void setCompetitiveExams(String competitiveExams) {
        this.competitiveExams = competitiveExams;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getFinancialFactors() {
        return financialFactors;
    }

    public void setFinancialFactors(String financialFactors) {
        this.financialFactors = financialFactors;
    }

    public String getFutureScope() {
        return futureScope;
    }

    public void setFutureScope(String futureScope) {
        this.futureScope = futureScope;
    }

    public String getJobProfiles() {
        return jobProfiles;
    }

    public void setJobProfiles(String jobProfiles) {
        this.jobProfiles = jobProfiles;
    }

    public String getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(String lifestyle) {
        this.lifestyle = lifestyle;
    }

    public String getPreparationMethodAndCourseDuration() {
        return PreparationMethodAndCourseDuration;
    }

    public void setPreparationMethodAndCourseDuration(String preparationMethodAndCourseDuration) {
        PreparationMethodAndCourseDuration = preparationMethodAndCourseDuration;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}
