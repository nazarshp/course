package iot.lviv.ua.nazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hive {
    private int hiveId;

    public String getHeader() {

        return "hiveId";
    }

    public String toCSV() {
        return String.format("%s", getHiveId());

    }
}