package com.saark.catalogs.service;

import com.saark.catalogs.models.CatalogMappingRequest;
import com.saark.catalogs.models.ResponseStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest("service.apiUrl=http://localhost:3000/api/mapping")
public class CatalogsServiceTest {

    @Autowired
    private CatalogsService catalogsService;

    @Test
    public void contextLoads() {
        assertThat(catalogsService.apiUrl()).isNotNull();
    }

    @Test
    public void testResponse() {
        CatalogMappingRequest request = catalogsService.getMappingResponse((long) 47, 0);
        assertEquals(request.getResponseStatus(), ResponseStatus.SUCCESS);
    }

    @SpringBootApplication
    static class TestConfiguration {
    }

}