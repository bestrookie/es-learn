package com.bestrookie;

import com.bestrookie.dto.Product;
import com.bestrookie.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
public class SpringDataESProductDaoTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void save(){
        Product product = Product.builder().id(2L).title("华为手机").category("手机").price(2999.0).images("url").build();
        productMapper.save(product);

    }

    @Test
    public void update(){
        Product product = Product.builder().id(2L).title("小米手机").category("手机").price(2999.0).images("url").build();
        productMapper.save(product);
    }

    @Test
    public void findById(){
        Optional<Product> byId = productMapper.findById(2L);
        System.out.println(byId);
    }

    @Test
    public void findAll(){
        Iterable<Product> all = productMapper.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }

    @Test
    public void delete(){
        productMapper.deleteById(2L);
    }

    @Test
    public void saveAll(){
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            Product product = Product.builder().id((long)i).title("小米手机" + i).category("手机").images("url" + i).price(1999.0 + i).build();
            list.add(product);
        }
        productMapper.saveAll(list);
    }

    @Test
    public void findByPageable(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        int current = 0;
        int pageSize = 5;
        PageRequest request = PageRequest.of(current, pageSize, sort);
        Page<Product> all = productMapper.findAll(request);
        for (Product product : all) {
            System.out.println(product);
        }
    }


}
