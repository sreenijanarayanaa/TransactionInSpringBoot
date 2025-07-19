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

