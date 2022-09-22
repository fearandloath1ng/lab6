package com.daniel.client;

import com.daniel.client.connection.ClientConnectionManager;
import com.daniel.client.managers.ClientCommandManager;
import com.daniel.common.util.FileManager;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.Scanner;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("В аргументы необходимо передать адресс и порт сервера");
        }
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager();
        ClientConnectionManager connectionManager = new ClientConnectionManager();
        DatagramSocket socket;
        try {
            socket = connectionManager.openConnection(args[0], Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
            System.err.println("Порт сервера должен быть числом");
            return;
        } catch (IOException e) {
            System.err.println("Не удалось установить соединение");
            return;
        }

        ClientCommandManager commandManager = new ClientCommandManager(socket, connectionManager.getSocketAddress(), fileManager);
        while (true) {
            System.out.println("Введите команду или help для вывода справки:");
            if (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!"".equals(s)) {
                    String[] line = s.trim().split(" ");
                    try {
                        commandManager.execute(line);
                    } catch (IOException e) {
                        System.err.println("Ошибка при отправке запроса");
                    }
                }
            }
        }
    }
}
