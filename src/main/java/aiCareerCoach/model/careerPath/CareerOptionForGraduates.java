package aiCareerCoach.model.careerPath;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "careerOptions")
public class CareerOptionForGraduates{
    @Id
    private String id; // MongoDB document ID
    private String careerPath;
    private String futureScope;
    private String eligibility;
    private String lifestyle;
    private String competitiveExams;
    private String preparationMethod;
    private String courseDuration;
    private String courses;
    private String requiredSkills;
    private String financialFactors;
    private String jobOpportunities;
    private String topCompanies;
    private String industryTrends;
    private String prosAndCons;
    private String jobProfiles;
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCareerPath() { return careerPath; }
    public void setCareerPath(String careerPath) { this.careerPath = careerPath; }

    public String getJobProfiles() { return jobProfiles; }
    public void setJobProfiles(String jobProfiles) { this.jobProfiles = jobProfiles; }

    public String getFutureScope() { return futureScope; }
    public void setFutureScope(String futureScope) { this.futureScope = futureScope; }

    public String getEligibility() { return eligibility; }
    public void setEligibility(String eligibility) { this.eligibility = eligibility; }

    public String getLifestyle() { return lifestyle; }
    public void setLifestyle(String lifestyle) { this.lifestyle = lifestyle; }

    public String getCompetitiveExams() { return competitiveExams; }
    public void setCompetitiveExams(String competitiveExams) { this.competitiveExams = competitiveExams; }

    public String getPreparationMethod() { return preparationMethod; }
    public void setPreparationMethod(String preparationMethod) { this.preparationMethod = preparationMethod; }

    public String getCourses() { return courses; }
    public void setCourses(String courses) { this.courses = courses; }

    public String getCourseDuration() { return courseDuration; }
    public void setCourseDuration(String courseDuration) { this.courseDuration = courseDuration; }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }

    public String getFinancialFactors() { return financialFactors; }
    public void setFinancialFactors(String financialFactors) { this.financialFactors = financialFactors; }

    public String getJobOpportunities() { return jobOpportunities; }
    public void setJobOpportunities(String jobOpportunities) { this.jobOpportunities = jobOpportunities; }

    public String getTopCompanies() { return topCompanies; }
    public void setTopCompanies(String topCompanies) { this.topCompanies = topCompanies; }

    public String getIndustryTrends() { return industryTrends; }
    public void setIndustryTrends(String industryTrends) { this.industryTrends = industryTrends; }

    public String getProsAndCons() { return prosAndCons; }
    public void setProsAndCons(String prosAndCons) { this.prosAndCons = prosAndCons; }
}



//package aiCareerCoach.model.responseFormat;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class CareerOptionForGraduates {
//    private String careerPath;
//    private String jobProfiles;
//    private String futureScope;
//    private String eligibility;
//    private String lifestyle;
//    private String competitiveExams;
//    private String preparationMethod;
//    private String courses;
//    private String courseDuration;
//    private String requiredSkills;
//    private String financialFactors;
//    private String jobOpportunities;
//    private String topCompanies;
//    private String industryTrends;
//    private String prosAndCons;
//
//    public String getIndustryTrends() {
//        return industryTrends;
//    }
//
//    public void setIndustryTrends(String industryTrends) {
//        this.industryTrends = industryTrends;
//    }
//
//    public String getProsAndCons() {
//        return prosAndCons;
//    }
//
//    public void setProsAndCons(String prosAndCons) {
//        this.prosAndCons = prosAndCons;
//    }
//
//    public String getTopCompanies() {
//        return topCompanies;
//    }
//
//    public void setTopCompanies(String topCompanies) {
//        this.topCompanies = topCompanies;
//    }
//
//    public String getJobOpportunities() {
//        return jobOpportunities;
//    }
//
//    public void setJobOpportunities(String jobOpportunities) {
//        this.jobOpportunities = jobOpportunities;
//    }
//
//    public String getCareerPath() {
//        return careerPath;
//    }
//
//    public void setCareerPath(String careerPath) {
//        this.careerPath = careerPath;
//    }
//
//    public String getCompetitiveExams() {
//        return competitiveExams;
//    }
//
//    public void setCompetitiveExams(String competitiveExams) {
//        this.competitiveExams = competitiveExams;
//    }
//
//    public String getCourseDuration() {
//        return courseDuration;
//    }
//
//    public void setCourseDuration(String courseDuration) {
//        this.courseDuration = courseDuration;
//    }
//
//    public String getCourses() {
//        return courses;
//    }
//
//    public void setCourses(String courses) {
//        this.courses = courses;
//    }
//
//    public String getEligibility() {
//        return eligibility;
//    }
//
//    public void setEligibility(String eligibility) {
//        this.eligibility = eligibility;
//    }
//
//    public String getFinancialFactors() {
//        return financialFactors;
//    }
//
//    public void setFinancialFactors(String financialFactors) {
//        this.financialFactors = financialFactors;
//    }
//
//    public String getFutureScope() {
//        return futureScope;
//    }
//
//    public void setFutureScope(String futureScope) {
//        this.futureScope = futureScope;
//    }
//
//    public String getJobProfiles() {
//        return jobProfiles;
//    }
//
//    public void setJobProfiles(String jobProfiles) {
//        this.jobProfiles = jobProfiles;
//    }
//
//    public String getLifestyle() {
//        return lifestyle;
//    }
//
//    public void setLifestyle(String lifestyle) {
//        this.lifestyle = lifestyle;
//    }
//
//    public String getPreparationMethod() {
//        return preparationMethod;
//    }
//
//    public void setPreparationMethod(String preparationMethod) {
//        this.preparationMethod = preparationMethod;
//    }
//
//    public String getRequiredSkills() {
//        return requiredSkills;
//    }
//
//    public void setRequiredSkills(String requiredSkills) {
//        this.requiredSkills = requiredSkills;
//    }
//}