package com.qyc.service.biz;

import com.qyc.model.data.Product;

import java.util.List;

/**
 * @author qianyongchao
 * @date 2019/5/9 10:34
 */
public interface ProductService {

    List<Product> getProductList();

    void showInfo();
}
