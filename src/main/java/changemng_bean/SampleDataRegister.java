package changemng_bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import changemng_services.PersistTestClass;


@RequestScoped
@Named
public class SampleDataRegister {

	@Inject
	private PersistTestClass serviceMethodCall;

	public void createCrData() {
		
		serviceMethodCall.callSampleDate();
	}
	
}
