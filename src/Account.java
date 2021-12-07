public class Account {
    //The username of the person's account
    private String username;
    //The password of the person's account
    private String password;
    //Determines if the person is a student or not
    private boolean isStudent;
    //Represents the account id number of the person

    public Account(String username, String password, boolean isStudent) {
        this.username = username;
        this.password = password;
        this.isStudent = isStudent;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
