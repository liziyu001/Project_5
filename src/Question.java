import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the questions on the quiz that the teacher creates and the students take
 *
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Question {
    //The question prompt
    private String prompt;
    //The list of answer choices for a particular question
    private String[] answerChoices;
    //Random object that is used to help implement randomization for each question
    Random r = new Random();

    /**
     * Constructs a newly allocated Question object with the specified prompt and list of answer choices
     *
     * @param prompt        The specified question prompt used for construction
     * @param answerChoices The specified list of answer choices used for construction
     */
    public Question(String prompt, String[] answerChoices) {
        this.prompt = prompt;
        this.answerChoices = answerChoices;

    }

    /**
     * Returns the question prompt
     *
     * @return Returns the question prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * Updates the question prompt with the specified question prompt
     *
     * @param prompt The specified question prompt used for the update
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    /**
     * Returns the list of answer choices in the question
     *
     * @return Returns the list of answer choices in the question
     */
    public String[] getAnswerChoices() {
        return answerChoices;
    }

    /**
     * Updates the list of answers choices in the question with the specified list of answer choices
     *
     * @param answerChoices The specified list of answer choices used for the update
     */
    public void setAnswerChoices(String[] answerChoices) {
        this.answerChoices = answerChoices;
    }

    /**
     * Displays each question with the question prompt being displayed first, and then the answer choices being displayed next line by line
     */
    public void displayQuestion() {
        JOptionPane.showMessageDialog(null, prompt, "Question Prompt", JOptionPane.QUESTION_MESSAGE);
        for (int i = 0; i < 4; i++) {
            JOptionPane.showMessageDialog(null, (i + 1) + ". " + answerChoices[i], "Answer Choices", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Returns a String representation for a Question
     * Example:
     * prompt = "Who am I?"
     * answerChoices[0] = "Ram"
     * answerChoices[1] = "Manas"
     * answerChoices[2] = "William"
     * answerChoices[3] = "Leo"
     * toString() =
     * "Who am I?"
     * "1. Ram"
     * "2. Manas"
     * "3. William"
     * "4. Leo"
     *
     * @return Returns a String representation for a Question
     */
    @Override
    public String toString() {
        String string = prompt + "\n";
        for (int i = 0; i < 4; i++) {
            string = string + (i + 1) + ". " + answerChoices[i] + "\n";
        }
        return string;
    }

    /**
     * Randomizes the order of the answer choices in each question
     */
    public void randomizeOption() {
        ArrayList<String> option = new ArrayList<String>();
        for (int i = 0; i < answerChoices.length; i++) {
            option.add(answerChoices[i]);
        }
        for (int i = 0; i < answerChoices.length; i++) {
            int n = r.nextInt(option.size());
            answerChoices[i] = option.get(n);
            option.remove(n);
        }
    }
}

