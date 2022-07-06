package iot.lviv.ua.nazar.storage;

import java.io.IOException;

public interface StorageData {
    boolean saveToFile() throws IOException;

    boolean getFromFile();
}
