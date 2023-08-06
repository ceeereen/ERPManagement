package erp.erp.database.repository;


import erp.erp.database.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findAll();

    CustomerEntity findAllByNameIgnoreCase(String name);

    void deleteCustomerEntitiesByUuid(UUID uuid);

    //null ise false nul değilse true döner
    Optional<CustomerEntity> findByUuid(UUID uuid);

}
