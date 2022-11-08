package com.bestrookie.com.bestrookie.test;

import com.bestrookie.service.EsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author bestrookie
 * @date 2022/11/8 18:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {
    @Autowired
    private EsService esService;
    @Test
    public void test() throws IOException {
        System.out.println(esService.indexExists("bestrookie"));
    }
}
