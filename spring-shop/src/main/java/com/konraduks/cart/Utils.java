package com.konraduks.cart;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	// Products in Cart, stored in Session.
    public static Cart getCartInSession(HttpServletRequest request) {
 
        // Get Cart from Session.
        Cart cart = (Cart) request.getSession().getAttribute("myCart");
        
        // If null, create it.
        if (cart == null) {
            cart = new Cart();
            
            // And store to Session.
            request.getSession().setAttribute("myCart", cart);
        }
 
        return cart;
    }
    
    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }
}
