package com.bazlur.eshoppers.service;

import com.bazlur.eshoppers.domain.Cart;
import com.bazlur.eshoppers.domain.User;
import com.bazlur.eshoppers.repository.CartRepository;

import java.util.Optional;

public class CartServiceImpl implements CartService
{
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository)
    {
        this.cartRepository = cartRepository;

    }

    @Override
    public Cart getCartByUser(User currentUser)
    {
        Optional<Cart> optionalCart
                = cartRepository.findByUser(currentUser);
        return optionalCart.orElseGet(() -> createNewCart(currentUser));
    }

    private Cart createNewCart(User currentUser)
    {
        Cart cart = new Cart();
        cart.setUser(currentUser);

        return cartRepository.save(cart);
    }
}
