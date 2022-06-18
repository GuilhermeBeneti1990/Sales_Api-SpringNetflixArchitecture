package com.springnetflix.sales.api.entities;

import com.springnetflix.sales.api.data.vo.SaleVO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "date", nullable = false)
    private Date date;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = {CascadeType.REFRESH})
    private List<ProductSale> products;
    @Column(name = "total", nullable = false)
    private Double total;

    public static Sale create(SaleVO saleVO) {
        return new ModelMapper().map(saleVO, Sale.class);
    }
}
