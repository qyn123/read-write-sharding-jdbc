package com.qiaoyn.split.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiaoyn.split.model.ProductInfo;
import com.qiaoyn.split.model.dto.ProductInfoDto;
import com.qiaoyn.split.model.dto.RegionDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-13 10:41
 **/
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {


    /**
     * 测试插入
     * @author yn.qiao
     * @param productInfo
     * @return void
     **/
    @Insert("INSERT INTO product_info(store_info_id, product_name, spec, region_code, price, image_url) " +
            "VALUES (#{storeInfoId},#{productName},#{spec}, #{regionCode}, #{price}, #{imageUrl})")
    @Options(keyProperty="productInfoId", keyColumn="product_info_id")
    void addProductInfo(ProductInfo productInfo);




    /**
     * 不支持跨节点left....join关联查询
     * 测试分页，关联查询，排序
     * @author yn.qiao
     **/
/*  @Select("select p.product_info_id,p.product_name,p.spec,p.price,p.image_url, r.region_name,s.store_name from \n" +
            "product_info p\n" +
            "LEFT JOIN region  r\n" +
            "on p.region_code = r.region_code\n" +
            "LEFT JOIN store_db.store_info s\n" +
            "on p.store_info_id = s.id")*/
   @Select("select p.product_info_id,p.product_name,p.spec,p.price,p.image_url, r.region_name from \n" +
            "product_info p\n" +
            "LEFT JOIN region  r\n" +
            "on p.region_code = r.region_code")
    List<ProductInfoDto> selectProductInfoList();


    @Select("select p.product_info_id,p.product_name,p.spec,p.price,p.image_url, r.region_name from \n" +
            "product_info p\n" +
            "LEFT JOIN region  r\n" +
            "on p.region_code = r.region_code  order by product_info_id desc limit #{pageNum},#{pageSize}")
    List<ProductInfoDto> selectProductInfoListPage(@Param("pageNum")Integer pageNum,@Param("pageSize") Integer pageSize);

    /**
     * 测试聚合函数:
     * 支持：(max,min,sum,avg),分组(group by),计数(count)，简单的去重(distinct)
     * 不支持having等
     * @author yn.qiao
     * @return  Integer
     **/
    @Select("select r.region_name as regionName,count(1) as regionCount,max(product_info_id) as productInfoId from product_info p " +
            "LEFT JOIN region r on  p.region_code = r.region_code " +
            "GROUP BY region_name")
    /*@Select("select DISTINCT(region_code) as regionName from product_info ")*/
    List<RegionDto> count();
}
