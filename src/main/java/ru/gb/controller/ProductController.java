package ru.gb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gb.dao.ProductDao;
import ru.gb.entity.Product;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product findById(@PathVariable Long id) {
        return productDao.findById(id).orElseThrow();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Product> save(@RequestBody Product product) {
        productDao.saveOrUpdate(product);
        return productDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        productDao.deleteById(id);
    }

}