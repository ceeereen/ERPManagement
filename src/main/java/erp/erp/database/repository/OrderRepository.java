package erp.erp.database.repository;


import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.OrderEntity;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAll();
    //OrderEntity findAllByNameIgnoreCase(String name);

    void  deleteOrderEntitiesByUuid(UUID uuid);
    //null ise false nul değilse true döner
    Optional<OrderEntity> findByUuid(UUID uuid);

}
