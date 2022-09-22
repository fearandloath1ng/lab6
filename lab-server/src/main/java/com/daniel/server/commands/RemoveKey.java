package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;


/**
 * Команда удаляет элемент коллекции по ключу
 */
public class RemoveKey {

    public Response execute(CollectionHandler collectionHandler, Integer key) {
        if (collectionHandler.isEmpty()) {
            return ResponseFactory.createErrorResponse("Коллекция пуста!");
        }
        if (collectionHandler.containsKey(key)) {
            collectionHandler.remove(key);
            return ResponseFactory.createOkResponse("Удален элемент с ключом " + key);
        } else {
            return ResponseFactory.createErrorResponse("Элемента с данным ключом не существует!");
        }
    }

}
