package iot.lviv.ua.nazar.storages;

import iot.lviv.ua.nazar.models.Hive;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HiveStorage {
    public static void writeToFile(List<Hive> hiveList, String fileName) throws IOException {
        String separator = File.separator;

        try (FileWriter fileWriter = new FileWriter("src" + separator + "test" + separator + "resources" + separator + fileName)) {


            String previousClassName = "";

            for (Hive hive : hiveList) {
                if (!hive.getClass().getSimpleName().equals(previousClassName)) {
                    fileWriter.write(hive.getClass().getSimpleName() + "s:");
                    fileWriter.write("\n");
                    fileWriter.write(hive.getHeader());
                    fileWriter.write("\n");
                    previousClassName = hive.getClass().getSimpleName();
                }

                fileWriter.write(hive.toCSV());
                fileWriter.write("\n");
            }
        }
    }
}

