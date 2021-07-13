package com.qiaoyn.database.service.impl;

import com.qiaoyn.database.mapper.DictMapper;
import com.qiaoyn.database.model.TDict;
import com.qiaoyn.database.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 15:55
 **/
@Service
public class DictServiceImpl implements DictService {


    @Autowired
    DictMapper dictMapper;

    /**
     * 添加一条字典
     * @param dict
     * @author yn.qiao
     **/
    public void add(TDict dict) {
        dictMapper.insert(dict);
    }

    /**
     * 删除公共字典
     *
     * @param dictId
     * @author yn.qiao
     **/
    public void delete(Long dictId) {
        dictMapper.deleteById(dictId);
    }
}
