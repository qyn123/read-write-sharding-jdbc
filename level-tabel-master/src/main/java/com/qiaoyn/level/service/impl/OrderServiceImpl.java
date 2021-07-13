package com.qiaoyn.level.service.impl;

import com.qiaoyn.level.mapper.OrderMapper;
import com.qiaoyn.level.model.TOrder;
import com.qiaoyn.level.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-08 14:01
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 添加用户
     *
     * @param TOrder
     * @author yn.qiao
     **/
    @Override
    public void addOrder(TOrder TOrder) {
        orderMapper.insert(TOrder);
    }

    /**
     * 查询订单
     *
     * @param orderId
     * @return Order
     * @author yn.qiao
     **/
    @Override
    public TOrder selectById(Long orderId) {
        System.out.println(orderId);
        return orderMapper.selectOrdersById(orderId);
    }

    /**
     * 查询所有订单信息
     *
     * @return List
     * @author yn.qiao
     **/
    @Override
    public List<TOrder> selectAll() {
        return orderMapper.selectList(null);
    }
}
