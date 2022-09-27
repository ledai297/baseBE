package vn.sapo.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@Configuration
@EnableElasticsearchRepositories(basePackages = "org/springframework/data/elasticsearch/repositories")
@ConfigurationProperties(prefix = "application.data.elasticsearch")
public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {
    private String host;
    private String port;
    private Boolean useSsl;

    @Value("${application.data.elasticsearch.indexes.product.name}")
    private String productIndexName;

    @Bean
    public String productIndexName(){
        return productIndexName;
    }

    @Value("${application.data.elasticsearch.indexes.variant.name}")
    private String variantIndexName;

    @Bean
    public String variantIndexName(){
        return variantIndexName;
    }

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration;
        ClientConfiguration.MaybeSecureClientConfigurationBuilder clientConfigurationBuilder =
            org.springframework.data.elasticsearch.client.ClientConfiguration.builder()
            .connectedTo(host + ":" + port);
        if(useSsl)
            clientConfiguration = clientConfigurationBuilder
                .usingSsl()
                .build();
        else
            clientConfiguration = clientConfigurationBuilder
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }

    @Bean
    public EntityMapper entityMapper() {
        ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(
            elasticsearchMappingContext(),
            new DefaultConversionService());
        entityMapper.setConversions(elasticsearchCustomConversions());
        return entityMapper;
    }

    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(
            Arrays.asList(
                DateToStringConverter.INSTANCE,
                InstantToStringConverter.INSTANCE,
                StringToDateConverter.INSTANCE,
                StringToInstantConverter.INSTANCE
            )
        );
    }

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ");
    @WritingConverter
    enum DateToStringConverter implements Converter<Date, String> {
        INSTANCE;
        @Override
        public String convert(Date date) {
            return formatter.format(date);
        }
    }
    @WritingConverter
    enum InstantToStringConverter implements Converter<Instant, String> {
        INSTANCE;
        @Override
        public String convert(Instant instant) {
            return formatter.format(Date.from(instant));
        }
    }

    @ReadingConverter
    enum StringToInstantConverter implements Converter<String, Instant> {
        INSTANCE;
        @Override
        public Instant convert(String s) {
            try {
                return formatter.parse(s).toInstant();
            } catch (ParseException e) {
                return null;
            }
        }
    }

    @ReadingConverter
    enum StringToDateConverter implements Converter<String, Date> {
        INSTANCE;
        @Override
        public Date convert(String s) {
            try {
                return formatter.parse(s);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Boolean getUseSsl() {
        return useSsl;
    }

    public void setUseSsl(Boolean useSsl) {
        this.useSsl = useSsl;
    }
}
