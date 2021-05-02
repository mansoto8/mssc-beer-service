package com.ms.msscbeerservice.repositories;

import java.util.UUID;

import com.ms.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>
{

}
