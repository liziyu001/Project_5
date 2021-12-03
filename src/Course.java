import java.util.ArrayList;

public class Course {
    private String name;
    private ArrayList<Quiz> courseQuiz;

    public Course(String name, ArrayList<Quiz> courseQuiz) {
        this.name = name;
        this.courseQuiz = courseQuiz;
    }

    public Course(String name) {
        this.name = name;
        this.courseQuiz = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Quiz> getCourseQuiz() {
        return courseQuiz;
    }

    public void addQuiz(Quiz quiz){courseQuiz.add(quiz);}

    public void setCourseQuiz(ArrayList<Quiz> courseQuiz) {
        this.courseQuiz = courseQuiz;
    }

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

    public void printString() {
        for (int i = 0; i < courseQuiz.size(); i++) {
            courseQuiz.get(i).printString();
        }
    }
}
