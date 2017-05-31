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

import main.IndividualModel;

public class IndividualModelDaoTest {
	private final static File f = new File(".\\src\\test\\resources\\HibernateTest.cfg.xml");
	private final static String testFirstName = "Tester";
	private final static String testMiddleName = "Tester";
	private final static String testLastName = "Tester";

	@Test
	public void saveOrUpdate_correctInput_successSave() {
		IndividualModelDAO dao = new IndividualModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		IndividualModel model = new IndividualModel();
		dao.setSessionFactory(sessionFactory);
		model.setFirstName(testFirstName);
		model.setMiddleName(testMiddleName);
		model.setLastName(testLastName);

		dao.saveOrUpdate(model);

		dao.close();
	}

	@Test
	public void getModel_saveAndGetTheSameIndividualModel_modelsEqual() {
		IndividualModelDAO dao = new IndividualModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		IndividualModel model = new IndividualModel();
		dao.setSessionFactory(sessionFactory);
		model.setFirstName(testFirstName);
		model.setMiddleName(testMiddleName);
		model.setLastName(testLastName);
		dao.saveOrUpdate(model);

		IndividualModel newModel = dao.getModel(testLastName, testFirstName, testMiddleName);

		assertEquals(model, newModel);
		
		dao.close();
	}

	@Test
	public void getModel_saveAndGetDifferentModels_modelsNotEqual() {
		IndividualModelDAO dao = new IndividualModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		/* first account */
		IndividualModel model = new IndividualModel();
		model.setFirstName(testFirstName);
		model.setMiddleName(testMiddleName);
		model.setLastName(testLastName);
		dao.saveOrUpdate(model);
		/* second account */
		IndividualModel model2 = new IndividualModel();
		model.setFirstName("BadFirstName");
		model.setMiddleName("BadMiddleName");
		model.setLastName("BadLastName");

		IndividualModel newModel = dao.getModel(testLastName, testFirstName, testMiddleName);

		assertNotEquals(model2, newModel);
		
		dao.close();
	}

	@Test
	public void getAllModels_save3Accounts_get3Accounts() {
		IndividualModelDAO dao = new IndividualModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		/* first account */
		IndividualModel model = new IndividualModel();
		model.setFirstName(testFirstName);
		model.setMiddleName(testMiddleName);
		model.setLastName(testLastName);
		dao.saveOrUpdate(model);
		/* second account */
		IndividualModel model2 = new IndividualModel();
		model.setFirstName("Tester2");
		model.setMiddleName("Tester2");
		model.setLastName("Tester2");
		dao.saveOrUpdate(model2);
		/* third account */
		IndividualModel model3 = new IndividualModel();
		model.setFirstName("Tester3");
		model.setMiddleName("Tester3");
		model.setLastName("Tester3");
		dao.saveOrUpdate(model3);
		List<IndividualModel> preparedList = new ArrayList<>();
		preparedList.add(model);
		preparedList.add(model2);
		preparedList.add(model3);

		List<IndividualModel> testList = dao.getAllModels();
		dao.close();

		assertEquals(preparedList, testList);
	}

	@Test
	public void delete_saveThenDeleteModel_returnsNull() {
		IndividualModelDAO dao = new IndividualModelDAO();
		SessionFactory sessionFactory = new Configuration().configure(f).buildSessionFactory();
		dao.setSessionFactory(sessionFactory);
		IndividualModel model = new IndividualModel();
		model.setFirstName(testFirstName);
		model.setMiddleName(testMiddleName);
		model.setLastName(testLastName);
		dao.saveOrUpdate(model);

		dao.delete(model);

		assertEquals(null, dao.getModel(model.getLastName(), model.getFirstName(), model.getMiddleName()));
		dao.close();
	}

	@Test
	public void close_closeDAO_sessionFactoryClosed() {
		IndividualModelDAO dao = new IndividualModelDAO();
		SessionFactory sessionFactoryMock = mock(SessionFactory.class);
		dao.setSessionFactory(sessionFactoryMock);
		dao.close();
		verify(sessionFactoryMock).close();
	}

}
