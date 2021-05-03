package com.ms.msscbeerservice.mappers;

import com.ms.msscbeerservice.domain.Beer;
import com.ms.msscbeerservice.web.model.BeerDTO;
import com.ms.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest(classes = {BeerMapperImpl.class, DateMapper.class})
class BeerMapperTest
{
  @Autowired
  BeerMapper beerMapper;

  @Test
  void beerToBeerDTO() {
    BeerDTO beerDTO = BeerDTO.builder().beerName("Poker").beerStyle(BeerStyleEnum.GOSE).upc(134123L).build();
    Beer beer = beerMapper.beerDTOToBeer(beerDTO);
    Assert.notNull(beer, "beer must not be null");
  }

  @Test
  void beerDTOToBeer() {
    Beer beer = Beer.builder().beerName("Poker").beerStyle(BeerStyleEnum.GOSE.toString()).upc(134123L).build();
    BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
    Assert.notNull(beerDTO, "beer must not be null");
  }
}