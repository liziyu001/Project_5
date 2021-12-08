/**
 * Represents a person's account where they have certain features based on the role they choose (Teacher or Student)
 * @author Ram, William, Leo, Manas, Miras
 * @version December 13, 2021
 */
public class Account {
    //The username of the person's account
    private String username;
    //The password of the person's account
    private String password;
    //Determines if the person is a student or not
    private boolean isStudent;
    
    /** 
     * Constructs a newly allocated Account object with the specified username, password, and student status
     * @param username The specified username used for construction
     * @param password The specified password used for construction
     * @param isStudent The specified student status used for construction
     */
    public Account(String username, String password, boolean isStudent) {
        this.username = username;
        this.password = password;
        this.isStudent = isStudent;
    }
    
    /**
     * Updates the username of the person's account with the specified username
     * @param username The specified username used for the update
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Returns the username of the person's account
     * @return Returns the username of the person's account
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Updates the password of the person's account with the specified password
     * @param password The specified password used for the update
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Returns the password of the person's account
     * @return Returns the password of the person's account
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Returns the student status of the person's account
     * @return Returns the student status of the person's account
     */
    public boolean isStudent() {
        return isStudent;
    }
    
    /**
     * Updates the student status of the person's account with the specified student status
     * @param student The specified student status used for the update
     */
    public void setStudent(boolean student) {
        isStudent = student;
    }
}
