package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null
                            && !str.isEmpty(); str = in.readLine()) {
                        String[] strArgs = str.split("=");
                        System.out.println(str);
                        if (strArgs.length > 1) {
                            String[] msg = strArgs[1].split(" ");
                            if (msg[0].equals("Hello")) {
                                out.write(" Hello!".getBytes());
                                break;
                            }
                            if (msg[0].equals("Exit")) {
                                out.write(" Server is closed. Bye-Bye!".getBytes());
                                System.out.println("Server is closed.");
                                server.close();
                                break;
                            }
                                out.write(" What?".getBytes());
                            break;
                        }
                    }
                    out.flush();
                }
            }
        }
    }
}
