package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;

/**
 * Команда удаляет все элементы коллекции, чей ключ превышает заданный пользователем
 */
public class RemoveGreaterKey {

    public Response execute(CollectionHandler collectionHandler, Integer key) {
        if (collectionHandler.isEmpty()) {
            return ResponseFactory.createErrorResponse("Коллекция пуста!");
        }

        int deleted = collectionHandler.removeGreater(key);
        return ResponseFactory.createOkResponse("Удалено " + deleted + " элементов, превышающих заданный");
    }

}
