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
@AddAddress @Demorun
Feature: Add Withdrawal Address in Round App

  Scenario: Login to metamask for wallet setup
    
    Given I install the metamask extension
    Then I am on Metamask Login Home Screen
    When I Sign In to metamask account with 12 Word Phrase
    And Change the Metamask Password to "Welcome1-2"
    Then I am logged in on metemask
    When I go to Settings and turn on show test networks and verify they are available
		Then I close the tab
#		

  Scenario: Login to Round    
    Given I launch the RoundPe WebApp
    Then I am on the Welcome Sign In page
    When I enter my email as "rog4499@gmail.com" and password as "Welcome1-2"
    Then I am logged in and am on the Round Dashboard screen
    
   
   Scenario: Go to Settings and add Withdrawal address
   
  	Given I navigate to tab "Settings"
  	When I am on Settings page
  	Then I scroll down to the Withdrawal Addresses and expand if it is not already expanded
  	Then I add the withdrawal address 