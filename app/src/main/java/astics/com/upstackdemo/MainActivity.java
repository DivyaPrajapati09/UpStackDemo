package astics.com.upstackdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.MediaResponse;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setUpRecyclerView();
        setupSearchView();
        //  initGify();
    }

    private void setUpToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.black));

        mToolbar.setTitle("Search Gifs");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void initGify() {
        GPHApi client = new GPHApiClient(getString(R.string.api_key));
        client.random("cats dogs", MediaType.gif, null, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(MediaResponse result, Throwable e) {
                if (result == null) {
                    // Do what you want to do with the error
                } else {
                    if (result.getData() != null) {
                        Log.e("giphy", result.getData().getUrl());
                    } else {
                        Log.e("giphy error", "No results found");
                    }
                }
            }
        });

    }

    private void setupSearchView() {
        mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
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
        Call<ResponseModel> call = ApiClient.apiService.getSearchedGifs(query, getString(R.string.api_key));
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    List<ResponseModel.Data> data = new ArrayList<>();
                    data.addAll(response.body().getData());
                    for (ResponseModel.Data data1 : data) {
                        listOfImage.add(data1.getImages().getImageData());
                    }
                    mAdapter.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {

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