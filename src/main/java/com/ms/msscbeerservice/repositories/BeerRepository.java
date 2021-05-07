package com.ms.msscbeerservice.repositories;

import java.util.UUID;

import com.ms.msscbeerservice.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>
{
  Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, String beerStyle, PageRequest pageRequest);
  Page<Beer> findAllByBeerName(String beerName, PageRequest pageRequest);
  Page<Beer> findAllByBeerStyle(String beerStyle, PageRequest pageRequest);
}
