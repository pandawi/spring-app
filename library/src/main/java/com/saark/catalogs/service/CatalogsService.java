package com.saark.catalogs.service;

import com.saark.catalogs.models.CatalogMappingRequest;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(CatalogsServiceProperties.class)
public class CatalogsService {
    
    private final CatalogsServiceProperties catalogsServiceProperties;

    public CatalogsService(CatalogsServiceProperties catalogsServiceProperties) {
        this.catalogsServiceProperties = catalogsServiceProperties;
    }

    public CatalogMappingRequest getMappingResponse(Long catalogId, Integer siteId) {
        return this.catalogsServiceProperties.getMappingResponse(catalogId, siteId);
    }

}
