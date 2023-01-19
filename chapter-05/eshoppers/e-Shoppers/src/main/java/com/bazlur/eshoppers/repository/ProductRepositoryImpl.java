package com.bazlur.eshoppers.repository;

import com.bazlur.eshoppers.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository
{
    private static final List<Product> ALL_PRODUCTS = List.of(
            new Product(
                    1L,
                    "Apple iPad",
                    "Apple iPad 10.2 32GB",
                    BigDecimal.valueOf(369.99)),
            new Product(
                    2L,
                    "Headphones",
                    "Jabra Ellite Blutooth Headphones",
                    BigDecimal.valueOf(249.99))
            );

    @Override
    public List<Product> findAllProducts()
    {
        return ALL_PRODUCTS;
    }
}
