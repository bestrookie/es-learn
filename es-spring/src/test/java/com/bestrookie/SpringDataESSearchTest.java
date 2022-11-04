package com.bestrookie;

import com.bestrookie.dto.Product;
import com.bestrookie.mapper.ProductMapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author bestrookie
 * @version 1.0
 * @date 2022/11/3 21:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESSearchTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void termQuery(){
        List<Product> list = productMapper.findProductByCategoryAndIdBetween("手机",5L,9L);
        for (Product product : list) {
            System.out.println(product);
        }
        }
    }


