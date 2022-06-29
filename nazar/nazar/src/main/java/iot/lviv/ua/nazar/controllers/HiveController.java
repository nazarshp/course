package iot.lviv.ua.nazar.controllers;

import iot.lviv.ua.nazar.models.Hive;
import iot.lviv.ua.nazar.models.Telemetry;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/hive")
public class HiveController {


    private Map<Integer, Hive> hives = new HashMap<>();
    public int counter = 1;
    HashMap<Integer, Hive> map = null;

    @GetMapping
    public Map<Integer, Hive> getAll() {
        return hives;
    }

    @GetMapping("/{id}")
    public Hive getById(@PathVariable Integer id) throws NotFoundException {
        return getHive(id);
    }

    private Hive getHive(Integer id) throws NotFoundException {

        Optional<Integer> key = map.keySet().stream().filter(hiveId -> hiveId.equals(id)).findFirst();
        if (key.isPresent()) {
            return map.get(id);
        } else {
            throw new NotFoundException("Not found hive with id:" + id);
        }
    }

    @PostMapping
    public Hive create(@RequestBody Hive hive) {
        map.put(counter, hive);
        return hive;
    }

    @PutMapping("{id}")
    public Hive update(@PathVariable Integer id, @RequestBody Hive hive) {
        map.put(id, hive);
        return hive;

    }


    @DeleteMapping("{id}")
    public Hive delete(@PathVariable Integer id) throws NotFoundException {
        Hive hive = getById(id);
        Optional<Map.Entry<Integer, Hive>> entry = hives.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(hive)).findFirst();
        if (entry.isPresent()) {
            hives.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }
}
