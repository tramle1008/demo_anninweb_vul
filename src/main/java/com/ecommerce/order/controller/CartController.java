package com.ecommerce.order.controller;



import com.ecommerce.order.dto.CartDTO;
import com.ecommerce.order.models.Cart;
import com.ecommerce.order.repository.CartRepository;
import com.ecommerce.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    CartService cartService;
//    @Autowired
//    AuthUtil authUtil;

    @Autowired
    CartRepository cartRepository;
    @PostMapping("/auth/cart/products/{productId}/quantity/{quantity}")
    public ResponseEntity<?> addProductToCart( @PathVariable Long productId,  @PathVariable Integer quantity){
        try {
            return new ResponseEntity<>(cartService.addProductToCart(productId, quantity), HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace(); // để in ra log xem lỗi gì
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/admin/carts")
    public ResponseEntity<List<CartDTO>> getCarts(){
        List<CartDTO> cartDTOS = cartService.getAllCarts();
        return new ResponseEntity<List<CartDTO>>(cartDTOS,HttpStatus.FOUND);
    }

    @GetMapping("/auth/carts/user/cart/{id}")
    public ResponseEntity<?> getCartById(@PathVariable Long id) {
        // Tìm cart theo id
        Cart userCart = cartRepository.findById(id).orElse(null);

        if (userCart == null) {
            // Trả về giỏ hàng rỗng hoặc thông báo
            CartDTO emptyCart = new CartDTO();
            emptyCart.setTotalPrice(0.0);
            emptyCart.setProducts(new ArrayList<>());
            return ResponseEntity.ok(emptyCart);
        }

        // Lấy cartId để truyền vào service
        String cartId = userCart.getCartId();
        CartDTO cartDTO = cartService.getCart(cartId);

        return ResponseEntity.ok(cartDTO);
    }


    @PutMapping("/auth/user/cart/products/{productId}/quantity/{operation}")
    public ResponseEntity<CartDTO> updateCartProduct(@PathVariable  Long productId,@PathVariable String operation){
        CartDTO cartDTO = cartService.updateProductQuantityInCart(productId, operation.equalsIgnoreCase("delete") ? -1 : 1 );
        return  new ResponseEntity<CartDTO>(cartDTO, HttpStatus.OK);
    }

    @DeleteMapping("/auth/user/cart/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(
            @PathVariable Long productId){
        String status = cartService.deleteProductFromCart( productId);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

}
