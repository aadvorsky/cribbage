package com.example.anastasia.cribbage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Singleton socket class with support for writing or reading lines to/from the server.
 */
public class SingletonSocket {
  private static final int RETRY_COUNT = 2;
  private static Scanner scanner;
  private static PrintWriter printWriter;

  /**
   * Initializes SingletonSocket to connect to server with given IP and port.
   *
   * @return true if successful, false otherwise.
   **/
  public static boolean initialize(String ipAddress, int port) {
    if (scanner != null) {
      throw new IllegalStateException("Cannot create new socket after one is already created.");
    }
    try {
      Socket socket = new Socket(ipAddress, port);
      scanner = new Scanner(socket.getInputStream());
      printWriter = new PrintWriter(socket.getOutputStream(), true);
      return true;
    } catch (UnknownHostException e) {
    } catch (IOException e) {
    }
    return false;
  }

  /**
   * Reads next line from socket. Blocks until line is read.
   *
   * @return next line from socket.
   **/
  public static String readline(){
    if (scanner == null) {
      throw new IllegalStateException("SingletonSocket not initialized.");
    }
    return scanner.nextLine();
  }

  /**
   * Writes a line to the socket. Blocks until line is written.
   *
   * @param s String to be written
   **/
  public static void writeLine(String s) {
    if (printWriter == null) {
      throw new IllegalStateException("SingletonSocket not initialized.");
    }
    printWriter.println(s);
  }
}
