package com.ibm.ws.jbatch.sample.sleepybatchlet;

import javax.interceptor.AroundConstruct;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@MyIntBinding
public class MyInterceptor {
	
    @AroundConstruct
    public void aroundConstructInterception(InvocationContext invocationContext) throws Exception {
        System.out.println("SKSK: " + 
           invocationContext.getConstructor().getDeclaringClass() + " will be manipulated");
        invocationContext.proceed();
    }

}
