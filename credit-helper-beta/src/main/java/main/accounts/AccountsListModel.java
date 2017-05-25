package main.accounts;

import java.util.ArrayList;

public class AccountsListModel {
	
	private ArrayList<AccountModel> accountsList;
	
	public AccountsListModel() {
		accountsList = new ArrayList<>();
	}

	public ArrayList<AccountModel> getAccountsList() {
		return accountsList;
	}

	public void setAccountsList(ArrayList<AccountModel> accountsList) {
		this.accountsList = accountsList;
	}

}
