package aiCareerCoach.model.resumeBuilder.fields;

public class Language {
    private String id;
    private String name;
    // Getters & Setters

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    @Override
    public String toString() {
        return "Language{"+"id= " +id + ", name= " + name +'}';
    }
}