import java.util.ArrayList;
import java.util.Random;
public class Question {
    private String prompt;
    private String[] answerChoices;
    Random r = new Random();

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

    public void randomizeOption () {
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

