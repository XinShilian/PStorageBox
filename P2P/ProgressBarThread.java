import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


// ??????
public class ProgressBarThread implements Runnable{

    private ArrayList<Integer>proList = new ArrayList<Integer>();
    private int progress;
    private int totalSize;
    private boolean run = true;
    public ProgressBarThread(int totalsize){
        this.totalSize = totalsize;
        // TODO ??????????
    }

    public void updateProgress(int progress){
        synchronized(this.proList){
            if(this.run){
                this.proList.add(progress);
                this.proList.notify();
            }
        }
    }

    public void finish(){
        this.run = false;
    }

    @Override
    public void run() {
        synchronized(this.proList){
            try{
                while(this.run){
                    if(this.proList.size() == 0){
                        this.proList.wait();
                    }
                    synchronized(proList){
                        this.progress += this.proList.remove(0);
                        // TODO ?????????
                        System.err.println(
                            "???????"+ (this.progress/this.totalSize*100)+"%"
                            );
                    }
                }
                System.err.println("???????");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    

    // public static void main(String[] args){
    //     try{
    //         File file = new File("F:\\Courseware\\SoftwareDesign\\P2P\\Main.java");
    //         FileInputStream   fis = new FileInputStream(file);
    //         FileOutputStream  fos = new FileOutputStream("F:\\Courseware");
    //         ProgressBarThread pbt = new ProgressBarThread((int)file.length());//??????????
    //         new Thread(pbt).start(); //????????????????
    //         byte[] buf = new byte[1024];
    //         int size = 0;
    //         while((size = fis.read(buf))> -1){
    //             fos.write(buf, 0, size);
    //             pbt.updateProgress(size); //§Õ????¦È????????
    //         }
    //         pbt.finish(); //????????????????
    //         fos.flush();
    //         fos.close();
    //     } catch(FileNotFoundException e){
    //         e.printStackTrace();
    //     } catch(IOException e){
    //         e.printStackTrace();
    //     } 
    // }
}