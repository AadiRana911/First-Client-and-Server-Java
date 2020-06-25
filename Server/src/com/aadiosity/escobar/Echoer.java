package com.aadiosity.escobar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
    private Socket socket;
    public Echoer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (true){
                System.out.println("Client Connected");
                String echoString = bufferedReader.readLine();
                if (echoString.equalsIgnoreCase("exit"))
                    break;
                printWriter.println("Echo from Server: " + echoString);
            }
        }catch (IOException e){
            System.out.println("Exception Raised " + e.getMessage());
        }finally {
            try {
                socket.close();
            }catch (IOException e){
                System.out.println("OOPS " + e.getMessage());
            }
        }
    }
}
