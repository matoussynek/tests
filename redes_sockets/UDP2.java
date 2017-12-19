import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 * Write a description of class UDPServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UDP2
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class UDPServer
     */
    public UDP2()
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
        byte[] input = new byte[512];
        byte[] output = new byte[512];
        boolean repeat = true;
        while(repeat)
        {
            DatagramPacket receivePacket = new DatagramPacket(input,input.length);
            ds.receive(receivePacket);
            int port = receivePacket.getPort();
            String sentence = new String(receivePacket.getData());
            InetAddress address = receivePacket.getAddress();
            String responce = new String("Message recieved");
            if (sentence.equals("FIN"))
            {
                repeat = false;
                continue;
            }
            output = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(output,output.length,address,port);
            ds.send(sendPacket);
        }
        ds.close();
    }
}
