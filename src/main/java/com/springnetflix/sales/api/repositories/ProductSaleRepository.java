package com.springnetflix.sales.api.repositories;

import com.springnetflix.sales.api.entities.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {
}
