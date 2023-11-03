package org.utm.lab2;
import java.util.Date;

public interface MyFile {

    String getFileName();
    String getInfo();
    boolean hasChanged(Date snapshotTime);
//    void update();

}

