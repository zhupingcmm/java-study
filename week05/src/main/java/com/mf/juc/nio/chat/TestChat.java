package com.mf.juc.nio.chat;

import java.io.IOException;
import java.util.Scanner;

public class TestChat {
    public static void main(String[] args) throws IOException {
        ChatClient chatClient = new ChatClient();

        new Thread(() -> {
            while (true) {
                try {
                    chatClient.receiveMsg();
                    Thread.sleep(200);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String msg = scanner.nextLine();
            chatClient.sendMsg(msg);
        }
    }
}
