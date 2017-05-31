package dataBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import main.LegalModel;

public class LegalModelDaoTest {
	private final static File f = new File(".\\src\\test\\resources\\HibernateTest.cfg.xml");
	private final static String testCompanyName = "TesterInc";

	@Test
	public void saveOrUpdate_correctInput_successSave() {
		LegalModelDAO dao = new LegalModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		LegalModel model = new LegalModel();
		dao.setSessionFactory(sessionFactory);
		model.setOrganizationName(testCompanyName);

		dao.saveOrUpdate(model);

		dao.close();
	}

	@Test
	public void getModel_saveAndGetTheSameLegalModel_modelsEqual() {
		LegalModelDAO dao = new LegalModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		LegalModel model = new LegalModel();
		dao.setSessionFactory(sessionFactory);
		model.setOrganizationName(testCompanyName);
		dao.saveOrUpdate(model);

		LegalModel newModel = dao.getModel(testCompanyName);

		assertEquals(model, newModel);
		
		dao.close();
	}

	@Test
	public void getModel_saveAndGetDifferentModels_modelsNotEqual() {
		LegalModelDAO dao = new LegalModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		/* first account */
		LegalModel model = new LegalModel();
		model.setOrganizationName(testCompanyName);
		dao.saveOrUpdate(model);
		/* second account */
		LegalModel model2 = new LegalModel();
		model.setOrganizationName("BadCompanyName");

		LegalModel newModel = dao.getModel(testCompanyName);

		assertNotEquals(model2, newModel);
		
		dao.close();
	}

	@Test
	public void getAllModels_save3Accounts_get3Accounts() {
		LegalModelDAO dao = new LegalModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		/* first account */
		LegalModel model = new LegalModel();
		model.setOrganizationName(testCompanyName);
		dao.saveOrUpdate(model);
		/* second account */
		LegalModel model2 = new LegalModel();
		model2.setOrganizationName("testCompanyName2");
		dao.saveOrUpdate(model2);
		/* third account */
		LegalModel model3 = new LegalModel();
		model3.setOrganizationName("testCompanyName3");
		dao.saveOrUpdate(model3);
		List<LegalModel> preparedList = new ArrayList<>();
		preparedList.add(model);
		preparedList.add(model2);
		preparedList.add(model3);

		List<LegalModel> testList = dao.getAllModels();
		dao.close();

		assertEquals(preparedList, testList);
	}

	@Test
	public void delete_saveThenDeleteModel_returnsNull() {
		LegalModelDAO dao = new LegalModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		LegalModel model = new LegalModel();
		model.setOrganizationName(testCompanyName);
		dao.saveOrUpdate(model);

		dao.delete(model);

		assertEquals(null, dao.getModel(model.getOrganizationName()));
		dao.close();
	}

	@Test
	public void close_closeDAO_sessionFactoryClosed() {
		LegalModelDAO dao = new LegalModelDAO();
		SessionFactory sessionFactoryMock = mock(SessionFactory.class);
		dao.setSessionFactory(sessionFactoryMock);
		dao.close();
		verify(sessionFactoryMock).close();
	}

}
