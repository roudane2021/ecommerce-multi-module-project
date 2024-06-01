package ma.roudane.service.config;

import java.util.ResourceBundle;

public class ErrorMessages {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");


    public static String getMessage(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
