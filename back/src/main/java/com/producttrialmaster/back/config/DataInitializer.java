package com.producttrialmaster.back.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producttrialmaster.back.dto.ProductImportDTO;
import com.producttrialmaster.back.mapper.ProductMapper;
import com.producttrialmaster.back.model.Product;
import com.producttrialmaster.back.repository.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadData(ProductRepository productRepository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();

            TypeReference<List<ProductImportDTO>> typeRef = new TypeReference<>() {};
            InputStream inputStream = getClass().getResourceAsStream("/data/products.json");

            if (inputStream != null) {
                List<ProductImportDTO> productDTOs = mapper.readValue(inputStream, typeRef);

                List<Product> products = productDTOs.stream()
                        .map(ProductMapper::toEntity)
                        .collect(Collectors.toList());

                productRepository.saveAll(products);
                System.out.println("✅ Produits chargés : " + products.size());
            } else {
                System.out.println("❌ Fichier JSON non trouvé.");
            }
        };
    }
}
