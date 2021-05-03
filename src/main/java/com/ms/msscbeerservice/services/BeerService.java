package com.ms.msscbeerservice.services;

import java.util.UUID;

import com.ms.msscbeerservice.web.model.BeerDTO;

public interface BeerService
{
  BeerDTO getById(UUID beerId);

  BeerDTO saveNewBeer(BeerDTO beerDTO);

  BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO);
}
