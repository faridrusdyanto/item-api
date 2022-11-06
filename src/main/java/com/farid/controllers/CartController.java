package com.farid.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farid.services.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  @Autowired
  private CartService cartService;

  @PostMapping("/addOne")
  public Map<String, String> addOne(@RequestBody Map<String, String> mapRequest) {
    Long idItem = Long.parseLong(mapRequest.get("idItem"));
    Integer qty = Integer.parseInt(mapRequest.get("qty"));
    Map<String, String> mapResp = new HashMap<>();
    mapResp.put("Message", cartService.addItemToCart(idItem, qty));
    return mapResp;
  }

  @PostMapping("/addSome")
  public Map<String, String> addSome(@RequestBody Map<String, String> mapRequest) {
    String[] arrIdItems = mapRequest.get("idItems").split(",");
    String[] arrQtys = mapRequest.get("qtys").split(",");
    List<Long> listId = new ArrayList<>();
    for (String arrIdItem : arrIdItems) {
      listId.add(Long.parseLong(arrIdItem));
    }
    List<Integer> listQty = new ArrayList<>();
    for (String arrQty : arrQtys) {
      listQty.add(Integer.parseInt(arrQty));
    }
    
    Map<String, String> mapResp = new HashMap<>();
    mapResp.put("Message", cartService.addMoreItemsToCart(listId, listQty));
    return mapResp;
  }

  @PostMapping("/removeOne")
  public Map<String, String> removeOne(@RequestBody Map<String, String> mapRequest) {
    Long idItem = Long.parseLong(mapRequest.get("idItem"));
    Map<String, String> mapResp = new HashMap<>();
    mapResp.put("Message", cartService.removeItemFromCart(idItem));
    return mapResp;
  }

  @GetMapping("/finalize")
  public Map<String, Object> findAll() {
    Map<String, Object> mapResp = new HashMap<>();
    mapResp.put("Data", cartService.getAll());
    mapResp.put("Grand_Total", cartService.finalizeCart());
    return mapResp;
  }
}
