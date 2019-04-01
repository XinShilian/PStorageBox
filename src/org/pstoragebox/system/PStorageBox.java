package org.pstoragebox.system;

import org.pstoragebox.filesystem.FileSystem;
import org.pstoragebox.tools.AutoIdGenerator;
import org.pstoragebox.tools.FormatSystemPrint;
import org.pstoragebox.tools.MyLogger;

import java.io.*;
import java.util.logging.Level;

public class PStorageBox {
    public static void run(){
        initSystem();
    }

    private static void initSystem(){
        FormatSystemPrint.printMessage("系统初始化开始...");

        String systemID = AutoIdGenerator.getId();
        if (systemID.equals("ID_GET_ERROR")){
            MyLogger.getMyLogger().log(Level.FINER,"本机识别码加载错误！");
        }
        FormatSystemPrint.printMessage("UUID初始化完成");

        String localFilePath = initLocalFilePath();
        if (localFilePath.equals("LOCAL_FILE_PATH_INTI_ERROR")){
            MyLogger.getMyLogger().log(Level.FINER,"共享文件夹目录初始化错误！");
        }
        FormatSystemPrint.printMessage("共享文件夹目录初始化完成");

        fileSystem = new FileSystem(localFilePath,systemID);
        FormatSystemPrint.printMessage("文件系统初始化完成");
    }

    private static String initLocalFilePath(){
        String filepath="./etc/localFilePathConfig.txt";
        File file=new File(filepath);
        if (!file.exists()){
            try {
                FormatSystemPrint.printMessage("第一次启动系统需要配置共享文件夹路径");
                FormatSystemPrint.printMessage("请输入共享文件夹路径：");
                FormatSystemPrint.printMessage("");
                String localFilePath = FormatSystemPrint.getNextLine();
                File dic = new File(localFilePath);
                while (!dic.isDirectory()){
                    FormatSystemPrint.printMessage("文件夹不存在，请重新输入");
                    FormatSystemPrint.printMessage("请输入共享文件夹路径：");
                    FormatSystemPrint.printMessage("");
                    localFilePath = FormatSystemPrint.getNextLine();
                    dic = new File(localFilePath);
                }
                file.createNewFile();
                FileWriter fileWriter=new FileWriter(file.getAbsoluteFile(),true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(localFilePath);
                bufferedWriter.close();
                fileWriter.close();
                return localFilePath;
            } catch (Exception e) {
                MyLogger.getMyLogger().log(Level.FINER,e.toString());
            }
        }else{
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                return bufferedReader.readLine();
            } catch (Exception e) {
                MyLogger.getMyLogger().log(Level.FINER,e.toString());
            }
        }
        return "LOCAL_FILE_PATH_INTI_ERROR";
    }


    private static FileSystem fileSystem;
}
