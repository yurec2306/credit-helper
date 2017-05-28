package dataBase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.internal.SessionImpl;
import org.junit.Test;
import main.accounts.AccountModel;

public class AccountDaoTest {

	@Test
	public void testSaveOrUpdate() {
		AccountDAO dao = new AccountDAO();
		SessionFactory sessionFactoryMock = mock(SessionFactoryImpl.class);
		Session session = mock(SessionImpl.class);
		when(sessionFactoryMock.openSession()).thenReturn(session);
		dao.setSessionFactory(sessionFactoryMock);
		AccountModel model = new AccountModel();
		model.setFirstName("Test Name");
		dao.saveOrUpdate(model);
	}
	
	@Test
	public void testDelete() {
		
	}
	
	@Test
	public void testGetAccount() {

	}
	
	@Test
	public void testGetAllAccounts() {
	
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
