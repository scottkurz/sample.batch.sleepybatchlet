package com.ibm.ws.jbatch.sample.sleepybatchlet;

import javax.batch.api.listener.AbstractStepListener;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

//@ApplicationScoped
@Dependent
public class MyStepListener extends AbstractStepListener {

	private int count = 0;

	public void getCount() {
		System.out.println("SKSK: in step listener , cnt = " + ++count);
	}
	
    @Inject SleepyBatchlet batchlet;
    
	@Override
	public void beforeStep() throws Exception {
		System.out.println("SKSK: in step listener before,  cnt = " + ++count);
		batchlet.getCount();
		System.out.println("SKSK: in step listener before,  date =   " + batchlet.getDate());
	}

	@Override
	public void afterStep() throws Exception {
		System.out.println("SKSK: in step listener after, cnt = " + ++count);
		batchlet.getCount();
	}
}
