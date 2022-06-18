package com.springnetflix.sales.api.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.springnetflix.sales.api.entities.ProductSale;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "productId", "quantity"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductSaleVO  extends RepresentationModel<ProductSaleVO> implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("quantity")
    private Integer quantity;

    public static ProductSaleVO create(ProductSale productSale) {
        return new ModelMapper().map(productSale, ProductSaleVO.class);
    }

}
