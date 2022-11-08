package com.bestrookie.service;

import java.io.IOException;

/**
 * @author bestrookie
 * @date 2022/11/8 18:32
 */
public interface EsService {
    void addIndex(String name) throws IOException;
    boolean indexExists(String name) throws IOException;
}
