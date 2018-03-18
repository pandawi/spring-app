package com.saark.app;

import com.saark.app.models.CatalogReport;
import com.saark.app.models.ReportRow;
import com.saark.catalogs.domain.Catalog;
import com.saark.catalogs.models.CatalogMappingRequest;
import com.saark.catalogs.service.CatalogsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

@SpringBootApplication(scanBasePackages = "com.saark")
public class Application {

    private final CatalogsService catalogsService;

    Application(CatalogsService catalogsService) {
        this.catalogsService = catalogsService;
        CatalogMappingRequest catalogMappingRequest = catalogsService.getMappingResponse((long) 47, 0);
        for (Catalog c: catalogMappingRequest.getMappingList()) {
//            System.out.println(this.mapCatalog(c));
        }

        Stream<Catalog> catalogStream = catalogMappingRequest.getMappingList().stream();

        Stream<CatalogReport> catalogReportStream = catalogStream.map(a -> {
            CatalogReport c = new CatalogReport();
            c.setCatalogId(a.getCatalogId());
            c.setCategoryName(a.getCategoryName());
            c.setSiteID(a.getSiteID());
            c.setCategoryId(a.getCategoryId());
            return c;
        }).sorted(Comparator.comparing(CatalogReport::getCategoryId));

//        CatalogReport[] catalogs = catalogReportStream.toArray(CatalogReport[]::new);

        Map<ReportRow, List<CatalogReport>> listMap = catalogReportStream
                .collect(groupingBy(catalogReport -> new ReportRow(catalogReport.getSiteID(), this.mapCatalog(catalogReport.getCatalogId(), catalogReport.getCategoryName(), catalogReport.getCategoryId()))));

        System.out.println("hi");
    }

    private String mapCatalog(Long catalogId, String categoryName, Long categoryId) {
        return "Catalog " + catalogId + " is mapped to category " + categoryName + " (" + categoryId + ")";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}