package com.ms.msscbeerservice.mappers;

import com.ms.msscbeerservice.domain.Beer;
import com.ms.msscbeerservice.services.inventory.BeerInventoryService;
import com.ms.msscbeerservice.web.model.BeerDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class BeerMapperDecorator
    implements BeerMapper
{
  private BeerInventoryService beerInventoryService;

  private BeerMapper mapper;

  @Autowired
  public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
    this.beerInventoryService = beerInventoryService;
  }

  @Autowired
  void setMapper(BeerMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public BeerDTO beerToBeerDTO(Beer beer) {
    BeerDTO dto = mapper.beerToBeerDTO(beer);
    dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
    return dto;
  }

  @Override
  public Beer beerDTOToBeer(BeerDTO beerDTO) {
    return mapper.beerDTOToBeer(beerDTO);
  }
}
