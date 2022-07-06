package nazar.controllers;

import iot.lviv.ua.nazar.models.BeeFamily;
import iot.lviv.ua.nazar.services.BeeFamilyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/beefamily")
public class BeeFamilyController {

    @Autowired
    BeeFamilyService beeFamilyService;

    @PostMapping("/save")
    public void saveToFile() {
        beeFamilyService.saveToFile();
    }

    @PostMapping("/read")
    public void getFromFile() {
        beeFamilyService.getFromFile();
    }

    @GetMapping("/bees")
    public Map<Integer, BeeFamily> getAll() {
        return beeFamilyService.getAllBees();
    }

    @GetMapping("/bees/{id}")
    public BeeFamily getById(@PathVariable Integer id) {
        return beeFamilyService.getById(id);
    }

    @PostMapping()
    public BeeFamily create(@RequestBody BeeFamily bee_family) {
        return beeFamilyService.save(bee_family);
    }

    @PutMapping("update/{id}")
    public BeeFamily update(@PathVariable Integer id, @RequestBody BeeFamily bee_family) {
        return beeFamilyService.update(id, bee_family);
    }

    @DeleteMapping("delete/{id}")
    public BeeFamily delete(@PathVariable Integer id) throws NotFoundException {
        return beeFamilyService.delete(id);
    }
}
