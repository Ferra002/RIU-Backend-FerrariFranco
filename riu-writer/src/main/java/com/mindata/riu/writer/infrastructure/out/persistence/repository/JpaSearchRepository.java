package com.mindata.riu.writer.infrastructure.out.persistence.repository;

import com.mindata.riu.writer.infrastructure.out.persistence.entity.SearchEntity;
import org.springframework.data.repository.Repository;

public interface JpaSearchRepository extends Repository<SearchEntity, Long> {

    SearchEntity save(SearchEntity entity);

}
