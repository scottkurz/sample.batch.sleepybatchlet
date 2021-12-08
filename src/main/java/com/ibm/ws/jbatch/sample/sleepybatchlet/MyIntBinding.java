package com.ibm.ws.jbatch.sample.sleepybatchlet;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface MyIntBinding {
	@Nonbinding String requiredHttpHeader() default "X-Duke";
}
