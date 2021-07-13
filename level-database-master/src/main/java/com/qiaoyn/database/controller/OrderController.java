package com.qiaoyn.database.controller;

import com.qiaoyn.database.model.TOrder;
import com.qiaoyn.database.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 水平分库控制器
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 14:56
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/add")
    public void add(@RequestBody TOrder order){
        orderService.addOrder(order);
    }


    @RequestMapping("/all")
    public List<TOrder> all(){
        return orderService.selectAll();
    }

}
