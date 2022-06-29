package iot.lviv.ua.nazar.storages;

import iot.lviv.ua.nazar.models.Bee_Family;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Bee_FamilyStorage {
    public static void writeToFile(List<Bee_Family> bee_familyList, String fileName) throws IOException {
        String separator = File.separator;

        try (FileWriter fileWriter = new FileWriter("src" + separator + "test" + separator + "resources" + separator + fileName)) {


            String previousClassName = "";

            for (Bee_Family bee_family : bee_familyList) {
                if (!bee_family.getClass().getSimpleName().equals(previousClassName)) {
                    fileWriter.write(bee_family.getClass().getSimpleName() + "s:");
                    fileWriter.write("\n");
                    fileWriter.write(bee_family.getHeader());
                    fileWriter.write("\n");
                    previousClassName = bee_family.getClass().getSimpleName();
                }

                fileWriter.write(bee_family.toCSV());
                fileWriter.write("\n");
            }
        }
    }
}
