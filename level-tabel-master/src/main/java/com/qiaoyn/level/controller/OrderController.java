package com.qiaoyn.level.controller;

import com.qiaoyn.level.model.TOrder;
import com.qiaoyn.level.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 水平分表控制器
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-08 14:03
 **/
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;



    @PostMapping("/addOrder")
    public void addOrder(@RequestBody TOrder tOrder){
        System.out.println(tOrder);
        orderService.addOrder(tOrder);
    }

    @GetMapping("/select/{orderId}")
    public TOrder selectOrderById(@PathVariable Long orderId){
        return orderService.selectById(orderId);
    }

    @GetMapping("/select/all")
    public List<TOrder> selectAll(){
        return orderService.selectAll();
    }
}
