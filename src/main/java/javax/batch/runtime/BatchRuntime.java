/*
 * Copyright 2012 International Business Machines Corp.
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javax.batch.runtime;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.batch.operations.JobOperator;

/**
 * BatchRuntime represents the JSR 352 Batch Runtime.
 * It provides factory access to the JobOperator interface.
 *
 */
public class BatchRuntime {

    private final static String sourceClass = BatchRuntime.class.getName();
    private final static Logger logger = Logger.getLogger(sourceClass);

    // Remember we can't add anything public without affecting signature test which is part of EE compliance.
	private static final String DISABLE_IBM_IMPL = "com.ibm.ws.jbatch.disable";
	
	private static Boolean disableIBMImplementation = false;
	// CLASS NAME MUST NEVER CHANGE !!
	private static String IBM_JOB_OPERATOR_IMPL_CLASSNAME = "com.ibm.jbatch.container.api.impl.JobOperatorImpl";
	
	static {
		disableIBMImplementation = Boolean.getBoolean(DISABLE_IBM_IMPL);

		if(logger.isLoggable(Level.FINE)) { 
			String disableProp = System.getProperty(DISABLE_IBM_IMPL);
			logger.log(Level.FINE, "Disable IBM Implementation = " + disableIBMImplementation + "; Disable prop = (" + DISABLE_IBM_IMPL + " => " + disableProp + ").");
		}
	}

	/**
	* The getJobOperator factory method returns
	* an instance of the JobOperator interface.
	*
	* @return JobOperator instance.
	*/
	public static JobOperator getJobOperator() {


		JobOperator operator = AccessController.doPrivileged(new PrivilegedAction<JobOperator> () {
            public JobOperator run() {

            	ServiceLoader<JobOperator> loader = ServiceLoader.load(JobOperator.class);
            	JobOperator returnVal = null;
            	for (JobOperator provider : loader) {
        			if (provider != null) {
        				String providerClassName = provider.getClass().getCanonicalName();
        				if (logger.isLoggable(Level.FINE)) {
        					logger.fine("Loaded BatchContainerServiceProvider with className = " + providerClassName);
        				}
        			
        				if (disableIBMImplementation && providerClassName.equals(IBM_JOB_OPERATOR_IMPL_CLASSNAME)) {
        					if (logger.isLoggable(Level.FINE)) {
        						logger.fine("Use of IBM JobOperator impl disabled, skipping ...");
        					}
        					continue;
        				}
        				
        				// Use first one that otherwise matches`
        				returnVal = provider;
        				break;
        			}
        		}

                return returnVal;
            }
        });


		if (operator == null) {
			if (logger.isLoggable(Level.WARNING)) {
				logger.warning("The ServiceLoader was unable to find an implementation for JobOperator. Check classpath for META-INF/services/javax.batch.operations.JobOperator file.");
			}
		}
		return operator;
	}
}
