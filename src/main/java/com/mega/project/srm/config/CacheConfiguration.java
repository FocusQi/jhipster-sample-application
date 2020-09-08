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
            createCache(cm, com.mega.project.srm.domain.KpiAssGroup.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiAssGroup.class.getName() + ".assGroupInfos");
            createCache(cm, com.mega.project.srm.domain.KpiTemplateGroup.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiTemplateGroup.class.getName() + ".scoreGradeConfigs");
            createCache(cm, com.mega.project.srm.domain.KpiTemplateGroup.class.getName() + ".templateGroupInfos");
            createCache(cm, com.mega.project.srm.domain.KpiScoreGradeConfig.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiTemplateGroupInfo.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiTemplateGroupInfo.class.getName() + ".users");
            createCache(cm, com.mega.project.srm.domain.KpiAssTemplate.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiAssTemplate.class.getName() + ".calItems");
            createCache(cm, com.mega.project.srm.domain.KpiScoreCalConfig.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiScoreCalConfig.class.getName() + ".scoreCalConfigItems");
            createCache(cm, com.mega.project.srm.domain.KpiScoreCalConfigItem.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiScoreCalConfigItem.class.getName() + ".scoreReferences");
            createCache(cm, com.mega.project.srm.domain.KpiCalItem.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiScoreReference.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiAssGroupInfo.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiAssGroupInfo.class.getName() + ".assTemplateGroups");
            createCache(cm, com.mega.project.srm.domain.KpiAssTemplateGroup.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiAssTemplateGroup.class.getName() + ".assTemplateInfos");
            createCache(cm, com.mega.project.srm.domain.KpiAssTemplateInfo.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiAssTemplateInfo.class.getName() + ".scoreInfos");
            createCache(cm, com.mega.project.srm.domain.KpiScoreInfo.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiImproveReport.class.getName());
            createCache(cm, com.mega.project.srm.domain.KpiImproveReport.class.getName() + ".improveReportInfos");
            createCache(cm, com.mega.project.srm.domain.KpiImproveReportInfo.class.getName());
            createCache(cm, com.mega.project.srm.domain.Vendor.class.getName());
            createCache(cm, com.mega.project.srm.domain.Role.class.getName());
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
