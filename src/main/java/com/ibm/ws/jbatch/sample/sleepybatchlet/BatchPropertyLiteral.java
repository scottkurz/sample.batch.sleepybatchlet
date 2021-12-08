package com.ibm.ws.jbatch.sample.sleepybatchlet;

import javax.batch.api.BatchProperty;
import javax.enterprise.util.AnnotationLiteral;

public class BatchPropertyLiteral extends AnnotationLiteral<BatchProperty> implements BatchProperty {

	private String name;

	BatchPropertyLiteral() {
		this("ddd");
	}

	BatchPropertyLiteral(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

}
