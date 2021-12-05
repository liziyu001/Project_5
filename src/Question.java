/**
 * Represents the Questions on the quiz that the teacher makes and the students answer
 */
public class Question {
    //The question prompt
    private String prompt;
    //The answer choices that the student has to choose from in each question
    private String[] answerChoices;

    /**
     * Constructs a newly allocated Question object with the specified question prompt and answer choices
     * @param prompt The question prompt used for construction
     * @param answerChoices The answer choices used for construction
     */
    public Question(String prompt, String[] answerChoices) {
        this.prompt = prompt;
        this.answerChoices = answerChoices;

    }
    
    /**
     * Returns the question prompt 
     * @return Returns the question prompt
     */
    public String getPrompt() {
        return prompt;
    }
    
    /**
     * Updates the question prompt with the specified prompt
     * @param prompt The prompt used in the update
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    
    /**
     * Returns the answer choices for the question
     * @return Returns the answer choices for the question
     */
    public String[] getAnswerChoices() {
        return answerChoices;
    }
    
    /**
     * Updates the answer choices for the question with the specified list of answer choices
     * @param answerChoices The answer choices used for the update
     */
    public void setAnswerChoices(String[] answerChoices) {
        this.answerChoices = answerChoices;
    }
    
    /**
     * Displays the question with the prompt and each answer choice numbered
     */
    public void displayQuestion() {
        /* GUI version 
        JOptionPane.showMessageDialog(null, prompt, "Prompt", JOptionPane.QUESTION_MESSAGE);
        for (int i = 0; i < 4; i++) {
            JOptionPane.showMessageDialog(null, (i + 1) + ". " + answerChoices[i], "Answer Choices", JOptionPane.INFORMATION_MESSAGE);
        }
        */
        System.out.println(prompt);
        for (int i = 0; i < 4; i++) {
            System.out.println((i + 1) + ". " + answerChoices[i]);
        }
    }
    
    /** 
     * Returns a String representation of a question
     * Example:
     * prompt = "Who am I?"
     * answerChoices[0] = "Ram"
     * answerChoices[1] = "Manas"
     * answerChoices[2] = "William"
     * answerChoices[3] = "Leo"
     * toString():
     * "Who am I?
     * 1. Ram
     * 2. Manas
     * 3. William
     * 4. Leo
     * @return Returns a String representation of a question
     */
    @Override
    public String toString () {
        String string = prompt + "\n";
        for (int i = 0; i < 4; i++) {
            string = string + (i + 1) + ". " + answerChoices[i] + "\n";
        }
        return string;
    }
}
