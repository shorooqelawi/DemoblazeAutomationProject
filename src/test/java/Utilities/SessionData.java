package Utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class SessionData {

    private static final String FILE_PATH = "session.properties";

    public static void setCredentials(String username, String password) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write("username=" + username + "\n");
            writer.write("password=" + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUsername() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Properties props = new Properties();
            props.load(reader);
            return props.getProperty("username");
        } catch (IOException e) {
            return null;
        }
    }

    public static String getPassword() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Properties props = new Properties();
            props.load(reader);
            return props.getProperty("password");
        } catch (IOException e) {
            return null;
        }
    }
}
