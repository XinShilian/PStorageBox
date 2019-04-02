import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
// import java.util.ArrayList;

class Fun {
    public String ls(String path) {
        // TODO
        // for (String i:arr){
        // System.out.println("i = " + i);
        // }
        traverseFile(path);

        return null;
    }

    // 打印当前文件夹所含的文件
    static void traverseFile(String path) {
        File f = new File(path);
        File[] files = f.listFiles();

        for (File fi : files) {
            // 如果是目录
            if (fi.isDirectory()) {
                System.out.println(fi);

                traverseFile(fi.getAbsolutePath());
            } else {
                // 如果是文件
                if (!fi.isHidden()) {
                    System.out.println(fi);
                }
            }
        }
    }

    public void upload() {
        JFileChooser jf = new JFileChooser();
        jf.setSelectedFile(new File("c:\\filename"));
        int value = jf.showSaveDialog(null);
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setFileHidingEnabled(false);
        if (value == JFileChooser.APPROVE_OPTION) { // 判断窗口是否打开
            File getPath = jf.getSelectedFile();
            System.out.println(getPath);

            // TODO
        } else {
            // TODO
            try {
                throw new Exception("Failed to open file directory!");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("Successful file upload");
    }

    public void download() {
        JFileChooser jf = new JFileChooser();
        jf.setSelectedFile(new File("c:\\filename"));
        int value = jf.showSaveDialog(null);
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setFileHidingEnabled(false);
        if (value == JFileChooser.APPROVE_OPTION) { // 判断窗口是否打开
            File getPath = jf.getSelectedFile();
            System.out.println(getPath);
            // TODO
            try{
                File file = new File("F:\\Courseware\\SoftwareDesign\\P2P\\Main.java");
                FileInputStream   fis = new FileInputStream(file);
                FileOutputStream  fos = new FileOutputStream(getPath);
                ProgressBarThread pbt = new ProgressBarThread((int)file.length());//创建进度条
                new Thread(pbt).start(); //开启线程，刷新进度条
                byte[] buf = new byte[1024];
                int size = 0;
                while((size = fis.read(buf))> -1){
                    fos.write(buf, 0, size);
                    pbt.updateProgress(size); //写完一次更新进度条
                }
                pbt.finish(); //文件读取完成关闭进度条
                fos.flush();
                fos.close();
            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }


        } else {
            // TODO
            try {
                throw new Exception("Failed to open file directory!");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("File download Successful");
    }
}