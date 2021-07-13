package com.qiaoyn.level.service;

import com.qiaoyn.level.model.TOrder;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-08 14:00
 **/
public interface OrderService {

    /**
     * 添加用户
     * @author yn.qiao
     * @param TOrder
     **/
    void addOrder(TOrder TOrder);


    /**
     * 查询订单
     * @author yn.qiao
     * @param orderId
     * @return  Order
     **/
    TOrder selectById(Long orderId);

    /**
     * 查询所有订单信息
     * @author yn.qiao
     * @return List
     **/
    List<TOrder> selectAll();
}
