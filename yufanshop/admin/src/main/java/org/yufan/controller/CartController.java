package org.yufan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yufan.common.Result;
import org.yufan.common.ResultUtils;
import org.yufan.service.CartService;

@Controller
@RequestMapping("/cart")
@ResponseBody
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/add")
    @ResponseBody
        public Result addCart(Long user_id,Long item_id,Long num){
            cartService.addItemToCart(item_id,user_id,num);
            return ResultUtils.buildSuccess();
        }
        @RequestMapping("/del")
        @ResponseBody
        public Result delCart(Long cart_id){
            cartService.delCartItem(cart_id);
            return ResultUtils.buildSuccess();
        }
        @RequestMapping("/clear")
        @ResponseBody
        public Result clearCart(Long user_id){
            cartService.clearCart(user_id);
            return ResultUtils.buildSuccess();
        }

        @RequestMapping("/query")
        @ResponseBody
        public Result query(Long user_id){
            return ResultUtils.buildSuccess(cartService.queryCartList(user_id));
        }
}
