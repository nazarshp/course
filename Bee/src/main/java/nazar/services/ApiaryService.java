package nazar.services;

import iot.lviv.ua.nazar.models.Apiary;
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
public class ApiaryService implements StorageData {

    private HashMap<Integer, Apiary> apiaryHashMap = new HashMap<>();

    @Override
    public boolean saveToFile() {
        ArrayList<Apiary> apiaries = new ArrayList<>(apiaryHashMap.values());
        int counter = 0;
        for (Apiary apiary : apiaries) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            File file = new File("C:\\Users\\olexa\\Desktop\\nazar\\nazar\\apiaries-" + formatter.format(date) + ".csv");
            if (file.exists()) {
                try (FileWriter fileWriter = new FileWriter(file.getAbsolutePath(), true)) {
                    for (int i = 0; i < apiary.getHiveList().size(); i++) {
                        fileWriter.write(apiary.toCSV(counter) + System.lineSeparator());
                        counter++;
                        Thread.sleep(1000);
                    }
                    counter = 0;
                } catch (IOException | InterruptedException e) {
                    System.out.println("Exception: " + e);
                    return false;
                }
            } else {
                try (FileWriter fileWriter = new FileWriter(file.getAbsolutePath())) {
                    fileWriter.write("apiaryId;hiveId;beeFamilyId;beeFamilyAmount;temperature;humidity;street" + System.lineSeparator());
                    for (int i = 0; i < apiary.getHiveList().size(); i++) {
                        fileWriter.write(apiary.toCSV(counter) + System.lineSeparator());
                        counter++;
                        Thread.sleep(1000);
                    }
                    counter = 0;
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
            try (Scanner sc = new Scanner(new File("C:\\Users\\olexa\\Desktop\\nazar\\nazar\\apiaries-2022-" + monthStr + "-" + day + ".csv"))) {
                sc.nextLine();
                while (sc.hasNext()) {
                    String[] fileProps = sc.nextLine().split(";");
                    if (apiaryHashMap.get(Integer.parseInt(fileProps[0])) != null) {
                        Hive hive = new Hive(Integer.parseInt(fileProps[1]),
                                new BeeFamily(Integer.parseInt(fileProps[2]), Integer.parseInt(fileProps[3])),
                                new Telemetry(Integer.parseInt(fileProps[4]), Integer.parseInt(fileProps[5]), fileProps[6]));
                        apiaryHashMap.get(Integer.parseInt(fileProps[0])).getList().add(hive);
                    } else {
                        List<Hive> list = new ArrayList<>();
                        list.add(new Hive(Integer.parseInt(fileProps[1]),
                                new BeeFamily(Integer.parseInt(fileProps[2]), Integer.parseInt(fileProps[3])),
                                new Telemetry(Integer.parseInt(fileProps[4]), Integer.parseInt(fileProps[5]), fileProps[6])));
                        apiaryHashMap.put(Integer.parseInt(fileProps[0]),
                                new Apiary(Integer.parseInt(fileProps[0]), list));
                    }
                }
            } catch (FileNotFoundException ignored) {

            }
        }
        return true;
    }

    public Apiary save(Apiary apiary) {
        try {
            apiaryHashMap.put(apiary.getApiaryId(), apiary);
            return apiary;
        } catch (NullPointerException e) {
            System.out.println("Ex: ");
            return null;
        }
    }

    public Map<Integer, Apiary> getAllApiaries() {
        return apiaryHashMap;
    }

    public Apiary getById(Integer id) {
        for (Apiary a : apiaryHashMap.values()) {
            if (a.getApiaryId() == id) {
                return a;
            }
        }
        return null;
    }

    public Apiary update(Integer id, Apiary apiary) {
        return apiaryHashMap.put(id, apiary);
    }

    public Apiary delete(Integer id) throws NotFoundException {
        Apiary apiary = getById(id);
        Optional<Map.Entry<Integer, Apiary>> entry = apiaryHashMap.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(apiary)).findFirst();
        if (entry.isPresent()) {
            apiaryHashMap.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }
}
