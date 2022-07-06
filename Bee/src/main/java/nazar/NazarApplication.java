package nazar;

import iot.lviv.ua.nazar.services.ApiaryService;
import iot.lviv.ua.nazar.services.BeeFamilyService;
import iot.lviv.ua.nazar.services.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NazarApplication {
    @Autowired
    BeeFamilyService beeFamilyService;
    @Autowired
    HiveService hiveService;
    @Autowired
    ApiaryService apiaryService;

    public static void main(String[] args) {
        SpringApplication.run(NazarApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            beeFamilyService.getFromFile();
            hiveService.getFromFile();
            apiaryService.getFromFile();
        };
    }
}
