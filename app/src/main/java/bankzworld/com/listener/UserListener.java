package bankzworld.com.listener;

import java.util.List;

import bankzworld.com.model.User;
import bankzworld.com.model.UserList;

public interface UserListener {

    void onSuccessfull(UserList userList);

    void onFailure(String message);

    void onProgressShow();

    void onProgressHide();

}
