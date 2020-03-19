
#Find Longest or Shorted word from the sentence 

Function to find long or short word from sentence is added in Utils
Test is written in BDD format using cucumber-jvm framework

Feature file : - src/main/resources/features/FindWord.feature
StepDefinition : - src/test/java/com/gk/test/step_definitions/FindLongestWordSteps.java
String Function to find : - src/test/java/com/gk/test/framework/helpers/utils/StringUtilsHelper.java

#Assumptions
Sentence is not blank

Result contains only 1  Value returned ,if there are many words with same length and same value then only first value is returned

Negative Scenarios can be better handled

To run the test
------------------------

mvn clean install -P dev  

*Note -P dev is default profile hence doesn't need to be specified for every run 

Report
======

Local reports
-------------
Standard HTML Report  
A report will be generated at /target/cucumber-report/index.html  

Pretty Cucumber-Html Report  
A report will be generated at /target/cucumber-report/cucumber-html-reports/feature-overview.html 
