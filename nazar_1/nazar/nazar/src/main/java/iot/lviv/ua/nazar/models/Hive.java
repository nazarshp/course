package iot.lviv.ua.nazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hive {
    private int hiveId;
    private BeeFamily beeFamily;
    private Telemetry telemetry;

    public String getHeader() {
        return "hiveId";
    }

    public String toCSV() {
        return String.format("%s;%s;%s;%s;%s", getHiveId(), getBeeFamily().getBeeFamilyId(), getTelemetry().getTemperature(),
                getTelemetry().getHumidity(), getTelemetry().getStreet());
    }
}