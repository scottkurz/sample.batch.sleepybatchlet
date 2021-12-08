package com.ibm.ws.jbatch.sample.sleepybatchlet;

import javax.batch.api.listener.AbstractStepListener;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

//@ApplicationScoped
//@Dependent
public class MyNonBeanStepListener extends AbstractStepListener {

    @Inject MyCounterBean counter;
    
	@Override
	public void beforeStep() throws Exception {
		System.out.println("SKSK: in MyNonBeanStepListener step listener before");
		Instance<String> myBatchProp = CDI.current().select(String.class, new BatchPropertyLiteral("mm"));
		String mbpStr = null;
		if (myBatchProp.isResolvable()) {
			//mbpStr = myBatchProp.get();
		}

		System.out.println("SKSK: in MyNonBeanStepListener mbpStr " + mbpStr);
	}

	@Override
	public void afterStep() throws Exception {
		System.out.println("SKSK: in MyNonBeanStepListener step listener after");
//		batchlet.getCount();
	}
}
