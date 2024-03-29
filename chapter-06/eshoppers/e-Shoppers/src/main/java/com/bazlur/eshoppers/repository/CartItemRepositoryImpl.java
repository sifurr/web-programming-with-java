package com.bazlur.eshoppers.repository;

import com.bazlur.eshoppers.domain.Cart;
import com.bazlur.eshoppers.domain.CartItem;
import com.bazlur.eshoppers.domain.User;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class CartItemRepositoryImpl implements CartItemRepository
{

    private static final Set<CartItem> CARTS
            = new CopyOnWriteArraySet<>();


    @Override
    public CartItem update(CartItem cartItem)
    {
        CARTS.add(cartItem);
        return cartItem;
    }

    @Override
    public CartItem save(CartItem cartItem){
        CARTS.add(cartItem);
        return cartItem;
    }

    @Override
    public void remove(CartItem cartItem)
    {
        CARTS.remove(cartItem);
    }
}
