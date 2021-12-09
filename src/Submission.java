import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * Represents the student's quiz submission 
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Submission {
    //Represents the account id
    private String id;
    //The name of the course
    private String courseName;
    //The name of the quiz
    private String quizName;
    //The status of whether the student's submission has been graded or not
    private boolean graded;
    //List of student's answers
    private int[] answers;
    //List of grades for each question
    private int[] subGrades;
    //The total grade received on the whole quiz
    private int totalGrades;
    //The time when the student submitted the quiz
    private String timestamp;
    
    /**
     * Constructs a newly allocated Submission object with the specified course name, quiz name, account id, and list of student's answers
     * Submission object has not been graded yet with no grades put in yet, and the time stamp of the student's submission has been formatted
     * @param courseName The name of the course used for construction
     * @param quizName The name of the quiz used for construction
     * @param id The student's account id used for construction
     * @param answers The list of student's answers used for construction
     */
    public Submission(String courseName, String quizName, String id, int[] answers) {
        this.id = id;
        this.answers = answers;
        this.courseName = courseName;
        this.quizName = quizName;
        graded = false;
        subGrades = new int[answers.length];
        totalGrades = 0;
        this.timestamp = new SimpleDateFormat("yyyy.MM.dd HH-mm-ss").format(new java.util.Date());
    }
    
    /**
     * Returns a String representation of a Submission
     * Example: 
     * id = 1
     * graded = true
     * Arrays.toString(answers) = {"1","2","3","4"}
     * Arrays.toString(subGrades) = {"1","2","2","1"}
     * totalGrades = 6
     * toString() = "Submission{, Account=1, graded=true, answers={"1","2","3","4"}, subGrades={"1","2","2","1"}, totalGrades=6}
     * @return Returns a String representation of a Submission
     */
    @Override
    public String toString() {
        return "Submission{" +
                ", Account=" + id +
                ", graded=" + graded +
                ", answers=" + Arrays.toString(answers) +
                ", subGrades=" + Arrays.toString(subGrades) +
                ", totalGrades=" + totalGrades +
                "}";
    }
    
    /**
     * Constructs a newly allocated Submission object with the specified course name, quiz name, account id, graded status, list of answers, and list of sub grades
     * @param courseName The specified course name used for construction
     * @param quizName The specified quiz name used for construction
     * @param id The specified student account id used for construction
     * @param graded The specified graded status used for construction
     * @param answers The specified list of student's answers used for construction
     * @param subGrades The specified list of grades for each question used for construction
     */
    public Submission(String courseName, String quizName, String id, boolean graded, int[] answers, int[] subGrades) {
        this.courseName = courseName;
        this.quizName = quizName;
        this.id = id;
        this.graded = graded;
        this.answers = answers;
        this.subGrades = subGrades;
    }
    
    /**
     * Returns the time stamp of when the student submitted the quiz
     * @return Returns the time stamp of when the student submitted the quiz
     */
    public String getTimestamp() {
        return timestamp;
    }
    
    /**
     * Returns the student's account id
     * @return Returns the student's account id
     */
    public String getId() {
        return id;
    }
    
    /**
     * Updates the student's account id with the specified account id
     * @param id The specified account id used for the update
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Returns the list of the student's answers
     * @return Returns the list of the student's answers
     */
    public int[] getAnswers() {
        return answers;
    }
    
    /**
     * Updates the student's list of answers with the specified list of answers
     * @param answers The list of answers used for the update
     */
    public void setAnswers(int[] answers) {
        this.answers = answers;
    }
    
    /**
     * Returns the list of grades for each question
     * @return Returns the list of grades for each question
     */
    public int[] getSubGrades() {
        return subGrades;
    }
    
    /**
     * Updates the list of grades for each question with the specified list of sub grades
     * @param subGrades The specified list of grades for each question used for the update
     */
    public void setSubGrades(int[] subGrades) {
        this.subGrades = subGrades;
    }
    
    /**
     * Returns the student's total grade on the quiz
     * @return Returns the student's total grade on the quiz
     */
    public int getTotalGrades() {
        return totalGrades;
    }
    
    /**
     * Updates the student's total grade with the specified total grade
     * @param totalGrades The specified total grade of the student used for the update
     */
    public void setTotalGrades(int totalGrades) {
        this.totalGrades = totalGrades;
    }
    
    /**
     * Updates the graded status of the submission with the specified graded status
     * @param graded The specified graded status used for the update
     */
    public void setGraded(boolean graded) {
        this.graded = graded;
    }
    
    /**
     * Returns the graded status of the submission
     * @return Returns the graded status of the submission
     */
    public boolean isGraded() {
        return graded;
    }
    
    /**
     * Returns the name of the course where the submission is under
     * @return Returns the name of the course where the submission is under
     */
    public String getCourseName() {
        return courseName;
    }
    
    /**
     * Returns the name of the quiz that the student submitted
     * @return Returns the name of the quiz that the student submitted
     */
    public String getQuizName() {
        return quizName;
    }
    
    /**
     * Updates the time stamp of when the student submitted the quiz with the specified time stamp with the format "yyyy.MM.dd.HH.mm.ss"
     */
    public void setTimestamp() {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(String.valueOf(System.currentTimeMillis()));
            long ts = date.getTime();
            res = String.valueOf(ts);
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            this.timestamp = timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


}
