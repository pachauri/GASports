package com;

/**
 * @author vipul pachauri
 */
public class Mappings {

    public static final String SIGN_UP_URL          = "/ga-sports/user/sign-up";
    public static final String LOGIN_URL            = "/ga-sports/user/login";
    public static final String ADMIN_LOGIN_URL      = "/ga-sports/admin/login";
    public static final String ADD_CATEGORY         = "/ga-sports/admin/addCategory";
    public static final String ADD_SUB_CATEGORY     = "/ga-sports/admin/addSubCategory";
    public static final String ADD_BRAND            = "/ga-sports/admin/addBrand";
    public static final String ADD_PRODUCT_DETAILS  = "/ga-sports/admin/addProductDetails";

    //Category Details
    public static final String GET_CATEGORY         = "/ga-sports/category/{categoryName}";
    public static final String GET_CATEGORIES       = "/ga-sports/category";

    //Sub Category Details
    public static final String GET_SUB_CATEGORY     = "/ga-sports/subcategory/{categoryName}/{subcategoryName}";
    public static final String GET_SUB_CATEGORIES   = "/ga-sports/subcategory/{categoryName}";

    //Brand Details
    public static final String GET_BRAND            = "/ga-sports/brand/{categoryName}/{subcategoryName}/{brandName}";
    public static final String GET_BRANDS           = "/ga-sports/brand/{categoryName}/{subcategoryName}";

    //Product Details
    public static final String GET_PRODUCT          = "/ga-sports/product/{categoryName}/{subcategoryName}/{brandName}/{productName}";
    public static final String GET_PRODUCTS         = "/ga-sports/product/{categoryName}/{subcategoryName}/{brandName}";


}
