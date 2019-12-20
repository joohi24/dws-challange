package com.db.awmd.challenge.service;

import org.springframework.stereotype.Service;

import com.db.awmd.challenge.domain.Account;

@Service
public class EmailNotificationService implements NotificationService {

	@Override
	public void notifyAboutTransfer(Account account, String transferDescription) {
		// THIS METHOD SHOULD NOT BE CHANGED - ASSUME YOUR COLLEAGUE WILL IMPLEMENT IT
		// log.info("Sending notification to owner of {}: {}", account.getAccountId(),
		// transferDescription);
	}

}