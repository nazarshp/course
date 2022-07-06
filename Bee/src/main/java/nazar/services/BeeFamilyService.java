package nazar.services;

import iot.lviv.ua.nazar.models.BeeFamily;
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
public class BeeFamilyService implements StorageData {

    private final HashMap<Integer, BeeFamily> beeFamilyHashMap = new HashMap<>();

    public boolean saveToFile() {
        ArrayList<BeeFamily> beeFamilies = new ArrayList<>(beeFamilyHashMap.values());
        for (BeeFamily beeFamily : beeFamilies) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            File file = new File("C:\\Users\\olexa\\Desktop\\nazar\\nazar\\bees-" + formatter.format(date) + ".csv");
            if (file.exists()) {
                try (FileWriter fileWriter = new FileWriter(file.getAbsolutePath(), true)) {
                    fileWriter.write(beeFamily.toCSV());
                    fileWriter.write(System.lineSeparator());
                    Thread.sleep(1000); //pause
                } catch (IOException | InterruptedException e) {
                    System.out.println("Exception: " + e);
                    return false;
                }
            } else {
                try (FileWriter fileWriter = new FileWriter(file.getAbsolutePath())) {
                    fileWriter.write("beesFamilyId; amountOfBees" + System.lineSeparator());
                    fileWriter.write(beeFamily.toCSV());
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
            try (Scanner sc = new Scanner(new File("C:\\Users\\olexa\\Desktop\\nazar\\nazar\\bees-2022-" + monthStr + "-" + day + ".csv"))) {
                sc.nextLine();
                while (sc.hasNext()) {
                    String[] fileProps = sc.nextLine().split(";");
                    beeFamilyHashMap.put(Integer.parseInt(fileProps[0]),
                            new BeeFamily(Integer.parseInt(fileProps[0]), Integer.parseInt(fileProps[1])));
                }
            } catch (FileNotFoundException ignored) {

            }
        }
        return true;
    }

    public BeeFamily save(BeeFamily beeFamily) {
        try {
            beeFamilyHashMap.put(beeFamily.getBeeFamilyId(), beeFamily);
            return beeFamily;
        } catch (NullPointerException e) {
            System.out.println("Ex: ");
            return null;
        }
    }

    public Map<Integer, BeeFamily> getAllBees() {
        return beeFamilyHashMap;
    }

    public BeeFamily getById(Integer id) {
        for (BeeFamily b : beeFamilyHashMap.values()) {
            if (b.getBeeFamilyId() == id) {
                return b;
            }
        }
        return null;
    }

    public BeeFamily update(Integer id, BeeFamily beeFamily) {
        return beeFamilyHashMap.put(id, beeFamily);
    }

    public BeeFamily delete(Integer id) throws NotFoundException {
        BeeFamily bee_family = getById(id);
        Optional<Map.Entry<Integer, BeeFamily>> entry = beeFamilyHashMap.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(bee_family)).findFirst();
        if (entry.isPresent()) {
            beeFamilyHashMap.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }
}
