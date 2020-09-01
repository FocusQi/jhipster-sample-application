package com.mega.project.srm.config;

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
            createCache(cm, com.mega.project.srm.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.mega.project.srm.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.mega.project.srm.domain.User.class.getName());
            createCache(cm, com.mega.project.srm.domain.Authority.class.getName());
            createCache(cm, com.mega.project.srm.domain.User.class.getName() + ".authorities");
            createCache(cm, com.mega.project.srm.domain.BiddingQuotationHeader.class.getName());
            createCache(cm, com.mega.project.srm.domain.BiddingQuotationHeader.class.getName() + ".openers");
            createCache(cm, com.mega.project.srm.domain.BiddingQuotationHeader.class.getName() + ".roundInfos");
            createCache(cm, com.mega.project.srm.domain.BiddingOpener.class.getName());
            createCache(cm, com.mega.project.srm.domain.BiddingRoundInfo.class.getName());
            createCache(cm, com.mega.project.srm.domain.BiddingRoundInfo.class.getName() + ".vendorRounds");
            createCache(cm, com.mega.project.srm.domain.BiddingRoundInfo.class.getName() + ".materialRounds");
            createCache(cm, com.mega.project.srm.domain.BiddingVendorRound.class.getName());
            createCache(cm, com.mega.project.srm.domain.BiddingMaterialRound.class.getName());
            createCache(cm, com.mega.project.srm.domain.BiddingMaterialRound.class.getName() + ".quotationInfos");
            createCache(cm, com.mega.project.srm.domain.BiddingMaterialRound.class.getName() + ".bomTemplateHeaders");
            createCache(cm, com.mega.project.srm.domain.BomTemplateHeader.class.getName());
            createCache(cm, com.mega.project.srm.domain.BomTemplateHeader.class.getName() + ".infos");
            createCache(cm, com.mega.project.srm.domain.BomTemplateInfo.class.getName());
            createCache(cm, com.mega.project.srm.domain.BomTemplateInfo.class.getName() + ".columns");
            createCache(cm, com.mega.project.srm.domain.BomTemplateInfoColumn.class.getName());
            createCache(cm, com.mega.project.srm.domain.BiddingQuotationInfo.class.getName());
            createCache(cm, com.mega.project.srm.domain.BiddingPriceCompare.class.getName());
            createCache(cm, com.mega.project.srm.domain.BiddingPriceCompare.class.getName() + ".quotationInfos");
            createCache(cm, com.mega.project.srm.domain.BiddingPriceCompare.class.getName() + ".poHeaders");
            createCache(cm, com.mega.project.srm.domain.Vendor.class.getName());
            createCache(cm, com.mega.project.srm.domain.Material.class.getName());
            createCache(cm, com.mega.project.srm.domain.PoHeader.class.getName());
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
