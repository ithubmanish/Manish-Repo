package Utilities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Logger log = (Logger) LoggerFactory.getLogger(ConfigReader.class);

    public static Properties loadPropertyFile(String pathFile) {
        File file = new File(pathFile);
        Properties prop = new Properties();

        FileInputStream fileInput = null;

        try {
            fileInput = new FileInputStream(file);
            prop.load(fileInput);
        } catch (Exception e) {
            log.error("Not able to load property file");
        }

        return prop;
    }

    public static String getValue(String key) {
        Properties prop = loadPropertyFile("src/main/resources/Config/config.properties");
        return prop.getProperty(key);
}
}
