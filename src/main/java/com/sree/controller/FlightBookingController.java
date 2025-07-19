package com.sree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sree.entity.Passenger;
import com.sree.entity.Payment;
import com.sree.service.FlightBookingService;

@RestController
@RequestMapping("/api")
public class FlightBookingController {

    @Autowired
    private FlightBookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody Passenger passenger,
                                             @RequestParam String paymentId,
                                             @RequestParam String accountNo,
                                             @RequestParam double amount,
                                             @RequestParam String cardType) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setAccountNo(accountNo);
        payment.setAmount(amount);
        payment.setCardType(cardType);

        try {
            String response = bookingService.bookFlight(passenger, payment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking failed: " + e.getMessage());
        }
    }
}
