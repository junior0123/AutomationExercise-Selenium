package linkedinautomationexercise.framework;

import linkedinautomationexercise.utils.LoggerManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CredentialsManager {
    private Properties properties;
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final String envFilePath = System.getProperty("user.dir") + File.separator + "environment.properties";
    private static CredentialsManager instance;
    private String envId;

    private CredentialsManager() {
        initialize();
    }

    private void initialize() {
        log.info("Reading credentials");
        String wpEnvironmentId = System.getProperty("envId");
        if ((wpEnvironmentId == null) || (wpEnvironmentId.isEmpty())) {
            envId = "local";
        } else {
            envId = wpEnvironmentId.toLowerCase();
        }
        log.info("WordPress Environment Id --> " + envId);

        properties = new Properties();
        Properties envProperties = new Properties();
        try {
            envProperties.load(new FileInputStream(envFilePath));
        } catch (IOException e) {
            log.error("unable to load properties file");
        }
        properties.putAll(envProperties);
    }

    public static CredentialsManager getInstance() {
        if (instance == null) {
            instance = new CredentialsManager();
        }
        return instance;
    }

    private String getEnvironmentSetting(String setting) {
        return (String) getInstance().properties.get(setting);
    }

    public String getEnvId() {
        return envId;
    }

    public String getBaseURL() {
        return getEnvironmentSetting(getEnvId() + ".baseURL");
    }

    public String getUsername(String user) {

        return getEnvironmentSetting(getEnvId() + "." + user + ".username");
    }

    public String getPassword(String user) {
        return getEnvironmentSetting(getEnvId() + "." + user + ".password");
    }

    public String getEmail(String user) {
        return getEnvironmentSetting(getEnvId() + "." + user + ".email");
    }

    public String getUserLoginURL() {
        return getBaseURL() + getEnvironmentSetting(getEnvId() + ".user.loginURL");
    }

    public String getUserURL() {
        return getBaseURL() + getEnvironmentSetting(getEnvId() + ".userURL");
    }
}
