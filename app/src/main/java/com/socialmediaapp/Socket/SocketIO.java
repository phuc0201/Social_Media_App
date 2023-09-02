package com.socialmediaapp.Socket;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketIO {
    public static Socket join;
    public static String IPv4 = "192.168.43.160";
    static {
        try {
            join = IO.socket("http://"+IPv4+":3000");
        } catch (URISyntaxException ignored) {
        }
    }
}
