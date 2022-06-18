package com.springnetflix.sales.api.services;

import com.springnetflix.sales.api.data.vo.SaleVO;
import com.springnetflix.sales.api.entities.ProductSale;
import com.springnetflix.sales.api.entities.Sale;
import com.springnetflix.sales.api.exceptions.ResourceNotFoundException;
import com.springnetflix.sales.api.repositories.ProductSaleRepository;
import com.springnetflix.sales.api.repositories.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductSaleRepository productSaleRepository;

    public SaleService(SaleRepository saleRepository, ProductSaleRepository productSaleRepository) {
        this.saleRepository = saleRepository;
        this.productSaleRepository = productSaleRepository;
    }

    private SaleVO convertToSaleVO(Sale sale) {
        return SaleVO.create(sale);
    }

    public SaleVO create (SaleVO saleVO) {
        Sale sale = saleRepository.save(Sale.create(saleVO));

        List<ProductSale> productsSaved = new ArrayList<>();
        saleVO.getProducts().forEach(p -> {
            ProductSale pv = ProductSale.create(p);
            pv.setSale(sale);
            productsSaved.add(productSaleRepository.save(pv));
        });
        sale.setProducts(productsSaved);
        return SaleVO.create(sale);
    }

    public Page<SaleVO> findAll(Pageable pageable) {
        var page = saleRepository.findAll(pageable);
        return page.map(this::convertToSaleVO);
    }

    public SaleVO findById(Long id) {
        var entity = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for the id: " + id));
        return SaleVO.create(entity);
    }

}
