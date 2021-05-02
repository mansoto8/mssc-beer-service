package com.ms.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import com.ms.msscbeerservice.domain.Beer;
import com.ms.msscbeerservice.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BeerLoader implements CommandLineRunner
{
  @Autowired
  private BeerRepository beerRepository;

  @Override
  public void run(final String... args) throws Exception {
    loadBeerObjects();
  }

  private void loadBeerObjects() {
    if(beerRepository.count() == 0) {
      beerRepository.save(Beer.builder()
          .beerName("Club Colombia")
         .beerStyle("Rubia")
          .quantifyToBrew(200)
          .upc(13411432L)
          .minOnHand(12)
          .price(new BigDecimal("12.95"))
          .build());

      beerRepository.save(Beer.builder()
          .beerName("Delirium")
          .beerStyle("Roja")
          .quantifyToBrew(100)
          .upc(1341143214L)
          .minOnHand(20)
          .price(new BigDecimal("14.95"))
          .build());
    }

    System.out.println("Count of beers: " +  beerRepository.count());
  }
}
