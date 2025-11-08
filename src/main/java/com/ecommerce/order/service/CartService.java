package com.ecommerce.order.service;


import com.ecommerce.order.dto.CartDTO;
import com.ecommerce.order.models.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CartService {
    CartDTO addProductToCart(Long ProductId, Integer quantity);

    Cart getOrCreateUserCart();

    List<CartDTO> getAllCarts();

    CartDTO getCart(String cartId);

    @Transactional
    CartDTO updateProductQuantityInCart(Long productId, Integer quantity);

    String deleteProductFromCart(Long productId);
}
