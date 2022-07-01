package iot.lviv.ua.nazar.storages;

import iot.lviv.ua.nazar.models.Apiary;

import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.*;

public class ApiaryStorage {
    public static void writeToFile(List<Apiary> apiaryList) throws IOException {

        try {
            for (Apiary apiary : apiaryList) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_'at'_HH_mm_ss");
                Date date = new Date(System.currentTimeMillis());
                FileWriter fileWriter = new FileWriter("apiary" + formatter.format(date) + ".csv");
                fileWriter.write("apiaryId" + System.lineSeparator());
                fileWriter.write(apiary.toCSV());
                Thread.sleep(1000); //pause
                fileWriter.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
