package aiCareerCoach.model.careerPath;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "career_options_students")
public class CareerOptionForStudent {

    @Id
    private String id;  // MongoDB generated ID
    private String careerPath;
    private String futureScope;
    private String eligibility;
    private String lifestyle;
    private String competitiveExams;
    private String preparationMethod;
    private String courseDuration;
    private String resourceAvailable;
    private String prerequisites;
    private String requiredSkills;
    private String financialFactors;
    private String topUniversities;
    private String industryTrends;

    // Getters and Setters

    public String getRequiredSkills() {return requiredSkills;}

    public void setRequiredSkills(String requiredSkills) {this.requiredSkills = requiredSkills;}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCareerPath() { return careerPath; }
    public void setCareerPath(String careerPath) { this.careerPath = careerPath; }

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

    public String getCourseDuration() { return courseDuration; }
    public void setCourseDuration(String courseDuration) { this.courseDuration = courseDuration; }

    public String getResourceAvailable() { return resourceAvailable; }
    public void setResourceAvailable(String resourceAvailable) { this.resourceAvailable = resourceAvailable; }

    public String getPrerequisites() { return prerequisites; }
    public void setPrerequisites(String prerequisites) { this.prerequisites = prerequisites; }

    public String getFinancialFactors() { return financialFactors; }
    public void setFinancialFactors(String financialFactors) { this.financialFactors = financialFactors; }

    public String getTopUniversities() { return topUniversities; }
    public void setTopUniversities(String topUniversities) { this.topUniversities = topUniversities; }

    public String getIndustryTrends() { return industryTrends; }
    public void setIndustryTrends(String industryTrends) { this.industryTrends = industryTrends; }
}




//package aiCareerCoach.model.responseFormat;
//
//public class CareerOptionForStudent {
//    private String careerPath;
//    private String futureScope;
//    private String eligibility;
//    private String lifestyle;
//    private String competitiveExams;
//    private String preparationMethod;
//    private String courseDuration;
//    private String resourceAvailable;
//    private String prerequisites;
//    private String financialFactors;
//    private String topUniversities;
//    private String industryTrends;
//
//    public String getIndustryTrends() {
//        return industryTrends;
//    }
//
//    public void setIndustryTrends(String industryTrends) {
//        this.industryTrends = industryTrends;
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
//    public String getPrerequisites() {
//        return prerequisites;
//    }
//
//    public void setPrerequisites(String prerequisites) {
//        this.prerequisites = prerequisites;
//    }
//
//    public String getResourceAvailable() {
//        return resourceAvailable;
//    }
//
//    public void setResourceAvailable(String resourceAvailable) {
//        this.resourceAvailable = resourceAvailable;
//    }
//
//
//    public String getTopUniversities() {
//        return topUniversities;
//    }
//
//    public void setTopUniversities(String topUniversities) {
//        this.topUniversities = topUniversities;
//    }
//}