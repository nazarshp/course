package iot.lviv.ua.nazar.services;

import iot.lviv.ua.nazar.models.BeeFamily;
import iot.lviv.ua.nazar.models.Hive;
import iot.lviv.ua.nazar.models.Telemetry;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HiveServiceTest {

    private HiveService hiveServiceUnderTest;

    @BeforeEach
    void setUp() {
        hiveServiceUnderTest = new HiveService();
    }

    @Test
    void testSaveToFile() {
        // Setup
        // Run the test
        final boolean result = hiveServiceUnderTest.saveToFile();

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testGetFromFile() {
        // Setup
        // Run the test
        final boolean result = hiveServiceUnderTest.getFromFile();

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testSave() {
        // Setup
        final Hive hive = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));
        final Hive expectedResult = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));

        // Run the test
        final Hive result = hiveServiceUnderTest.save(hive);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllHives() {
        // Setup
        final Map<Integer, Hive> expectedResult = new HashMap<>();

        // Run the test
        final Map<Integer, Hive> result = hiveServiceUnderTest.getAllHives();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetById() {
        // Setup
        final Hive expectedResult = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));

        // Run the test
        final Hive result = hiveServiceUnderTest.getById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final Hive hive = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));
        final Hive expectedResult = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));

        // Run the test
        final Hive result = hiveServiceUnderTest.update(0, hive);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        final Hive expectedResult = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));

        // Run the test
        final Hive result = hiveServiceUnderTest.delete(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete_ThrowsNotFoundException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> hiveServiceUnderTest.delete(0)).isInstanceOf(NotFoundException.class);
    }
}
