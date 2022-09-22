package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;


/**
 * Команда, выводящая информацию о коллекции
 */
public class Info {

    public Response execute(CollectionHandler collectionHandler) {
        return ResponseFactory.createOkResponse("Тип коллекции: " + collectionHandler.getMap().getClass()
                + "\nРазмер коллекции: " + collectionHandler.getSize()
                + "\nДата инициализации: " + collectionHandler.getInitDate());
    }

}
