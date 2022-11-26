package com.bazlur.eshoppers.repository;

import com.bazlur.eshoppers.dto.ProductDTO;
import com.bazlur.eshoppers.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

public class DummyProductRepositoryImpl implements ProductRepository
{
    @Override
    public List<ProductDTO> findAllProducts()
    {
        return List.of(
                new ProductDTO(
                        "Apple iPad",
                        "Apple iPad 10.2 32GB",
                        BigDecimal.valueOf(369.99)),
                new ProductDTO(
                        "Headphones",
                        "Jabra Ellite Blutooth Headphones",
                        BigDecimal.valueOf(249.99))

                );
    }
}
