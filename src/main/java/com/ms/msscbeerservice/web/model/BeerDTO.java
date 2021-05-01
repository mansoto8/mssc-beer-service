package com.ms.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO
{
  private UUID id;
  private Integer version;

  private OffsetDateTime createdDate;
  private OffsetDateTime lastModifiedDate;

  private String beerName;

  private BeerStyleEnum beerStyle;

  private Long upc;

  private BigDecimal price;
}
