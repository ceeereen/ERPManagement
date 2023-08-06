package erp.erp.database.repository;

import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAll();
    ProductEntity findAllByNameIgnoreCase(String name);

    void  deleteProductEntitiesByUuid(UUID uuid);
    //null ise false nul değilse true döner
    Optional<ProductEntity> findByUuid(UUID uuid);

}
