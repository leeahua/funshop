package com.next.funshop.service;


import com.next.funshop.domains.ProductCategory;
import com.next.funshop.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理商品类目的业务逻辑
 * @Author liyaohua
 * @Date 20180116
 * */
@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    /**
     * 根据类目ID获取类目信息
     * @param   categoryId
     * @return  ProductCategory
     * */
    public ProductCategory findOne(int categoryId){
        return productCategoryRepository.findOne(categoryId);
    }

    /**
     * 保存类目信息
     * @param productCategory
     * */
    public void save(ProductCategory productCategory){
        productCategoryRepository.save(productCategory);
    }
}
