package vn.sapo.data.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.data.es.dto.product.EsVariant;

@Repository
public interface EsVariantDao extends ElasticsearchRepository<EsVariant, Long> {
}
