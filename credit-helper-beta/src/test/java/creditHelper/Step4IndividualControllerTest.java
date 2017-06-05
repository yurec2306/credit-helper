package creditHelper;

import static org.junit.Assert.*;

import org.junit.Test;

import main.IndividualModel;
import main.CreditModel;
import main.CreditModel.CreditType;

public class Step4IndividualControllerTest {
	
	@Test
	public void calcSum_10KAnd25Percents_returns12_5K() {
		Step4IndividualController contrl = new Step4IndividualController(null);
		
		double result = contrl.calcSum(10_000, 2500);
		
		assertEquals(12500.0, result, 0.001);
	}
	
	@Test
	public void calcPercentCost_10KAnd25PercentsAnd12Months_returns1354() {
		Step4IndividualController contrl = new Step4IndividualController(null);
		
		double result = contrl.calcPercentCost(10000, 0.25, 12);
		
		assertEquals(1354.1666667, result, 0.001);
	}
	
	@Test
	public void calcPercentCost_10KAnd5KAnd25PercentsAnd12Months_returns677() {
		Step4IndividualController contrl = new Step4IndividualController(null);
		
		double result = contrl.calcPercentCost(10000, 5000, 0.25, 12);
		
		assertEquals(677.083, result, 0.001);
	}
	
	@Test
	public void calcNetIncome_MonthlyIncome3K_returns1200() {
		Step4IndividualController contrl = new Step4IndividualController(null);
		
		double result = contrl.calcNetIncome(3_000);
		
		assertEquals(1200, result, 0.001);
	}
	
	@Test
	public void calcAllNetIncome_Length12AndNetIncome1200_returns14400() {
		Step4IndividualController contrl = new Step4IndividualController(null);
		
		double result = contrl.calcAllNetIncome(12, 1200);
		
		assertEquals(14400, result, 0.001);
	}
	
	
	@Test
	public void checkValidness_validModel_returnsTrue() {
		IndividualModel model = new IndividualModel();
		model.getCredits().add(new CreditModel());
		model.getLastCredit().setCreditSize(200_000); // 200_000 uah
		model.getLastCredit().setCreditRate(0.25); // 25%
		model.getLastCredit().setCreditLength(12*5); // 5 years
		model.getLastCredit().setDownPayment(20_000); //20_000 uah
		model.setMonthlyIncome(5_000); // 5_000 uah
		model.getLastCredit().setCreditType(CreditType.LONG_TERM_CAR); // car credit
		Step4IndividualController contrl = new Step4IndividualController(model);
		model = contrl.calcParams(model);
		
		boolean result = contrl.checkValidness(model);
		
		assertTrue(result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkValidness_tooSmallDownPayment_throwsIllegalArgumentException() {
		IndividualModel model = new IndividualModel();	
		model.getCredits().add(new CreditModel());
		model.getLastCredit().setCreditSize(200_000); // 200_000 uah
		model.getLastCredit().setCreditRate(0.25); // 25%
		model.getLastCredit().setCreditLength(12*5); // 5 years
		model.getLastCredit().setDownPayment(10_000); //10_000 uah
		model.setMonthlyIncome(5_000); // 5_000 uah
		model.getLastCredit().setCreditType(CreditType.LONG_TERM_CAR); // car credit
		Step4IndividualController contrl = new Step4IndividualController(model);
		model = contrl.calcParams(model);

		boolean result = contrl.checkValidness(model);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checkValidness_tooSmallMonthlyPayment_throwsIllegalArgumentException() {
		IndividualModel model = new IndividualModel();	
		model.getCredits().add(new CreditModel());
		model.getLastCredit().setCreditSize(200_000); // 200_000 uah
		model.getLastCredit().setCreditRate(0.25); // 25%
		model.getLastCredit().setCreditLength(12*5); // 1 year
		model.getLastCredit().setProvision(5_000); // 5_000 uah
		model.getLastCredit().setDownPayment(10_000); //10_000 uah
		model.setMonthlyIncome(500); // 500 uah
		model.getLastCredit().setCreditType(CreditType.LONG_TERM_CAR); // car credit
		Step4IndividualController contrl = new Step4IndividualController(model);
		model = contrl.calcParams(model);

		boolean result = contrl.checkValidness(model);
	}
}
