package com.springnetflix.sales.api.entities;

import com.springnetflix.sales.api.data.vo.ProductSaleVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductSale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id", nullable = false, length = 10)
    private Long productId;
    @Column(name = "quantity", nullable = false, length = 10)
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public static ProductSale create(ProductSaleVO productSaleVO) {
        return new ModelMapper().map(productSaleVO, ProductSale.class);
    }

}
