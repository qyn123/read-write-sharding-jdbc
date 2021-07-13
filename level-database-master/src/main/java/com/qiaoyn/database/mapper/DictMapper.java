package com.qiaoyn.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiaoyn.database.model.TDict;
import com.qiaoyn.database.model.dto.OrderDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 15:54
 **/
public interface DictMapper extends BaseMapper<TDict> {

    @Select("SELECT m.*,s.`value`\n" +
            "from t_dict s,t_order_1 m\n" +
            "where s.dict_id = m.`status`\n" +
            "and order_id = #{orderId}\n")
    OrderDto selectById(@Param("orderId") Long orderId);
}
