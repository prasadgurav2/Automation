
@CreateUser
Feature:: Create new User

Background: 
Given User Click on Signin
Then User is on loginPage
Then User can able to view 'Create An Account'
Then User enter email address 
And Click on Create an Account

Scenario: Create New User
Given User should navigated to account-creation Page
Then User fill all Personal information details
And click on Register button 
