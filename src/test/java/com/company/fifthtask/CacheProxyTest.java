package com.company.fifthtask;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CacheProxyTest {

    @Test
    public void cache_callGetCompanyTwice_returnCacheValue() {
        CompanyService service = mock(UserCompanyService.class);
        CompanyService cacheService = CacheProxy.cache(service);

        Object expected = cacheService.getCompany("new company");
        Object actual = cacheService.getCompany("new company");

        assertEquals(expected, actual);
        verify(service, times(1)).getCompany("new company");
    }

    @Test
    public void cache_callGetUserTwice_returnCacheValue() {
        UserService service = mock(UserCompanyService.class);
        UserService cacheService = CacheProxy.cache(service);

        String expected = cacheService.getUser("Nick");
        String actual = cacheService.getUser("Nick");

        assertEquals(expected, actual);
        verify(service, times(1)).getUser("Nick");
    }
}
