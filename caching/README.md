# Caching

Hibernate support two level of caching
1. First-level cache i.e. session scoped cache. Once the session is closed, first-level cache is terminated as well. 

2. Second-level cache i.e. SessionFactory-scoped, meaning it is shared by all sessions created with the same session factory. 
   
   * If an instance is already present in the first-level cache, it is returned from there
   * If an instance is not found in the first-level cache, and then it will check  corresponding instance state is cached in the second-level cache, 
     then the data is fetched from there. Otherwise, the necessary data are loaded from the database.
   * Once the instance is stored in the persistence context (first-level cache), it is returned from there in all subsequent calls within the same session 
     until the session is closed or the instance is manually evicted from the persistence context. 
     Also, the loaded instance state is stored in L2 cache if it was not there already.
     
Second level cache can be implemented using any of below
* ehcache
* swaram cache
* Jboss tree cache
* OS cache and many more

In this tutorial, ehcache is used to show the example. To setup second level ehcache.xml is used to create configuration and second level cache 
is enabled ib application.yml as

```
properties:
  hibernate:
    cache:
        use_second_level_cache: true
    region:
        factory_class: org.hibernate.cache.ehcache.EhCacheRegionFactory
```
