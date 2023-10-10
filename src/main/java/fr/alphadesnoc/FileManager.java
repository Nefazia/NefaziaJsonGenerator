package fr.alphadesnoc;

import java.io.File;

public class FileManager {

    private String folderPath;
    private String blockStatesPath;
    private String modelBlockPath;
    private String modelItemPath;

    public FileManager(){

        blockStatesPath = getFolderPath() + File.separator + "blockstates";
        modelBlockPath = getFolderPath() + File.separator + "models" + File.separator + "block";
        modelItemPath = getFolderPath() + File.separator + "models" + File.separator + "item";

    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setBlockStatesPath(String folderPath) {
        blockStatesPath = folderPath + File.separator + "blockstates";
    }

    public void setModelBlockPath(String folderPath) {
        modelBlockPath = folderPath + File.separator + "models" + File.separator + "block";
    }

    public void setModelItemPath(String folderPath) {
        modelItemPath = folderPath + File.separator + "models" + File.separator + "item";
    }

    public String getBlockStatesPath() {
        return blockStatesPath;
    }

    public String getModelBlockPath() {
        return modelBlockPath;
    }

    public String getModelItemPath() {
        return modelItemPath;
    }
}
