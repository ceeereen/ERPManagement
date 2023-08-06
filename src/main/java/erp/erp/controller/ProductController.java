package erp.erp.controller;

import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.ProductEntity;
import erp.erp.model.Customer;
import erp.erp.model.Product;
import erp.erp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController

public class ProductController {

    @Autowired
    ProductService productService;



    //in order to list all products.
    @GetMapping("product")
    public ResponseEntity<List<ProductEntity>> getProductList() {

        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }

    @GetMapping("product/{name}")
    public ResponseEntity<ProductEntity> getProduct(@PathVariable String name) {

        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
    }


    //save product to db.
    @PostMapping("product")
    public ResponseEntity<ProductEntity> createCustomer(@RequestBody ProductEntity product) throws Exception {

        ProductEntity product1 = productService.createProduct(product);


        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }

   @DeleteMapping("product/{uuid}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable UUID uuid) {

        Boolean isDeleted = productService.deleteProductByUUID(uuid);
        if(isDeleted){
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.NOT_FOUND);

        }else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);

        }
    }

    //update
    @PutMapping("product/{uuid}")
    public ResponseEntity<ProductEntity> updateProductByUUID(@PathVariable UUID uuid, @RequestBody ProductEntity newProductEntity){

        ProductEntity productEntity = productService.updateProductByUUID(uuid, newProductEntity);
        if(productEntity!=null){
            return new ResponseEntity<>(productEntity, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
