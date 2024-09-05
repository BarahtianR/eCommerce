feature: Login Functionality 
Scenario: Successful login with valid credentials    
Given the user is on the login page    
When the user enters valid username and password    
Then the user should be redirected to the dashboard  
Scenario: Unsuccessful login with invalid credentials    
Given the user is on the login page    
When the user enters invalid username and password    
Then the user should see an error message