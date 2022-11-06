package com.farid.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farid.models.entities.Item;
import com.farid.models.repos.ItemRepo;

@Service
@Transactional
public class ItemService {

  @Autowired
  private ItemRepo itemRepo;

  public Item save(Item item) {
    return itemRepo.save(item);
  }

  public Item findOne(Long id) {
    return itemRepo.findById(id).get();
  }

  public Iterable<Item> findAll() {
    return itemRepo.findAll();
  }

  public void removeOne(Long id) {
    itemRepo.deleteById(id);
  }
}
