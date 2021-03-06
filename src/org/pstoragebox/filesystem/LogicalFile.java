package org.pstoragebox.filesystem;

import java.util.ArrayList;
import java.util.List;

public class LogicalFile {

    public LogicalFile(String myId,String rootCatalog,String fileName, int backupNum, byte[] data) {
        this.fileName = fileName;
        this.fileSize = data.length;
        this.backupNum = backupNum;
        fileBlockList = new ArrayList<>();

        blockNum = data.length / blockSize;
        if (data.length % blockSize != 0)
            blockNum++;

        for (int i = 0; i < blockNum;i++){
            byte[] tempData = new byte[blockSize];
            for (int j = 0;j<blockSize;j++){
                if (i*blockSize+j < data.length)
                    tempData[j] = data[i*blockSize+j];
                else
                    tempData[j] = -1;
            }
            String filePath = rootCatalog+myId+"_"+fileName+"_"+i;
            LogicalFileBlock tempBlock = new LogicalFileBlock(myId,filePath,tempData);
            int lastBackupNum = backupNum -1;
            while (lastBackupNum > 0){
                tempBlock.addABackup();
                lastBackupNum--;
            }
            fileBlockList.add(tempBlock);
        }
    }

    public byte[] downloadFile(){
        byte[] data = new byte[0];
        for (int i = 0; i < blockNum;i++){
            byte[] tempData = fileBlockList.get(i).downloadBlock();
            byte[] newdata = new byte[data.length+tempData.length];
            System.arraycopy(data, 0, newdata, 0, data.length);
            System.arraycopy(tempData, 0, newdata, data.length, tempData.length);
            data = newdata;
        }
        if (data.length > fileSize){
            System.arraycopy(data,0,data,0,fileSize);
        }
        return data;
    }


    public String getFileName() {
        return fileName;
    }

    public int getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(int blockNum) {
        this.blockNum = blockNum;
    }

    private String fileName;
    private int fileSize;
    private int backupNum;
    private int blockNum;
    private List<LogicalFileBlock> fileBlockList;
    private final static int blockSize = 1048576;
}
