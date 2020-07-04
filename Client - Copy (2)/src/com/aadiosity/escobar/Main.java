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

public class Main {

    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost", 5000)){
            ServerConnection serverConn = new ServerConnection(socket);
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);
            Scanner scan = new Scanner(System.in);
            String echoString;
            new Thread(serverConn).start();
//            thread.start();
            while (true){
                Thread.sleep(100);
                System.out.print("Enter String to be echoed: ");
                echoString = scan.nextLine();
                if (!echoString.equals(""))
                    stringToEcho.println(serverConn.getname() + ": " + echoString);
                if (echoString.equalsIgnoreCase("exit")) break;
            }
        }catch (IOException e){
            System.out.println("Client Error: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
