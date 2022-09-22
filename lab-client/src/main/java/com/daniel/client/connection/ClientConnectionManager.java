package com.daniel.client.connection;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ClientConnectionManager {
    private DatagramSocket socket;
    private SocketAddress address;

    public DatagramSocket openConnection(String address, int port) throws IOException {
        this.address = new InetSocketAddress(address, port);
        socket = new DatagramSocket();
        socket.setSoTimeout(10000);
        return socket;
    }

    public SocketAddress getSocketAddress(){
        return address;
    }

    public void closeConnection(){
        socket.close();
    }
}
