package JOME.ProductService.domain.factory;

import JOME.ProductService.domain.entity.Product;
import JOME.ProductService.domain.valueObject.CategoryEnum;
import JOME.ProductService.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductFactory {

    public Product createProduct(ProductDTO productDTO){

        if(productDTO == null){
            return null;
        }

        if(productDTO.price < 0){
            return null;
        }

        if(productDTO.stock< 0){
            return null;
        }

        Product product =new Product(
                productDTO.name,
                productDTO.description,
                productDTO.price,
                productDTO.stock,
                productDTO.category,
                LocalDateTime.now()
                );

        return product;

    }




}
