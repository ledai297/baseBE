package vn.sapo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.domain.Province;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    List<Province> findAllByCountryIdIn(Collection<Long> countryIds);

    List<Province> findByIdIn(Collection<Long> ids);
}
