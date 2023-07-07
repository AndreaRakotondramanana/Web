package etu1831.framework;
public class FileUpload {
    String fileName;
    String path;
    byte[] data;
    public FileUpload(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }
    public FileUpload(String fileName, String path, byte[] data) {
        this.fileName = fileName;
        this.path = path;
        this.data = data;
    }
    public FileUpload() {
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
}

