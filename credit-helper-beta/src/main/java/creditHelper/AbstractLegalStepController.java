package creditHelper;

import main.LegalModel;

public abstract class AbstractLegalStepController implements StepController {
	
	protected LegalModel model;
	
	public AbstractLegalStepController(LegalModel model) {
		this.model = model;
	}
	
	abstract public void init();
}
