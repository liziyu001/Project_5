/**
 *  Represents the Quiz that the Teacher makes and the Student takes
 */
public class Quiz {
    //The name of the quiz
    private String name;
    //The list of questions that are on the quiz
    private Question[] questions;

    /** 
     * Constructs a newly allocated Quiz object with the specified name and list of questions
     * @param name The name of the quiz used for construction
     * @param questions The list of questions used for construction
     */
    public Quiz(String name, Question[] questions) {
        this.name = name;
        this.questions = questions;

    }

    /** 
     * Updates the name of the quiz with the specified name
     * @param name The specified name used for the update 
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
     * Updates the list of questions with the specified list of questions
     * @param name The specified list of questions used for the update 
     */
    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
    
    /**
     * Returns the list of questions
     * @return Returns the list of questions
     */
    public Question[] getQuestions() {
        return questions;
    }
    
    /** 
     * Displays the name of the quiz and the list of questions from the questions array
     */
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
    
    /**
     * String representation of Quiz
     * Example: 
     * name = "CS"
     * questions.length = 1
     * questions[0] = 
     * "Who am I?
     * "1. Ram"
     * "2. Manas"
     * "3. William"
     * "4. Leo"
     * toString():
     * "CS"
     * "Who am I?
     * "1. Ram"
     * "2. Manas"
     * "3. William"
     * "4. Leo"
     * @return Returns the String representation of Quiz
     */
    @Override
    public String toString() {
        String string = name + "\n";
        for (int i = 0; i < questions.length; i++) {
            string = string + questions[i].toString();
        }
        return string;
    }
}
