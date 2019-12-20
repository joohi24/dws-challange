package com.db.awmd.challenge.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferMoneyDto {
	
	private BigDecimal amount;

	private String accountFrom;

	private String accountTo;

//	private TransferMoneyDto(BigDecimal amount, String accountFrom, String accountTo) {
//		this.accountTo = "";
//		this.amount = amount;
//		this.accountFrom = accountFrom;
//		this.accountTo = accountTo;
//	}

	public TransferMoneyDto() {

	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAccountFrom() {
		return accountFrom;
	}

	public String getAccountTo() {
		return accountTo;
	}

	public void setAccountFrom(String accountFrom) {
		this.accountFrom = accountFrom;
	}

	public void setAccountTo(String accountTo) {
		this.accountTo = accountTo;
	}
	
}
