package aiCareerCoach.model.quizQuestion;

public class QuestionDTO {

        private String id;
        private String question;

        public QuestionDTO() {}
        public QuestionDTO(String id, String question) {
            this.id = id;
            this.question = question;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
    }
