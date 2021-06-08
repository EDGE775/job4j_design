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
                    String str;
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.flush();
                    while (in.ready() && (str = in.readLine()) != null && !str.isEmpty()) {
                        System.out.println(str);
                        if (str.startsWith("GET")) {
                            String parameter = str.split(" ")[1].split("=")[1];
                            if (parameter.equals("Bye") || parameter.equals("Exit")) {
                                server.close();
                            } else if (parameter.equals("Hello")) {
                                out.write("Hello, dear friend.\n".getBytes());
                                out.flush();
                            } else {
                                out.write(String.format("%s%n", parameter).getBytes());
                                out.flush();
                            }
                        }
                    }
                }
            }
        }
    }
}