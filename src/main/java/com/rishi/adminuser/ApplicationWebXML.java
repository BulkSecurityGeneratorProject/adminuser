package com.rishi.adminuser;

/**
 * @author raushanr
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.rishi.adminuser.config.Constants;

/**
 * It provides an alternative to creating a web.xml.
 */
public class ApplicationWebXML extends SpringBootServletInitializer {

	private final Logger log = LoggerFactory.getLogger(ApplicationWebXML.class);

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builderApplication) {

		return builderApplication.profiles(getDefaultProfile()).sources(
				AdminuserApplication.class);
	}

	/**
	 * getting default profile for database
	 * 
	 */
	private String getDefaultProfile() {
		String profile = System.getProperty("spring.profiles.active");
		if (profile != null) {
			log.info("Running with Spring profile(s) : {}", profile);
			return profile;
		}

		log.warn("No Spring profile configured, running with default configuration");
		return Constants.SPRING_PROFILE_DEVELOPMENT;
	}

}
