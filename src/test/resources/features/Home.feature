Feature: Home Page Title and Navigation

Background:
	Given I launch the application
 @TC_Home_01_Positive
  Scenario: Verify home page title and visibility of login and join buttons
  
    Then The logo should be displayed
    And The logo should have the aria-label "Go to homepage"
    And The logo background image should contain "tcb-logo-brand.svg"
    And Login and Join Now buttons should be visible

@TC_JoinNow_02_Negative
  Scenario: Click on Join Now and verify navigation
  
 		When I click on the Join Now button without entering email and password
 		Then The email field should display the error "Your email address is not valid."
 		And The password field should display the error "Your password does not meet the requirements."

@TC_JoinNow_03_Positive
  Scenario: Click on Join Now and verify navigation
   
 		When I click on the Join Now button by entering email and password and title should be "Account Overview"
 		
 @TC_Home_04_Login_positive
  Scenario: Click on Login and verify navigation

  	When I click the login button and it should redirect with page title should be "Hello again!"
		Then I click on the Login button with positive Email and Password as input and validating the page title as "Apply Cash Back savings as you shop with just one click."
  