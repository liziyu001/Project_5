import java.util.ArrayList;
/* 
 * Represents the Course that the teacher creates and the student takes part in
 */
public class Course {
    //The name of the course
    private String name;
    //The list of quizzes that are in the course
    private ArrayList<Quiz> courseQuiz;
    
    /* 
     * Constructs a newly allocated Course object with the specified name and list of course quizzes
     * @param name The name of the course used for construction
     * @param courseQuiz The list of course quizzes used for construction
     */
    public Course(String name, ArrayList<Quiz> courseQuiz) {
        this.name = name;
        this.courseQuiz = courseQuiz;
    }
    
    /*
     * Constructs a newly allocated Course object with the specified name and empty list of quizzes in the course
     * @param name The name of the course used for construction
     */
    public Course(String name) {
        this.name = name;
        this.courseQuiz = new ArrayList<>();
    }
    
    /* 
     * Returns the specified name of the course
     * @return Returns the specified name of the course
     */
    public String getName() {
        return name;
    }
    
    /* 
     * Updates the name of the course with the specified name
     * @param name The name to be used in the update
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /* 
     * Returns the list of quizzes in the course
     * @return Returns the list of quizzes in the course
     */
    public ArrayList<Quiz> getCourseQuiz() {
        return courseQuiz;
    }
    
    /* 
     * Adds a quiz to the the course
     * @param quiz The quiz that is being added to the course
     */  
    public void addQuiz(Quiz quiz){courseQuiz.add(quiz);}
    
    /* 
     * Updates the list of course quizzes with the specified list of course quizzes
     * @param courseQuiz The list of course quizzes to be used in the update
     */
    public void setCourseQuiz(ArrayList<Quiz> courseQuiz) {
        this.courseQuiz = courseQuiz;
    }

    /* 
     * Lists the quizzes in the course line by line
     * @return Returns a String representation of the list of quizzes in the course 
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
    
    /* 
     * Prints the String representation of the list of quizzes in the course
     */
    public void printString() {
        for (int i = 0; i < courseQuiz.size(); i++) {
            courseQuiz.get(i).printString();
        }
    }
}
