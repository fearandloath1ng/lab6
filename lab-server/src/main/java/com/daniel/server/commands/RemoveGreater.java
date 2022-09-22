package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.common.person.Person;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;


/**
 * Команда удаляет все элементы коллекции, превышающий заданный пользователем
 */
public class RemoveGreater {


    public Response execute(CollectionHandler collectionHandler, Person p) {
        if (collectionHandler.isEmpty()) {
            return ResponseFactory.createErrorResponse("Коллекция пуста!");
        }

        int deleted = collectionHandler.removeGreater(p);
        return ResponseFactory.createOkResponse("Удалено " + deleted + " элементов, превышающих заданный");
    }

}
