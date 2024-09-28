It is project that include multiple packages with clearly defined responsibility.

1. com.arsh.dao - SeatDao
This package has the SeatDao class which is performing data access operations.
For database this interacts through hibernate and helps to perform CRUD operation.
Key methods include:

->What is findAvailableSeatsInRow : It finds continuous available seats in a row and books them
->findNearbyAvailableSeats: Looks for available seats across the rows,
  when consecutive seats in one row are not available.
->bookSeats – For booking the chosen seats to database
->getAllSeats: Get Current all seating arrangements

2. com.arsh.entities - Seat
This package creates a definition for Seat, which relates each seat on the train
to its own row and seat number in database. It includes:
seatId: Number | Defines seat with an unique id
rowNum: The seat row number
seatNumber: an integer, the seat number in its row
- isBooked - Boolean, to tell if this seat is already booked or still available.

3. com. arsh. helper - FactoryProvider
FactoryProvider provides a singleton instance of the SessionFactory and this
SessionFactory is responsible for managing Hibernate Sessions. It will configure
the database connection and Hibernate properties through hibernate. cfg. xml file.

4. com.arsh.TrainReservation System - reservation
TrainReservation System class is the entry point where things get executed.
It receives user input for seat booking requests and communicates with the
SeatDao class to book a seat. This class includes:
->reserveSeats: Block Reserves Concat seats or Nearby based on availability
->displaySeats: Display the current seat arrangement and status of each seat.
->Train-reservation: A Train Seat Reservation System that functions in
conjunction with the following packages.


<---- hibernate.cfg.xml ---->
This file defines configuration of Hibernate related how to connect to the
Database and properties in it. Key configurations include:

->Database Connection Settings:
->connection. driver_class: Classname of the mysql jdbc driver.mysql.cj.jdbc.Driver).
->connection. The url here give us the Database connection URL(jdbc:mysql://localhost:3306/
SeatReservation) of our MySQL Server.
->connection. username: The username to enter database (-----)
->connection. password: The database password (-------)

SQL Dialect:
dialect: Specify the Hibernate dialect to use with MySQL (org. hibernate. dialect. MySQL5Dialect).

Logging SQL:
show_sql: Enable logging of SQL statements (default : false).
format_sql: Whether to format the SQL output for prettier reading (true).

Schema Management:
hbm2ddl. auto: Auto detection, Hibernate will update the schema but never delete existing data.

Entity Mapping:
: Maps the Seat entity class to the corresponding database table.



<---- pom.xml ---->
The pom.xml file is the heart of your Maven project. It defines what the project
is about, how it should be built, and what external libraries (dependencies) it
relies on.

Project Details: It names the project as SeatReservation and specifies that the
project will be packaged as a war file, which is typically used for web
applications. The version is 0.0.1-SNAPSHOT, indicating it’s an early-stage version.

Java Version: The maven.compiler.source and maven.compiler.target are both set to 17,
meaning your project is written and compiled using Java 17. This ensures our code
uses the latest features of Java 17, but it will also be compatible with environments
running this version.

Dependencies: These are the external libraries our project needs to work properly:
Hibernate Core: Version 5.4.33.Final is specified to allow our project to interact
with the database through Hibernate. It’s a version that works well with Java 8,
ensuring backward compatibility.

MySQL Connector: Version 8.0.33 is used to establish a connection between
Hibernate and your MySQL database. It acts as a bridge for your Java application
to interact with the database.

Build Configuration: The maven-compiler-plugin is configured to ensure that
Maven compiles your project using Java 17. It makes sure that your code is
compiled and built correctly according to the project's Java version.

Together, these XML configurations make sure your project can smoothly manage
the database using Hibernate, handle all necessary dependencies like MySQL and
be compiled with the right Java version, ensuring everything works well together.
