package iot.lviv.ua.nazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apiary {
    public int apiaryId;

    public String getHeader() {

        return "apiaryId";
    }

    public String toCSV() {
        return String.format("%s", getApiaryId());

    }
}
