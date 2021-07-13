package com.qiaoyn.database.service.impl;

import com.qiaoyn.database.mapper.OrderMapper;
import com.qiaoyn.database.model.TOrder;
import com.qiaoyn.database.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 14:59
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 添加订单
     * @param order
     * @author yn.qiao
     **/
    public void addOrder(TOrder order) {
        orderMapper.insert(order);
    }

    /**
     * 查询全部订单
     *
     * @return List
     * @author yn.qiao
     **/
    public List<TOrder> selectAll() {
        return orderMapper.selectList(null);
    }
}
