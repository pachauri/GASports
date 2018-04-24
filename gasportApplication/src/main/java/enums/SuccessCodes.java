package enums;

/**
 * @author vipul pachauri
 */
public enum SuccessCodes {

    SUCCESS_ADDED_CATEGORY("CA01", "SUCCESS: Category added successfully."),
    SUCCESS_ADDED_SUB_CATEGORY("SCA01", "SUCCESS: Sub Category added successfully."),
    SUCCESS_ADDED_BRAND("BA01", "SUCCESS: Brand added successfully."),
    SUCCESS_ADDED_PRODUCT_DETAILS("PD01", "SUCCESS: Product Details added successfully.");


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
