package com.qiaoyn.database.controller;

import com.qiaoyn.database.mapper.DictMapper;
import com.qiaoyn.database.model.TDict;
import com.qiaoyn.database.model.dto.OrderDto;
import com.qiaoyn.database.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共表:将公共表在每个库中都保存一份，然后再配置文件中声明公共表，如果插入公共表数据时，将会在每个库中都会插入一份
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 15:52
 **/
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    DictService dictService;
    @Autowired
    DictMapper dictMapper;



    @RequestMapping("/add")
    public void add(@RequestBody TDict dict){
        dictService.add(dict);
    }


    @RequestMapping("/delete/{dictId}")
    public void delete(@PathVariable Long dictId){
        dictService.delete(dictId);
    }

    @RequestMapping("/select/{orderId}")
    public OrderDto select(@PathVariable Long orderId){
        return dictMapper.selectById(orderId);
    }



}
