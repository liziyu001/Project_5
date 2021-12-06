import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
/**
 * Represents the Submission that the student makes once finished with the quiz
 */
public class Submission {
    //Represents the person's account id
    private String id;
    //The name of the course that the submission is under
    private String courseName;
    //The name of the quiz that the student took and submitted
    private String quizName;
    //Status of if the submission was graded or not
    private boolean graded;
    //The list of answers that the student chose
    private int[] answers;
    //The list of grades for each question
    private int[] subGrades;
    //The total grade on the quiz
    private int totalGrades;
    //The time stamp of when the student submitted the quiz
    private String timestamp;
    
    /**
     * Constructs a newly allocated Submission object with the specified course name, quiz name, account id, and list of student's answers
     * The status of graded is set to false, the total grade is set to 0, and an empty subGrades array and timeStamp String object is created
     * @param courseName The name of the course used for construction
     * @param quizName The name of the quiz used for construction
     * @param id The account id used for construction
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
     * Returns the String representation of a Submission
     * Example: 
     * id = "1"
     * graded = true
     * answers = {1,2,3,4}
     * subGrades{1,2,2,1}
     * totalGrades = 6
     * toString() = "Submission{, Account=1, graded=true, answers={1,2,3,4}, subGrades={1,2,2,1}, totalGrades=6}"
     * @return Returns the String representation of a Submission
     */
    @Override
    public String toString() {
        return "Submission{" +
                ", Account=" + id +
                ", graded=" + graded +
                ", answers=" + Arrays.toString(answers) +
                ", subGrades=" + Arrays.toString(subGrades) +
                ", totalGrades=" + totalGrades +
                '}';
    }
    
    /**
     * Constructs a newly allocated Submission object with the specified course name, quiz name, account id, graded status, student's answers, and sub grades
     * @param courseName The name of the course used for construction
     * @param quizName The name of the quiz used for construction
     * @param id The account id used for construction
     * @param graded The graded status used for construction
     * @param answers The list of student's answers used for construction
     * @param subGrades The list of grades of each question used for construction
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
     * Returns the account id number
     * @return Returns the account id number
     */
    public String getId() {
        return id;
    }
    
    /**
     * Updates the account id with the specified account id number
     * @param id The specified account id number used for the update
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
     * Updates the list of the student's answers with the specified list of the student's answers
     * @param answers The list of the student's answers that are used for the update
     */
    public void setAnswers(int[] answers) {
        this.answers = answers;
    }
    
    /**
     * Returns the list of the grades for each question
     * @return Returns the list of the grades for each question
     */
    public int[] getSubGrades() {
        return subGrades;
    }
    
    /** 
     * Updates the list of the grades for each question with the specified list of grades for each question
     * @param subGrades The list of grades for each question that are used for the update
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
     * Updates the student's total grade with the specified grade
     * @param totalGrades The specified total grade of the student used for the update
     */
    public void setTotalGrades(int totalGrades) {
        this.totalGrades = totalGrades;
    }
    
    /**
     * Updates the graded status of the submission with the specified graded status
     * @param graded The graded status used for the update
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
     * Returns the name of the course that the submission is under
     * @return Returns the name of the course that the submission is under
     */
    public String getCourseName() {
        return courseName;
    }
    
    /**
     * Returns the name of the quiz that the student took and submitted
     * @return Returns the name of the quiz that the student took and submitted
     */
    public String getQuizName() {
        return quizName;
    }
    
    /**
     * Updates the time stamp of the student's submission by getting the time, and by formatting the Date object using a SimpleDateFormat object
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
