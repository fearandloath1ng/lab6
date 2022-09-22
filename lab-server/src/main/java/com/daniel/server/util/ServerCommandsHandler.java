package com.daniel.server.util;

import com.daniel.server.managers.CommandManager;

import java.io.IOException;
import java.util.Scanner;

public class ServerCommandsHandler implements Runnable{

    private CommandManager commandManager;

    public ServerCommandsHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!"".equals(s)) {
                    String[] line = s.trim().split(" ");
                    if (line[0].equals("save")) {
                        System.out.println(commandManager.save());
                    } else if (line[0].equals("exit")) {
                        System.exit(0);
                    }
                }
            }
        }
    }
}
