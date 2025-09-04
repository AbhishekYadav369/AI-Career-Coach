package com.aicareercoach.model.InitialQuiz;
import java.util.List;
import java.util.Map;

/* This class is used to accept quiz response
       direct from the controller and letter used
       for getting responses from that
    .Where JSON auto mapped to POJO */
public class QuizData {

    private Map<String, List<Questions>> sections;

        public QuizData(Map<String, List<Questions>> sections) {
            this.sections = sections;
        }

        // Getters & Setters
        public Map<String, List<Questions>> getSections() {
            return sections;
        }

        public void setSections(Map<String, List<Questions>> sections) {
            this.sections = sections;
        }


}
