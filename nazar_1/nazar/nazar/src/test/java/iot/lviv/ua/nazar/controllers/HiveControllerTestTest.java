package iot.lviv.ua.nazar.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HiveControllerTestTest {

    @InjectMocks
    private HiveControllerTest hiveControllerTestUnderTest;

    @Test
    void testTestSaveToFile() throws Exception {
        // Setup
        // Run the test
        hiveControllerTestUnderTest.testSaveToFile();

        // Verify the results
    }

    @Test
    void testTestSaveToFile_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> hiveControllerTestUnderTest.testSaveToFile()).isInstanceOf(Exception.class);
    }

    @Test
    void testTestGetFromFile() throws Exception {
        // Setup
        // Run the test
        hiveControllerTestUnderTest.testGetFromFile();

        // Verify the results
    }

    @Test
    void testTestGetFromFile_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> hiveControllerTestUnderTest.testGetFromFile()).isInstanceOf(Exception.class);
    }

    @Test
    void testTestGetAll() throws Exception {
        // Setup
        // Run the test
        hiveControllerTestUnderTest.testGetAll();

        // Verify the results
    }

    @Test
    void testTestGetAll_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> hiveControllerTestUnderTest.testGetAll()).isInstanceOf(Exception.class);
    }

    @Test
    void testTestGetById() throws Exception {
        // Setup
        // Run the test
        hiveControllerTestUnderTest.testGetById();

        // Verify the results
    }

    @Test
    void testTestGetById_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> hiveControllerTestUnderTest.testGetById()).isInstanceOf(Exception.class);
    }

    @Test
    void testTestCreate() throws Exception {
        // Setup
        // Run the test
        hiveControllerTestUnderTest.testCreate();

        // Verify the results
    }

    @Test
    void testTestCreate_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> hiveControllerTestUnderTest.testCreate()).isInstanceOf(Exception.class);
    }

    @Test
    void testTestUpdate() throws Exception {
        // Setup
        // Run the test
        hiveControllerTestUnderTest.testUpdate();

        // Verify the results
    }

    @Test
    void testTestUpdate_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> hiveControllerTestUnderTest.testUpdate()).isInstanceOf(Exception.class);
    }

    @Test
    void testTestDelete() throws Exception {
        // Setup
        // Run the test
        hiveControllerTestUnderTest.testDelete();

        // Verify the results
    }

    @Test
    void testTestDelete_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> hiveControllerTestUnderTest.testDelete()).isInstanceOf(Exception.class);
    }

    @Test
    void testTestDelete_HiveServiceThrowsNotFoundException() throws Exception {
        // Setup
        // Run the test
        hiveControllerTestUnderTest.testDelete_HiveServiceThrowsNotFoundException();

        // Verify the results
    }

    @Test
    void testTestDelete_HiveServiceThrowsNotFoundException_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(
                () -> hiveControllerTestUnderTest.testDelete_HiveServiceThrowsNotFoundException())
                .isInstanceOf(Exception.class);
    }
}
