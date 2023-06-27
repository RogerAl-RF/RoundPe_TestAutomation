@signin @Demorun
Feature: Sign In

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

  Scenario: Create a Payment
  Given I am logged in and am on the Round Dashboard screen
  Then I navigate to tab "Payments"
  When I click on New Payment to create a new payment
  Then I am on "Create Payment Link" screen
  #payment name is max 20 characters
  Then I enter name as "RFTest", description as "Automation testing payments Roger", amount as 2.1, apply fee 'true' and create the payment link
  Then I get the end user payable amount
  Then the payment link is generated and i click on view payment link
  Then I verify Name of payment is "RFTest" and description is "Automation testing payments Roger"
  Then I search for "goerli" and select "ether" as crypto
  #Then I search for "Ether" and select "Ether (ETH)" as token
  Then I agree sending "ETH" on "Goerli" and click button Pay
  Then I verify the charge code is displayed and the QR code for payment is displayed 
  
