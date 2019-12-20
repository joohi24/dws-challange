package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.TransferMoneyDto;
import com.db.awmd.challenge.repository.AccountsRepository;
import lombok.Getter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;
  
	
	  @Getter private EmailNotificationService emailNotificationService;
	  
	  @Autowired public void EmailNotificationService(EmailNotificationService
	  emailNotificationService) { this.emailNotificationService =
	  emailNotificationService; }
	 


  @Autowired
  public AccountsService(AccountsRepository accountsRepository) {
    this.accountsRepository = accountsRepository;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }
  
  public String fundTransfer(TransferMoneyDto transferMoneyDto) {
	  Map<String, Account> accounts = this.accountsRepository.fundTransfer(transferMoneyDto);
	  Account accountPayee =accounts.get(transferMoneyDto.getAccountFrom());
		Account accountBeneficiary =accounts.get(transferMoneyDto.getAccountTo());
	  emailNotificationService.notifyAboutTransfer(accountPayee, transferMoneyDto.getAmount() + "Amount transferred to "+accountBeneficiary.getAccountId());
	  emailNotificationService.notifyAboutTransfer(accountBeneficiary, transferMoneyDto.getAmount() + "Amount transferred from "+accountPayee.getAccountId());
	  String msg = transferMoneyDto.getAmount() +" amount transfer from :"+accountPayee.getAccountId() + "\n"
			       + transferMoneyDto.getAmount() + " amount transfer to : "+accountBeneficiary.getAccountId() + "\n \n"
			       + "Final Balance \n \n"
			       + accountPayee.getAccountId() + " > " + accountPayee.getBalance() + "\n"
			       + accountBeneficiary.getAccountId() + " > " + accountBeneficiary.getBalance();
	  
	  return msg;
  }
}
