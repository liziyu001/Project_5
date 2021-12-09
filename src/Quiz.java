import java.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * Represents the Quiz in the Course that the Teacher creates, and the student takes and submits
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Quiz {
    //The name of the quiz
    private String name;
    //The list of questions in the quiz
    private Question[] questions;
    //Random object that is used to help implement randomization for each question in the quiz 
    Random r = new Random();
    
    /**
     * Constructs a newly allocated Quiz object with the specified name and list of questions
     * @param name The specified quiz name used for construction
     * @param questions The specified list of questions used for construction
     */
    public Quiz(String name, Question[] questions) {
        this.name = name;
        this.questions = questions;

    }

    /**
     * Updates the name of the quiz with the specified name
     * @param name The specified quiz name used for the update
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the name of the quiz
     * @return Returns the name of the quiz
     */
    public String getName() {
        return name;
    }
    
    /**
     * Updates the list of questions on the quiz with the specified list of question
     * @param questions The list of questions used for the update
     */
    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
    
    /**
     * Returns the list of questions on the quiz
     * @return Returns the list of questions on the quiz
     */
    public Question[] getQuestions() {
        return questions;
    }
    
    /**
     * Prints the quiz with the name of the quiz being printed first, and then the question prompt and answer choices being displayed after
     */
    public void printString() {
        JOptionPane.showMessageDialog(null, name, "Quiz Name", JOptionPane.PLAIN_MESSAGE);
        for (int i = 0; i < questions.length; i++) {
            questions[i].displayQuestion();
        }
        
    }
    
    /**
     * Returns a String Representation of a Quiz
     * Example:
     * name = "CS 180"
     * questions.length = 1
     * questions[0] = 
     * "Who am I?"
     * "1. Ram"
     * "2. Manas"
     * "3. William"
     * "4. Leo"
     * toString() = 
     * "CS 180"
     * "Who am I?"
     * "1. Ram"
     * "2. Manas"
     * "3. William"
     * "4. Leo"
     * @return Returns a String Representation of a Quiz
     */
    @Override
    public String toString() {
        String string = name + "\n";
        for (int i = 0; i < questions.length; i++) {
            string = string + questions[i].toString();
        }
        return string;
    }
    
    /**
     * Randomizes the question order in the quiz with the answer choices being randomized as well
     */
    public void randomizeQuiz() {
        ArrayList<Question> q = new ArrayList<Question>();
        for (int i = 0; i < questions.length; i++) {
            q.add(questions[i]);
        }
        for (int i = 0; i < questions.length; i++) {
            int n = r.nextInt(q.size());
            questions[i] = q.get(n);
            q.remove(n);
        }
        for (int i = 0; i < questions.length; i++) {
            questions[i].randomizeOption();
        }
    }
}
