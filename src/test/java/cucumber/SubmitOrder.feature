#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Validation of submitting order
	
	@OrderValidation
  Scenario Outline: Validation of product added to Cart and order submitted
    Given Landed on Ecommerce Page
    And Logged in with username <name> and password <password>
    When I add product <productName> from Catalog
    And Checkout <productName> and submit the order
    Then <message> message is displayed on ConfirmationPage
    
    Examples: 
      | name  					| password 		 | productName 				 	 | message									 |
      | standard_user 	| secret_sauce | Sauce Labs Bike Light | Thank you for your order! |