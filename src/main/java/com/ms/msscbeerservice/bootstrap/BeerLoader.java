package com.ms.msscbeerservice.bootstrap;

import java.math.BigDecimal;
import java.util.UUID;

import com.ms.msscbeerservice.domain.Beer;
import com.ms.msscbeerservice.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//This component was replaced by sql data script
//@Component
public class BeerLoader implements CommandLineRunner
{
  public static final String BEER_1_UPC = "06312342134";
  public static final String BEER_2_UPC = "06312342139";
  public static final String BEER_3_UPC = "06312342138";
  public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
  public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
  public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

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
          .quantityToBrew(200)
          .upc(BEER_1_UPC)
          .minOnHand(12)
          .price(new BigDecimal("12.95"))
          .build());

      beerRepository.save(Beer.builder()
          .beerName("Delirium")
          .beerStyle("Roja")
          .quantityToBrew(100)
          .upc(BEER_2_UPC)
          .minOnHand(20)
          .price(new BigDecimal("14.95"))
          .build());

      beerRepository.save(Beer.builder()
          .beerName("Poker")
          .beerStyle("Roja")
          .quantityToBrew(100)
          .upc(BEER_3_UPC)
          .minOnHand(20)
          .price(new BigDecimal("14.95"))
          .build());
    }

    System.out.println("Count of beers: " +  beerRepository.count());
  }
}
