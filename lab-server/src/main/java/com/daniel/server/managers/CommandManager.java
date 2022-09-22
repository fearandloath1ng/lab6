package com.daniel.server.managers;

import com.daniel.common.network.Request;
import com.daniel.common.network.Response;
import com.daniel.common.person.Person;
import com.daniel.common.util.FileManager;
import com.daniel.common.util.StringParser;
import com.daniel.server.commands.Help;
import com.daniel.server.commands.Clear;
import com.daniel.server.commands.Info;
import com.daniel.server.commands.Insert;
import com.daniel.server.commands.MaxByCoordinates;
import com.daniel.server.commands.PrintDescending;
import com.daniel.server.commands.RemoveGreater;
import com.daniel.server.commands.RemoveGreaterKey;
import com.daniel.server.commands.RemoveKey;
import com.daniel.server.commands.ReplaceIfLower;
import com.daniel.server.commands.Show;
import com.daniel.server.commands.SumOfHeight;
import com.daniel.server.commands.Update;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CommandManager {

    private final CollectionHandler collectionHandler;
    private final StringParser parser = new StringParser();
    private final FileManager fileManager;

    public CommandManager(CollectionHandler collectionHandler, FileManager fileManager) {
        this.collectionHandler = collectionHandler;
        this.fileManager = fileManager;
    }

    /**
     * Метод выполняет команду, введенную пользователем
     * @param request запрос
     */
    public Response execute(Request request) {
        switch(request.getType()) {
            case SIMPLE_REQUEST:
                return simpleExecute(request.getCommand());
            case ARG_REQUEST:
                return argExecute(request.getCommand(), request.getArg());
            case OBJECT_REQUEST:
                return objectExecute(request.getCommand(), request.getPerson());
            case ARG_OBJECT_REQUEST:
                return objectArgExecute(request.getCommand(), request.getArg(), request.getPerson());
            default:
                return ResponseFactory.createErrorResponse("Некорректный запрос");
        }
    }

    /**
     * Выполнение команд без аргументов
     * @param command команда
     */
    private Response simpleExecute(String command) {
        switch (command) {
            case "help":
                return new Help().execute();
            case "clear":
                return new Clear().execute(collectionHandler);
            case "info":
                return new Info().execute(collectionHandler);
            case "max_by_coordinates":
                return new MaxByCoordinates().execute(collectionHandler);
            case "show":
                return new Show().execute(collectionHandler);
            case "print_descending":
                return new PrintDescending().execute(collectionHandler);
            case "sum_of_height":
                return new SumOfHeight().execute(collectionHandler);
            default:
                return ResponseFactory.createErrorResponse("Неверная команда!");
        }
    }

    /**
     * Выполнение команд, требующих аргумента
     * @param command команда
     * @param arg аргумент в строковом виде
     */
    private Response argExecute(String command, Integer arg) {
        switch (command) {
            case "remove_greater_key":
                return new RemoveGreaterKey().execute(collectionHandler, arg);
            case "remove_key":
                return new RemoveKey().execute(collectionHandler, arg);
            default:
                return ResponseFactory.createErrorResponse("Неверная команда!");
        }
    }

    private Response objectExecute(String command, Person p) {
        switch (command) {
            case "remove_greater":
                return new RemoveGreater().execute(collectionHandler, p);
            default:
                return ResponseFactory.createErrorResponse("Неверная команда!");
        }
    }

    private Response objectArgExecute(String command, Integer arg, Person p) {
        switch (command) {
            case "insert":
                return new Insert().execute(collectionHandler, arg, p);
            case "replace_if_lower":
                return new ReplaceIfLower().execute(collectionHandler, arg, p);
            case "update":
                return new Update().execute(collectionHandler, arg, p);
            default:
                return ResponseFactory.createErrorResponse("Неверная команда!");
        }
    }

    /**
     * Команда сохранения коллекции
     */
    public String save() {
        if (collectionHandler.isEmpty()) {
            return "Коллекция пуста!";
        }
        try {
            fileManager.toFile(collectionHandler.getMap());
            return "Коллекция успешно сохранена в файл!";
        } catch (SecurityException | FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
            return "";
        }
    }

}
