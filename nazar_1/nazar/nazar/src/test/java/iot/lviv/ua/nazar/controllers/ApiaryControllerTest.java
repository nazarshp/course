package iot.lviv.ua.nazar.controllers;

import iot.lviv.ua.nazar.models.Apiary;
import iot.lviv.ua.nazar.models.BeeFamily;
import iot.lviv.ua.nazar.models.Hive;
import iot.lviv.ua.nazar.models.Telemetry;
import iot.lviv.ua.nazar.services.ApiaryService;
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

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiaryController.class)
class ApiaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiaryService mockApiaryService;

    @Test
    void testSaveToFile() throws Exception {
        // Setup
        when(mockApiaryService.saveToFile()).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/apiary/save")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockApiaryService).saveToFile();
    }

    @Test
    void testGetFromFile() throws Exception {
        // Setup
        when(mockApiaryService.getFromFile()).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/apiary/read")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockApiaryService).getFromFile();
    }

    @Test
    void testGetAll() throws Exception {
        // Setup
        when(mockApiaryService.getAllApiaries()).thenReturn(new HashMap<>());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/apiary/apiaries")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetById() throws Exception {
        // Setup
        // Configure ApiaryService.getById(...).
        final Apiary apiary = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));
        when(mockApiaryService.getById(0)).thenReturn(apiary);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/apiary/apiary/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        // Configure ApiaryService.save(...).
        final Apiary apiary = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));
        when(mockApiaryService.save(new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))))))
                .thenReturn(apiary);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/apiary")
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
        // Configure ApiaryService.update(...).
        final Apiary apiary = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));
        when(mockApiaryService.update(0, new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))))))
                .thenReturn(apiary);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/apiary/update/{id}", 0)
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
        // Configure ApiaryService.delete(...).
        final Apiary apiary = new Apiary(0,
                Arrays.asList(new Hive(0, new BeeFamily(0, 0), new Telemetry(0.0, 0.0, "street"))));
        when(mockApiaryService.delete(0)).thenReturn(apiary);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/apiary/delete/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDelete_ApiaryServiceThrowsNotFoundException() throws Exception {
        // Setup
        when(mockApiaryService.delete(0)).thenThrow(NotFoundException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/apiary/delete/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
