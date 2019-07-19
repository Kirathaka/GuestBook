# GuestBook
This repository holds the code for GuestBook application.


Steps to Run this application:

1) Clone this repository into your local system.
2) Import the code in STS/ Eclipse IDE.
3) Open the "\src\main\resources\application.properties" file and update the following values as per your MySql database configuration.
	*spring.datasource.url
	*spring.datasource.username
	*spring.datasource.password
4) Right click on the project folder and goto Maven -> click on "Update Project..." option.
5) Right click on pom.xml and click on "Run As" --> "Maven Clean"
6) Right click on pom.xml again and click on "Run As" --> "Maven Install"
7) For the last time, click on project main folder again and click on "Run As" --> "Spring Boot App"
8) Open your browser and visit "http://localhost:8090" to visit the application.
9) Admin and few Guest user accounts will be automatically created by the application during startup. Here are credentials for the same:  
	
	Admin User:-
		Username: admin
		Password: admin12345
	Guest User 1 :-
		username: micheal
		password: defaultpassword
	Guest User 2 :-
		username: jennifer
		password: defaultpassword
	Guest User 3 :-
		username: kenneth
		password: defaultpassword
