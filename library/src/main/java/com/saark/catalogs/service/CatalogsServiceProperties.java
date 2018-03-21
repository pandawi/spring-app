package com.saark.catalogs.service;

import com.saark.catalogs.models.CatalogMappingRequest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties("service")
public class CatalogsServiceProperties {

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    private String apiUrl;

    private final RestTemplate restTemplate;

    public CatalogsServiceProperties(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    public CatalogMappingRequest getMappingResponse(Long catalogId, Integer siteId) {
        return this.restTemplate.getForObject(this.apiUrl + "/" + catalogId + "/" + siteId, CatalogMappingRequest.class);
    }
    
}