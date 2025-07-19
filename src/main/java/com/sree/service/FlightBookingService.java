package com.sree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sree.entity.Passenger;
import com.sree.entity.Payment;
import com.sree.repository.PassengerRepository;
import com.sree.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class FlightBookingService {

		@Autowired
	    private PassengerRepository passengerRepository;

	    @Autowired
	    private PaymentRepository paymentRepository;

	    @Transactional
	    public String bookFlight(Passenger passenger, Payment payment) {
	        Passenger savedPassenger = passengerRepository.save(passenger);
	        payment.setPassenger(savedPassenger);

	        // Simulate a failure (e.g. amount too low)
	        if (payment.getAmount() < savedPassenger.getFare()) {
	            throw new RuntimeException("Payment amount is less than fare. Transaction failed.");
	        }

	        paymentRepository.save(payment);
	        return "flight booked successfully";
	}

}
