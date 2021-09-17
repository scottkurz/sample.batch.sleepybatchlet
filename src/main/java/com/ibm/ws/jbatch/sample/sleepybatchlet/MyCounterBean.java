package com.ibm.ws.jbatch.sample.sleepybatchlet;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyCounterBean {
	
	private int count = 0;

	public int getCount() {
		return ++count;
	}
	

}
