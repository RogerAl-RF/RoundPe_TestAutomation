@navigationLinks
Feature: Verify all sidebar links are opening and navigating to the correct URL

 	Scenario: Setup Environment
 		Given I setup environment to setupNewProfile as "true" and addMetamask as "false"

  Scenario: Login to Round    
    Given I launch the RoundPe WebApp
    Then I am on the Welcome Sign In page
    When I enter my email as "rog4499@gmail.com" and password as "Welcome1-2"
    Then I am logged in and am on the Round Dashboard screen
    
  Scenario: Open Docs from navigation side bar and verify user is navigated to the correct page
  	 Given I am logged in and am on the Round Dashboard screen
 		 Then I navigate to tab "Docs"
 		 Then verify that the link opened is "https://docs.roundpe.com/"
 		 And page title contains "RoundPe API Docs"
 		 Then On navigating to RoundPe Docs the link opened is "https://docs.roundpe.com/docs/intro"
 		 And page title contains "Introduction to RoundPe"
 		 Then I close the tab
 		 
 	Scenario: Open Terms of Service from navigation side bar and verify user is navigated to the correct page
 		Given I am logged in and am on the Round Dashboard screen
 		Then I navigate to tab "Terms of Service"
 		Then verify that the link opened is "https://roundpe-website.vercel.app/TermsandConditions/"
 		And page title contains "RoundPe"
 		And page has text "RoundPe Terms of Service"
 		Then I close the tab
 		
 	 Scenario: Open Privacy Policy from navigation side bar and verify user is navigated to the correct page
 		Given I am logged in and am on the Round Dashboard screen
 		Then I navigate to tab "Privacy Policy"
 		Then verify that the link opened is "https://roundpe-website.vercel.app/PrivacyPolicy/"
 		And page title contains "RoundPe"
 		And page has text "RoundPe Privacy Policy"
 		Then I close the tab
 		
 	 Scenario: Open FAQs from navigation side bar and verify user is navigated to the correct page
 		Given I am logged in and am on the Round Dashboard screen
 		Then I navigate to tab "FAQs"
 		Then verify that the link opened is "https://roundpe-website.vercel.app/FAQs"
 		And page title contains "RoundPe"
 		And page has text "Frequently Asked Questions"
 		Then I close the tab
 		
 	 Scenario: Open Support from navigation side bar and verify user is navigated to the correct discord page
 		Given I am logged in and am on the Round Dashboard screen
 		Then I navigate to tab "Support"
 		Then verify that the link opened is "https://discord.com/invite/WxgRXcFfSe"
 		And page title contains "Discord"
 		And page has text "MojoJojo invited you to join"     
 		And page has text "RoundPe"
 		Then I close the tab
 		
 	Scenario: Sign Out from Round
 		Given I am logged in and am on the Round Dashboard screen
 		Then I navigate to tab "SignoutButton"
 		Then I am Signed Out
 		
 		
 		