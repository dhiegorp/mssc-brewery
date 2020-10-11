package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    BeerDto validBeer;

    @Before
    public void setUp() {
        validBeer = BeerDto.builder().
                id(UUID.randomUUID())
                .beerName("Some Beer")
                .beerStyle("IPA")
                .upc(1020123L)
                .build();
    }

    @Test
    public void getBeer() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get("/api/v1/beer/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is("Some Beer")));

    }

    @Test
    public void create() throws Exception {
        //given
        BeerDto dto = validBeer;
        dto.setId(null);
        BeerDto saved = BeerDto.builder().id(UUID.randomUUID()).beerName("New One").build();
        String jsonStr = mapper.writeValueAsString(dto);

        given(beerService.createBeer(any())).willReturn(saved);

        //when
        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isCreated()
        );
    }

    @Test
    public void  update() throws Exception {
        //given
        BeerDto dto = validBeer;
        validBeer.setId(null);
        String jsonString = mapper.writeValueAsString(dto);

        //when
        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isNoContent());
        then(beerService).should().update(any(), any());

    }


}
