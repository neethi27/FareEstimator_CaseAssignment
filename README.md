# Fare Estimator Case Assignment
Technologies used:-

  1. SpringBoot 2.x
  2. Angular 8
  3. Bootstrap CSS
  4. Rest API
  5. Spring Oauth 2.0 client
  6. Mockito.Junit
  7. Maven
  
Steps to run the application:-

1. Clone the repo  'FareEstimator_Assignment' into your favourite IDE 
2. Update project with maven/gradle to download all application level dependencies.
3. Start the 'simple-travel-api-mock' application first to boots up the API at port 8080.
4. Now start the 'FareEstimator' application using the following commands.
   Build:
   mvn clean install 
   Run: 
   mvn spring-boot:run
5. Now start the frond end module using the command : npm install and npm start
   
   Hit the below URL  http://localhost:4200 to view the application.
   
   
Project description:

1. The homepage will have 3 tabs - FareEstimator,Airports and Dashboard.
2. On click of FareEstimator tab will be displayed 2 textboxes (Origin and Destination) with autocomplete feature.
3. After selecting the Origin and Destination aiports, the user can view the Fare Details on clicking  'Find Fare' button.
4. User can view the airports list by clicking on Airports tab. User can go through by page as paginator is implemented
5. The Dashboard shows the metrics with the number of successfull and failed http requests
