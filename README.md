# TransactionInSpringBoot

✈️ Imagine This Scenario

You're booking a flight. Two things happen:

The system saves your passenger details (name, email, etc.).

It saves your payment (amount, payment method, etc.).

Now, what if the payment fails? You wouldn’t want your name to be saved without a ticket, right?

The `@Transactional` annotation ensures that **passenger and payment records** are either both saved together or both discarded if something fails.

### 💡 Example Use Case:

1. Save Passenger details  
2. Save Payment details  
3. If payment amount is less than the fare → ❌ transaction fails → no data saved

```java
@Transactional
public String bookFlight(Passenger passenger, Payment payment) {
    Passenger savedPassenger = passengerRepository.save(passenger);
    payment.setPassenger(savedPassenger);

    if (payment.getAmount() < savedPassenger.getFare()) {
        throw new RuntimeException("Insufficient payment. Transaction rolled back.");
    }

    paymentRepository.save(payment);
    return "Flight booked successfully!";
}
```
📮 How to Test with Postman
POST Endpoint:
```java
http://localhost:8080/api/book
```
Request Params:
```java
paymentId: TXN9001
accountNo: 12345678
amount: 800.0
cardType: VISA
```
Request Body (JSON):

```java
json
{
  "name": "Alice",
  "email": "alice@example.com",
  "Source": "Delhi",
  "Destination": "Mumbai",
  "travelDate": "20-07-2025",
  "pickUpTime": "10:00",
  "arrivaltime": "12:00",
  "fare": 800.0
}
```
✅ Success Response
Flight booked successfully!
❌ Failure Response (e.g., amount < fare)
Payment too low. Transaction rolled back.

🧪 Testing with JUnit
✅ Successful Booking Test
```java
@Test
public void testSuccessfulBooking() {
    Passenger p = new Passenger(); p.setFare(500.0);
    Payment pay = new Payment(); pay.setAmount(500.0);

    bookingService.bookFlight(p, pay);

    Assertions.assertTrue(passengerRepo.findAll().size() > 0);
    Assertions.assertTrue(paymentRepo.findAll().size() > 0);
}
```
❌ Failed Booking Rollback
```java
@Test
public void testFailedBookingRollback() {
    Passenger p = new Passenger(); p.setFare(1000.0);
    Payment pay = new Payment(); pay.setAmount(500.0);

    Assertions.assertThrows(RuntimeException.class, () -> {
        bookingService.bookFlight(p, pay);
    });

    Assertions.assertEquals(0, passengerRepo.findAll().size());
    Assertions.assertEquals(0, paymentRepo.findAll().size());
}
```
**🔧 Setup & Run**
```java
Clone the repo git clone https://github.com/your-username/flight-booking-app.git
```
Configure application.properties with MySQL credentials

Run the app mvn spring-boot:run or start via your IDE

**🧠 Concepts Covered**

Declarative transaction management

Exception-based rollback

JPA entity mapping

REST API development

Postman integration

Unit testing with assertions

