## Friend Management ##

### Technical Requirements
- Java 8
- Maven 3+

### How to run
1. `mvn clean package`
2. `java -jar target/friend-app-1.0-SNAPSHOT.jar`

### User Stories
In a social application, there are users. Any two users can be connected as a friend or a user can subscribe or block any other user. 

### Documentation
For Documentation, I have used the swagger API. Swagger help us to write the API document in the code. 
API documentation is automatically generated once you start the application.
We can find all the details about the API End Points documentation after starting the application  at `http://localhost:8080/swagger-ui.html`.

I have assumed users data and all the friend connections, subscriptions are done from the user data. My API first validates whether the user exists or not. To test the application easily, I have defined 2 APIs 
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

#### API End Points
We can find the details of all the APIs at `http://localhost:8080/swagger-ui.html` after running the application.
There are Two Controllers in the Service. One is to manage Users and the other is for managing friends.
##### User Management APIs
Manages Users

- GET `/users`: Get all the users of the application
- POST `/users`: Register a new User in the System
- POST `/users/bulk-create`: Register multiple users in the system
- POST `/users/dummy-data`: This will register 20 dummmy users in the system. I have created this API only for testing purpose.

##### Friend Management APIs
Manages Friend Connection, subscribe, and block 

- POST `/friends`: Create a new Friend Connection between two users
- POST `/friends/search`: Find friends of a given email address (user)
- POST `/friends/common`: Find common friends of two given email addresses (users)
- POST `/friends/subscribe`: A user (requestor) can subscribe to updates of another user (target).
- POST `/friends/block`: A user (requestor) can block to updates from another user (target).
- POST `/friends/recipients`: Returns list of all the users who will get the updates of sender.

  




	





