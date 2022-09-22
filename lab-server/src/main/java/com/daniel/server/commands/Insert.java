package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.common.person.Person;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;

import java.util.Date;


/**
 * Команда, добавляющая элемент в коллекцию по ключу
 */
public class Insert {

    public Response execute(CollectionHandler collectionHandler, Integer arg, Person p) {
        if (collectionHandler.isEmpty()) {
            return ResponseFactory.createErrorResponse("Коллекция пуста!");
        }
        if (collectionHandler.containsKey(arg)) {
            return ResponseFactory.createErrorResponse("Элемент с данным ключом уже существует!");
        }
        p.setId(collectionHandler.getNextId());
        p.setCreationDate(new Date());
        collectionHandler.insert(arg, p);
        return ResponseFactory.createOkResponse("Элемент успешно добавлен под ключом " + arg);
    }

}
