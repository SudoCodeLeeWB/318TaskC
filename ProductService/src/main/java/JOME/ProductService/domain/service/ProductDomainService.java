package JOME.ProductService.domain.service;

import JOME.ProductService.domain.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductDomainService {


    public Product updateQuantity( Product product , int quantity){
        int newStock = product.getStock() + quantity;
        if (newStock < 0 ){
            newStock = 0;
        }
        product.setStock(newStock);

        // update recentUpdateTime
        product.updateRecentUpdateTime();

        return product;
    }

}
