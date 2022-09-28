package org.betterme.framework.reportlistener;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.log4j.Logger;
import org.betterme.framework.helper.Logger.LoggerHelper;
import org.testng.ISuite;
import org.testng.ISuiteListener;



public class CucumberReport implements ISuiteListener {
	
	public final Logger logger = LoggerHelper.getLogger(CucumberReport.class);
	
	@Override
	public void onStart(ISuite suite) {
		
	}

	@Override
	public void onFinish(ISuite suite) {
		try {
			
			File jsonfile = new File("target/");
			File reportOutputDirectory = new File("target/test-classes/reports/cucumberreports/");
			
			String[] fileNames = jsonfile.list(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					if(name.endsWith(".json"))
						return true;
					return false;
				}
			});
			
			for (int i = 0; i < fileNames.length; i++) {
				fileNames[i] = jsonfile.getAbsolutePath() + "/" + fileNames[i];
			}
			
			List<String> jsonFiles = Arrays.asList(fileNames);

			Configuration configuration = new Configuration(reportOutputDirectory, suite.getName());

			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			reportBuilder.generateReports();
			logger.info("Report Generated : " + configuration.getReportDirectory());

		} catch (Exception e) {
			logger.equals(e);
		}
	}

}
