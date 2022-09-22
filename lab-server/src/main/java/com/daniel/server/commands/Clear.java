package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;


/**
 * Команда очищения коллекции
 */
public class Clear {

    public Response execute(CollectionHandler collectionHandler) {
        collectionHandler.clear();
        return ResponseFactory.createOkResponse("Коллекция очищена");
    }

}
