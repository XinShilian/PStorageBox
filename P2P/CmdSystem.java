import java.util.Scanner;

public class CmdSystem {
    public static void  Run() {
        System.out.println("Please enter the command");
        Scanner scan = new Scanner(System.in);
        Fun mf = new Fun();
        
        while (scan.hasNext()) {
            String input = scan.next();
        
            System.out.println("input = " + input);
            if (input.equals("ls")) {
                System.out.println("please input the path");
                String path = scan.next();
                mf.ls(path);
            }
            else if (input.equals("upload")){
                // System.out.println("Please enter the path where the file is located");
                // String path = scan.next();
                mf.upload();
            }
            else if (input.equals("download")){
                // System.out.println("Please input the path to save the file");
                // String path = scan.next();
                mf.download();
            }
            else if (input.equals("exit")){
                break;
            }
            else {
                System.out.println("Invalid input");
            }
        }
        scan.close();
    }
}



