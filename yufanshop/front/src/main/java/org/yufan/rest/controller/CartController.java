package org.yufan.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yufan.common.Result;
import org.yufan.bean.CartItem;
import org.yufan.rest.service.CartService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1")Long num, HttpServletRequest request, HttpServletResponse response){
        Result result=cartService.addCartItem(itemId,num,request,response);
        return "redirect:/cart/success";
    }
    @RequestMapping("/success")
    public String showSuccess(){
        return "cartsuccess";
    }

    @RequestMapping("/cart")
    public String showCart(HttpServletRequest request, HttpServletResponse response, Model model){
        List<CartItem> list=cartService.getCartItemList(request,response);
        model.addAttribute("cartList",list);
        return "cart";
    }
    @RequestMapping("/delete/{itemId}")
    public String updateCart(HttpServletRequest request,HttpServletResponse response,@RequestParam Long itemId){
        cartService.delCartItem(itemId,request,response);
        return "redirect:/cart/cart";
    }


}
