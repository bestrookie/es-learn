package com.bestrookie.mapper;

import com.bestrookie.dto.Product;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bestrookie
 * @date 2022/11/3 15:44
 */
@Repository
public interface ProductMapper extends ElasticsearchRepository<Product,Long> {
    List<Product> findProductByCategoryAndIdBetween(String category,Long ge,Long le);

}
