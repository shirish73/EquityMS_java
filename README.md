# EquityMS_java

1. Directory Structure

equity-position-service/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── equity/
│       │           └── position/
│       │               ├── EquityPositionApplication.java
│       │               ├── config/
│       │               │   └── CorsConfig.java
│       │               ├── controller/
│       │               │   └── TransactionController.java
│       │               ├── dto/
│       │               │   ├── TransactionRequest.java
│       │               │   └── TransactionResponse.java
│       │               ├── entity/
│       │               │   ├── Transaction.java
│       │               │   ├── Position.java
│       │               │   ├── TransactionAction.java
│       │               │   └── BuySell.java
│       │               ├── exception/
│       │               │   └── GlobalExceptionHandler.java
│       │               ├── repository/
│       │               │   └── TransactionRepository.java
│       │               └── service/
│       │                   └── TransactionService.java
│       └── resources/
│           └── application.properties


2. Run the application:

Clone the project to your machine 

bash# Navigate to project root
cd equity-position-service

# Run with Maven (if Maven is installed)
mvn spring-boot:run

# OR run with Maven wrapper (if mvnw exists)
./mvnw spring-boot:run

# OR import into IDE (IntelliJ IDEA/Eclipse) and run EquityPositionApplication.java

3. Testing the APIs:
   
Once running, you can test these endpoints:
bash# Health check
GET http://localhost:8080/api/transactions/health

# Load sample data
POST http://localhost:8080/api/transactions/sample-data

# Get all transactions  
GET http://localhost:8080/api/transactions

# Get positions
GET http://localhost:8080/api/transactions/positions

# Add new transaction
POST http://localhost:8080/api/transactions
Content-Type: application/json
{
  "tradeId": 5,
  "securityCode": "TCS",
  "quantity": 100,
  "action": "INSERT", 
  "buySell": "Buy"
}
🌐 Database Console:

H2 Console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: password



