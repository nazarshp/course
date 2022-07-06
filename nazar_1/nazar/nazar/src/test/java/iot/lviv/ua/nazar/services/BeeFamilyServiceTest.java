package iot.lviv.ua.nazar.services;

import iot.lviv.ua.nazar.models.BeeFamily;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeeFamilyServiceTest {

    private BeeFamilyService beeFamilyServiceUnderTest;

    @BeforeEach
    void setUp() {
        beeFamilyServiceUnderTest = new BeeFamilyService();
    }

    @Test
    void testSaveToFile() {
        // Setup
        // Run the test
        final boolean result = beeFamilyServiceUnderTest.saveToFile();

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testGetFromFile() {
        // Setup
        // Run the test
        final boolean result = beeFamilyServiceUnderTest.getFromFile();

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    void testSave() {
        // Setup
        final BeeFamily beeFamily = new BeeFamily(0, 0);
        final BeeFamily expectedResult = new BeeFamily(0, 0);

        // Run the test
        final BeeFamily result = beeFamilyServiceUnderTest.save(beeFamily);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllBees() {
        // Setup
        final Map<Integer, BeeFamily> expectedResult = new HashMap<>();

        // Run the test
        final Map<Integer, BeeFamily> result = beeFamilyServiceUnderTest.getAllBees();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetById() {
        // Setup
        final BeeFamily expectedResult = new BeeFamily(0, 0);

        // Run the test
        final BeeFamily result = beeFamilyServiceUnderTest.getById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final BeeFamily beeFamily = new BeeFamily(0, 0);
        final BeeFamily expectedResult = new BeeFamily(0, 0);

        // Run the test
        final BeeFamily result = beeFamilyServiceUnderTest.update(0, beeFamily);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        final BeeFamily expectedResult = new BeeFamily(0, 0);

        // Run the test
        final BeeFamily result = beeFamilyServiceUnderTest.delete(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete_ThrowsNotFoundException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> beeFamilyServiceUnderTest.delete(0)).isInstanceOf(NotFoundException.class);
    }
}
