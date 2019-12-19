package com.spring.test.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.test.dao.CountryRepository;
import com.spring.test.dao.CustomerRepository;
import com.spring.test.domain.Country;
import com.spring.test.domain.Customer;

@Controller
public class CustomerController {

	private CustomerRepository customerRepository;
	private CountryRepository countryRepository;
	
	public CustomerController(CustomerRepository customerRepository, CountryRepository countryRepository) {
		super();
		this.customerRepository = customerRepository;
		this.countryRepository = countryRepository;
	}

	@GetMapping("/customers")
	public String showCustomersList(Model model) {
		model.addAttribute("customers", customerRepository.findAll());   
		return "customers";
	}
	
	@GetMapping("/customer/new")
	public String newCustomer(Model model) {
		Customer customer = new Customer();
		customer.setCountry(new Country());
        model.addAttribute("customer", customer);     
		model.addAttribute("countries", countryRepository.findAll());
		return "customer-form";
	}
	
    @GetMapping("/customer/edit/{id}")
	public String editCustomer(@PathVariable Long id, Model model, RedirectAttributes atts) {
    	
    	Optional<Customer> customerOptional = customerRepository.findById(id);
    	if(!customerOptional.isPresent()) {
			atts.addFlashAttribute("errorMessage", "Cliente no encontrando");
			return "redirect:/customers";
    	}
		model.addAttribute("customer", customerOptional.get());
		model.addAttribute("countries", countryRepository.findAll());
		return "customer-form";
	}
    

    @PostMapping("/customer")
	public String saveCustomer(@Valid Customer customer, BindingResult result, Model model, RedirectAttributes atts) {
	 	if(result.hasErrors()) {
			model.addAttribute("countries", countryRepository.findAll());
	 		return "customer-form";
	 	}
	 	customerRepository.save(customer);
	 	model.addAttribute("message", "Cliente guardado exitosamente");
    	atts.addFlashAttribute("successMessage", "Cliente guardado exitosamente");
		return "redirect:/customers";
	}
    
    @GetMapping("/customer/delete/{id}")
	public String deleteCustomer(@PathVariable Long id, Model model, RedirectAttributes atts) {
    	
    	Optional<Customer> customerOptional = customerRepository.findById(id);
    	if(!customerOptional.isPresent()) {
        	atts.addFlashAttribute("errorMessage", "Cliente no encontrando");
    		return "redirect:/customers";
    	}
    	customerRepository.deleteById(id);
 		return "redirect:/customers";
	}
}
