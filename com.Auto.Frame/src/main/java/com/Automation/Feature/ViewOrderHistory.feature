@viewOrder
Feature: Order History and Detail 

Background: 
Given User Click on Signin
Then User is on loginPage
And Enter valid Emailid 
And Enter valid Password
When User Click on Sign in button 
Then User can able to succesfully signedin
And User click on Profile
And User click on Order History and Detail



Scenario: Check order History 
Given Order history get Open
Then  User can able to verify the amount under Order Summary
