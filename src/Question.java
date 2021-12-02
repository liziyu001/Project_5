public class Question {
    private String prompt;
    private String[] answerChoices;


    public Question(String prompt, String[] answerChoices) {
        this.prompt = prompt;
        this.answerChoices = answerChoices;

    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(String[] answerChoices) {
        this.answerChoices = answerChoices;
    }

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

    @Override
    public String toString () {
        String string = prompt + "\n";
        for (int i = 0; i < 4; i++) {
            string = string + (i + 1) + ". " + answerChoices[i] + "\n";
        }
        return string;
    }
}
