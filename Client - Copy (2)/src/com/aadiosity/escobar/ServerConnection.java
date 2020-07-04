package com.aadiosity.escobar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnection extends Thread{
    private Scanner scan = new Scanner(System.in);
    private BufferedReader in;
    private String name;
    public ServerConnection(Socket server) throws IOException {
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        System.out.print("Enter your name: ");
        this.name = scan.nextLine();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String serverResponse = in.readLine();
                if (serverResponse == null) break;
                System.out.println(serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getname() {
        return name;
    }
}
