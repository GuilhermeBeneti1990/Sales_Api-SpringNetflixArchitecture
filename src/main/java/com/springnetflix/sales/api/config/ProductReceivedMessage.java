package com.springnetflix.sales.api.config;

import com.springnetflix.sales.api.data.vo.ProductVO;
import com.springnetflix.sales.api.entities.Product;
import com.springnetflix.sales.api.repositories.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProductReceivedMessage {

    private final ProductRepository productRepository;

    @Autowired
    public ProductReceivedMessage(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RabbitListener(queues = {"${products-api.rabbitmq.queue}"})
    public void receive(@Payload ProductVO productVO) {
        productRepository.save(Product.create(productVO));
    }

}
