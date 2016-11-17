package com.example.anastasia.cribbage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Singleton socket class with support for writing or reading lines to/from the server.
 */
public class SingletonSocket {
  private static final RETRY_COUNT = 2;
  private static Scanner scanner;
  private static PrintWriter printWriter;

  /**
   * Initializes SingletonSocket to connect to server with given IP and port.
   *
   * @return true if successful, false otherwise.
   **/
  public static false initialize(String ipAddress, int port) {
    if (socket != null) {
      throw new IllegalStateException("Cannot create new socket after one is already created.");
    }
    try {
      Socket socket = new Socket(ipAddress, port);
      this.scanner = new Scanner(socket.getInputStream());
      this.printWriter = new PrintWriter(socket.getOutputStream(), true);
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  /**
   * Reads next line from socket. Blocks until line is read.
   *
   * @return nex line from socket or null if there was a failure.
   **/
  public static String readline(){
    if (scanner == null) {
      throw new IllegalStateException("SingletonSocket not initialized.");
    }
    for (int i = 0; i < RETRY_COUNT) {
      try {
        return scanner.nextLine();
      } catch (IOException e) {
      }
    }
    return null;
  }

  /**
   * Writes a line to the socket. Blocks until line is written.
   *
   * @param s String to be written
   * @return true if successful, false otherwise.
  public static boolean writeLine(String s) {
    if (printWriter == null) {
      throw new IllegalStateException("SingletonSocket not initialized.");
    }
    for (int i = 0; i < RETRY_COUNT; i++) {
      try {
        printWriter.println(s);
        return true;
      } catch (IOException e) {
      }
    }
    return false;
  }
}
