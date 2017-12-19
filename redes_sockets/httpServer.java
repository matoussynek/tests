import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 * Write a description of class httpServer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class httpServer
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class httpServer
     */
    public httpServer()
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
        ServerSocket ss = new ServerSocket(8000);
        Socket s = ss.accept();
        
        Scanner scan = new Scanner(s.getInputStream());
        PrintWriter printer = new PrintWriter(s.getOutputStream(),true);
        String line = scan.nextLine();
        int i = 0;
        while(i<1)
        {
            printer.println(line);
            line = scan.nextLine();
            if (line.equals(""))
            {
                i++;
            }
        }
        printer.println("Closing connection!");
        s.close();
        ss.close();
        return;
    }
}
