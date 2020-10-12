package com.gabrielhd.Stormbreaker.helper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PingHelper
{
    public static void testPing(InetSocketAddress address, String host, int port) {
        try {
            Socket socket = new Socket();
            System.out.println("Connecting...");
            socket.connect(address, 3000);
            System.out.println("Done!");
            System.out.println("Making streams...");
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());
            System.out.println("Done!");
            System.out.println("Attempting handshake... " + address.toString());
            byte[] handshakeMessage = VarHelper.createHandshakeMessage(host, port);
            VarHelper.writeVarInt(output, handshakeMessage.length);
            output.write(handshakeMessage);
            output.writeByte(1);
            output.writeByte(0);
            int size = VarHelper.readVarInt(input);
            int packetId = VarHelper.readVarInt(input);
            if (packetId == -1) {
                throw new IOException("Premature end of stream.");
            }
            if (packetId != 0) {
                throw new IOException("Invalid packetID");
            }
            int length = VarHelper.readVarInt(input);
            if (length == -1) {
                throw new IOException("Premature end of stream.");
            }
            if (length == 0) {
                throw new IOException("Invalid string length.");
            }
            byte[] in = new byte[length];
            input.readFully(in);
            String json = new String(in);
            long now = System.currentTimeMillis();
            output.writeByte(9);
            output.writeByte(1);
            output.writeLong(now);
            VarHelper.readVarInt(input);
            System.out.println("length:" + length + ", size: " + size);
            packetId = VarHelper.readVarInt(input);
            if (packetId == -1) {
                throw new IOException("Premature end of stream.");
            }
            if (packetId != 1) {
                throw new IOException("Invalid packetID");
            }
            long pingtime = input.readLong();
            System.out.println(json);
            System.out.println("Done!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
