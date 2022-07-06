package iot.lviv.ua.nazar.controllers;

import iot.lviv.ua.nazar.models.BeeFamily;
import iot.lviv.ua.nazar.services.BeeFamilyService;
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
@WebMvcTest(BeeFamilyController.class)
class BeeFamilyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeeFamilyService mockBeeFamilyService;

    @Test
    void testSaveToFile() throws Exception {
        // Setup
        when(mockBeeFamilyService.saveToFile()).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/beefamily/save")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockBeeFamilyService).saveToFile();
    }

    @Test
    void testGetFromFile() throws Exception {
        // Setup
        when(mockBeeFamilyService.getFromFile()).thenReturn(false);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/beefamily/read")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockBeeFamilyService).getFromFile();
    }

    @Test
    void testGetAll() throws Exception {
        // Setup
        when(mockBeeFamilyService.getAllBees()).thenReturn(new HashMap<>());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/beefamily/bees")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetById() throws Exception {
        // Setup
        when(mockBeeFamilyService.getById(0)).thenReturn(new BeeFamily(0, 0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/beefamily/bees/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        when(mockBeeFamilyService.save(new BeeFamily(0, 0))).thenReturn(new BeeFamily(0, 0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/beefamily")
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
        when(mockBeeFamilyService.update(0, new BeeFamily(0, 0))).thenReturn(new BeeFamily(0, 0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/beefamily/update/{id}", 0)
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
        when(mockBeeFamilyService.delete(0)).thenReturn(new BeeFamily(0, 0));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/beefamily/delete/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDelete_BeeFamilyServiceThrowsNotFoundException() throws Exception {
        // Setup
        when(mockBeeFamilyService.delete(0)).thenThrow(NotFoundException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/beefamily/delete/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
