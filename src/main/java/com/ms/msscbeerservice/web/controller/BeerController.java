package com.ms.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.ms.msscbeerservice.web.model.BeerDTO;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController
{
  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDTO> getBeerById(@PathVariable("beerId") UUID beerId) {
    //todo impl
    return new ResponseEntity<>(BeerDTO.builder().build(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDTO beerDTO) {

    //todo impl
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDTO beerDTO) {

    //todo impl
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
