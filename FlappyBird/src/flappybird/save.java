/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Matouš
 */
public class save {
    Path configPath;
    int hs = 0;
    Scanner scanner;
    
    public save() throws Exception{
        configPath = Paths.get("src\\flappybird\\config.txt");
        scanner = new Scanner(configPath); 
    }
    
    public int load(){
        
        List<Integer> integers = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                 integers.add(scanner.nextInt());
            }
            else {
            scanner.next();
        }
            hs = integers.get(0);
        }
        if (hs >0) return hs;
        return -1;
    }
    public void write(int cur) throws Exception{
        PrintWriter pw = new PrintWriter(configPath.toString());
        pw.println(cur);
        pw.close();
    }
    
}
