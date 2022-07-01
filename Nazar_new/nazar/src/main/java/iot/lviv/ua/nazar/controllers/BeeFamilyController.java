package iot.lviv.ua.nazar.controllers;

import iot.lviv.ua.nazar.models.BeeFamily;
import iot.lviv.ua.nazar.storages.BeeFamilyStorage;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/beefamily")
public class BeeFamilyController {


    private Map<Integer, BeeFamily> beefamilys = new HashMap<>();
    public int counter = 0;
    HashMap<Integer, BeeFamily> map = new HashMap<>();

    @PostMapping("/save")
    public void saveToFile() throws IOException {
        ArrayList<BeeFamily> valueList = new ArrayList<>(map.values());
        BeeFamilyStorage.writeToFile(valueList);
    }

    @GetMapping("getAll")
    public Map<Integer, BeeFamily> getAll() {
        return beefamilys;
    }

    @GetMapping("{id}")
    public BeeFamily getById(@PathVariable Integer id) throws NotFoundException {
        return getBee_Family(id);
    }

    private BeeFamily getBee_Family(Integer id) throws NotFoundException {

        Optional<Integer> key = map.keySet().stream().filter(bee_familyId -> bee_familyId.equals(id)).findFirst();
        if (key.isPresent()) {
            return map.get(id);
        } else {
            throw new NotFoundException("Not found hive with id:" + id);
        }
    }

    @PostMapping
    public BeeFamily create(@RequestBody BeeFamily bee_family) {
        map.put(counter, bee_family);
        return bee_family;
    }

    @PutMapping("update/{id}")
    public BeeFamily update(@PathVariable Integer id, @RequestBody BeeFamily bee_family) {
        map.put(id, bee_family);
        return bee_family;

    }


    @DeleteMapping("delete/{id}")
    public BeeFamily delete(@PathVariable Integer id) throws NotFoundException {
        BeeFamily bee_family = getById(id);
        Optional<Map.Entry<Integer, BeeFamily>> entry = map.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(bee_family)).findFirst();
        if (entry.isPresent()) {
            map.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }
}
