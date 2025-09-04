package com.aicareercoach.services.aiModel;

import com.aicareercoach.model.InitialQuiz.QuizData;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PromptService {
    public Prompt getPromptForJuniorCollege(String interest, String skills, String goals, String comfort) {
        String temp = """
                  Interested in {interest}, passionate about {passions},
                  skilled in {skills}, and comfortable with {comfort} and goal {goals}.
 
                  suggest suitable junior college streams or career paths.
                   By including these aspects :
                    1.future scope
                    2.eligibility
                    3.lifestyle
                    4.preparation methods and course duration
                    5.competitive exams.
             
                """;
        PromptTemplate promptTemplate = new PromptTemplate(temp);

        return promptTemplate.create(Map.of(
                "interest",interest,"skills", skills,
                "goals", goals,"comfort", comfort
        ));
    }

    public Prompt getPromptForCollege(String interest, String skills, String goals, String comfort) {
        String temp = """
                  Interested in {interest}, passionate about {passions},
                  skilled in {skills}, and comfortable with {comfort} and goal {goals}.
 
                  suggest suitable junior college streams or career paths.
                   By including these aspects :
                    1.future scope
                    2.eligibility
                    3.lifestyle
                    4.preparation methods and course duration
                    5.competitive exams.
             
                """;
        PromptTemplate promptTemplate = new PromptTemplate(temp);

        return promptTemplate.create(Map.of(
                "interest",interest,"skills", skills,
                "goals", goals,"comfort", comfort
        ));
    }

    public Prompt getPromptForFreshGraduate(String interest, String skills,
                                            String goals,String comfort,String status) {
        String temp = """
                Interested in {interest}, skilled in {skills},having
                career goal {goals}, digital comfort {comfort} and Your Current Status &
                Motivation {status}.Please suggest suitable career paths, for each career paths,
                include each points in short in pointer form :
                
                1. Future scope
                2. Eligibility criteria
                3. Lifestyle and work environment
                4. Salary expectations
                5. Required skills
                6. Job security
                """;
        PromptTemplate promptTemplate = new PromptTemplate(temp);

        return promptTemplate.create(Map.of(
                "interest",interest,"skills", skills,
                "goals", goals,"comfort", comfort,"status",status
        ));
    }

    public void processQuizData(QuizData quizData) {
    }
}


// for graduates
/*Based on my interests in math, science and technology, skills in python,
 and career goals getting stable job and better lifestyle, please suggest
  suitable career paths. For each career paths, include each points in short in pointer form :
1. Future scope
2. Eligibility criteria
3. Lifestyle and work environment
4. Salary expectations
5. Required skills
6. Job security*/

// for junior college students
/*Interested in {subjects}, passionate about {passions},
  skilled in {skills}, and comfortable with {digital_tools}.
  Considering my background and preferences—{learning_style}, {career_goals},
  {financial_constraints}, and {education_preferences}

  suggest suitable junior college streams or career paths.
   Include:
    1.future scope
    2.eligibility
    3.lifestyle
    4.preparation methods and course duration
    5.competitive exams.
 */