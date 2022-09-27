package vn.sapo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.Address;
import vn.sapo.statics.AddressOwnerType;

import java.util.Collection;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByIdIn(List<Long> ids);

    List<Address> findByOwnerTypeAndOwnerId(AddressOwnerType ownerType, long ownerId);

    List<Address> findByOwnerTypeAndOwnerIdOrderByIdAsc(AddressOwnerType ownerType, long ownerId);

    List<Address> findByOwnerTypeAndOwnerIdIn(AddressOwnerType ownerType, Collection<Long> ownerIds);
}
