package com.ms.msscbeerservice.services;

import java.util.UUID;

import com.ms.msscbeerservice.mappers.BeerMapper;
import com.ms.msscbeerservice.repositories.BeerRepository;
import com.ms.msscbeerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl
    implements BeerService
{
  private final BeerRepository beerRepository;
  private final BeerMapper beerMapper;

  @Override
  public BeerDTO getById(final UUID beerId) {
    return null;
  }

  @Override
  public BeerDTO saveNewBeer(final BeerDTO beerDTO) {
    return null;
  }

  @Override
  public BeerDTO updateBeer(final UUID beerId, final BeerDTO beerDTO) {
    return null;
  }
}
