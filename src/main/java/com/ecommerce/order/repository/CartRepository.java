package com.ecommerce.order.repository;
import com.ecommerce.order.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

//    @Query("SELECT c FROM Carts c WHERE c.user.email = ?1")
//    Carts findCartByEmail(String currentUserEmail);


    Cart findByUser_EmailAndCartId(String email, Long cartId);

    Cart findByUser_Email(String emailId);
}
