package iot.lviv.ua.nazar.storages;

import iot.lviv.ua.nazar.models.Apiary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class ApiaryStorage {
    public static void writeToFile(List<Apiary> apiaryList, String fileName) throws IOException {
        String separator = File.separator;

        try (FileWriter fileWriter = new FileWriter("src" + separator + "test" + separator + "resources" + separator + fileName)) {


            String previousClassName = "";

            for (Apiary apiary : apiaryList) {
                if (!apiary.getClass().getSimpleName().equals(previousClassName)) {
                    fileWriter.write(apiary.getClass().getSimpleName() + "s:");
                    fileWriter.write("\n");
                    fileWriter.write(apiary.getHeader());
                    fileWriter.write("\n");
                    previousClassName = apiary.getClass().getSimpleName();
                }

                fileWriter.write(apiary.toCSV());
                fileWriter.write("\n");
            }
        }
    }
}
