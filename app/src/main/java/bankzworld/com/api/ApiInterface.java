package bankzworld.com.api;

import bankzworld.com.model.UserList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by King Jaycee on 03/10/2017.
 */

public interface ApiInterface {

    @GET("/search/users")
    Call<UserList> getUserList(@Query("q") String filter);

}