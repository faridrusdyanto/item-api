package com.farid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farid.models.entities.Item;
import com.farid.services.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

  @Autowired
  private ItemService itemService;

  @PostMapping
  public Item create(@RequestBody Item item) {
    return itemService.save(item);
  }

  @GetMapping
  public Iterable<Item> findAll() {
    return itemService.findAll();
  }

  @GetMapping("/{id}")
  public Item findOne(@PathVariable("id") Long id) {
    return itemService.findOne(id);
  }

  @PutMapping
  public Item update(@RequestBody Item item) {
    return itemService.save(item);
  }

  @DeleteMapping("/{id}")
  public void removeOne(@PathVariable("id") Long id) {
    itemService.removeOne(id);
  }
  
}
