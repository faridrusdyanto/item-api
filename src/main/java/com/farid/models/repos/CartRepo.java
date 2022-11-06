package com.farid.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.farid.models.entities.Cart;


public interface CartRepo extends CrudRepository<Cart, Long> {
  @Query("select c from Cart c left join fetch c.item i where i.id = :idItem")
  List<Cart> findByItem(@Param("idItem") Long idItem);
}
