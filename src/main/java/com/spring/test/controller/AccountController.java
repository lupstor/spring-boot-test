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

import com.spring.test.dao.AccountRepository;
import com.spring.test.dao.AccountTypeRepository;
import com.spring.test.dao.CustomerRepository;
import com.spring.test.domain.Account;
import com.spring.test.domain.AccountType;
import com.spring.test.domain.Customer;

@Controller
public class AccountController {

	private AccountTypeRepository accountTypeRepository;
	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;

	public AccountController(AccountTypeRepository accountTypeRepository, AccountRepository accountRepository,
			CustomerRepository customerRepository) {
		super();
		this.accountTypeRepository = accountTypeRepository;
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}

	@GetMapping("/customer/{customerId}/accounts")
	public String showCustomersList(@PathVariable Long customerId, Model model, RedirectAttributes atts) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (!optionalCustomer.isPresent()) {
			atts.addFlashAttribute("errorMessage", "Cliente no encontrando");
			return "redirect:/customers";
		}
		model.addAttribute("customer", optionalCustomer.get());
		model.addAttribute("accounts", customerRepository.findById(customerId).get().getAccounts());
		return "accounts";
	}

	@GetMapping("/customer/{customerId}/account/new")
	public String newCustomer(@PathVariable Long customerId, Model model, RedirectAttributes atts) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (!optionalCustomer.isPresent()) {
			atts.addFlashAttribute("errorMessage", "Cliente no encontrando");
			return "redirect:/customers";
		}
		Account account = new Account();
		account.setAccountType(new AccountType());
		account.setCustomer(optionalCustomer.get());
		model.addAttribute("account", account);
		model.addAttribute("accountTypes", accountTypeRepository.findAll());
		return "account-form";
	}

	@PostMapping("/customer/{customerId}/account/save")
	public String saveCustomer(@PathVariable Long customerId, @Valid Account account, BindingResult result, Model model,
			RedirectAttributes atts) {
		Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
		if (!optionalCustomer.isPresent()) {
			atts.addFlashAttribute("errorMessage", "Cliente no encontrando");
			return "redirect:/customers";
		}

		Boolean existsAccountNumber = accountRepository.existsByAccountNumber(account.getAccountNumber());
		if (result.hasErrors() || (existsAccountNumber && account.getId() == null)) {
			model.addAttribute("account", account);
			model.addAttribute("accountTypes", accountTypeRepository.findAll());
			if (existsAccountNumber) {
				model.addAttribute("errorMessage", "No. de cuenta ya existe");
			}
			return "account-form";
		}
		account.setCustomer(optionalCustomer.get());
		accountRepository.save(account);
		atts.addFlashAttribute("successMessage", "Cuenta guardada exitosamente");
		return "redirect:/customer/" + customerId + "/accounts";
	}

	@GetMapping("/account/edit/{id}")
	public String editCustomer(@PathVariable Long id, Model model, RedirectAttributes atts) {

		Optional<Account> accountOptional = accountRepository.findById(id);
		if (!accountOptional.isPresent()) {
			atts.addFlashAttribute("errorMessage", "Cuenta no encontranda");
			return "redirect:/customers";
		}
		model.addAttribute("account", accountOptional.get());
		model.addAttribute("accountTypes", accountTypeRepository.findAll());
		return "account-form";
	}

	@GetMapping("/account/delete/{id}")
	public String deleteCustomer(@PathVariable Long id, Model model, RedirectAttributes atts) {

		Optional<Account> accountOptional = accountRepository.findById(id);
		if (!accountOptional.isPresent()) {
			atts.addFlashAttribute("errorMessage", "Cuenta no encontranda");
			return "redirect:/customers";
		}
		Account account = accountOptional.get();
		System.out.println("ACCOUNT TO DELETE: " + account.getAccountNumber());
		accountRepository.delete(account);
		atts.addFlashAttribute("successMessage", "Cuenta borrada exitosamente");
		return "redirect:/customer/" + account.getCustomer().getId() + "/accounts";
	}

}
