package iot.lviv.ua.nazar.controllers;

import iot.lviv.ua.nazar.models.Apiary;
import iot.lviv.ua.nazar.services.ApiaryService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/apiary")
public class ApiaryController {

    @Autowired
    ApiaryService apiaryService;

    @PostMapping("/save")
    public void saveToFile() {
        apiaryService.saveToFile();
    }

    @PostMapping("/read")
    public void getFromFile() {
        apiaryService.getFromFile();
    }

    @GetMapping("/apiaries")
    public Map<Integer, Apiary> getAll() {
        return apiaryService.getAllApiaries();
    }

    @GetMapping("/apiary/{id}")
    public Apiary getById(@PathVariable Integer id) {
        return apiaryService.getById(id);
    }

    @PostMapping
    public Apiary create(@RequestBody Apiary apiary) {
        return apiaryService.save(apiary);
    }

    @PutMapping("/update/{id}")
    public Apiary update(@PathVariable Integer id, @RequestBody Apiary apiary) {
        return apiaryService.update(id, apiary);
    }

    @DeleteMapping("/delete/{id}")
    public Apiary delete(@PathVariable Integer id) throws NotFoundException {
        return apiaryService.delete(id);
    }
}
