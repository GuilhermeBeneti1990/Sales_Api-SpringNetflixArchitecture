package com.springnetflix.sales.api.controllers;

import com.springnetflix.sales.api.data.vo.SaleVO;
import com.springnetflix.sales.api.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;
    private final PagedResourcesAssembler<SaleVO> assembler;

    @Autowired
    public SaleController(SaleService saleService, PagedResourcesAssembler<SaleVO> assembler) {
        this.saleService = saleService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public SaleVO findById(@PathVariable("id") Long id) {
        SaleVO saleVO = saleService.findById(id);
        saleVO.add(linkTo(methodOn(SaleController.class).findById(id)).withSelfRel());
        return saleVO;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "date"));

        Page<SaleVO> sales = saleService.findAll(pageable);
        sales.stream().forEach(s -> s.add(linkTo(methodOn(SaleController.class).findById(s.getId())).withSelfRel()));

        PagedModel<EntityModel<SaleVO>> pagedModel = assembler.toModel(sales);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
                 consumes = {"application/json", "application/xml", "application/x-yaml"})
    public SaleVO create(@RequestBody SaleVO saleVO) {
        SaleVO saleVOCreated = saleService.create(saleVO);
        saleVOCreated.add(linkTo(methodOn(SaleController.class).findById(saleVOCreated.getId())).withSelfRel());
        return saleVOCreated;
    }

}
