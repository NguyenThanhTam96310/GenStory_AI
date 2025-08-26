package com.ebizworld.genstory.constant;

public class AppConstants {
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "2";
    public static final String SORT_CATEGORIES_BY = "categoryId";
    public static final String SORT_DIR = "asc";
    public static final String[] PUBLIC_POST_ENDPOINTS = {
        "/users", "/auth/token", "/auth/introspect", "/auth/logout", "/auth/refresh", "/chat"
    };
    public static final String[] PUBLIC_GET_ENDPOINTS = {"/stories/**", "/chat/**", "/chapters/**"};
    public static final String ADMIN_USER_NAME = "admin";
    public static final String ADMIN_PASSWORD = "admin";
}
