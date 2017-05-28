package creditHelper;

import main.IndividualModel;

public abstract class AbstractStepController implements StepController {
	
	protected IndividualModel model;
	
	public AbstractStepController(IndividualModel model) {
		this.model = model;
	}
	
	@Override
	abstract public void init();
}
