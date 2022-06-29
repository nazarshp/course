package iot.lviv.ua.nazar.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Telemetry {
    private double temperature;
    private double humidity;
    private String street;
    private int hiveId;

    public String getHeader() {

        return "temperature, humidity, street, hiveId";
    }


    public String toCSV() {
        return String.format("%s; %s; %s; %s", getHiveId(), getStreet(), getTemperature(), getHumidity());

    }
}
