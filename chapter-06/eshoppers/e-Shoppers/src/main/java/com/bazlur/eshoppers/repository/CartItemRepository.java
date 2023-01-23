package com.bazlur.eshoppers.repository;

import com.bazlur.eshoppers.domain.CartItem;

public interface CartItemRepository
{
    CartItem update(CartItem cartItem);

    CartItem save(CartItem cartItem);

    void remove(CartItem cartItem);
}
