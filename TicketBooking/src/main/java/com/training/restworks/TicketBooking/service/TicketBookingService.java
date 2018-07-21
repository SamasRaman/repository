package com.training.restworks.TicketBooking.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.restworks.TicketBooking.entity.Customer;
import com.training.restworks.TicketBooking.util.TicketBookingUtil;


@RestController
public class TicketBookingService {

	private TicketBookingUtil util;

	//Ticket Booking
	@PostMapping("/book")
	public String bookTicket(@RequestBody Customer cust) {		
		String message="";
		util.bookTicket(cust);

		//for one Way Journey
		if(cust.getReturnDate()==null) {
			if(cust.getNoOfPassenger()>6) {
				message="Hi "+cust.getName()+" Ur Ticket is Booked From :"+cust.getFrom()+
						" To :"+cust.getTo()+
						" On Date :"+cust.getDate()+" And Ur Transaction Id Is :"+cust.getTxnId()
						+"For "+cust.getNoOfPassenger()+" Passengers U will get 10% Discount";
				return message;
			}
			message="Hi "+cust.getName()+" Ur Ticket is Booked From :"+cust.getFrom()+
					" To :"+cust.getTo()+
					" On Date :"+cust.getDate()+" And Ur Transaction Id Is :"+cust.getTxnId()
					+"For "+cust.getNoOfPassenger()+" Passengers";
			return message;
		}
		
		//For Multiple Journey
			if(cust.getNoOfPassenger()>6) {
				
				message="Hi "+cust.getName()+" Ur Ticket is Booked From :"+cust.getFrom()+
						" To :"+cust.getTo()+" On Date :"+cust.getDate()+
						" Ur Transaction Id Is :"+cust.getTxnId()+" And Ur Return Journey On :"+cust.getReturnDate()
						+"For "+cust.getNoOfPassenger()+" Passengers U will get 10% Discount";

				return message;
			}
			message="Hi "+cust.getName()+" Ur Ticket is Booked From :"+cust.getFrom()+
					" To :"+cust.getTo()+" On Date :"+cust.getDate()+
					" Ur Transaction Id Is :"+cust.getTxnId()+" And Ur Return Journey On :"+cust.getReturnDate()
					+"For "+cust.getNoOfPassenger()+" Passengers";

			return message;

		}



		//Ticket Cancellation
		@GetMapping("/cancelBooking/{txnId}")
		public String cancelTicket(@PathVariable("txnId") int txnId) {

			String message="";
			int transactionId=util.cancelTicket(txnId);
			System.out.println(transactionId);
			if(transactionId!=0) {
				message="Ur Booking is canceled with Transaction Id :"+transactionId;
				return message;
			}
			return "Transaction Id Not Found";
		}

		//Getting All Customers
		@GetMapping("/book")
		public List<Customer> getAllCustomers(){
			return util.getAllCustomers();
		}

		//Getting Customers By name
		@GetMapping("/book/{name}")
		public Customer getProfile(@PathVariable("name") String name) {
			return util.getCustomer(name);
		}
	}
