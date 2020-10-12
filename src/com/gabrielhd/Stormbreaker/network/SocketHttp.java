package com.gabrielhd.Stormbreaker.network;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketHttp extends Socket
{
    public SocketHttp(String targetHost, int targetPort, SocketAddress socketAddress, int timeout) throws IOException {
        this.connect(socketAddress, timeout);
        this.setSoTimeout(timeout);
        this.connectToTarget(targetHost, targetPort);
    }
    
    private void connectToTarget(String targetHost, int targetPort) throws IOException {
        PrintStream printStream = new PrintStream(this.getOutputStream());
        printStream.println("CONNECT " + targetHost + ":" + targetPort + " HTTP/1.1");
        printStream.println("HOST: " + targetHost + ":" + targetPort);
        printStream.println();
        printStream.flush();
    }
}
