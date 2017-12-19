import java.io.*;
import java.net.*;

/**
 * Write a description of class ClientTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ClientTest
{
    // instance variables - replace the example below with your own
    private Socket sock;
    
    /**
     * Constructor for objects of class ClientTest
     */
    public ClientTest(Socket s)
    {
        // initialise instance variables
        sock = s;
    }
    
    public void run() throws IOException
    {
        PrintWriter  printer = new PrintWriter(sock.getOutputStream(),true);
        printer.println("Ive connected and might not fail tommorow");
        
    }
    
    public static void main() throws IOException
    {
        Socket s = new Socket("localhost",444);
        ClientTest client = new ClientTest(s);
    }
}
