package com.ms.msscbeerservice.web.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO implements Serializable
{
  static final long serialVersionUID = 999667790473683369L;

  @Null
  private UUID id;

  @Null
  private Integer version;

  @Null
  private OffsetDateTime createdDate;

  @Null
  private OffsetDateTime lastModifiedDate;

  @NotBlank
  private String beerName;

  @NotNull
  private BeerStyleEnum beerStyle;

  @NotNull
  private String upc;

  @Positive
  @NotNull
  private BigDecimal price;

  private Integer quantityOnHand;
}
