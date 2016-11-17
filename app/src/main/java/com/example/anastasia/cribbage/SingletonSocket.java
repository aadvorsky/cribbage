package com.example.anastasia.cribbage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Anastasia on 11/13/2016.
 */
public class SingletonSocket {
    private static SingletonSocket ourInstance = null;
    private static int mserverPort;
    private static String mipAddress;
    private static Socket socket;
    private static Scanner scanner;
    private static PrintWriter printWriter;


    public static SingletonSocket getInstance() {

        if (ourInstance == null){
            ourInstance = new SingletonSocket();

        }
        return ourInstance;
    }

    private SingletonSocket() {

    }



    public String getIpAddress() {
        return mipAddress;
    }

    public void setIpAddress(String ipAddress){
        mipAddress = ipAddress;


    }

    public int getPortNo() {
        return mserverPort;
    }

    public void setserverPort(int port){
        mserverPort = port;
    }


    public void setSocket(int port, String ipAddress){


    }
    /*
  public static void setSocket(InetAddress ipAddress, int portAddress){
      try {
          Socket socket = new Socket(ipAddress,portAddress);



      } catch (IOException e) {
          e.printStackTrace();
      }


  }
  */
    /*
    public static void Readline(Socket s){
        try {
            InputStream inBS = s.getInputStream();
            Scanner in = new Scanner(inBS);

            while (in.hasNext()){
                String message = in.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    /*

    public static void WriteLine(Socket s){
        PrintWriter out = null;
        String str = null;
        try {
            out = new PrintWriter(s.getOutputStream());
            out.println(str);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    */

}
