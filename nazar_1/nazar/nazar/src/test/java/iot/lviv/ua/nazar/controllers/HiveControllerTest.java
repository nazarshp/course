package iot.lviv.ua.nazar.controllers;

import iot.lviv.ua.nazar.models.BeeFamily;
import iot.lviv.ua.nazar.models.Hive;
import iot.lviv.ua.nazar.models.Telemetry;
import iot.lviv.ua.nazar.services.HiveService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HiveController.class)
class HiveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HiveService mockHiveService;

    @Test
    void testSaveToFile() throws Exception {
        // Setup
        when(mockHiveService.saveToFile()).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/hive/save")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockHiveService).saveToFile();
    }

    @Test
    void testGetFromFile() throws Exception {
        // Setup
        when(mockHiveService.getFromFile()).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/hive/read")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockHiveService).getFromFile();
    }

    @Test
    void testGetAll() throws Exception {
        // Setup
        when(mockHiveService.getAllHives()).thenReturn(new HashMap<>());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/hive/hives")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetById() throws Exception {
        // Setup
        // Configure HiveService.getById(...).
        final Hive hive = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));
        when(mockHiveService.getById(0)).thenReturn(hive);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/hive/hive/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        // Configure HiveService.save(...).
        final Hive hive = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));
        when(mockHiveService.save(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))))
                .thenReturn(hive);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/hive")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        // Configure HiveService.update(...).
        final Hive hive = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));
        when(mockHiveService.update(0, new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))))
                .thenReturn(hive);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/hive/update/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Configure HiveService.delete(...).
        final Hive hive = new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"));
        when(mockHiveService.delete(0)).thenReturn(hive);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/hive/delete/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDelete_HiveServiceThrowsNotFoundException() throws Exception {
        // Setup
        when(mockHiveService.delete(0)).thenThrow(NotFoundException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/hive/delete/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
