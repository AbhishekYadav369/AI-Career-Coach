package aiCareerCoach.model.roadmap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;


 // MongoDB collection name
public class SkillsRoadmapResponse {


    private String path;
    private List<String> skillsIHave;
    private List<String> skillsGap;
    private List<RoadmapItem> roadmap;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static class RoadmapItem {
        private String month;
        private List<String> focus;
        private Map<String, String> timeDistribution;
        private List<Resource> resources;

        public List<String> getFocus() { return focus; }

        public void setFocus(List<String> focus) { this.focus = focus;}

        public String getMonth() {return month;}

        public void setMonth(String month) {this.month = month;}

        public List<Resource> getResources() { return resources;}

        public void setResources(List<Resource> resources) { this.resources = resources;}

        public Map<String, String> getTimeDistribution() { return timeDistribution;}

        public void setTimeDistribution(Map<String, String> timeDistribution) {
            this.timeDistribution = timeDistribution;
        }
    }

    public static class Resource {
        private String skill;
        private List<String> links;

        public List<String> getLinks() { return links;}

        public void setLinks(List<String> links) {this.links = links;}

        public String getSkill() { return skill;}

        public void setSkill(String skill) { this.skill = skill;}
    }

    public List<RoadmapItem> getRoadmap() { return roadmap;}

    public void setRoadmap(List<RoadmapItem> roadmap) { this.roadmap = roadmap;}

    public List<String> getSkillsGap() { return skillsGap;}

    public void setSkillsGap(List<String> skillsGap) { this.skillsGap = skillsGap;}

    public List<String> getSkillsIHave() { return skillsIHave;}

    public void setSkillsIHave(List<String> skillsIHave) {this.skillsIHave = skillsIHave;}

}
