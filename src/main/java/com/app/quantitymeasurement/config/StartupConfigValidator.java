package com.app.quantitymeasurement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Validates critical environment configurations at startup.
 * Ensures that the application fails fast if production-ready environment variables are missing.
 */
@Component
public class StartupConfigValidator implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(StartupConfigValidator.class);

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String[] activeProfiles = env.getActiveProfiles();
        String oauthRedirectUri = env.getProperty("app.oauth2.redirectUri");
        String corsOrigins = env.getProperty("app.cors.allowedOrigins");

        logger.info("================================================================================");
        logger.info("Application Startup Validation");
        logger.info("Active Profiles: {}", Arrays.toString(activeProfiles));
        logger.info("OAuth2 Redirect URI: {}", oauthRedirectUri);
        logger.info("CORS Allowed Origins: {}", corsOrigins);
        logger.info("================================================================================");

        if (List.of(activeProfiles).contains("prod")) {
            validateProdEnvironment();
        }
    }

    private void validateProdEnvironment() {
        validateProperty("APP_OAUTH2_REDIRECT_URI", "app.oauth2.redirectUri");
        validateProperty("APP_CORS_ALLOWED_ORIGINS", "app.cors.allowedOrigins");
        validateProperty("DB_URL", "spring.datasource.url");
        validateProperty("DB_USERNAME", "spring.datasource.username");
        validateProperty("DB_PASSWORD", "spring.datasource.password");
        validateProperty("REDIS_HOST", "spring.redis.host");
        
        logger.info("Production configuration validated successfully.");
    }

    private void validateProperty(String envVarName, String propertyKey) {
        String value = env.getProperty(propertyKey);
        if (value == null || value.isBlank() || value.contains("${" + envVarName + "}")) {
            String errorMessage = String.format(
                "\n\n[FATAL ERROR] Production configuration missing!\n" +
                "The environment variable '%s' (linked to '%s') is not set.\n" +
                "In 'prod' profile, this variable is REQUIRED for secure operation.\n" +
                "Please set it in your environment or .env file before restarting.\n",
                envVarName, propertyKey
            );
            logger.error(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
    }
}
