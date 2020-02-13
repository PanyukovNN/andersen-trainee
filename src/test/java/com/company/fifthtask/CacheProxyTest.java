package com.company.fifthtask;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CacheProxyTest {

    @Test
    public void cache() {
        Service service = mock(Service.class);
        Service cacheService = CacheProxy.cache(service);

        Object expected = cacheService.doHardWork();
        Object actual = cacheService.doHardWork();

        assertEquals(expected, actual);
        verify(service, times(1)).doHardWork();
    }
}
