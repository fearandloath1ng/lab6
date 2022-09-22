package com.daniel.client.connection;

import com.daniel.common.network.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class RequestSender {
    private final SocketAddress address;
    private final DatagramSocket socket;

    public RequestSender(SocketAddress address, DatagramSocket socket){
        this.address = address;
        this.socket = socket;
    }

    public void sendRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        ObjectOutput objOutput = new ObjectOutputStream(byteArrayOutputStream);
        objOutput.writeObject(request);
        DatagramPacket requestPacket = new DatagramPacket(byteArrayOutputStream.toByteArray(), byteArrayOutputStream.size(), address);
        socket.send(requestPacket);
        byteArrayOutputStream.close();
    }
}
