package iot.lviv.ua.nazar.controllers;

import iot.lviv.ua.nazar.models.Apiary;
import iot.lviv.ua.nazar.models.Hive;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/apiary")
public class ApiaryController {


    private Map<Integer, Apiary> apiarys = new HashMap<>();
    public int counter = 1;
    HashMap<Integer, Apiary> map = null;

    @GetMapping
    public Map<Integer, Apiary> getAll() {
        return apiarys;
    }

    @GetMapping("/{id}")
    public Apiary getById(@PathVariable Integer id) throws NotFoundException {
        return getApiary(id);
    }

    private Apiary getApiary(Integer id) throws NotFoundException {

        Optional<Integer> key = map.keySet().stream().filter(apiaryId -> apiaryId.equals(id)).findFirst();
        if (key.isPresent()) {
            return map.get(id);
        } else {
            throw new NotFoundException("Not found hive with id:" + id);
        }
    }

    @PostMapping
    public Apiary create(@RequestBody Apiary apiary) {
        map.put(counter, apiary);
        return apiary;
    }

    @PutMapping("{id}")
    public Apiary update(@PathVariable Integer id, @RequestBody Apiary apiary) {
        map.put(id, apiary);
        return apiary;

    }


    @DeleteMapping("{id}")
    public Apiary delete(@PathVariable Integer id) throws NotFoundException {
        Apiary apiary = getById(id);
        Optional<Map.Entry<Integer, Apiary>> entry = apiarys.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(apiary)).findFirst();
        if (entry.isPresent()) {
            apiarys.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }
}
