package com.ms.msscbeerservice.services;

import java.util.UUID;

import com.ms.msscbeerservice.domain.Beer;
import com.ms.msscbeerservice.mappers.BeerMapper;
import com.ms.msscbeerservice.repositories.BeerRepository;
import com.ms.msscbeerservice.web.controller.NotFoundException;
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
  public BeerDTO getById(final UUID beerId) throws NotFoundException {
    return beerMapper.beerToBeerDTO(
        beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
    );
  }

  @Override
  public BeerDTO saveNewBeer(final BeerDTO beerDTO) {
    Beer beer = beerMapper.beerDTOToBeer(beerDTO);
    return beerMapper.beerToBeerDTO(beerRepository.save(beer));
  }

  @Override
  public BeerDTO updateBeer(final UUID beerId, final BeerDTO beerDTO) throws NotFoundException {
    Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

    beer.setBeerName(beerDTO.getBeerName());
    beer.setBeerStyle(beerDTO.getBeerStyle().name());
    beer.setPrice(beerDTO.getPrice());
    beer.setUpc(beerDTO.getUpc());

    return beerMapper.beerToBeerDTO(beerRepository.save(beer));
  }
}
