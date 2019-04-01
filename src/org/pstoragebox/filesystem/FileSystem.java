package org.pstoragebox.filesystem;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {

    public FileSystem(String rootCatalog, String myId) {
        this.rootCatalog = rootCatalog;
        this.myId = myId;
        logicalFileList = new ArrayList<>();
    }

    public void logInNet(){

    }

    public void logOutNet(){

    }

    public void joinNet(){

    }

    public void leaveNet(){

    }

    public String[] lsCommand(){
        String[] fileName = new String[logicalFileList.size()];
        for (int i =0;i<fileName.length;i++){
            fileName[i] = logicalFileList.get(i).getFileName();
        }
        return fileName;
    }

    public void uploadCommand(){

    }

    public void downloadCommand(){

    }

    private String rootCatalog;
    private String myId;
    private List<LogicalFile> logicalFileList;
}
