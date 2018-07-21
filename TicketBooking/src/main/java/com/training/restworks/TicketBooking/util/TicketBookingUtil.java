package com.training.restworks.TicketBooking.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.training.restworks.TicketBooking.entity.Customer;

@Component
public class TicketBookingUtil {
	
	private static List<Customer> cust=new ArrayList<>();

	public static Customer bookTicket(Customer customer) {
		if(customer.getNoOfPassenger()==0) {
			customer.setNoOfPassenger(1);
		}
		customer.setTxnId(new Random().nextInt(55677));
		cust.add(customer);
		System.out.println(customer.getName());
		return customer;
		
		
	}

	public static int cancelTicket(int txnId) {
		int transactionId=txnId;
		for(Customer customer : cust) {
			if(customer.getTxnId()==txnId) {
				cust.remove(customer);
				System.out.println(transactionId);
				return transactionId;
				
			}
			
		}
		return  0;
		
	}

	public static List<Customer> getAllCustomers() {
		
		return cust;
	}
	
	

	public static Customer getCustomer(String name) {
		
		for(Customer customer : cust) {
			if(customer.getName().equalsIgnoreCase(name)) {
					
				return customer;
				
			}
	}
		return null;

	}
}
