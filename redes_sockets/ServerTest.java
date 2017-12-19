import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 * Write a description of class Server here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ServerTest
{
    // instance variables - replace the example below with your own
    private Socket sock;
    
    public ServerTest(Socket temp)
    {
        sock = temp;
    }
    /**
     * Constructor for objects of class Server
     */
    public void run() throws IOException
    {
        Scanner scan = new Scanner(sock.getInputStream());
        PrintWriter printer = new PrintWriter(sock.getOutputStream(),true);
        String str = new String();
        str = scan.nextLine();
        boolean repeat = true;
        while(repeat)
        {
            printer.println(str);
            str = scan.nextLine();
            if(str.equals("FIN"))
            {
                repeat = false;
            }
        }
        printer.println("Closing connestion...");
        sock.close();
        return;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void main() throws IOException
    {
        ServerSocket ss = new ServerSocket(8888);
        Socket s = ss.accept();
        System.out.println("User connected");
        ServerTest serv = new ServerTest(s);
        serv.run();
        ss.close();
        return;
    }
}
