package enums;

/**
 * @author vipul pachauri
 */
public enum  ErrorCodes {


    ERROR_INTERNAL_SERVER("-300", "ERROR: Something went wrong while processing request."),
    ERROR_USER_NOT_FOUND("-301", "ERROR: No records found for user."),
    ERROR_ADDING_CATEGORY("-302", "ERROR: Adding category."),
    ERROR_ADDING_SUB_CATEGORY("-303", "ERROR: Adding sub category."),
    ERROR_ADDING_BRAND("-304", "ERROR: Adding brand."),
    ERROR_ADDING_PRODUCT_DETAILS("-305", "ERROR: Adding product details."),
    ERROR_CATEGORY_NOT_FOUND("-306", "ERROR: No records found for category."),
    ERROR_SUB_CATEGORY_NOT_FOUND("-307", "ERROR: No records found for sub category."),
    ERROR_BRAND_NOT_FOUND("-308", "ERROR: No records found for brand."),
    ERROR_UPDATING_CATEGORY("-309", "ERROR: Updating category."),
    ERROR_UPDATING_SUB_CATEGORY("-310", "ERROR: Updating sub category."),
    ERROR_UPDATING_BRAND("-311", "ERROR: Updating brand."),
    ERROR_UPDATING_PRODUCT_DETAILS("-312", "ERROR: Updating product details."),
    ERROR_PRODUCT_NOT_FOUND("-313","ERROR : Product not found.") ;


    private final String responseCode;
    private final String responseMessage;

    ErrorCodes(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
