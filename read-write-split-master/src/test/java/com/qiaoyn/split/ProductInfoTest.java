package com.qiaoyn.split;

import com.qiaoyn.split.model.ProductInfo;
import com.qiaoyn.split.model.dto.ProductInfoDto;
import com.qiaoyn.split.service.ProductInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-13 11:22
 **/
@SpringBootTest
public class ProductInfoTest {

    @Autowired
    ProductInfoService productInfoService;

    @Test
    void testAddProductInfo(){
       for(int i = 0; i< 10;i++){
           ProductInfo info = new ProductInfo();
           info.setProductName("清凉一夏" + i);
           info.setDescript("这个夏天清凉一夏" + i);
           info.setPrice(new BigDecimal("23.23"));
           info.setImageUrl("https://pics4.baidu.com/feed/d50735fae6cd7b89e6a62a0573a489afdb330ef2.jpeg?token=083cd9364b4c78e91492c962eb48490f");
           info.setRegionCode("110100");
           info.setSpec("哈士奇" + i);
           info.setStoreInfoId(1L);
           productInfoService.addProductInfo(info);
       }
    }

    @Test
    void testSelectAll(){
        List<ProductInfoDto> list = productInfoService.slectAll();
        System.out.println(list);
        System.out.println(list.size());
    }


    @Test
    void testSelectAllPage(){
        System.out.println(productInfoService.selectAllPage(1, 5));
    }


    @Test
    void testCount(){
        System.out.println(productInfoService.selectCount());
    }
}
