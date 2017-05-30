package dataBase;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import main.accounts.AccountModel;

public class AccountDaoTest {
	private File f = new File(".\\src\\test\\resources\\HibernateTest.cfg.xml");
	private SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
	private AccountModel model = new AccountModel();
	private String testLogin = "Test";
	private char[] testPass = {'T', 'e', 's', 't'};
	

	@Test
	public void testSaveOrUpdate() {
		AccountDAO dao = new AccountDAO();
		dao.setSessionFactory(sessionFactory);
		model.setLogin(testLogin);
		model.setPassword(testPass);
		dao.saveOrUpdate(model);
		dao.close();
	}
	
	@Test
	public void testGetAccount() {
		AccountDAO dao = new AccountDAO();
		dao.setSessionFactory(sessionFactory);
		AccountModel newModel = dao.getAccount(testLogin, testPass);
		dao.close();
		assertEquals(model, newModel);
	}
	
	@Test
	public void testGetAllAccounts() {
	
	}
	
	@Test
	public void testDelete() {
		AccountDAO dao = new AccountDAO();
		dao.setSessionFactory(sessionFactory);
		AccountModel newModel = dao.getAccount(testLogin, testPass);
		dao.close();
		assertEquals(model, newModel);
	}
	
	@Test
	public void testClose() {
		AccountDAO dao = new AccountDAO();
		SessionFactory sessionFactoryMock = mock(SessionFactory.class);
		dao.setSessionFactory(sessionFactoryMock);
		dao.close();
		verify(sessionFactoryMock).close();
	}
	
}
