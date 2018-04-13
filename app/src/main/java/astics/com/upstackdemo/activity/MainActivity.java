package astics.com.upstackdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import astics.com.upstackdemo.R;
import astics.com.upstackdemo.adapter.RecyclerAdapter;
import astics.com.upstackdemo.api.ApiClient;
import astics.com.upstackdemo.app.MyApplication;
import astics.com.upstackdemo.dbModel.DBModel;
import astics.com.upstackdemo.model.Data;
import astics.com.upstackdemo.model.ImageData;
import astics.com.upstackdemo.model.ResponseModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.objectbox.Box;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_list_of_gif)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_view)
    EditText mSearchView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.im_search)
    ImageView imSearch;
    private RecyclerAdapter mAdapter;
    private List<ImageData> listOfImage = new ArrayList<>();
    private Box<DBModel> notesBox;
    private List<Data> data = new ArrayList<>();
    private Subscription subscription;
    private RecyclerAdapter.OnRecyclerViewItemClickListener mOnListClickListener = new RecyclerAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onListItemClick(ImageData images) {
            Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
            intent.putExtra("data", images);
            startActivity(intent);
        }

        @Override
        public void setLikeUnlikeCount(int position, String likeCount, String unlikeCount) {
            DBModel note = new DBModel(data.get(position).getImageId(), likeCount, unlikeCount);
            notesBox.put(note);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        notesBox = ((MyApplication) getApplicationContext()).getBoxStore().boxFor(DBModel.class);
        setUpToolbar();
        setUpRecyclerView();
        setupSearchView();
    }

    private void setUpToolbar() {
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mToolbar.setTitle("Search Gifs");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private void setupSearchView() {
        imSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyBoardAndSearch();

            }
        });
        mSearchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyBoardAndSearch();
                    return true;
                }
                return false;
            }
        });
    }

    private void hideKeyBoardAndSearch() {
        if (mSearchView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);
        }
        assert mSearchView != null;
        String searchQuery = mSearchView.getText().toString().trim();
        if (!TextUtils.isEmpty(searchQuery)) {
            listOfImage.clear();
            searchGif(searchQuery);
        }
    }

    private void searchGif(String query) {
        mProgressBar.setVisibility(View.VISIBLE);
        subscription = ApiClient.getInstance().getGifs(query, getString(R.string.api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResponseModel response) {
                        mProgressBar.setVisibility(View.GONE);
                        data.clear();
                        data.addAll(response.getData());
                        for (Data data1 : data) {
                            listOfImage.add(data1.getImages().getImageData());
                        }
                        mAdapter.notifyDataSetChanged();

                    }
                });
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter(this, listOfImage, mOnListClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }
}