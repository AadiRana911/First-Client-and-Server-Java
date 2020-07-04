package com.aadiosity.escobar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private ArrayList<ClientHandler> clients;
    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) throws IOException{
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        this.clients = clients;
    }


    @Override
    public void run() {
        try {
            System.out.println(socket.getInetAddress() + "Connected\n");
            outToAll("Welcome to the Chat room ^_^\n");
            while (true){
                String request = in.readLine();
                outToAll(request);
            }
        }catch (IOException e){
            System.out.println("Exception Raised " + e.getMessage());
        }finally {
            try {
                socket.close();
                in.close();
                out.close();
            }catch (IOException e){
                System.out.println("OOPS " + e.getMessage());
            }
        }
    }

    private void outToAll(String msg) {
        for (ClientHandler aClient: clients) {
            aClient.out.println(msg);
        }
    }
}
