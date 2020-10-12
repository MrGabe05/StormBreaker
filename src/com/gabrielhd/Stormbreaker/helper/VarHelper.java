package com.gabrielhd.Stormbreaker.helper;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class VarHelper
{
    public static byte[] createHandshakeMessage(String host, int port) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream handshake = new DataOutputStream(buffer);
        handshake.writeByte(0);
        handshake.write(1000);
        writeVarInt(handshake, 47);
        writeString(handshake, host, StandardCharsets.UTF_8);
        handshake.writeShort(port);
        writeVarInt(handshake, 1);
        return buffer.toByteArray();
    }

    public static String getRandomString(int bit) {
        byte[] array = new byte[bit];
        new Random().nextBytes(array);

        return new String(array, StandardCharsets.UTF_8);
    }
    
    public static void writeString(DataOutputStream out, String string, Charset charset) throws IOException {
        byte[] bytes = string.getBytes(charset);
        writeVarInt(out, bytes.length);
        out.write(bytes);
    }
    
    public static void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
        while ((paramInt & 0xFFFFFF80) != 0x0) {
            out.writeByte((paramInt & 0x7F) | 0x80);
            paramInt >>>= 7;
        }
        out.writeByte(paramInt);
    }
    
    public static void writeVarInt2(DataOutputStream out, int value) throws IOException {
        do {
            byte temp = (byte)(value & 0x7F);
            value >>>= 7;
            if (value != 0) {
                temp |= (byte)128;
            }
            out.writeByte(temp);
        } while (value != 0);
    }
    
    public static int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        int k;
        do {
            k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) {
                throw new RuntimeException("VarInt too big");
            }
        } while ((k & 0x80) == 0x80);
        return i;
    }
}
