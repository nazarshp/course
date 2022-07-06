package iot.lviv.ua.nazar.services;

import iot.lviv.ua.nazar.models.BeeFamily;
import iot.lviv.ua.nazar.models.Hive;
import iot.lviv.ua.nazar.models.Telemetry;
import iot.lviv.ua.nazar.storage.StorageData;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HiveService implements StorageData {
    private final HashMap<Integer, Hive> hiveHashMap = new HashMap<>();

    @Override
    public boolean saveToFile() {
        ArrayList<Hive> hives = new ArrayList<>(hiveHashMap.values());
        for (Hive hive : hives) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            File file = new File("C:\\Users\\nazar\\Downloads\\nazar_1\\nazar\\nazar\\hives_" + formatter.format(date) + ".csv");
            if (file.exists()) {
                try (FileWriter fileWriter = new FileWriter(file.getAbsolutePath(), true)) {
                    fileWriter.write(hive.toCSV());
                    fileWriter.write(System.lineSeparator());
                    Thread.sleep(1000); //pause
                } catch (IOException | InterruptedException e) {
                    System.out.println("Exception: " + e);
                    return false;
                }
            } else {
                try (FileWriter fileWriter = new FileWriter(file.getAbsolutePath())) {
                    fileWriter.write("hiveId; beeFamilyId; amountOfBees; temperature; humidity; street" + System.lineSeparator());
                    fileWriter.write(hive.toCSV());
                    fileWriter.write(System.lineSeparator());
                    Thread.sleep(1000); //pause
                } catch (IOException | InterruptedException e) {
                    System.out.println("Exception: " + e);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean getFromFile() {
        int month = new Date(System.currentTimeMillis()).getMonth() + 1;
        String monthStr;
        if (month < 10) monthStr = "0" + month;
        else monthStr = String.valueOf(month);
        for (int i = 0; i < 31; i++) {
            String day;
            if (i < 10) day = "0" + i;
            else day = String.valueOf(i);
            try (Scanner sc = new Scanner(new File("C:\\Users\\nazar\\Downloads\\nazar_1\\nazar\\nazar\\hives_2022-" + monthStr + "-" + day + ".csv"))) {
                sc.nextLine();
                while (sc.hasNext()) {
                    String[] fileProps = sc.nextLine().split(";");
                    hiveHashMap.put(Integer.parseInt(fileProps[0]),
                            new Hive(Integer.parseInt(fileProps[0]),
                                    new BeeFamily(Integer.parseInt(fileProps[1]), Integer.parseInt(fileProps[2])),
                                    new Telemetry(Integer.parseInt(fileProps[3]), Integer.parseInt(fileProps[4]), fileProps[5])));
                }
            } catch (FileNotFoundException ignored) {

            }
        }
        return true;
    }

    public Hive save(Hive hive) {
        try {
            hiveHashMap.put(hive.getHiveId(), hive);
            return hive;
        } catch (NullPointerException e) {
            System.out.println("Ex: ");
            return null;
        }
    }

    public Map<Integer, Hive> getAllHives() {
        return hiveHashMap;
    }

    public Hive getById(Integer id) {
        for (Hive h : hiveHashMap.values()) {
            if (h.getHiveId() == id) {
                return h;
            }
        }
        return null;
    }

    public Hive update(Integer id, Hive hive) {
        return hiveHashMap.put(id, hive);
    }

    public Hive delete(Integer id) throws NotFoundException {
        Hive hive = getById(id);
        Optional<Map.Entry<Integer, Hive>> entry = hiveHashMap.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(hive)).findFirst();
        if (entry.isPresent()) {
            hiveHashMap.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }


}
