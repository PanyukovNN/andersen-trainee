package com.company.fifthtask;

public class UserCompanyService implements UserService, CompanyService {

    @Override
    public String getCompany(String companyName) {
        return null;
    }

    @Cached
    @Override
    public String getUser(String userName) {
        return null;
    }
}
