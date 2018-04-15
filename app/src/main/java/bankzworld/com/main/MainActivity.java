package bankzworld.com.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import bankzworld.com.R;
import bankzworld.com.adapter.UserAdapter;
import bankzworld.com.api.RetrofitClient;
import bankzworld.com.listener.UserListener;
import bankzworld.com.model.UserList;
import bankzworld.com.viewmodel.GithubViewmodel;

public class MainActivity extends AppCompatActivity implements UserListener {

    private GithubViewmodel githubViewmodel;
    String mLanguage, mLocation;

    String searchParams = "language:c location:lagos";

    String language = "language:";
    String location = "location:";

    private RecyclerView recyclerView;
    LinearLayoutManager linearLayout;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        pb = (ProgressBar) findViewById(R.id.pb_loading);
        Intent intent = getIntent();

        if (intent.hasExtra("language") || intent.hasExtra("location")) {
            mLanguage = intent.getStringExtra("language");
            mLocation = intent.getStringExtra("location");
        }

        this.setTitle(mLanguage + " developers from " + mLocation);

        searchParams = language + mLanguage + " " + location + mLocation;

        githubViewmodel = ViewModelProviders.of(this).get(GithubViewmodel.class);

        githubViewmodel.setListener(this);

        isNetworkAvailable(searchParams);

    }

    private void isNetworkAvailable(String searchParams) {
        if (RetrofitClient.isConnected(getApplicationContext())) {
            githubViewmodel.getUsers(searchParams);
        } else {
            Toast.makeText(this, "Unable to connect. Try refreshing.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            isNetworkAvailable(searchParams);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccessfull(UserList userList) {
        recyclerView.setAdapter(new UserAdapter(userList.getItems()));
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProgressShow() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProgressHide() {
        pb.setVisibility(View.INVISIBLE);
    }
}
