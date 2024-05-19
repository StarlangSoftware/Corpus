package Corpus;

import java.io.File;

public class FileDescription {
    private final String path;
    private final String extension;
    private int index;

    /**
     * Constructor for the FileDescription object. FileDescription object is used to store sentence or tree file names
     * in a format of path/index.extension such as 'trees/0123.train' or 'sentences/0002.test'. At most 10000 file names
     * can be stored for an extension.
     * @param path Path of the file
     * @param rawFileName Raw file name of the string without path name, including the index of the file and the
     *                    extension. For example 0023.train, 3456.test, 0125.dev, 0000.train etc.
     */
    public FileDescription(String path, String rawFileName){
        extension = rawFileName.substring(rawFileName.lastIndexOf('.') + 1);
        index = Integer.parseInt(rawFileName.substring(0, rawFileName.lastIndexOf('.')));
        this.path = path;
    }

    /**
     * Another constructor for the FileDescription object. FileDescription object is used to store sentence or tree
     * file names in a format of path/index.extension such as 'trees/0123.train' or 'sentences/0002.test'. At most 10000
     * file names can be stored for an extension.
     * @param path Path of the file
     * @param extension Extension of the file such as train, test, dev etc.
     * @param index Index of the file, should be larger than or equal to 0 and less than 10000. 123, 0, 9999, etc.
     */
    public FileDescription(String path, String extension, int index){
        this.path = path;
        this.extension = extension;
        this.index = index;
    }

    /**
     * Accessor for the path attribute.
     * @return Path
     */
    public String getPath(){
        return path;
    }

    /**
     * Accessor for the index attribute.
     * @return Index
     */
    public int getIndex(){
        return index;
    }

    /**
     * Accessor for the extension attribute.
     * @return Extension
     */
    public String getExtension(){
        return extension;
    }

    /**
     * Returns the filename with the original path, such as trees/1234.train
     * @return The filename with the original path
     */
    public String getFileName(){
        return getFileName(path);
    }

    /**
     * Returns the filename with extension replaced with the given extension.
     * @param extension New extension
     * @return The filename with extension replaced with the given extension.
     */
    public String getFileNameWithExtension(String extension){
        return getFileName(path, extension);
    }

    /**
     * Returns the filename with path replaced with the given path
     * @param thisPath New path
     * @return The filename with path replaced with the given path
     */
    public String getFileName(String thisPath){
        return getFileName(thisPath, index);
    }

    /**
     * Returns the filename with path and extensions are replaced with the given path and extension.
     * @param thisPath New path
     * @param extension New extension
     * @return The filename with path and extensions are replaced with the given path and extension.
     */
    public String getFileName(String thisPath, String extension){
        return getFileName(thisPath, index, extension);
    }

    /**
     * Returns the filename with path and index are replaced with the given path and index.
     * @param thisPath New path
     * @param thisIndex New Index
     * @return The filename with path and index are replaced with the given path and index.
     */
    public String getFileName(String thisPath, int thisIndex){
        return thisPath + "/" + String.format("%04d", thisIndex) + "." + extension;
    }

    /**
     * Returns the filename with path, index, and extension are replaced with the given path, index, and extension.
     * @param thisPath New path
     * @param thisIndex New Index
     * @param extension New extension
     * @return The filename with path, index, and extension are replaced with the given path, index, and extension.
     */
    public String getFileName(String thisPath, int thisIndex, String extension){
        return thisPath + "/" + String.format("%04d", thisIndex) + "." + extension;
    }

    /**
     * Returns only the filename without path as 'index.extension'.
     * @return File name without path as 'index.extension'.
     */
    public String getRawFileName(){
        return String.format("%04d", index) + "." + extension;
    }

    /**
     * Increments index by count
     * @param count Count to be incremented
     */
    public void addToIndex(int count){
        index += count;
    }

    /**
     * Checks if the next file (found by changing the path and adding count to the index) exists or not. Returns true
     * if it exists, false otherwise.
     * @param thisPath New path
     * @param count Count to be incremented.
     * @return Returns true, if the next file (found by changing the path and adding count to the index) exists,
     * false otherwise.
     */
    public boolean nextFileExists(String thisPath, int count){
        File file = new File(getFileName(thisPath, index + count));
        return file.exists();
    }

    /**
     * Checks if the next file (found by adding count to the index) exists or not. Returns true  if it exists, false
     * otherwise.
     * @param count Count to be incremented.
     * @return Returns true, if the next file (found by adding count to the index) exists, false otherwise.
     */
    public boolean nextFileExists(int count){
        return nextFileExists(path, count);
    }

    /**
     * Checks if the previous file (found by changing the path and subtracting count from the index) exists or not.
     * Returns true  if it exists, false otherwise.
     * @param thisPath New path
     * @param count Count to be decremented.
     * @return Returns true, if the previous file (found by changing the path and subtracting count to the index)
     * exists, false otherwise.
     */
    public boolean previousFileExists(String thisPath, int count){
        File file = new File(getFileName(thisPath, index - count));
        return file.exists();
    }

    /**
     * Checks if the previous file (found by subtracting count from the index) exists or not.
     * Returns true  if it exists, false otherwise.
     * @param count Count to be decremented.
     * @return Returns true, if the previous file (found by subtracting count to the index) exists, false otherwise.
     */
    public boolean previousFileExists(int count){
        return previousFileExists(path, count);
    }

}
