package dataBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.File;
import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import main.accounts.AccountModel;

public class AccountDaoTest {
	private final static File f = new File(".\\src\\test\\resources\\HibernateTest.cfg.xml");
	private final static String testLogin = "Test";
	private final static char[] testPass = {'T', 'e', 's', 't'};
	

	@Test
	public void saveOrUpdate_correctInput_successSave() {
		AccountDAO dao = new AccountDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		AccountModel model = new AccountModel();
		dao.setSessionFactory(sessionFactory);
		model.setLogin(testLogin);
		model.setPassword(testPass);
		
		dao.saveOrUpdate(model);
		
		dao.close();
	}
	
	@Test
	public void getAccount_saveAndGetTheSameAccount_accountsEqual() {
		AccountDAO dao = new AccountDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		AccountModel model = new AccountModel();
		dao.setSessionFactory(sessionFactory);
		model.setLogin(testLogin);
		model.setPassword(testPass);
		dao.saveOrUpdate(model);
		
		AccountModel newModel = dao.getAccount(testLogin, testPass);
		dao.close();
		
		assertEquals(model, newModel);
	}
	
	@Test
	public void getAccount_saveAndGetDifferentAccounts_accountsNotEqual() {
		AccountDAO dao = new AccountDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		/* first account */
		AccountModel model = new AccountModel();
		model.setLogin(testLogin);
		model.setPassword(testPass);
		dao.saveOrUpdate(model);
		/* second account */
		AccountModel model2 = new AccountModel();
		model2.setLogin("BadLogin");
		model2.setPassword(new char[]{'n','o','t'});
		
		AccountModel newModel = dao.getAccount(testLogin, testPass);
		dao.close();
		
		assertNotEquals(model2, newModel);
	}
	
	@Test
	public void getAllAccounts_save3Accounts_get3Accounts() {
		AccountDAO dao = new AccountDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		/* first account */
		AccountModel model = new AccountModel();
		model.setLogin(testLogin);
		model.setPassword(testPass);
		dao.saveOrUpdate(model);
		/* second account */
		AccountModel model2 = new AccountModel();
		model2.setLogin("Test2");
		model2.setPassword(new char[]{'T','e','s','t'});
		dao.saveOrUpdate(model2);
		/* third account */
		AccountModel model3 = new AccountModel();
		model3.setLogin("Test3");
		model3.setPassword(new char[]{'T','e','s','t'});
		dao.saveOrUpdate(model3);
		ArrayList<AccountModel> preparedList = new ArrayList<>();
		preparedList.add(model);
		preparedList.add(model2);
		preparedList.add(model3);
		
		ArrayList<AccountModel> testList = dao.getAllAccounts();
		dao.close();
		
		assertEquals(preparedList, testList);
	}
	
	@Test
	public void delete_saveThenDeleteModel_returnsNull() {
		AccountDAO dao = new AccountDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		AccountModel model = new AccountModel();
		model.setLogin(testLogin);
		model.setPassword(testPass);
		dao.saveOrUpdate(model);
		
		dao.delete(model);
		
		assertEquals(null, dao.getAccount(model.getLogin(), model.getPassword()));
		dao.close();
	}
	
	@Test
	public void close_closeDAO_sessionFactoryClosed() {
		AccountDAO dao = new AccountDAO();
		SessionFactory sessionFactoryMock = mock(SessionFactory.class);
		dao.setSessionFactory(sessionFactoryMock);
		dao.close();
		verify(sessionFactoryMock).close();
	}
	
}
