package iot.lviv.ua.nazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apiary {
    private int apiaryId;
    public List<Hive> hiveList = new ArrayList<>();

    public List<Hive> getList(){
        return hiveList;
    }

    public String getHeader() {
        return "apiaryId";
    }

    public String toCSV(Integer i) {
        return String.format("%s; %s; %s; %s; %s; %s; %s", getApiaryId(), getHiveList().get(i).getHiveId(),
                getHiveList().get(i).getBeeFamily().getBeeFamilyId(),
                getHiveList().get(i).getBeeFamily().getAmountOfBees(),
                getHiveList().get(i).getTelemetry().getTemperature(),
                getHiveList().get(i).getTelemetry().getHumidity(),
                getHiveList().get(i).getTelemetry().getStreet());
    }
}
