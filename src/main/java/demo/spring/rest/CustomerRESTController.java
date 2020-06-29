package demo.spring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.entity.Customer;
import demo.spring.exception.CustomerNotFoundException;
import demo.spring.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRESTController {
	
	// Autowire the CustomerService 
	@Autowired
	private CustomerService customerService;
	
	// add mapping for GET /customers : get all customers ordered by last-name
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	// add mapping for GET /customer/{customerId} : get customer by Id
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer customer = customerService.getCustomer(customerId);
		
		if(customer == null)
			throw new CustomerNotFoundException("Customer Not found with Id : "+customerId);
		
		return customer;
	}
	
	// add mapping for POST /customers : Add a new Customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer) {
		// just in case they passed the id in JSON, force set it to 0, so that it will perform an Insert
		customer.setId(0);
		
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	// add mapping for PUT /customers : Update a customer data
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	// add mapping for DELETE /customers/{customerId} : Delete a customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		// check if the customer with the id exists or not, if not, throw an exception
		Customer customer = customerService.getCustomer(customerId);
		if(customer == null)
			throw new CustomerNotFoundException("Customer Not found with Id : "+customerId);
		
		// delete the customer
		customerService.deleteCustomer(customerId);
		
		return "Deleted Customer Id : "+customerId;
	}
	
}










