package com.spring.test.bootstrap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.test.dao.AccountRepository;
import com.spring.test.dao.AccountTypeRepository;
import com.spring.test.dao.BankMovementRepository;
import com.spring.test.dao.BankMovementTypeRepository;
import com.spring.test.dao.CountryRepository;
import com.spring.test.dao.CustomerRepository;
import com.spring.test.dao.RoleRepository;
import com.spring.test.dao.UserRepository;
import com.spring.test.domain.Account;
import com.spring.test.domain.AccountType;
import com.spring.test.domain.BankMovement;
import com.spring.test.domain.BankMovementType;
import com.spring.test.domain.Country;
import com.spring.test.domain.Customer;
import com.spring.test.domain.Role;
import com.spring.test.domain.User;

@Component
public class SeedDataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	private CountryRepository countryRepository;
	private BankMovementTypeRepository bankMovementTypeRepository;
	private AccountTypeRepository accountTypeRepository;
	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	private BankMovementRepository bankMovementRepository;
	private RoleRepository roleRepository;
	private UserRepository userRepository;

	public SeedDataInitializer(CountryRepository countryRepository, BankMovementTypeRepository bankMovementTypeRepository,
			AccountTypeRepository accountTypeRepository, CustomerRepository customerRepository, 
			AccountRepository accountRepository, BankMovementRepository bankMovementRepository, RoleRepository roleRepository, 
			UserRepository userRepository) {
		super();
		this.countryRepository = countryRepository;
		this.bankMovementTypeRepository = bankMovementTypeRepository;
		this.accountTypeRepository = accountTypeRepository;
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		this.bankMovementRepository = bankMovementRepository;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		initData();
		
	}
	
	private void initData() {
		initCountries();
		initAccountTypes();
		initBankMovementTypes();
		initCustomers();
		initAccounts();
		initBankMovements();
		initRoles();
		initUsers();
	}
	
	private void initRoles() {
		Role role = new Role();
		role.setName("ADMIN");
		roleRepository.save(role);
	}

	private void initCountries (){
		Country guatemala = new Country();
		guatemala.setName("Guatemala");
		countryRepository.save(guatemala);
		
		Country costaRica = new Country();
		costaRica.setName("CostaRica");
		countryRepository.save(costaRica);
		
		Country cuba = new Country();
		cuba.setName("Cuba");
		countryRepository.save(cuba);
	}
	
	private void initAccountTypes() {
		AccountType cash = new AccountType();
		cash.setName("Debito");
		accountTypeRepository.save(cash);
		
		AccountType saving = new AccountType();
		saving.setName("Ahorros");
		accountTypeRepository.save(saving);
	}
	
	private void initBankMovementTypes() {
		BankMovementType debit = new BankMovementType("Debito");
		bankMovementTypeRepository.save(debit);
		
		BankMovementType credit = new BankMovementType("Credito");
		bankMovementTypeRepository.save(credit);
		
		BankMovementType refund = new BankMovementType("Reembolso");
		bankMovementTypeRepository.save(refund);
	}
	
	private void initCustomers() {

		ArrayList<Country>  countries = (ArrayList<Country>) countryRepository.findAll();
		System.out.println(Arrays.toString(countries.toArray()));
		Country country =countries.stream().findFirst().get();
		Customer customer1 = new Customer();
		customer1.setFirstName("Juan");
		customer1.setLastName("Perez");
		customer1.setCountry(country);
		customer1.setBirthDate(LocalDate.now());
		customerRepository.save(customer1);

		Customer customer2 = new Customer();
		customer2.setFirstName("Jaime");
		customer2.setLastName("Lopez");
		customer2.setCountry(country);
		customer2.setBirthDate(LocalDate.now());
		customerRepository.save(customer2);

		Customer customer3 = new Customer();
		customer3.setFirstName("Maria");
		customer3.setLastName("Rosales");
		customer3.setCountry(country);
		customer3.setBirthDate(LocalDate.now());
		customerRepository.save(customer3);		
	}
	
	private void initAccounts() {
		ArrayList<Customer>  customers = (ArrayList<Customer>) customerRepository.findAll();
		Customer customer = customers.stream().findFirst().get();
		
		ArrayList<AccountType>  accountTypes = (ArrayList<AccountType>) accountTypeRepository.findAll();
		AccountType accountType = accountTypes.stream().findFirst().get();

		Account account = new Account();
		account.setAccountNumber(1000l);
		account.setCustomer(customer);
		account.setAccountType(accountType);
		accountRepository.save(account);
	}
	
	private void initBankMovements() {
		ArrayList<Account>  acccounts = (ArrayList<Account>) accountRepository.findAll();
		Account account = acccounts.stream().findFirst().get();
		
		ArrayList<BankMovementType>  bankMovementTypes = (ArrayList<BankMovementType>) bankMovementTypeRepository.findAll();
		BankMovementType bankMovementType = bankMovementTypes.stream().findFirst().get();
		
		BankMovement bankMovement1 = new BankMovement();
		bankMovement1.setAccount(account);
		bankMovement1.setAmount(new BigDecimal(10));
		bankMovement1.setCreateDate(LocalDateTime.now());
		bankMovement1.setBankMovementType(bankMovementType);
		bankMovementRepository.save(bankMovement1);	
	}
	
	private void initUsers() {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User firstUser = new User();
        firstUser.setUsername("test");
        firstUser.setPassword("test");
        firstUser.setEnabled(true);
        firstUser.setAuthorities((Collection<Role>) roleRepository.findAll());
        userRepository.save(firstUser);
	}
	
	
}
