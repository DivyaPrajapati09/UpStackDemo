package astics.com.upstackdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private SearchView mSearchView;
    private List<ResponseModel.ImageData> listOfImage = new ArrayList<>();

    private RecyclerAdapter.OnRecyclerViewItemClickListener mOnListClickListener = new RecyclerAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onListItemClick(ResponseModel.ImageData images) {
            Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
            intent.putExtra("data", images);
            startActivity(intent);
        }
    };
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progress_bar);
        setUpToolbar();
        setUpRecyclerView();
        setupSearchView();
    }

    private void setUpToolbar() {
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black));

        mToolbar.setTitle("Search Gifs");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void setupSearchView() {
        mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                if (mSearchView != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);
                }
                listOfImage.clear();
                searchGif(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                return false;
            }
        });
    }

    private void searchGif(String query) {
        mProgressBar.setVisibility(View.VISIBLE);
        Call<ResponseModel> call = ApiClient.apiService.getSearchedGifs(query, getString(R.string.api_key));
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    List<ResponseModel.Data> data = new ArrayList<>();
                    data.addAll(response.body().getData());
                    for (ResponseModel.Data data1 : data) {
                        listOfImage.add(data1.getImages().getImageData());
                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.rv_list_of_gif);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter(this, listOfImage, mOnListClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }
}