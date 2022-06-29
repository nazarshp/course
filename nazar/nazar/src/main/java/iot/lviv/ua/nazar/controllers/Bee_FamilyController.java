package iot.lviv.ua.nazar.controllers;

import iot.lviv.ua.nazar.models.Bee_Family;
import iot.lviv.ua.nazar.models.Hive;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/bee_family")
public class Bee_FamilyController {


    private Map<Integer, Bee_Family> bee_familys = new HashMap<>();
    public int counter = 1;
    HashMap<Integer, Bee_Family> map = null;

    @GetMapping
    public Map<Integer, Bee_Family> getAll() {
        return bee_familys;
    }

    @GetMapping("/{id}")
    public Bee_Family getById(@PathVariable Integer id) throws NotFoundException {
        return getBee_Family(id);
    }

    private Bee_Family getBee_Family(Integer id) throws NotFoundException {

        Optional<Integer> key = map.keySet().stream().filter(bee_familyId -> bee_familyId.equals(id)).findFirst();
        if (key.isPresent()) {
            return map.get(id);
        } else {
            throw new NotFoundException("Not found hive with id:" + id);
        }
    }

    @PostMapping
    public Bee_Family create(@RequestBody Bee_Family bee_family) {
        map.put(counter, bee_family);
        return bee_family;
    }

    @PutMapping("{id}")
    public Bee_Family update(@PathVariable Integer id, @RequestBody Bee_Family bee_family) {
        map.put(id, bee_family);
        return bee_family;

    }


    @DeleteMapping("{id}")
    public Bee_Family delete(@PathVariable Integer id) throws NotFoundException {
        Bee_Family bee_family = getById(id);
        Optional<Map.Entry<Integer, Bee_Family>> entry = bee_familys.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(bee_family)).findFirst();
        if (entry.isPresent()) {
            bee_familys.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }
}
