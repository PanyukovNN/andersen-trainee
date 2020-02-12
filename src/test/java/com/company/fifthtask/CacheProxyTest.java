package com.company.fifthtask;

import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CacheProxyTest {

    @Test
    public void cache() {
        CacheProxy cacheProxy = new CacheProxy();
        Supplier supplier = mock(Supplier.class);
        Supplier cacheSupplier = cacheProxy.cache(supplier);

        Object expected = cacheSupplier.get();
        Object actual = cacheSupplier.get();

        assertEquals(expected, actual);
        verify(supplier, times(1)).get();
    }
}
