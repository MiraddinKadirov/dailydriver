package org.example.dailydriver.consatnt;


public class Util {
    public static final String[] WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html",

            "/api/v1/user/login",
            "/api/v1/user/register",
            "/api/v1/user/refreshToken",

            "/api/v1/car/popular",
            "/api/v1/car/rating/**",
            "/api/v1/car/carget/**",
            "/api/v1/car/getpage",
            "/api/v1/car/category/**"
    };
}
