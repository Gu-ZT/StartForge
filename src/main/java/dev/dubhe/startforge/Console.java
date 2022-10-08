package dev.dubhe.startforge;

import java.io.*;
import java.util.Scanner;

public class Console extends Thread {
    public final Process process;

    public Console(Process process) {
        this.process = process;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try (
                OutputStream outputStream = process.getOutputStream();
                OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter writer = new BufferedWriter(streamWriter)
        ) {
            while (process.isAlive()) {
                String command = scanner.next();
                writer.write(format(command));
                writer.flush();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public String format(String command) {
        command = command + '\n';
        return command.startsWith("/") ? command.substring(1) : command;
    }
}
