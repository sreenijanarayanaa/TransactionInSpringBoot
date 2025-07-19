package com.sree.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="passenger_info")
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pId;
	private String email;
	private String name;
	private  String Source;
	private String Destination;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	private Date travelDate;
	private String pickUpTime;
	private String arrivaltime;
	private Double fare;
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	public String getPickUpTime() {
		return pickUpTime;
	}
	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}
	public String getArrivaltime() {
		return arrivaltime;
	}
	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public Passenger(Long pId, String email, String name, String source, String destination, Date travelDate,
			String pickUpTime, String arrivaltime, Double fare) {
		super();
		this.pId = pId;
		this.email = email;
		this.name = name;
		Source = source;
		Destination = destination;
		this.travelDate = travelDate;
		this.pickUpTime = pickUpTime;
		this.arrivaltime = arrivaltime;
		this.fare = fare;
	}
	public Passenger() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
