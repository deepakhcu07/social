## Friend Management ##

### Technical Requirements
- Java 8
- Maven 3+

### How to run
1. `mvn clean install`
2. `java -jar target/friend-api-xxxx.jar`

### User Stories
In a social application, there are users. Any two users can be connected as a friend or a user can subscribe or block any other user. 

### Documentation
For Documentation, I have used the swagger API. Swagger help us to write the API document in the code. 
API documentation is automatically generated once you start the application.
We can find all the details about the API End Points documentation after starting the application  at `http://localhost:8080/swagger-ui.html`.

I have assumed users data and all the connection, subscriptions are done from the user data. My API first validates whether the user exists or not. To test the application easily, I have defined 2 APIs 
1. /users/bulk-create
	Documentation: `http://localhost:8080/swagger-ui.html#/User_Management_API/createUsingPOST_1`
2. /users/dummy-data
	Documentation: `http://localhost:8080/swagger-ui.html#/User_Management_API/createDummyUserUsingPOST`
	This API creates 20 Users in the system. The patterns of the users are
	abcD@example.com
	xyzD@example.com
	The D is from 1 to 10
	
#### Exceptions
I have defined three exceptions in the applications.
1. `InvalidDataException` This exception is thrown if the Request Entity is not valid to process on the server. 
For Example: Email is not valid or the expected value or size does not match the API requirements.
2. `ResourceAlreadyExistApplication` This exception is thrown when you try to create friend which is already friends.
3. `UserNotFoundException` If the given user is not found on the server, this exception is thrown.

#### Database
For this application, I have used h2 database. We can access the H2 Database Console, after starting the application 
at `http://localhost:8080/h2`.
Please provide the following information to connect to the database:
```
	Driver Class: org.h2.Driver
	JDBC URL: jdbc:h2:~/socialdb
	User Name: spgroup
	Password:
```
	





