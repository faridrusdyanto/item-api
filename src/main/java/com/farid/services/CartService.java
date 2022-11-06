package com.farid.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farid.models.entities.Cart;
import com.farid.models.entities.Item;
import com.farid.models.repos.CartRepo;
import com.farid.models.repos.ItemRepo;

@Service
@Transactional
public class CartService {

  @Autowired
  private CartRepo cartRepo;

  @Autowired
  private ItemRepo itemRepo;

  public String addItemToCart(Long idItem, Integer qty) {

    try {
      List<Cart> listCart = cartRepo.findByItem(idItem);
      if (listCart == null || listCart.size() == 0) {
        Item item = itemRepo.findById(idItem).get();
        Cart cart = new Cart();
        cart.setItem(item);
        cart.setQty(qty);
        cartRepo.save(cart);
      } else {
        for (int i = 0; i < listCart.size(); i++) {
          Cart cart = listCart.get(i);
          cart.setQty(cart.getQty() + qty);
          cartRepo.save(cart);
        }
      }

      return "Success";
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return "Failed";
    }
  }

  public String addMoreItemsToCart(List<Long> listIdItem, List<Integer> listQty) {
    try {
      for (int i = 0; i < listIdItem.size(); i++) {
        Long idItem = listIdItem.get(i);
        Integer qty = listQty.get(i);
        List<Cart> listCart = cartRepo.findByItem(idItem);
        if (listCart == null || listCart.size() == 0) {
          Item item = itemRepo.findById(idItem).get();
          Cart cart = new Cart();
          cart.setItem(item);
          cart.setQty(qty);
          cartRepo.save(cart);
        } else {
          for (int j = 0; j < listCart.size(); j++) {
            Cart cart = listCart.get(j);
            cart.setQty(cart.getQty() + qty);
            cartRepo.save(cart);
          }
        }
      }
      return "Success";
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return "Failed";
    }
  }

  public String removeItemFromCart(Long idItem) {
    try {
      List<Cart> listCart = cartRepo.findByItem(idItem);
      for (int i = 0; i < listCart.size(); i++) {
        Cart cart = listCart.get(i);
        cartRepo.delete(cart);
      }
      return "Success";
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return "Failed";
    }
  }

  public Integer finalizeCart() {
    try {
      Integer total = 0;
      Iterator<Cart> listCarts = cartRepo.findAll().iterator();
      while (listCarts.hasNext()) {
        Cart cart = listCarts.next();
        Integer subtotal = cart.getItem().getPrice() * cart.getQty();
        cart.setTotal(subtotal);
        cartRepo.save(cart);
        total += subtotal;
      }
      return total;
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return 0;
    }
  }

  public List<Cart> getAll() {
    Iterator<Cart> itCarts = cartRepo.findAll().iterator();
    List<Cart> listCart = new ArrayList<>();
      while (itCarts.hasNext()) {
        Cart cart = itCarts.next();
        listCart.add(cart);
      }
      return listCart;
  }
}
