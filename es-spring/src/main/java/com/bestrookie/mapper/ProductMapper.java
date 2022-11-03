package com.bestrookie.mapper;

import com.bestrookie.dto.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bestrookie
 * @date 2022/11/3 15:44
 */
@Repository
public interface ProductMapper extends ElasticsearchRepository<Product,Long> {
}
