/*
package in.co.iman.prometheusdeepdrive.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

@Slf4j
public class CustomCacheErrorHandler implements CacheErrorHandler {


  @Override
  public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
    log.error("Error while getting cache from redis");
  }

  @Override
  public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
    log.error("Error while putting cache from redis");
  }

  @Override
  public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
    log.error("Error while evict cache from redis");
  }

  @Override
  public void handleCacheClearError(RuntimeException e, Cache cache) {
    log.error("Error while clearing cache from redis");
  }
}*/
