import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
*  UDP Client Program
*  Connects to a UDP Server
*  Receives a line of input from the keyboard and sends it to the server
*  Receives a response from the server and displays it.
*
*  @author: Nima Nakhjavani
*     email: nakhjava@chapman.edu
*     date: 2/27/2020
*  @version: 3.1
*
*/


class MyUdpClient {
  public static void main(String[] args) throws Exception {

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

    DatagramSocket clientSocket = new DatagramSocket();

    InetAddress ipAddress = InetAddress.getByName("icd.chapman.edu");
    
       
    byte[] sendData = new byte[1024];
    byte[] receiveData = new byte[1024];
    
    String sentence = "";
    String modifiedSentence = "";
    while (true) {
      System.out.println("Type a Sentence");
      sentence = inFromUser.readLine();
      if (sentence.equals("Goodbye")) {
        System.out.println(sentence);
        break;
      }          
    
      sendData = sentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9876);

      clientSocket.send(sendPacket);

      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

      clientSocket.receive(receivePacket);

      modifiedSentence = new String(receivePacket.getData());

      System.out.println("FROM SERVER:" + modifiedSentence);
    }

    clientSocket.close();
  }
}

