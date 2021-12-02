import java.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Quiz {
    private String name;
    private Question[] questions;


    public Quiz(String name, Question[] questions) {
        this.name = name;
        this.questions = questions;

    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void printString() {
        /* GUI version
        JOptionPane.showMessageDialog(null, name, "Name", JOptionPane.PLAIN_MESSAGE);
        for (int i = 0; i < questions.length; i++) {
            questions[i].displayQuestion();
        }
        */
        System.out.println(name);
        for (int i = 0; i < questions.length; i++) {
            questions[i].displayQuestion();
        }
    }

    @Override
    public String toString() {
        String string = name + "\n";
        for (int i = 0; i < questions.length; i++) {
            string = string + questions[i].toString();
        }
        return string;
    }
}
