package nazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Telemetry {
    private double temperature;
    private double humidity;
    private String street;

    public String getHeader() {
        return "temperature, humidity, street";
    }

    public String toCSV() {
        return String.format("%s; %s; %s",  getStreet(), getTemperature(), getHumidity());
    }
}
