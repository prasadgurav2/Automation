@login
Feature: MyStore Login Page 

@Reg 
Scenario: User navigate to Sign in Page 
Given User Click on Signin
Then User is on loginPage
And Enter valid Emailid 
And Enter valid Password
When User Click on Sign in button 
Then User can able to succesfully signedin







