package org.pstoragebox.tools;

import java.util.Scanner;

public class FormatSystemPrint {
    public static void printMessage(String message){
        System.out.println("PStorageBox > " + message);
    }

    public static String getNextLine(){
        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine();
        scan.close();
        return data;
    }
}
