package com;

/**
 * @author vipul pachauri
 */
public class Mappings {

    public static final String SIGN_UP_URL          = "/ga-sports/signup";
    public static final String LOGIN_URL            = "/ga-sports/login";
    public static final String LOGOUT_URL            = "/ga-sports/logout";

    //Admin  URLs
    public static final String ADD_CATEGORY         = "/ga-sports/admin/category";
    public static final String ADD_SUB_CATEGORY     = "/ga-sports/admin/subCategory";
    public static final String ADD_BRAND            = "/ga-sports/admin/brand";
    public static final String ADD_PRODUCT_DETAILS  = "/ga-sports/admin/productDetails";

    public static final String UPDATE_CATEGORY      = "/ga-sports/admin/category/{oldCategoryName}";
    public static final String UPDATE_SUB_CATEGORY  = "/ga-sports/admin/subCategory/{oldSubCategoryName}";
    public static final String UPDATE_BRAND         = "/ga-sports/admin/brand/{oldBrandName}";
    public static final String UPDATE_PRODUCT_DETAILS    = "/ga-sports/admin/productDetails/{oldProductName}";



    //Category Details
    public static final String GET_CATEGORY         = "/ga-sports/category/{categoryName}";
    public static final String GET_CATEGORIES       = "/ga-sports/category";
    public static final String DELETE_CATEGORY      = "/ga-sports/admin/category/{categoryName}";

    //Sub Category Details
    public static final String GET_SUB_CATEGORY     = "/ga-sports/subcategory/{categoryName}/{subcategoryName}";
    public static final String GET_SUB_CATEGORIES   = "/ga-sports/subcategory/{categoryName}";
    public static final String DELETE_SUB_CATEGORY      = "/ga-sports/admin/subcategory/{categoryName}/{subcategoryName}";

    //Brand Details
    public static final String GET_BRAND            = "/ga-sports/brand/{categoryName}/{subcategoryName}/{brandName}";
    public static final String GET_BRANDS           = "/ga-sports/brand/{categoryName}/{subcategoryName}";
    public static final String DELETE_BRAND         = "/ga-sports/brand/{categoryName}/{subcategoryName}/{brandName}";

    //Product Details
    public static final String GET_PRODUCT          = "/ga-sports/product/{categoryName}/{subcategoryName}/{brandName}/{productName}";
    public static final String GET_PRODUCTS         = "/ga-sports/product/{categoryName}/{subcategoryName}/{brandName}";
    public static final String DELETE_PRODUCT        = "/ga-sports/brand/{categoryName}/{subcategoryName}/{brandName}/{productName}";

    public static final String CART_ADD_PRODUCT     = "/ga-sports/user/cart/product";
    public static final String CART_REMOVE_PRODUCT  = "/ga-sports/user/cart/product";
    public static final String CART_PRODUCT_COUNT_UPDATE = "/ga-sports/user/cart/product/count";
}
