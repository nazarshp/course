package iot.lviv.ua.nazar.storages;

import iot.lviv.ua.nazar.models.Telemetry;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class TelemetryStorage {
    public static void writeToFile(List<Telemetry> telemetryList) throws IOException {

        try {
            for (Telemetry telemetry : telemetryList) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_'at'_HH_mm_ss");
                Date date = new Date(System.currentTimeMillis());
                FileWriter fileWriter = new FileWriter("telemetry" + formatter.format(date) + ".csv");
                fileWriter.write("Street;Temperature;Humidity" + System.lineSeparator());
                fileWriter.write(telemetry.toCSV());
                Thread.sleep(1000); //pause
                fileWriter.close();
            }
        }
        catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}
