package enums;

/**
 * @author vipul pachauri
 */
public enum SuccessCodes {

    SUCCESS_ADDED_CATEGORY("CA01", "SUCCESS: Category added successfully."),
    SUCCESS_ADDED_SUB_CATEGORY("SCA01", "SUCCESS: Sub Category added successfully."),
    SUCCESS_ADDED_BRAND("BA01", "SUCCESS: Brand added successfully."),
    SUCCESS_ADDED_PRODUCT_DETAILS("PD01", "SUCCESS: Product Details added successfully."),
    SUCCESS_FOUND_CATEGORY("CF01", "SUCCESS: Category found."),
    SUCCESS_FOUND_SUB_CATEGORY("SCF01", "SUCCESS: Sub Category found."),
    SUCCESS_FOUND_BRAND("BF01", "SUCCESS: Brand found."),
    SUCCESS_FOUND_PRODUCT("PF01", "SUCCESS: Product found."),
    SUCCESS_UPDATED_CATEGORY("CU01", "SUCCESS: Category updated successfully."),
    SUCCESS_UPDATED_SUB_CATEGORY("SCU01", "SUCCESS: Sub Category updated successfully."),
    SUCCESS_UPDATED_BRAND("BU01", "SUCCESS: Brand updated successfully."),
    SUCCESS_UPDATED_PRODUCT_DETAILS("PDU01", "SUCCESS: Product Details updated successfully."),
    SUCCESS_ADDED_PRODUCT_CART("APC01", "SUCCESS: Product added in cart successfully."),
    SUCCESS_REMOVED_PRODUCT_CART("RPC01", "SUCCESS: Product removed from cart successfully."),
    SUCCEES_USER_REGISTRATION("US01","SUCCESS: User registered successfully."),
    SUCCEES_USER_LOGIN("UL01","SUCCESS: User logged in successfully."),
    SUCCESS_LOGOUT("UL01", "SUCCESS: User logged out successfully."),
    SUCCESS_UPDATED_PRODUCT_COUNT("IPC01", "SUCCESS: Product count updated successfully."),
    SUCCESS_DELETED_CATEGORY("CD01", "SUCCESS: Category deleted successfully."),
    SUCCESS_DELETED_SUB_CATEGORY("SCD01", "SUCCESS: Sub Category deleted successfully."),
    SUCCESS_DELETED_PRODUCT_DETAILS("SPD01", "SUCCESS: Product Details deleted successfully.");


    private final String responseCode;
    private final String responseMessage;

    SuccessCodes(String responseCode, String responseMessage) {
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
