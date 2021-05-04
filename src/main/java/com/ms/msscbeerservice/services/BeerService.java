package com.ms.msscbeerservice.services;

import java.util.UUID;

import com.ms.msscbeerservice.web.controller.NotFoundException;
import com.ms.msscbeerservice.web.model.BeerDTO;

public interface BeerService
{
  BeerDTO getById(UUID beerId) throws NotFoundException;

  BeerDTO saveNewBeer(BeerDTO beerDTO);

  BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO) throws NotFoundException;
}
