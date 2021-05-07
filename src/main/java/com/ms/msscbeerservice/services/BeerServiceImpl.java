package com.ms.msscbeerservice.services;

import java.util.UUID;
import java.util.stream.Collectors;

import com.ms.msscbeerservice.domain.Beer;
import com.ms.msscbeerservice.mappers.BeerMapper;
import com.ms.msscbeerservice.repositories.BeerRepository;
import com.ms.msscbeerservice.web.controller.NotFoundException;
import com.ms.msscbeerservice.web.model.BeerDTO;
import com.ms.msscbeerservice.web.model.BeerPagedList;
import com.ms.msscbeerservice.web.model.BeerStyleEnum;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl
    implements BeerService
{
  private final BeerRepository beerRepository;

  private final BeerMapper beerMapper;

  @Override
  public BeerDTO getById(final UUID beerId, boolean showInventoryOnHands) throws NotFoundException {
    if (showInventoryOnHands) {
      return beerMapper.beerToBeerDTO(
          beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
      );
    }
    else {
      return beerMapper.beerToBeerDTOWithInventory(
          beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
      );
    }
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

  @Override
  public BeerPagedList listBeers(
      final String beerName, final BeerStyleEnum beerStyle,
      final PageRequest pageRequest, boolean showInventoryOnHand
  )
  {
    BeerPagedList beerPagedList;
    Page<Beer> beerPage;

    String beerStyleString = (beerStyle == null) ? null : beerStyle.name();

    if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyleString)) {
      //search both
      beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyleString, pageRequest);
    }
    else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyleString)) {
      //search beer_service name
      beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
    }
    else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyleString)) {
      //search beer_service style
      beerPage = beerRepository.findAllByBeerStyle(beerStyleString, pageRequest);
    }
    else {
      beerPage = beerRepository.findAll(pageRequest);
    }

    beerPagedList = new BeerPagedList(beerPage
        .getContent()
        .stream()
        .map(beer -> {
          if (showInventoryOnHand) {
            return beerMapper.beerToBeerDTOWithInventory(beer);
          }
          else {
            return beerMapper.beerToBeerDTO(beer);
          }
        })
        .collect(Collectors.toList()),
        PageRequest.of(
            beerPage.getPageable().getPageNumber(),
            beerPage.getPageable().getPageSize()
        )
    );

    return beerPagedList;
  }
}
