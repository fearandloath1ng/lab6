package com.daniel.server.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class ServerConnectionManager {
    private DatagramChannel channel;

    public DatagramChannel openConnection(int port) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        channel = DatagramChannel.open();
        channel.bind(socketAddress);
        return channel;
    }

    public void closeConnection() throws IOException{
        channel.socket().close();
        channel.close();
    }
}
