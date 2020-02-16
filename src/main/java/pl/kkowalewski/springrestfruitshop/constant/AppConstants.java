package pl.kkowalewski.springrestfruitshop.constant;

public class AppConstants {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String ROOT = "";
    public static final String SLASH = "/";
    public static final String API = "api";
    public static final String V1 = "v1";
    public static final String CATEGORIES = "categories";
    public static final String CUSTOMERS = "customers";
    public static final String VENDORS = "vendors";
    public static final String ID = "{id}";
    public static final String NAME = "{name}";

    public static final String RESOURCE_NOT_FOUND = "Resource Not Found";

    public static final String CATEGORY_ROOT_PATH = SLASH + API + SLASH + V1 + SLASH + CATEGORIES;
    public static final String CUSTOMERS_ROOT_PATH = SLASH + API + SLASH + V1 + SLASH + CUSTOMERS;
    public static final String VENDORS_ROOT_PATH = SLASH + API + SLASH + V1 + SLASH + VENDORS;

    /*------------------------ METHODS REGION ------------------------*/
    private AppConstants() {

    }
}
