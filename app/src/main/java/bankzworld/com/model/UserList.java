package bankzworld.com.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by King Jaycee on 03/10/2017.
 */

public class UserList {

    @SerializedName("items")
    @Expose
    private List<User> items = null;

    public List<User> getItems() {
        return items;
    }

    public void setItems(List<User> items) {
        this.items = items;
    }
}