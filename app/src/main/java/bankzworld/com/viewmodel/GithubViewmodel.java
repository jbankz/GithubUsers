package bankzworld.com.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import bankzworld.com.api.ApiInterface;
import bankzworld.com.api.ApiUtils;
import bankzworld.com.listener.UserListener;
import bankzworld.com.model.UserList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubViewmodel extends ViewModel {

    private static final String TAG = GithubViewmodel.class.getSimpleName();

    private ApiInterface apiInterface = ApiUtils.getSOService();

    static UserListener userListener;

    public void setListener(UserListener userListener) {
        GithubViewmodel.userListener = userListener;
    }

    public void getUsers(final String users) {
        userListener.onProgressShow();
        apiInterface.getUserList(users).enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                Log.d(TAG, "onResponse: " + response.raw());
                if (response.isSuccessful()) {
                    userListener.onProgressHide();
                    Log.e(TAG, "onResponse: " + response.body());
                    userListener.onSuccessfull(response.body());
                } else {
                    userListener.onProgressHide();
                    userListener.onFailure("Unable to connect. Try refreshing.");
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                userListener.onProgressHide();
                userListener.onFailure("Unable to connect. Try refreshing.");
            }
        });
    }

}
