package com.mycompany.myapp.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mycompany.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mycompany.myapp.domain.User.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Authority.class.getName());
            createCache(cm, com.mycompany.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mycompany.myapp.domain.BiddingQuotationHeader.class.getName());
            createCache(cm, com.mycompany.myapp.domain.BiddingQuotationHeader.class.getName() + ".roundInfos");
            createCache(cm, com.mycompany.myapp.domain.Opener.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Opener.class.getName() + ".bqHeaders");
            createCache(cm, com.mycompany.myapp.domain.BiddingRoundInfo.class.getName());
            createCache(cm, com.mycompany.myapp.domain.BiddingRoundInfo.class.getName() + ".vendorRounds");
            createCache(cm, com.mycompany.myapp.domain.BiddingRoundInfo.class.getName() + ".materialRounds");
            createCache(cm, com.mycompany.myapp.domain.VendorRound.class.getName());
            createCache(cm, com.mycompany.myapp.domain.MaterialRound.class.getName());
            createCache(cm, com.mycompany.myapp.domain.MaterialRound.class.getName() + ".quotationInfos");
            createCache(cm, com.mycompany.myapp.domain.MaterialRound.class.getName() + ".bomTemplateHeaders");
            createCache(cm, com.mycompany.myapp.domain.BomTemplateHeader.class.getName());
            createCache(cm, com.mycompany.myapp.domain.BomTemplateHeader.class.getName() + ".infos");
            createCache(cm, com.mycompany.myapp.domain.BomTemplateInfo.class.getName());
            createCache(cm, com.mycompany.myapp.domain.BomTemplateInfo.class.getName() + ".columns");
            createCache(cm, com.mycompany.myapp.domain.BomTemplateInfoColumn.class.getName());
            createCache(cm, com.mycompany.myapp.domain.QuotationInfo.class.getName());
            createCache(cm, com.mycompany.myapp.domain.PriceCompare.class.getName());
            createCache(cm, com.mycompany.myapp.domain.PriceCompare.class.getName() + ".quotationInfos");
            createCache(cm, com.mycompany.myapp.domain.PriceCompare.class.getName() + ".poHeaders");
            createCache(cm, com.mycompany.myapp.domain.Vendor.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Vendor.class.getName() + ".vendorRounds");
            createCache(cm, com.mycompany.myapp.domain.Material.class.getName());
            createCache(cm, com.mycompany.myapp.domain.Material.class.getName() + ".materialRounds");
            createCache(cm, com.mycompany.myapp.domain.PoHeader.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
