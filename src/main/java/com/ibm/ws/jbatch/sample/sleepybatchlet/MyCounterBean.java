package com.ibm.ws.jbatch.sample.sleepybatchlet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MyCounterBean {
	
	@Inject MyStepListener msl;

	private int count = 0;

	public void getCount() {
		System.out.println("SKSK: In MyCounterBean, count = " + ++count);
		msl.getCount();
	}
	

}
