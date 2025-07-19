package com.sree.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sree.entity.Passenger;
import com.sree.entity.Payment;
import com.sree.repository.PassengerRepository;
import com.sree.repository.PaymentRepository;

@SpringBootTest
public class FlightBookingServiceTest {

    @Autowired
    private FlightBookingService bookingService;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void testSuccessfulBooking() {
        Passenger passenger = new Passenger();
        passenger.setName("John");
        passenger.setFare(500.0);

        Payment payment = new Payment();
        payment.setPaymentId("TXN123");
        payment.setAmount(400.0);
        payment.setCardType("VISA");
        payment.setAccountNo("123456");

        bookingService.bookFlight(passenger, payment);

        Assertions.assertTrue(passengerRepository.findAll().size() > 0);
        Assertions.assertTrue(paymentRepository.findAll().size() > 0);
    }

    @Test
    public void testFailedBookingRollback() {
        Passenger passenger = new Passenger();
        passenger.setName("Jane");
        passenger.setFare(1000.0);

        Payment payment = new Payment();
        payment.setPaymentId("TXN124");
        payment.setAmount(500.0); // Less than fare
        payment.setCardType("MasterCard");
        payment.setAccountNo("654321");

        Assertions.assertThrows(RuntimeException.class, () -> {
            bookingService.bookFlight(passenger, payment);
        });

        Assertions.assertEquals(0, passengerRepository.findAll().size());
        Assertions.assertEquals(0, paymentRepository.findAll().size());
    }
}

