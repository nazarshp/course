package iot.lviv.ua.nazar.storages;

import iot.lviv.ua.nazar.models.Telemetry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TelemetryStorage {
    public static void writeToFile(Telemetry[] telemetryList, String fileName) throws IOException {
        String separator = File.separator;

        try (FileWriter fileWriter = new FileWriter("src" + separator + "test" + separator + "resources" + separator + fileName)) {


            String previousClassName = "";

            for (Telemetry telemetry : telemetryList) {
                if (!telemetry.getClass().getSimpleName().equals(previousClassName)) {

                    fileWriter.write(telemetry.getClass().getSimpleName() + "s:");
                    fileWriter.write("\n");
                    fileWriter.write(telemetry.getHeader());
                    fileWriter.write("\n");
                    fileWriter.write(telemetry.getStreet());
                    fileWriter.write("\n");
                    fileWriter.write((int) telemetry.getHumidity());
                    fileWriter.write("\n");
                    fileWriter.write((int) telemetry.getTemperature());
                    fileWriter.write("\n");
                    previousClassName = telemetry.getClass().getSimpleName();
                }

                fileWriter.write(telemetry.toCSV());
                fileWriter.write("\n");
            }
        }
    }
}
