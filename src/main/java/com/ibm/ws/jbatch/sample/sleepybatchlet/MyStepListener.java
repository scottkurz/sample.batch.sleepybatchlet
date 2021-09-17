package com.ibm.ws.jbatch.sample.sleepybatchlet;

import javax.batch.api.listener.AbstractStepListener;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

//@ApplicationScoped
@Dependent
public class MyStepListener extends AbstractStepListener {

	private int count = 0;

	public int getCount() {
		return ++count;
	}
	
    @Inject
    MyCounterBean counter;
    
	@Override
	public void beforeStep() throws Exception {
		System.out.println("SKSK: in step listener before, cnt = " + getCount());
		System.out.println("step listener Bean cnt = " + (counter != null ? counter.getCount() : "null"));
	}

	@Override
	public void afterStep() throws Exception {
		System.out.println("SKSK: in step listener after, cnt = " + getCount());
		System.out.println("step listener Bean cnt = " + (counter != null ? counter.getCount() : "null"));
	}
}
