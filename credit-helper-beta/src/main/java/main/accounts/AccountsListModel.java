package main.accounts;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountsListModel {
	
	private static Logger logger = LoggerFactory.getLogger(AccountsListModel.class);
	
	private ArrayList<AccountModel> accountsList;
	
	public AccountsListModel() {
		logger.trace("Calling AccountListModel()");
		this.accountsList = new ArrayList<>();
		logger.trace("Returning from AccountListModel()");
	}

	public ArrayList<AccountModel> getAccountsList() {
		logger.trace("Calling getAccountsList()");
		logger.debug("accountsList: ", accountsList);
		logger.trace("Returning from getAccountsList()");
		return this.accountsList;
	}

	public void setAccountsList(ArrayList<AccountModel> accountsList) {
		logger.trace("Calling setAccountsList({})", accountsList);
		logger.debug("accountsList: ", accountsList);
		logger.trace("Returning from setAccountsList({})", accountsList);
		this.accountsList = accountsList;
	}

}
