package Corpus;

import java.io.File;

public class FileDescription {
    private String path, extension;
    private int index;

    public FileDescription(String path, String rawFileName){
        extension = rawFileName.substring(rawFileName.lastIndexOf('.') + 1);
        index = Integer.parseInt(rawFileName.substring(0, rawFileName.lastIndexOf('.')));
        this.path = path;
    }

    public FileDescription(String path, String extension, int index){
        this.path = path;
        this.extension = extension;
        this.index = index;
    }

    public String getPath(){
        return path;
    }

    public int getIndex(){
        return index;
    }

    public String getExtension(){
        return extension;
    }

    public String getFileName(){
        return getFileName(path);
    }

    public String getFileNameWithExtension(String extension){
        return getFileName(path, extension);
    }

    public String getFileName(String thisPath){
        return getFileName(thisPath, index);
    }

    public String getFileName(String thisPath, String extension){
        return getFileName(thisPath, index, extension);
    }

    public String getFileName(String thisPath, int thisIndex){
        return thisPath + "/" + String.format("%04d", thisIndex) + "." + extension;
    }

    public String getFileName(String thisPath, int thisIndex, String extension){
        return thisPath + "/" + String.format("%04d", thisIndex) + "." + extension;
    }

    public String getRawFileName(){
        return String.format("%04d", index) + "." + extension;
    }

    public void addToIndex(int count){
        index += count;
    }

    public boolean nextFileExists(String thisPath, int count){
        File file = new File(getFileName(thisPath, index + count));
        return file.exists();
    }

    public boolean nextFileExists(int count){
        return nextFileExists(path, count);
    }

    public boolean previousFileExists(String thisPath, int count){
        File file = new File(getFileName(thisPath, index - count));
        return file.exists();
    }

    public boolean previousFileExists(int count){
        return previousFileExists(path, count);
    }

}
