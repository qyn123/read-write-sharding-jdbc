package com.qiaoyn.split.service;

import com.qiaoyn.split.model.ProductInfo;
import com.qiaoyn.split.model.dto.ProductInfoDto;
import com.qiaoyn.split.model.dto.RegionDto;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-13 10:46
 **/
public interface ProductInfoService {

    /**
     * 添加一条商品
     * @author yn.qiao
     * @param productInfo
     **/
    void addProductInfo(ProductInfo productInfo);

    /**
     * 查询全部商品信息
     * @author yn.qiao
     * @return List
     **/
    List<ProductInfoDto> slectAll();


    /**
     * 查询全部商品信息分页
     * @author yn.qiao
     * @param pageNum
     * @param size
     * @return List
     **/
    List<ProductInfoDto> selectAllPage(Integer pageNum,Integer size);



    /**
     * 测试聚合函数
     * @author yn.qiao
     * @return Integer
     **/
    List<RegionDto> selectCount();
}
