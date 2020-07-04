package com.aadiosity.escobar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try(Socket socket = new Socket("localhost", 5000)){
	        ServerConnection serverConn = new ServerConnection(socket);
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scan = new Scanner(System.in);
            String echoString;
            new Thread(serverConn).start();
            while (true){
                scan.nextLine();
                System.out.print("Enter String to be echoed: ");
                echoString = scan.nextLine();
                stringToEcho.println(serverConn.getname() + ": " + echoString);

                if (echoString.equalsIgnoreCase("exit")) break;

            }

        }catch (IOException e){
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
