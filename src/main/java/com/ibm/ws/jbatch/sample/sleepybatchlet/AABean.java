package com.ibm.ws.jbatch.sample.sleepybatchlet;

import javax.batch.api.BatchProperty;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class AABean {
	

    @Inject
    @BatchProperty(name = "aa")
    String aa;
    
    @Inject StepContext stepCtx;
    
	private int count = 0;

	public void getCount() {
		System.out.println("SKSK: In AABean, aa = " + aa);
		System.out.println("SKSK: In AABean, count = " + ++count);
		System.out.println("SKSK: In AABean, step name = " + stepCtx.getStepName());
	}
}
