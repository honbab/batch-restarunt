package startspring.common;

public class GlobalURI {
    public static final String ROOT_URI = "/";

    /// SHOP
    public static final String SHOP_URI = ROOT_URI  + "shop";
    public static final String SHOP_PATH_URI = ROOT_URI + "/shop"+ "/{shopId}";
    public static final String SHOP_PATH_URI_VA = "shopId";
    public static final String SHOP_URI_VA = "/{" + SHOP_PATH_URI_VA + "}";


    /// MENU
    public static final String MENU_URI = ROOT_URI + "menu";
    //public static final String MENU_PATH_URI = ROOT_URI + "menu" + "/{menuId}";
    public static final String MENU_PATH_URI_VA = "menuId";
    public static final String MENU_PATH_VA = "/{" + MENU_PATH_URI_VA + "}";

    // shop + shopid + menuid
    public static final String MENU_PATH_URI = SHOP_PATH_URI + MENU_PATH_VA;

}
