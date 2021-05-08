package com.ms.msscbeerservice.services;

import java.util.UUID;

import com.ms.msscbeerservice.web.controller.NotFoundException;
import com.ms.msscbeerservice.web.model.BeerDTO;
import com.ms.msscbeerservice.web.model.BeerPagedList;
import com.ms.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

public interface BeerService
{
  BeerDTO getById(UUID beerId, boolean showInventoryOnHand) throws NotFoundException;

  BeerDTO saveNewBeer(BeerDTO beerDTO);

  BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO) throws NotFoundException;

  BeerPagedList listBeers(
      String beerName,
      BeerStyleEnum beerStyle,
      PageRequest pageRequest,
      boolean showInventoryOnHand);

  BeerDTO getBeerByUpc(String beerUpc, boolean showInventoryOnHand) throws NotFoundException;;
}
