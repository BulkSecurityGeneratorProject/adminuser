package com.rishi.adminuser;

/**
 * @author raushanr
 *
 */
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.hateoas.config.EnableEntityLinks;

import com.rishi.adminuser.config.Constants;
import com.rishi.adminuser.config.JHipsterProperties;
import com.rishi.adminuser.config.JpaConfiguration;

@Import(JpaConfiguration.class)
@EnableEntityLinks
@SpringBootApplication(scanBasePackages = { "com.rishi.adminuser" })
@EnableConfigurationProperties({ JHipsterProperties.class, LiquibaseProperties.class })
public class AdminuserApplication {

	private static final Logger log = LoggerFactory
			.getLogger(AdminuserApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		// SpringApplication.run(AdminuserApplication.class, args);

		SpringApplication app = new SpringApplication(
				AdminuserApplication.class);
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(
				args);
		getDefaultProfile(app, source);
		Environment env = app.run(args).getEnvironment();
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t"
						+ "Local: \t\thttp://127.0.0.1:{}\n\t"
						+ "External: \thttp://{}:{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), env
						.getProperty("server.port"), InetAddress.getLocalHost()
						.getHostAddress(), env.getProperty("server.port"));
	}

	/**
	 * getting default profile for database
	 * 
	 */
	private static void getDefaultProfile(SpringApplication app,
			SimpleCommandLinePropertySource source) {

		if (!source.containsProperty("spring.profiles.active")
				&& !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

			app.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
		}

	}

}
