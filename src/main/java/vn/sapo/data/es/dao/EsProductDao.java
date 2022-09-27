package vn.sapo.data.es.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.data.es.dto.product.EsProduct;

@Repository
public interface EsProductDao extends ElasticsearchRepository<EsProduct, Long> {
}
