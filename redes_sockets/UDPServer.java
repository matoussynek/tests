import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 * Write a description of class UDPServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UDPServer
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class UDPServer
     */
    public UDPServer()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void main() throws Exception
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Server has started.");
        
        DatagramSocket ds = new DatagramSocket(4444);
        int port = ds.getLocalPort();
        System.out.println("Port is: "+port);
        InetAddress address = InetAddress.getByName("localhost");
        String sentence = scan.nextLine();
        
        DatagramPacket packetSend = new DatagramPacket(sentence.getBytes(),sentence.getBytes().length,address,port);
        ds.send(packetSend);
        
        byte[] buffer = new byte[512];
        DatagramPacket packetReceive = new DatagramPacket(buffer,buffer.length);
        ds.receive(packetReceive);
        
        String finalSentence = new String(packetReceive.getData(),0,packetReceive.getLength());
        System.out.println("Server replies:"+finalSentence);
        
        ds.close();
    }
}
