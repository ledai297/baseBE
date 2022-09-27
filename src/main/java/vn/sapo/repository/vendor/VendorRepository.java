package vn.sapo.repository.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.vendor.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
