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
    private static final Long SINGLE_ENTRY_IN_HEAP = 1L;
    public static final String TOKEN_CACHE_NAME = "TOKEN_CACHE";
    @Value("${cache.token.eviction-policy}")
    private String TOKEN_MEMORY_STORE_EVICTION_POLICY;
    @Value("${cache.token.time-to-live-seconds}")
    private Long TOKEN_LIVE_IN_CACHE_SECONDS;
    @Value("${cache.token.max-entries-in-heap}")
    private Long TOKEN_MAX_ENTRIES_IN_HEAP;

    public static final String ACCOUNT_CACHE_NAME = "ACCOUNT_CACHE";
    public static final String ACCOUNTS_CACHE_NAME = "ACCOUNTS_CACHE";
    @Value("${cache.account.eviction-policy}")
    private String ACCOUNT_MEMORY_STORE_EVICTION_POLICY;
    @Value("${cache.account.time-to-live-seconds}")
    private Long ACCOUNT_LIVE_IN_CACHE_SECONDS;
    @Value("${cache.account.max-entries-in-heap}")
    private Long ACCOUNT_MAX_ENTRIES_IN_HEAP;

    public static final String PERMISSION_CACHE_NAME = "PERMISSION_CACHE";
    public static final String PERMISSIONS_CACHE_NAME = "PERMISSIONS_CACHE";
    @Value("${cache.permission.eviction-policy}")
    private String PERMISSION_MEMORY_STORE_EVICTION_POLICY;
    @Value("${cache.permission.time-to-live-seconds}")
    private Long PERMISSION_LIVE_IN_CACHE_SECONDS;
    @Value("${cache.permission.max-entries-in-heap}")
    private Long PERMISSION_MAX_ENTRIES_IN_HEAP;

    @Bean
    net.sf.ehcache.CacheManager getEhcacheManager() {
        var tokenCacheConfig = new CacheConfiguration();
        tokenCacheConfig.setName(TOKEN_CACHE_NAME);
        tokenCacheConfig.setMemoryStoreEvictionPolicy(TOKEN_MEMORY_STORE_EVICTION_POLICY);
        tokenCacheConfig.setTimeToLiveSeconds(TOKEN_LIVE_IN_CACHE_SECONDS);
        tokenCacheConfig.setMaxEntriesLocalHeap(TOKEN_MAX_ENTRIES_IN_HEAP);

        var permissionCacheConfig = new CacheConfiguration();
        permissionCacheConfig.setName(PERMISSION_CACHE_NAME);
        permissionCacheConfig.setMemoryStoreEvictionPolicy(PERMISSION_MEMORY_STORE_EVICTION_POLICY);
        permissionCacheConfig.setTimeToLiveSeconds(PERMISSION_LIVE_IN_CACHE_SECONDS);
        permissionCacheConfig.setMaxEntriesLocalHeap(PERMISSION_MAX_ENTRIES_IN_HEAP);
        var permissionsCacheConfig = new CacheConfiguration();
        permissionsCacheConfig.setName(PERMISSIONS_CACHE_NAME);
        permissionsCacheConfig.setMemoryStoreEvictionPolicy(PERMISSION_MEMORY_STORE_EVICTION_POLICY);
        permissionsCacheConfig.setTimeToLiveSeconds(PERMISSION_LIVE_IN_CACHE_SECONDS);
        permissionsCacheConfig.setMaxEntriesLocalHeap(SINGLE_ENTRY_IN_HEAP);

        var accountCacheConfig = new CacheConfiguration();
        accountCacheConfig.setName(ACCOUNT_CACHE_NAME);
        accountCacheConfig.setMemoryStoreEvictionPolicy(ACCOUNT_MEMORY_STORE_EVICTION_POLICY);
        accountCacheConfig.setTimeToLiveSeconds(ACCOUNT_LIVE_IN_CACHE_SECONDS);
        accountCacheConfig.setMaxEntriesLocalHeap(ACCOUNT_MAX_ENTRIES_IN_HEAP);
        var accountsCacheConfig = new CacheConfiguration();
        accountsCacheConfig.setName(ACCOUNTS_CACHE_NAME);
        accountsCacheConfig.setMemoryStoreEvictionPolicy(ACCOUNT_MEMORY_STORE_EVICTION_POLICY);
        accountsCacheConfig.setTimeToLiveSeconds(ACCOUNT_LIVE_IN_CACHE_SECONDS);
        accountsCacheConfig.setMaxEntriesLocalHeap(SINGLE_ENTRY_IN_HEAP);

        var ehcacheConfiguration = new net.sf.ehcache.config.Configuration();
        ehcacheConfiguration.addCache(tokenCacheConfig);
        ehcacheConfiguration.addCache(accountCacheConfig);
        ehcacheConfiguration.addCache(accountsCacheConfig);
        ehcacheConfiguration.addCache(permissionCacheConfig);
        ehcacheConfiguration.addCache(permissionsCacheConfig);

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
