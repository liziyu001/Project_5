Submission:
Ram Laxminarayan - Submitted Report on Brightspace. William Li - Submitted Vocareum workspace, Submitted Presentation 


Step to run the project:
  1: Compile all classes
  2: Run the server(java Server)
  3. Run the Main(java Main)
     1. If the server is localhost, leave the file as is
     2. If the server is on a different computer, uncomment the specified lines and enter your ip; comment out the specified line 
     3. The menu window should appear after step 3
The menu window should appear after step 3

Format for creating quiz by uploading a file:

            ex. CS193
            The first Quiz
            2
            The first Question prompt
            The first Choice
            The second Choice
            The third Choice
            The fourth Choice
            The second Question prompt
            The first Choice
            The second Choice
            The third Choice
            The fourth Choice
  
  Format for submitting answer by uploading a file:
    "<answer for question 1>,<answer for question 2>,<answer for question 3>..."(one line)
      ex. 3,2,1,4

Test cases are documented in the TestCase.txt file

Class description:
    Account: record the username and password and role of a user, used by the manager and server.
    Course: information about courses; contain multiple quiz; 
    Quiz: information about a quiz; contain multiple questions;
    Question: information about a question including a prompt and four answer options;
    Manager: store all information about account, course, quiz, and question; read files when initialize; update file when changes are made
    Server: back-end of the project, can receive 17 kinds of request from any connected user and make responses, use manager to process data and update files
    Menu: the initial menu of the project, can direct user to login or register
    TeacherMenu: menu for teacher after login in, can perform course/quiz related function, grading and account setting;
    StudentMenu: menu of teacher, can help student to take quiz/view gradings.
    AccountSetting: menu for account setting function, can lead user to edit username/password or deleting account
    Main: the launcher of the project, also make requests to the server and receive responses.
    
