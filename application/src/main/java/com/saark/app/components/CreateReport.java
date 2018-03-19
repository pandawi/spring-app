package com.saark.app.components;

import com.saark.catalogs.domain.Catalog;
import com.saark.catalogs.models.CatalogMappingRequest;
import com.saark.catalogs.models.ResponseStatus;
import com.saark.catalogs.service.CatalogsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CreateReport {

    private CatalogsService catalogsService;
    private ArrayList<Catalog> allMapping = new ArrayList<>();
    private HashMap<Integer, List<Catalog>> catalogHashMap = new HashMap<>();

    public CreateReport(CatalogsService catalogsService) {
        this.catalogsService = catalogsService;
    }

    public void generateReport() {
        this.getAllMapping();
        this.setupHashMap();
        this.printToConsole();
    }

    private void getAllMapping() {
        for (int i = 40; i <= 101; i++) {
            CatalogMappingRequest catalogMappingRequest = this.catalogsService.getMappingResponse((long) i, -1);
            if (catalogMappingRequest.getResponseStatus() == ResponseStatus.SUCCESS) {
                this.allMapping.addAll(catalogMappingRequest.getMappingList());
            }
        }
    }

    private void setupHashMap() {
        for (Catalog c : this.allMapping) {
            if (this.catalogHashMap.get(c.getSiteID()) != null) {
                this.catalogHashMap.get(c.getSiteID()).add(c);
            } else {
                ArrayList<Catalog> list = new ArrayList<>();
                list.add(c);
                this.catalogHashMap.put(c.getSiteID(), list);
            }
        }
    }

    private void printToConsole() {
        for (Map.Entry<Integer, List<Catalog>> listEntry : this.catalogHashMap.entrySet()) {
            List<Catalog> currentValue = listEntry.getValue();
            currentValue.sort(Catalog::compareTo);
            System.out.println("For Site " + listEntry.getKey());
            for (Catalog catalog : currentValue) {
                String row = this.stringifyCatalogData(catalog.getCatalogId(), catalog.getCategoryName(), catalog.getCategoryId());
                System.out.println(row);
            }
        }
    }

    private String stringifyCatalogData(Long catalogId, String categoryName, Long categoryId) {
        return "Catalog " + catalogId + " is mapped to category " + categoryName + " (" + categoryId + ")";
    }
}
