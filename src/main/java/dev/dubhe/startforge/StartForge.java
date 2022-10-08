package dev.dubhe.startforge;

import org.apache.commons.io.IOUtils;

public class StartForge {
    public static void main(String[] args) {
        Config config = new Config();
        Runtime run = Runtime.getRuntime();
        String cmd = (String) config.pro.get("cmd");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(cmd.split(" "));
            Process process = processBuilder.start();
            new Console(process).start();
            IOUtils.copy(process.getInputStream(), System.out);
            IOUtils.copy(process.getErrorStream(), System.err);
            if (process.waitFor() != 0) {
                if (process.exitValue() == 1)
                    System.err.println("命令执行失败!");
            } else {
                System.exit(0);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
