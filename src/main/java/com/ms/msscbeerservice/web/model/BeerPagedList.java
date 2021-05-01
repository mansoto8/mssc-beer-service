package com.ms.msscbeerservice.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BeerPagedList extends PageImpl<BeerDTO>
{
  public BeerPagedList(final List<BeerDTO> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public BeerPagedList(final List<BeerDTO> content) {
    super(content);
  }
}
