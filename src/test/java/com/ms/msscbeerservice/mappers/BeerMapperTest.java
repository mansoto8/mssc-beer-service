package com.ms.msscbeerservice.mappers;

import com.ms.msscbeerservice.bootstrap.BeerLoader;
import com.ms.msscbeerservice.domain.Beer;
import com.ms.msscbeerservice.services.inventory.BeerInventoryService;
import com.ms.msscbeerservice.web.model.BeerDTO;
import com.ms.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

@SpringBootTest(classes = {BeerMapperImpl_.class, DateMapper.class})
class BeerMapperTest
{
  @Autowired
  BeerMapper beerMapper;

  @MockBean
  BeerInventoryService beerInventoryService;

  @Test
  void beerToBeerDTO() {
    BeerDTO beerDTO = BeerDTO.builder().beerName("Poker").beerStyle(BeerStyleEnum.GOSE).upc(BeerLoader.BEER_1_UPC).build();
    Beer beer = beerMapper.beerDTOToBeer(beerDTO);
    Assert.notNull(beer, "beer must not be null");
  }

  @Test
  void beerDTOToBeer() {
    Beer beer = Beer.builder().beerName("Poker").beerStyle(BeerStyleEnum.GOSE.toString()).upc(BeerLoader.BEER_1_UPC).build();
    BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
    Assert.notNull(beerDTO, "beer must not be null");
  }
}