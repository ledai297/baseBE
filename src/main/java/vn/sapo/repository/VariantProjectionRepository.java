package vn.sapo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.projection.product.VariantDto;
import vn.sapo.statics.product.VariantStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface VariantProjectionRepository extends JpaRepository<VariantDto, Long> {
    List<VariantDto> findByProductIdOrderByIdAsc(long productId);

    @Query(value = "SELECT * FROM variant_filter(:categoryIds, :countryIds, :brandIds, :name, :status, :ids)", nativeQuery = true)
    List<VariantDto> filterVariant(
        @Param("categoryIds") String categoryIds,
        @Param("countryIds") String countryIds,
        @Param("brandIds") String brandIds,
        @Param("name") String name,
        @Param("status") String status,
        @Param("ids") String ids,
        Pageable pageable
    );

    @Query(value = "select v.barcode from Variant v where v.barcode in :barcodes", nativeQuery = true)
    List<String> getBarcodeAvailable(@Param("barcodes") List<String> barcodes);

    <T> List<T> findByIdIn(Collection<Long> variantIds, Class<T> type);
}
