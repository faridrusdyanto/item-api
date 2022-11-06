package com.farid.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.farid.models.entities.Item;

public interface ItemRepo extends CrudRepository<Item, Long> {
  
}
