package com.example.anastasia.cribbage;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Anastasia on 11/13/2016.
 */
public class SingletonSocket {
    private static SingletonSocket ourInstance = new SingletonSocket();


    public static SingletonSocket getInstance() {


        return ourInstance;
    }

    private SingletonSocket() {
    }
  public static void setSocket(InetAddress ipAddress, int portAddress){
      try {
          Socket socket = new Socket(ipAddress,portAddress);
           InputStream inBS = socket.getInputStream();
          Scanner scanner = new Scanner(inBS);

      } catch (IOException e) {
          e.printStackTrace();
      }


  }

}
