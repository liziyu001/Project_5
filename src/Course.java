import java.util.ArrayList;

/**
 * Represents the Course that the Teacher creates and the Student takes
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Course {
    //The name of the Course
    private String name;
    //The list of quizzes in the course
    private ArrayList<Quiz> courseQuiz;
    
    /**
     * Constructs a newly allocated Course object with the specified course name and list of course quizzes
     * @param name The specified course name used for construction
     * @param courseQuiz The specified list of course quizzes used for construction
     */
    public Course(String name, ArrayList<Quiz> courseQuiz) {
        this.name = name;
        this.courseQuiz = courseQuiz;
    }
    
    /**
     * Constructs a newly allocated Course object with the specified course name and empty list of course quizzes
     * @param name The specified name used for construction
     */
    public Course(String name) {
        this.name = name;
        this.courseQuiz = new ArrayList<>();
    }
    
    /**
     * Returns the name of the course
     * @return Returns the name of the course
     */
    public String getName() {
        return name;
    }
    
    /**
     * Updates the name of the course with the specified name
     * @param name The specified name used for the update
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the list of quizzes in the course
     * @return Returns the list of quizzes in the course 
     */
    public ArrayList<Quiz> getCourseQuiz() {
        return courseQuiz;
    }
    
    /**
     * Updates the list of quizzes in the course with the specified list of course quizzes
     * @param courseQuiz The specified list of course quizzes used for the update
     */
    public void setCourseQuiz(ArrayList<Quiz> courseQuiz) {
        this.courseQuiz = courseQuiz;
    }
    
    /**
     * A String representation of the list of names of the quizzes
     */
    public String listQuiz () {
        String s = "";
        for (int i = 0; i < courseQuiz.size(); i++) {
            s = s + (i + 1) + ". " + courseQuiz.get(i).getName() + "\n";
        }
        if (s.length() > 0) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
    
    /**
     * Prints a String representation of all the course quizzes in the list
     */
    public void printString() {
        for (int i = 0; i < courseQuiz.size(); i++) {
            courseQuiz.get(i).printString();
        }
    }
}

