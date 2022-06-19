package com.springnetflix.sales.api.entities;

import com.springnetflix.sales.api.data.vo.ProductVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable {

    @Id
    private Long id;

    @Column(name = "quantity", nullable = false, length = 10)
    private Integer quantity;

    public static Product create(ProductVO productVO) {
        return new ModelMapper().map(productVO, Product.class);
    }
}
