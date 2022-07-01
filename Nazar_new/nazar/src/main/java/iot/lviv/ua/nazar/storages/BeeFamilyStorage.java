package iot.lviv.ua.nazar.storages;

import iot.lviv.ua.nazar.models.BeeFamily;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BeeFamilyStorage {
    public static void writeToFile(List<BeeFamily> beeFamilyList) throws IOException {

        try {
            for (BeeFamily beeFamily : beeFamilyList) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_'at'_HH_mm_ss");
                Date date = new Date(System.currentTimeMillis());
                FileWriter fileWriter = new FileWriter("beeFamily" + formatter.format(date) + ".csv");
                fileWriter.write("beeFamilyId" + System.lineSeparator());
                fileWriter.write(beeFamily.toCSV());
                Thread.sleep(1000); //pause
                fileWriter.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
