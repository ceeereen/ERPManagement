package erp.erp.service;

import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.ProductEntity;
import erp.erp.database.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    //prodcuctEntity requested By requestController in controller
    public ProductEntity createProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
        return productEntity;
    }

    //finds all products in database
    public List<ProductEntity> getProductList(){
        return productRepository.findAll();
    }

    public ProductEntity getProductByName(String name){
        return productRepository.findAllByNameIgnoreCase(name);
    }
    //will be used for delete
    public ProductEntity getProductByUUID(UUID uuid){
        Optional<ProductEntity> productEntityOptional = productRepository.findByUuid(uuid);

        if(productEntityOptional.isPresent()){
            return  productEntityOptional.get();
        }else {
            return null;
        }
    }

    @Transactional
    public  Boolean deleteProductByUUID(UUID uuid){
        ProductEntity productEntity = getProductByUUID(uuid);

        if(productEntity!=null){
            productRepository.deleteProductEntitiesByUuid(uuid);
            return true;
        }else {
            return  false;
        }
    }

    public  ProductEntity updateProductByUUID(UUID uuid, ProductEntity newProductEntity){
        ProductEntity productEntity = getProductByUUID(uuid);

        if(productEntity!=null){
            productEntity.setName(newProductEntity.getName());
            productEntity.setPrice(newProductEntity.getPrice());
            productEntity.setStockAmount(newProductEntity.getStockAmount());
            productEntity.setIsKdvApplicable(newProductEntity.getIsKdvApplicable());

            productRepository.save(productEntity);
            return productEntity;
        }else{
            return null;
        }
    }
}
