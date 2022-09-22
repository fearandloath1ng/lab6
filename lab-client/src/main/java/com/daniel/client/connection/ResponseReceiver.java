package com.daniel.client.connection;

import com.daniel.common.network.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

public class ResponseReceiver {
    private final DatagramSocket socket;

    public ResponseReceiver(DatagramSocket socket) {
        this.socket = socket;
    }

    public Response receiveResponse() throws IOException {
        ByteBuffer bytes = ByteBuffer.allocate(4096);
        DatagramPacket receivePacket = new DatagramPacket(bytes.array(), bytes.array().length);
        socket.receive(receivePacket);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes.array()));
        try {
            return (Response) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException();
        }
    }
}
