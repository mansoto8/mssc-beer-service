package com.ms.msscbeerservice.mappers;

import com.ms.msscbeerservice.domain.Beer;
import com.ms.msscbeerservice.web.model.BeerDTO;
import org.mapstruct.Mapper;


@Mapper(uses = DateMapper.class)
public interface BeerMapper
{
  BeerDTO beerToBeerDTO(Beer beer);

  Beer beerDTOToBeer(BeerDTO beerDTO);
}