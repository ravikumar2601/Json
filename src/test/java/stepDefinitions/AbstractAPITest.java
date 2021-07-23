package stepDefinitions;

import apiObjectRepository.APIPage;
import io.restassured.response.Response;
import utilsBrowser.configuration.Configuration;
import utilsBrowser.configuration.ConfigurationProvider;

public abstract class AbstractAPITest {

    public static final String environment = getEnvironment();

    protected static ConfigurationProvider configurationProvider = new ConfigurationProvider();
    protected Configuration configuration = configurationProvider.getConfiguration(environment);
    protected Response response;
    protected APIPage apiPage = new APIPage();

    private static String getEnvironment() {
        return System.getProperty("environment");
    }

    public String getEnvironmentType() {
        String env = null;
        if (getEnvironment().contains("uat")) {
            env = "UAT";
        } else if (getEnvironment().contains("prd")) {
            env = "PROD";

        } else if (getEnvironment().contains("qa")) {
            env = "QC";
        }
        return env;
    }
}
