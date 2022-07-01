package iot.lviv.ua.nazar.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeeFamily {
    private int beeFamilyId;



    public String getHeader() {

        return "beeFamilyId";
    }

    public String toCSV() {
        return String.format("%s", this.getBeeFamilyId());

    }
}
