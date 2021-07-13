package com.qiaoyn.database.service;

import com.qiaoyn.database.model.TDict;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-09 15:55
 **/
public interface DictService {

    /**
     * 添加一条字典
     * @author yn.qiao
     * @param dict
     **/
    void add(TDict dict);

    /**
     * 删除公共字典
     * @author yn.qiao
     * @param dictId
     **/
    void delete(Long dictId);
}
