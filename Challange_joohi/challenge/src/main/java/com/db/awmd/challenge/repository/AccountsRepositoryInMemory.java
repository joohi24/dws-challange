package com.db.awmd.challenge.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.TransferMoneyDto;
import com.db.awmd.challenge.exception.AccountNotFoundException;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.exception.InsuffcientBalanceException;

@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

	private final Map<String, Account> accounts = new ConcurrentHashMap<>();

	@Override
	public void createAccount(Account account) throws DuplicateAccountIdException {
		Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
		if (previousAccount != null) {
			throw new DuplicateAccountIdException("Account id " + account.getAccountId() + " already exists!");
		}
	}

	@Override
	public Account getAccount(String accountId) {
		return accounts.get(accountId);
	}

	@Override
	public void clearAccounts() {
		accounts.clear();
	}

	@Override
	public Map<String, Account> fundTransfer(TransferMoneyDto transferMoneyDto)
			throws AccountNotFoundException, InsuffcientBalanceException {

		Account accountPayee = accounts.get(transferMoneyDto.getAccountFrom());
		Account accountBeneficiary = accounts.get(transferMoneyDto.getAccountTo());

		if (accountPayee == null) {
			throw new AccountNotFoundException(
					"Payee Account : " + transferMoneyDto.getAccountFrom() + " does not exists!");
		} else if (accountBeneficiary == null) {
			throw new AccountNotFoundException(
					"Beneficiary Account : " + transferMoneyDto.getAccountTo() + " does not exists!");

		}
		int res = accountPayee.getBalance().compareTo(transferMoneyDto.getAmount());
		if (res == -1) {
			throw new InsuffcientBalanceException(accountPayee.getAccountId() + " has insufficient balance !");
		} else {
			accountPayee.setBalance(accountPayee.getBalance().subtract(transferMoneyDto.getAmount()));
			accountBeneficiary.setBalance(accountBeneficiary.getBalance().add(transferMoneyDto.getAmount()));
			accounts.put(accountPayee.getAccountId(), accountPayee);
			accounts.put(accountBeneficiary.getAccountId(), accountBeneficiary);

		}

		return accounts;
	}

}
