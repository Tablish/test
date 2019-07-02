package com.qyc.service.biz.impl;

import com.qyc.mapper.dc.ProductMapper;
import com.qyc.model.data.Product;
import com.qyc.service.biz.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author qianyongchao
 * @Description
 * @Date 2019/5/9 10:34
 * @Modified By
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    /**
     * 获取套餐列表
     *
     * @return
     */
    @Override
    public List<Product> getProductList() {
        return productMapper.getProductList();
    }

    @Override
    public void showInfo() {
        System.out.println("来自放羊的星星");
    }
}
