package com.example.simplerestserver.product;

import com.example.simplerestserver.util.ResourceReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

public class ProductRepository {
    private static final String MOCK_DATA_PATH  = "static/data.txt";
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private Resource resource = resourceLoader.getResource("classpath:" + MOCK_DATA_PATH);



    public String HTMLSushiMasterCards() {
        String res = ResourceReader.asString(resource);

        return res;
    }
}
