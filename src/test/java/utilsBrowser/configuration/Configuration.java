package utilsBrowser.configuration;

import java.util.Properties;

public class Configuration {
    private String url;
    private String userName;
    private String password;

    public String getApiURL() {
        return apiURL;
    }

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }

    private String apiURL;

    public String getHomePageBaseurl() {
        return homePageBaseurl;
    }

    private String homePageBaseurl;

    public String getExcelPath() {
        return excelPath;
    }

    private String excelPath;

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Configuration(Properties properties) {
        this.url = properties.getProperty("url");
        this.userName = properties.getProperty("userName");
        this.password = properties.getProperty("password");
        this.excelPath = properties.getProperty("excelPath");
        this.homePageBaseurl = properties.getProperty("homePageBaseurl");
        this.apiURL = properties.getProperty("apiUrl");
    }

}
