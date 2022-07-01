package iot.lviv.ua.nazar.storages;

import iot.lviv.ua.nazar.models.Hive;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HiveStorage {
    public static void writeToFile(List<Hive> hiveList) throws IOException {

        try {
            for (Hive hive : hiveList) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_'at'_HH_mm_ss");
                Date date = new Date(System.currentTimeMillis());
                FileWriter fileWriter = new FileWriter("hive" + formatter.format(date) + ".csv");
                fileWriter.write("hiveId" + System.lineSeparator());
                fileWriter.write(hive.toCSV());
                Thread.sleep(1000); //pause
                fileWriter.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}

