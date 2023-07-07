package etu001982.framework.UploadFile;
public class UploadFile {
    private String name;
    private Byte[] bytes;
    public UploadFile(String name,Byte[] bytes){
        setname(name);
        setbytes(bytes);
    }
    public UploadFile(){}
    public String getName() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public Byte[] getBytes() {
        return bytes;
    }
    public void setbytes(Byte[] bytes) {
        this.bytes = bytes;
    }
}
