package ua.com.cyberdone.accountmicroservice.config;


import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@EnableCaching
@Configuration
public class CyberdoneCachingConfig extends CachingConfigurerSupport {
    public static final String TOKEN_CACHE_NAME = "TOKEN_CACHE";
    public static final String ACCOUNT_CACHE_NAME = "ACCOUNT_CACHE";
    public static final String ACCOUNTS_CACHE_NAME = "ACCOUNTS_CACHE";
    public static final String PERMISSION_CACHE_NAME = "PERMISSION_CACHE";
    public static final String PERMISSIONS_CACHE_NAME = "PERMISSIONS_CACHE";
    public static final String ROLE_CACHE_NAME = "ROLE_CACHE";
    public static final String ROLES_CACHE_NAME = "ROLES_CACHE";
    private static final Long SINGLE_ENTRY_IN_HEAP = 1L;
    @Value("${cache.token.eviction-policy}")
    private String tokenMemoryStoreEvictionPolicy;
    @Value("${cache.token.time-to-live-seconds}")
    private Long tokenLiveInCacheSeconds;
    @Value("${cache.token.max-entries-in-heap}")
    private Long tokenMaxEntriesInHeap;

    @Value("${cache.model.eviction-policy}")
    private String modelMemoryStoreEvictionPolicy;
    @Value("${cache.model.time-to-live-seconds}")
    private Long modelLiveInCacheSeconds;
    @Value("${cache.model.max-entries-in-heap}")
    private Long modelMaxEntriesInHeap;

    @Bean
    net.sf.ehcache.CacheManager getEhcacheManager() {
        var tokenCacheConfig = new CacheConfiguration();
        tokenCacheConfig.setName(TOKEN_CACHE_NAME);
        tokenCacheConfig.setMemoryStoreEvictionPolicy(tokenMemoryStoreEvictionPolicy);
        tokenCacheConfig.setTimeToLiveSeconds(tokenLiveInCacheSeconds);
        tokenCacheConfig.setMaxEntriesLocalHeap(tokenMaxEntriesInHeap);

        var permissionCacheConfig = new CacheConfiguration();
        permissionCacheConfig.setName(PERMISSION_CACHE_NAME);
        permissionCacheConfig.setMemoryStoreEvictionPolicy(modelMemoryStoreEvictionPolicy);
        permissionCacheConfig.setTimeToLiveSeconds(modelLiveInCacheSeconds);
        permissionCacheConfig.setMaxEntriesLocalHeap(modelMaxEntriesInHeap);
        var permissionsCacheConfig = new CacheConfiguration();
        permissionsCacheConfig.setName(PERMISSIONS_CACHE_NAME);
        permissionsCacheConfig.setMemoryStoreEvictionPolicy(modelMemoryStoreEvictionPolicy);
        permissionsCacheConfig.setTimeToLiveSeconds(modelLiveInCacheSeconds);
        permissionsCacheConfig.setMaxEntriesLocalHeap(SINGLE_ENTRY_IN_HEAP);

        var accountCacheConfig = new CacheConfiguration();
        accountCacheConfig.setName(ACCOUNT_CACHE_NAME);
        accountCacheConfig.setMemoryStoreEvictionPolicy(modelMemoryStoreEvictionPolicy);
        accountCacheConfig.setTimeToLiveSeconds(modelLiveInCacheSeconds);
        accountCacheConfig.setMaxEntriesLocalHeap(modelMaxEntriesInHeap);
        var accountsCacheConfig = new CacheConfiguration();
        accountsCacheConfig.setName(ACCOUNTS_CACHE_NAME);
        accountsCacheConfig.setMemoryStoreEvictionPolicy(modelMemoryStoreEvictionPolicy);
        accountsCacheConfig.setTimeToLiveSeconds(modelLiveInCacheSeconds);
        accountsCacheConfig.setMaxEntriesLocalHeap(SINGLE_ENTRY_IN_HEAP);

        var roleCacheConfig = new CacheConfiguration();
        roleCacheConfig.setName(ROLE_CACHE_NAME);
        roleCacheConfig.setMemoryStoreEvictionPolicy(modelMemoryStoreEvictionPolicy);
        roleCacheConfig.setTimeToLiveSeconds(modelLiveInCacheSeconds);
        roleCacheConfig.setMaxEntriesLocalHeap(modelMaxEntriesInHeap);
        var rolesCacheConfig = new CacheConfiguration();
        rolesCacheConfig.setName(ROLES_CACHE_NAME);
        rolesCacheConfig.setMemoryStoreEvictionPolicy(modelMemoryStoreEvictionPolicy);
        rolesCacheConfig.setTimeToLiveSeconds(modelLiveInCacheSeconds);
        rolesCacheConfig.setMaxEntriesLocalHeap(SINGLE_ENTRY_IN_HEAP);

        var ehcacheConfiguration = new net.sf.ehcache.config.Configuration();
        ehcacheConfiguration.addCache(tokenCacheConfig);
        ehcacheConfiguration.addCache(accountCacheConfig);
        ehcacheConfiguration.addCache(accountsCacheConfig);
        ehcacheConfiguration.addCache(permissionCacheConfig);
        ehcacheConfiguration.addCache(permissionsCacheConfig);
        ehcacheConfiguration.addCache(roleCacheConfig);
        ehcacheConfiguration.addCache(rolesCacheConfig);

        return net.sf.ehcache.CacheManager.newInstance(ehcacheConfiguration);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(getEhcacheManager());
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new CyberdoneKeyGenerator();
    }

    static class CyberdoneKeyGenerator extends SimpleKeyGenerator {
        @Override
        public Object generate(Object target, Method method, Object... params) {
            return super.generate(target, method, Arrays.stream(params)
                    .filter(Objects::nonNull).map(Objects::toString).toArray());
        }
    }
}
