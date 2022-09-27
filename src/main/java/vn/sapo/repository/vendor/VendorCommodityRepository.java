package vn.sapo.repository.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.vendor.VendorCommodity;

@Repository
public interface VendorCommodityRepository extends JpaRepository<VendorCommodity, Long> {
}
