package com.springnetflix.sales.api.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.springnetflix.sales.api.entities.Product;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "quantity"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductVO extends RepresentationModel<ProductVO> implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("quantity")
    private Integer quantity;

    public static ProductVO create(Product product) {
        return new ModelMapper().map(product, ProductVO.class);
    }

}
