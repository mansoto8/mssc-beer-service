package com.ms.msscbeerservice.web.controller;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.msscbeerservice.bootstrap.BeerLoader;
import com.ms.msscbeerservice.services.BeerService;
import com.ms.msscbeerservice.web.model.BeerDTO;
import com.ms.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;

@WebMvcTest(BeerController.class)
class BeerControllerTest
{
  @MockBean
  BeerService beerService;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void getBeerById() throws Exception {
    BeerDTO beerDTO = BeerDTO.builder().beerName("Corona")
        .beerStyle(BeerStyleEnum.GOSE).price(new BigDecimal(2000)).upc(BeerLoader.BEER_1_UPC).build();
    given(beerService.getById(any(), anyBoolean())).willReturn(beerDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .get("/api/v1/beer/" + UUID.randomUUID().toString())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void saveNewBeer() throws Exception {
    BeerDTO beerDTO = BeerDTO.builder().beerName("Corona")
        .beerStyle(BeerStyleEnum.GOSE).price(new BigDecimal(2000)).upc(BeerLoader.BEER_1_UPC).build();
    String beerDTOJson = objectMapper.writeValueAsString(beerDTO);
    given(beerService.saveNewBeer(any())).willReturn(beerDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/api/v1/beer/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDTOJson)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void updateBeerById() throws Exception {
    BeerDTO beerDTO = BeerDTO.builder().beerName("Corona")
        .beerStyle(BeerStyleEnum.GOSE).price(new BigDecimal(2000)).upc(BeerLoader.BEER_1_UPC).build();
    String beerDTOJson = objectMapper.writeValueAsString(beerDTO);
    given(beerService.updateBeer(any(), any())).willReturn(beerDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .put("/api/v1/beer/5424974f-46ab-4cbd-88a6-2597fc52fb96 \n")
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDTOJson)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }
}