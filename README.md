# Tool Rental Application (Test)

## Overview
This Java application implements a point-of-sale tool rental system for a hardware store. It allows clerks to process tool rentals, applying appropriate charges based on the tool type, rental duration, and any applicable discounts.

## Features
- Process tool rentals for various tool types (Ladder, Chainsaw, Jackhammer)
- Calculate rental charges based on weekday, weekend, and holiday rates
- Apply discounts to the total rental charge
- Generate detailed rental agreements

## Project Structure

tool-rental-application/

├── src/

│ ├── main/java/com/toolrental/

│ │ ├── model/

│ │ │ ├── Tool.java

│ │ │ ├── ToolType.java

│ │ │ └── RentalAgreement.java

│ │ ├── service/

│ │ │ └── CheckoutService.java

│ │ ├── util/

│ │ │ └── DateCalculator.java

│ │ └── ToolRentalApplication.java

│ └── test/java/com/toolrental/

│ ├── service/

│ │ └── CheckoutServiceTest.java

│ └── ToolRentalApplicationTest.java

└── pom.xml

## Requirements
- Java 8 or higher
- Maven

## How to Run
1. Clone the repository
2. Navigate to the project directory
3. Build the project:
   mvn clean install
4. Run the application:
   java -cp target/tool-rental-application-1.0-SNAPSHOT.jar com.toolrental.ToolRentalApplication
## Running Tests
Execute the following command in the project root directory:
mvn test
## Sample Usage
```java
CheckoutService checkoutService = new CheckoutService();
RentalAgreement agreement = checkoutService.processCheckout("LADW", 3, 10, LocalDate.of(2020, 7, 2));
agreement.printAgreement();
License
This project is open source and available under the MIT License.

This README provides:

1. A brief overview of the project
2. Key features
3. Project structure
4. Requirements
5. Instructions for building and running the application
6. Instructions for running tests
7. A sample usage snippet
8. A license statement (you can choose a different license if preferred)

Remember to adjust any details as necessary to match your specific implementation. This README gives potential reviewers a clear understanding of your project without revealing any confidential information or specific company names.
