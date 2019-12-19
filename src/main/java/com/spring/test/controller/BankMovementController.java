package com.spring.test.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.test.dao.AccountRepository;
import com.spring.test.dao.BankMovementRepository;
import com.spring.test.dao.BankMovementTypeRepository;
import com.spring.test.dao.CustomerRepository;
import com.spring.test.domain.Account;
import com.spring.test.domain.BankMovement;
import com.spring.test.domain.Country;
import com.spring.test.domain.Customer;
import com.spring.test.dto.BankMovementDto;

@Controller
public class BankMovementController {
	
	private AccountRepository accountRepository;
	private BankMovementRepository bankMovementRepository;
	private BankMovementTypeRepository bankMovementTypeRepository;
	private CustomerRepository customerRepository;

	public BankMovementController(AccountRepository accountRepository, BankMovementRepository bankMovementRepository,
			BankMovementTypeRepository bankMovementTypeRepository, CustomerRepository customerRepository) {
		super();
		this.accountRepository = accountRepository;
		this.bankMovementRepository = bankMovementRepository;
		this.bankMovementTypeRepository = bankMovementTypeRepository;
		this.customerRepository = customerRepository;
	}
	
	@GetMapping("/customers/{customerId}/bank-movements")
	public String showBankMovements(@PathVariable Long customerId, Model model, RedirectAttributes atts) {
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		if (!customerOptional.isPresent()) {
			atts.addFlashAttribute("errorMessage", "Cliente no encontrando");
			return "redirect:/customers";
		}
		Customer customer = customerOptional.get();
		ArrayList<BankMovementDto> bankMovements = (ArrayList<BankMovementDto>)bankMovementRepository.fetchBankMovementsByCustomer(customerId);
		System.out.println("Printing BANK MOVEMENTS. Customer id: " + customer.getId());
		System.out.println(Arrays.toString(bankMovements.toArray()));

		model.addAttribute("customer", customerOptional.get());
		model.addAttribute("bankMovements", bankMovementRepository.fetchBankMovementsByCustomer(customerId));
		return "bank-movements";
	}

}
