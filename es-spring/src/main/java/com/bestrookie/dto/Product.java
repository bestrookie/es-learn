package com.bestrookie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author bestrookie
 * @date 2022/11/3 14:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "product",shards = 3,replicas = 1)
public class Product {
    @Id
    private Long id;
    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Keyword,index = false)
    private String images;
}
