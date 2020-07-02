Feature: My Account Detail Page

  Background: 
    Given User Click on Signin
    Then User is on loginPage
    And Enter valid Emailid
    And Enter valid Password
    When User Click on Sign in button
    Then User can able to succesfully signedin
@Reg
  Scenario: Verifying Order
    Given User click on Profile
    And User click on Order History and Detail
