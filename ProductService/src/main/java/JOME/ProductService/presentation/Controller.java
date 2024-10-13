package JOME.ProductService.presentation;


import JOME.ProductService.application.ProductService;
import JOME.ProductService.domain.valueObject.CategoryEnum;
import JOME.ProductService.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {


    private final ProductService productService;

    @Autowired
    public Controller(ProductService productService) {
        this.productService = productService;
    }


    // Test CURDs of DB


    // Add new product
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addNewProduct(@RequestBody ProductDTO productDTO) {

        // Call service layer to add the product
        ProductDTO response = productService.addNewProduct(productDTO);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PatchMapping("/updateStock/{productId}/{quantity}")
    public ResponseEntity<ProductDTO> updateProductQuantity( @PathVariable Long productId , @PathVariable int quantity) {

        // Call service layer to add the product
        ProductDTO response = productService.updateProductStock(productId, quantity);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> delteProduct( @PathVariable Long productId ) {

        // Call service layer to add the product
        String response = productService.deleteProduct(productId);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
