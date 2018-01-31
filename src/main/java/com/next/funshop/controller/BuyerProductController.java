package com.next.funshop.controller;

import com.next.funshop.domains.ProductCategory;
import com.next.funshop.domains.ProductInfo;
import com.next.funshop.enums.ProductStatusEnum;
import com.next.funshop.service.ProductCategoryService;
import com.next.funshop.service.ProductService;
import com.next.funshop.utils.ResultVOUtil;
import com.next.funshop.vo.ProductInfoVO;
import com.next.funshop.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 描述：〈商品api〉
 *
 * @author liyaohua
 * @create 2018/1/31
 * @since 1.0.0
 */
@RestController
@RequestMapping("/buyer/product")
@Slf4j
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * @Description: <br>
     *  获取商品列表
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/1/31 下午3:46
     */
    @RequestMapping("/list")
    public Object list(){
        log.info("【获取商品列表】");
        List<ProductInfo> productInfoList = productService.findByProductStatus(ProductStatusEnum.UP.getCode());
        //TODO 商品类目会不会重复
        Set<Integer> categoryList = productInfoList.stream().map(productInfo -> productInfo.getCategoryType())
                .collect(Collectors.toSet());

        List<ProductVO> productVOList = new ArrayList<>();
        for(Integer category : categoryList){
            ProductCategory productCategory = productCategoryService.findOne(category);
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                ProductInfoVO productInfoVO = new ProductInfoVO();
                BeanUtils.copyProperties(productInfo,productInfoVO);
                productInfoVOList.add(productInfoVO);
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
