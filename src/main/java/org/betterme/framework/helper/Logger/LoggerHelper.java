package org.betterme.framework.helper.Logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.betterme.framework.utility.ResourceHelper;

@SuppressWarnings("rawtypes")
public class LoggerHelper {
	
	private static boolean root = false;
	
	public static Logger getLogger(Class clazz) {
		if(root)
			return Logger.getLogger(clazz);
		
		PropertyConfigurator.configure(ResourceHelper
				.getResourcePath("configfile/log4j.properties"));
		root = true;
		return Logger.getLogger(clazz);
	}

}
