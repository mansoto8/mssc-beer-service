package com.ms.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import com.ms.msscbeerservice.services.BeerService;
import com.ms.msscbeerservice.web.model.BeerDTO;
import com.ms.msscbeerservice.web.model.BeerPagedList;
import com.ms.msscbeerservice.web.model.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController
{
  private static final Integer DEFAULT_PAGE_NUMBER = 0;

  private static final Integer DEFAULT_PAGE_SIZE = 25;

  private final BeerService beerService;

  @GetMapping(produces = {"application/json"})
  public ResponseEntity<BeerPagedList> listBeers(
      @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
      @RequestParam(value = "pageSize", required = false) Integer pageSize,
      @RequestParam(value = "beerName", required = false) String beerName,
      @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
      @RequestParam(value = "showInventoryOnHand", defaultValue = "false") Boolean showInventoryOnHand
  )
  {
    if (pageNumber == null || pageNumber < 0) {
      pageNumber = DEFAULT_PAGE_NUMBER;
    }

    if (pageSize == null || pageSize < 1) {
      pageSize = DEFAULT_PAGE_SIZE;
    }

    BeerPagedList beerList =
        beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

    return new ResponseEntity<>(beerList, HttpStatus.OK);
  }

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDTO> getBeerById(
      @PathVariable("beerId") UUID beerId,
      @RequestParam(value = "showInventoryOnHand", defaultValue = "false") Boolean showInventoryOnHand
  ) throws NotFoundException
  {
    return new ResponseEntity<>(beerService.getById(beerId, showInventoryOnHand), HttpStatus.OK);
  }

  @GetMapping("/upc/{upc}")
  public ResponseEntity<BeerDTO> getBeerById(
      @PathVariable("upc") String upc,
      @RequestParam(value = "showInventoryOnHand", defaultValue = "false") Boolean showInventoryOnHand
  ) throws NotFoundException
  {
    return new ResponseEntity<>(beerService.getBeerByUpc(upc, showInventoryOnHand), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDTO beerDTO) {

    return new ResponseEntity<>(beerService.saveNewBeer(beerDTO), HttpStatus.CREATED);
  }

  @PutMapping("/{beerId}")
  public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDTO beerDTO)
      throws NotFoundException
  {

    return new ResponseEntity<>(beerService.updateBeer(beerId, beerDTO), HttpStatus.NO_CONTENT);
  }
}
