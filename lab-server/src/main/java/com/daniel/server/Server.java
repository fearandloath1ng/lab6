package com.daniel.server;

import com.daniel.server.managers.CollectionManager;
import com.daniel.server.util.ServerCommandsHandler;

import java.rmi.ServerError;

public final class Server {

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("В аргументы необходимо указать порт сервера");
        }
        try {
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Порт должен быть числом");
            return;
        }
        CollectionManager collectionManager = new CollectionManager(System.getenv("Collection"), args[0]);
        collectionManager.run();
    }
}
