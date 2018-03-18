package com.saark.catalogs.service;

import com.saark.catalogs.models.CatalogMappingRequest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties("service")
public class CatalogsServiceProperties {

    public CatalogMappingRequest getMappingResponse(Long catalogId, Integer siteId) {
        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject("http://10.64.203.119/catalogmapping/getcatalogmapping/json/" + catalogId + "/" + siteId, CatalogMappingRequest.class);
        return restTemplate.getForObject("http://localhost:3000/api/mapping/" + catalogId + "/" + siteId, CatalogMappingRequest.class);
    }
    
}