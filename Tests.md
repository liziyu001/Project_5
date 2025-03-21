Test 1: User Login
Steps:
User runs Server
User runs Main
User clicks the Login button
User selects the username textbox
User enters “manas”
User selects the password textbox
User enters “hi”
User clicks the Login button

Expected Result: The application verifies the user’s identity, displays a success prompt, and displays the respective homepage.

Test status: Passed

Test 2: User Register
Steps:
User runs Server
User runs Main
User clicks the Register button
User selects the username textbox
User enters “TestUsername”
User selects the password textbox
User enters “TestPassword”
User clicks on the dropdown menu
User selects Student
User clicks the Register button

Expected Result: The application adds a new line “TestUsername;TestPassword;true” to Account.txt and displays a success prompt.

Test status: Passed

Test 3: Student Quiz Taking
Steps:
User runs Server
User runs Main
User clicks the Login button
User selects the username textbox
User enters “William”
User selects the password textbox
User enters “1234”
User clicks the Login button
User clicks OK on the success prompt
User clicks the Take a Quiz button
User selects CS180 in the dropdown menu
User clicks OK
User selects The first Quiz in the dropdown menu
User clicks OK
User clicks No for File Import
User selects 1 as their first response
User clicks OK
User selects 1 as their second response
User clicks OK

Expected Result: The application adds a new line “CS180;The first Quiz;William;false;1,1;0,0” to Submissions.txt and displays a success prompt.

Test status: Passed

Test 4: Teacher Quiz Grading
Steps:
User runs Server
User runs Main
User clicks the Login button
User selects the username textbox
User enters “lzy”
User selects the password textbox
User enters “123”
User clicks the Login button
User clicks Grade Submissions Button
User selects CS180-The Second Quiz-William
User clicks OK
User puts 1 for question 1 in the text field
User clicks OK
User puts 1 for question 2 in the text field
User clicks OK
User puts 1 for question 3 in the text field
User clicks OK

Expected Result: The application edits the line in Submissions.txt from “CS180;The Second Quiz;William;true;3,3,3;0,0,0” to “CS180;The Second Quiz;William;true;3,3,3;1,1,1” and displays a success prompt.

Test status: Passed

Test 5: Account Deletion
Steps:
User runs Server
User runs Main
User clicks the Register button
User selects the username textbox
User enters “TestUsername2”
User selects the password textbox
User enters “TestPassword2”
User clicks on the dropdown menu
User selects Student
User clicks the Register button
User clicks OK for the success prompt
User clicks the Account Setting button
User clicks the Delete this Account button

Expected Result: The Accounts.txt file does not have any changes and the user is taken back to the Login/Register window. A success prompt will be displayed confirming deletion of the account.

Test status: Passed

Test 6: Account Username/Password Editing
Steps:
User runs Server
User runs Main
User clicks the Register button
User selects the username textbox
User enters “UsernameEditing”
User selects the password textbox
User enters “PasswordEditing”
User clicks on the dropdown menu
User selects Student
User clicks the Register button
User clicks OK for the success prompt
User clicks the Account Setting button
User clicks the Edit Username button
User selects the text field and types Username2
User clicks the OK button
User clicks the OK button for the success prompt
User clicks the Edit Password button
User selects the text field and types Password2
User clicks the OK button
User clicks the OK button for the success prompt

Expected Result: The application adds a new line “Username2;Password2;true” to Accounts.txt

Test status: Passed

Test 7: Take Quiz via File
Steps:
User runs Server
User runs Main
User clicks the Login button
User selects the username textbox
User enters “miras”
User selects the password textbox 
User enters “123”
User clicks the Login button
User clicks the Take a quiz button 
User selects CS180 as the course in the dropdown menu 
User clicks OK
User selects “The Second Quiz” in the dropdown menu 
User clicks OK
User clicks YES for the prompt that asks if you wish to take a quiz via file import
User types in “answers.txt”

Expected Result: The application displays a success prompt with the following content, “Successfully submitting your answer at YYYY.DD.MM”. The application adds a chunk of text formatted as “CS180;The Second Quiz;Miras;false;1,2,3;0,0,0” to Submissions.txt 

Test status: Passed

Test 8: Make Quiz via File
Steps:
User runs Server
User runs Main
User clicks the Login button
User selects the username textbox
User enters “lzy”
User selects the password textbox
User enters “123”
User clicks the Login button
User clicks the View Courses button
User selects CS180 as the course in the dropdown menu
User clicks OK
User clicks the Create a Quiz button
User clicks Yes for the prompt that asks if you wish to create a quiz via file import
User types in “createQuiz.txt”
User clicks No for the prompt that asks if you wish to randomize the quiz

Expected Result: The application adds a chunk of text formatted as “test quiz:1 \n prompt \n first \n second \n third \n fourth” to CS180.txt and displays a success prompt.

Test status: Passed




