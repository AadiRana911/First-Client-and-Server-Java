package com.aadiosity.escobar;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

public class ServerConnection extends Thread{
    private Scanner scan = new Scanner(System.in);
    private Socket server;
    private BufferedReader in;
    private PrintWriter out;
//    private Image displayPicture = getImage("H:\\5th Semester\\Data Communication and Networks\\Java Network Programming\\First Client and Server\\Client\\src\\display picture.jpg");
    private String name;

    public ServerConnection(Socket server) throws IOException {
        this.server = server;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(), true);
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
//    private static Image getImage(String address){
//        URL url = null;
//        try {
//            url = new URL(address);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        if (url != null){
//            ImageIcon icon = new ImageIcon(url);
//            return icon.getImage();
//        }else {
//            return null;
//        }
//    }

    public String getname() {
        return name;
    }
}
