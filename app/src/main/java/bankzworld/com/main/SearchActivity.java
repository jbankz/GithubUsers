package bankzworld.com.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import bankzworld.com.R;

public class SearchActivity extends AppCompatActivity {

    TextInputEditText mLanguage, mLocation;
    Button mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLanguage = (TextInputEditText) findViewById(R.id.et_language);
        mLocation = (TextInputEditText) findViewById(R.id.location);
        mSearch = (Button) findViewById(R.id.search_button);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String language = mLanguage.getText().toString();
                String location = mLocation.getText().toString();

                if (!dataValidated(language, location)) {
                    return;
                }
            }
        });
    }

    boolean dataValidated(String language, String location) {
        if (language.isEmpty()) {
            mLanguage.setError("field required");
            return false;
        }
        if (location.isEmpty()) {
            mLocation.setError("field required");
            return false;
        }

        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        intent.putExtra("language", language);
        intent.putExtra("location", location);
        startActivity(intent);

        return true;
    }

    public void signUp(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://github.com"));
        startActivity(i);
    }
}
