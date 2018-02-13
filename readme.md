# COMCAST_TEST
This application provides endpoints which will be discussed below in detail.


## Prerequisites:
This is a Maven application. To build and run the application on the command line, you must have Maven installed first.

Steps to build and run the application on the command line:

	•mvn compile
	•mvn spring-boot:run

#Curl Commands to call the rest endpoints:

1) Hello world rest endpoint:

This is a greetings endpoint.

	•curl command to see the Hello world! message : curl http://localhost:8080/helloworld
	•Curl command to see customized greetings response : curl http://localhost:8080/helloworld?name={your name here}.


2) Fibonacci numbers Endpoint:

This is a Rest endpoint that accepts N as a request variable and returns a JSON array of first N Fibonacci numbers.

	•Curl command to return the first N fibonacci numbers : curl http://localhost:8080/fibonacciNumbers?N={any number}.

3) Deadlock Scenario:

This endpoint creates two threads that will become deadlock with each other. To create a deadlock and to check the status of the threads, please use the below curl commands.

	• To create a deadlock use curl command: curl -X POST http://localhost:8080/deadlock/
	• To check the status use thr curl command: curl http://localhost:8080/deadlock/status

4) Endpoints to add, query and delete rows in a database:

Here are the rest endpoints to add, query and delete rows in a database. Database has a User table with columns (userId, userName, firstName and lastName). The table has been initialized with 2 rows when you start the application.

Query Endpoints:
	
	•You can query all the rows in the user table using : curl http://localhost:8080/user/
	
	•You can also query rows in the user table by passing the userId as a path variable. Here is the curl command to query rows using userId: curl http://localhost:8080/user/id/{userId here}
	Example: curl http://localhost:8080/user/id/1
	
	•You can also query rows in the user table by passing the userName as a path variable. Here is the curl command to query rows using userName: curl http://localhost:8080/user/user_name/{userName here}
	Example: curl http://localhost:8080/user/user_name/user1

Add Endpoints:

To add a user to the database, please create a json file( example: data.json) with the data in the format given below. 
 
 {
        "userName": "user-5",
        "firstName": "firstName1",
        "lastName": "lastName1",
        "id": 5
 }

	•You can add now add user to the table by using the curl command: curl -H "Content-Type: application/json" -d "@data.json" -X POST http://localhost:8080/user/add_row

Delete Endpoint:

You can delete a user from the table with the userId.

	•Here is the curl command to delete a user from the table: Curl -X DELETE http://localhost:8080/user/{id here}
	Example: Curl -X DELETE http://localhost:8080/user/2.
	
5) Endpoint that queries an external service:

This is a endpoint that calls an external service.
		
		• Curl command: curl http://localhost:8080/externalService




