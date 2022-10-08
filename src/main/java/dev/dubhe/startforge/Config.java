package dev.dubhe.startforge;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public final Properties pro = new Properties();

    public Config() {
        File file = new File("start.properties");
        if (!file.isFile()) {
            try (FileWriter fw = new FileWriter(file)) {
                pro.put("cmd", "ping 127.0.0.1");
                pro.store(fw, null);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            try (FileReader fr = new FileReader(file)) {
                pro.load(fr);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
