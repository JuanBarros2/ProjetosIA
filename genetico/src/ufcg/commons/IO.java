package ufcg.commons;

import java.io.*;

public class IO<T> {
    private String filePath;

    public IO(String filePath) {
        this.filePath = filePath;
    }

    public IO(){
        filePath = "/home/juan/robo.txt";
    }

    public boolean write(T t) {
        ObjectOutputStream objectOut = null;
        try {
            objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)));
            objectOut.writeObject(t);
            objectOut.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public T read() {
        ObjectOutputStream objectOut = null;
        try {
            ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)));
            T obj = (T) objectIn.readObject();
            objectIn.close();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

}