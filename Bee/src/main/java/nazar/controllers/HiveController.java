package nazar.controllers;

import iot.lviv.ua.nazar.models.Hive;
import iot.lviv.ua.nazar.services.HiveService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/hive")
public class HiveController {

    @Autowired
    HiveService hiveService;

    @PostMapping("/save")
    public void saveToFile() {
        hiveService.saveToFile();
    }

    @PostMapping("/read")
    public void getFromFile() {
        hiveService.getFromFile();
    }

    @GetMapping("/hives")
    public Map<Integer, Hive> getAll() {
        return hiveService.getAllHives();
    }

    @GetMapping("/hive/{id}")
    public Hive getById(@PathVariable Integer id) {
        return hiveService.getById(id);
    }

    @PostMapping
    public Hive create(@RequestBody Hive hive) {
        return hiveService.save(hive);
    }

    @PutMapping("update/{id}")
    public Hive update(@PathVariable Integer id, @RequestBody Hive hive) {
        return hiveService.update(id, hive);
    }

    @DeleteMapping("delete/{id}")
    public Hive delete(@PathVariable Integer id) throws NotFoundException {
        return hiveService.delete(id);
    }
}
