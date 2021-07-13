package com.qiaoyn.database.service;

import com.qiaoyn.database.model.TOrder;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 14:58
 **/
public interface OrderService {

    /**
     * 添加订单
     * @author yn.qiao
     * @param order
     **/
    void addOrder(TOrder order);

    /**
     * 查询全部订单
     * @author yn.qiao
     * @return List
     **/
    List<TOrder> selectAll();
}
