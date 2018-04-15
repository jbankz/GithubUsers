package bankzworld.com.api;

import bankzworld.com.util.Config;

/**
 * Created by King Jaycee on 05/10/2017.
 */

public class ApiUtils {


    private ApiUtils() {
    }

    public static ApiInterface getSOService() {
        return RetrofitClient.getClient(Config.BASE_URL).create(ApiInterface.class);
    }
}
