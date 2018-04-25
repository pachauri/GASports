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
    SUCCESS_FOUND_PRODUCT("PF01", "SUCCESS: Product found.");


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
