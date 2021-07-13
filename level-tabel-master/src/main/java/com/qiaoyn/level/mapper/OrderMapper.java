package com.qiaoyn.level.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiaoyn.level.model.TOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-08 13:59
 **/
public interface OrderMapper extends BaseMapper<TOrder> {

    /**
     * 查询订单信息
     *
     * @param orderId 订单id
     * @return TOrder
     * @author yn.qiao
     **/
    @Select("SELECT order_id,price,user_id,status FROM t_order WHERE order_id= #{orderId}")
    TOrder selectOrdersById(@Param("orderId") Long orderId);
}
