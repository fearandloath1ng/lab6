package com.daniel.server.connection;

import com.daniel.common.network.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class RequestReceiver {
    private final DatagramChannel channel;
    private SocketAddress address;


    public RequestReceiver(DatagramChannel channel) throws IOException {
        this.channel = channel;
    }

    public Request receiveRequest() throws IOException, ClassNotFoundException {
        ByteBuffer buf = ByteBuffer.allocate(4096);
        address = channel.receive(buf);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
        return (Request) objectInputStream.readObject();
    }

    public SocketAddress getAddress(){
        return address;
    }
}
