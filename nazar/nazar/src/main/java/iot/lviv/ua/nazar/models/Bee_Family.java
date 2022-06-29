package iot.lviv.ua.nazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bee_Family {
    private int bee_familyId;

    public String getHeader() {

        return "bee_familyId";
    }

    public String toCSV() {
        return String.format("%s", getBee_familyId());

    }
}
