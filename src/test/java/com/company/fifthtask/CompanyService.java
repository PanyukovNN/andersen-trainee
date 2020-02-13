package com.company.fifthtask;

public interface CompanyService {

    @Cached
    String getCompany(String companyName);
}
