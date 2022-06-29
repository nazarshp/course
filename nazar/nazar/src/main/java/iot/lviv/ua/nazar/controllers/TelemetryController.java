package iot.lviv.ua.nazar.controllers;

import iot.lviv.ua.nazar.models.Telemetry;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {


    private Map<Double, Telemetry> telemetrys = new HashMap<>();
    public double counter = 0;

    @GetMapping
    public Map<Double, Telemetry> getAll() {
        return telemetrys;
    }

    @GetMapping("/{street}")
    public Telemetry getByStreet(@PathVariable String street) throws NotFoundException {
        return getTelemetry(street);
    }

    private Telemetry getTelemetry(String street) throws NotFoundException {

        Optional<Map.Entry<Double, Telemetry>> key = telemetrys.entrySet().stream().filter(telemetry ->
                telemetry.getValue().getStreet() == street).findFirst();
        if (key.isPresent()) {
            return key.get().getValue();
        } else {
            throw new NotFoundException("Not found hive with street:" + street);
        }
    }

    @GetMapping("/{temperature}")
    public Telemetry getByTemperature(@PathVariable Double temperature) throws NotFoundException {
        return getTelemetry(temperature);
    }

    private Telemetry getTelemetry(Double temperature) throws NotFoundException {

        Optional<Map.Entry<Double, Telemetry>> key = telemetrys.entrySet().stream().filter(telemetry ->
                telemetry.getValue().getTemperature() == temperature).findFirst();
        if (key.isPresent()) {
            return key.get().getValue();
        } else {
            throw new NotFoundException("Not found hive with temperature:" + temperature);
        }
    }

    @GetMapping("/{humidity}")
    public Telemetry getByHumidity(@PathVariable Double humidity) throws NotFoundException {
        return getTelemetry(humidity);
    }

    private Telemetry getTelemetry(double humidity) throws NotFoundException {

        Optional<Map.Entry<Double, Telemetry>> key = telemetrys.entrySet().stream().filter(telemetry ->
                telemetry.getValue().getHumidity() == humidity).findFirst();
        if (key.isPresent()) {
            return key.get().getValue();
        } else {
            throw new NotFoundException("Not found hive with humidity:" + humidity);
        }
    }


    @PostMapping
    public Telemetry create(@RequestBody Telemetry telemetry) {
        telemetrys.put(counter, telemetry);
        return telemetry;
    }

    @PutMapping("/{street}")
    public Telemetry update(@PathVariable Double id, @PathVariable String street, @RequestBody Telemetry telemetry) {
        telemetry.setStreet(street);
        telemetrys.put(id, telemetry);
        return telemetry;

    }


    @PutMapping("/{temperature}")
    public Telemetry update(@PathVariable Double id, @PathVariable double temperature, @RequestBody Telemetry telemetry) {
        telemetry.setTemperature(temperature);
        telemetrys.put(id, telemetry);
        return telemetry;
    }

    @PutMapping("/{humidity}")
    public Telemetry update(@PathVariable Double id, @PathVariable Double humidity, @RequestBody Telemetry telemetry) {
        telemetry.setHumidity(humidity);
        telemetrys.put(id, telemetry);
        return telemetry;

    }


    @DeleteMapping("{street}")
    public Telemetry delete(@PathVariable String street) throws NotFoundException {
        Telemetry telemetry = getByStreet(street);
        Optional<Map.Entry<Double, Telemetry>> entry = telemetrys.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(telemetry)).findFirst();
        if (entry.isPresent()) {
            telemetrys.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }


    @DeleteMapping("{temperature}")
    public Telemetry delete(@PathVariable double temperature) throws NotFoundException {
        Telemetry telemetry = getByTemperature(temperature);
        Optional<Map.Entry<Double, Telemetry>> entry = telemetrys.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(telemetry)).findFirst();
        if (entry.isPresent()) {
            telemetrys.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }

    @DeleteMapping("{humidity}")
    public Telemetry delete(@PathVariable Double humidity) throws NotFoundException {
        Telemetry telemetry = getByHumidity(humidity);
        Optional<Map.Entry<Double, Telemetry>> entry = telemetrys.entrySet().stream().
                filter(entry1 -> entry1.getValue().equals(telemetry)).findFirst();
        if (entry.isPresent()) {
            telemetrys.remove(entry.get().getKey());
            return entry.get().getValue();
        } else {
            throw new NotFoundException("Not found");
        }
    }


}
