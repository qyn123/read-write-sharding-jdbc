package com.qiaoyn.split.service.impl;

import com.qiaoyn.split.mapper.ProductDescriptMapper;
import com.qiaoyn.split.mapper.ProductInfoMapper;
import com.qiaoyn.split.model.ProductDescript;
import com.qiaoyn.split.model.ProductInfo;
import com.qiaoyn.split.model.dto.ProductInfoDto;
import com.qiaoyn.split.model.dto.RegionDto;
import com.qiaoyn.split.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-13 10:48
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;
    @Autowired
    ProductDescriptMapper productDescriptMapper;

    /**
     * 添加一条商品
     *
     * @param productInfo
     * @author yn.qiao
     **/
    @Transactional
    @Override
    public void addProductInfo(ProductInfo productInfo) {
        //插入商品信息
        productInfoMapper.addProductInfo(productInfo);
        //插入商品描述信息
        ProductDescript descript = new ProductDescript();
        descript.setProductInfoId(productInfo.getProductInfoId());
        descript.setStoreInfoId(productInfo.getStoreInfoId());
        descript.setDescript(productInfo.getDescript());
        productDescriptMapper.insert(descript);
    }

    /**
     * 查询全部商品信息
     *
     * @return List
     * @author yn.qiao
     **/
    @Override
    public List<ProductInfoDto> slectAll() {
        return productInfoMapper.selectProductInfoList();
    }

    /**
     * 查询全部商品信息分页
     *
     * @param pageNum
     * @param size
     * @return List
     * @author yn.qiao
     */
    @Override
    public List<ProductInfoDto> selectAllPage(Integer pageNum, Integer size) {
        int start = (pageNum - 1) * size;
        return productInfoMapper.selectProductInfoListPage(start, size);
    }

    /**
     * 测试聚合函数
     *
     * @return Integer
     * @author yn.qiao
     **/
    @Override
    public List<RegionDto> selectCount() {
        return productInfoMapper.count();
    }
}
