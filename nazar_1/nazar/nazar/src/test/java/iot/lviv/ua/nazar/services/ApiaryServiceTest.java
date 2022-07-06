package iot.lviv.ua.nazar.services;

import iot.lviv.ua.nazar.models.Apiary;
import iot.lviv.ua.nazar.models.BeeFamily;
import iot.lviv.ua.nazar.models.Hive;
import iot.lviv.ua.nazar.models.Telemetry;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApiaryServiceTest {

    private ApiaryService apiaryServiceUnderTest;

    @BeforeEach
    void setUp() {
        apiaryServiceUnderTest = new ApiaryService();
    }

    @Test
    void testSaveToFile() {
        // Setup
        // Run the test
        final boolean result = apiaryServiceUnderTest.saveToFile();

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testGetFromFile() {
        // Setup
        // Run the test
        final boolean result = apiaryServiceUnderTest.getFromFile();

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testSave() {
        // Setup
        final Apiary apiary = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));
        final Apiary expectedResult = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));

        // Run the test
        final Apiary result = apiaryServiceUnderTest.save(apiary);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllApiaries() {
        // Setup
        final Map<Integer, Apiary> expectedResult = new HashMap<>();

        // Run the test
        final Map<Integer, Apiary> result = apiaryServiceUnderTest.getAllApiaries();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetById() {
        // Setup
        final Apiary expectedResult = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));

        // Run the test
        final Apiary result = apiaryServiceUnderTest.getById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final Apiary apiary = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));
        final Apiary expectedResult = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));

        // Run the test
        final Apiary result = apiaryServiceUnderTest.update(0, apiary);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        final Apiary expectedResult = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));

        // Run the test
        final Apiary result = apiaryServiceUnderTest.delete(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete_ThrowsNotFoundException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> apiaryServiceUnderTest.delete(0)).isInstanceOf(NotFoundException.class);
    }
}
