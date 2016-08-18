package com.thoughtworks.jigsaw.repository;

import com.thoughtworks.jigsaw.domain.Technical;
import org.springframework.data.repository.CrudRepository;

public interface TechnicalRepository extends CrudRepository<Technical, Long> {
}
