package com.springnetflix.sales.api.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.springnetflix.sales.api.entities.Sale;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "products", "total", "date"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SaleVO extends RepresentationModel<SaleVO> implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("products")
    private List<ProductSaleVO> products;
    @JsonProperty("total")
    private Double total;

    public static SaleVO create(Sale sale) {
        return new ModelMapper().map(sale, SaleVO.class);
    }

}
