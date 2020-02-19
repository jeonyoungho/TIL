package com.mjart.block.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReservationInformation {
	private String reservationname;
	private String roomname;
	private int year;
	private int month;
	private int day;
	private String htel_c1;
	private String htel_c2;
	private String htel_c3;
	private String flowinDomain;
	private String memo;
	
	private int lengthOfStay;
	private int numberOfAdult;
	private int numberOfChild;
	private int numberOfBaby;
	
	private int appmRegState;
	private int is_banknosms;
	private int is_sms;
	private int is_admin;
	
	private String amt;
}
