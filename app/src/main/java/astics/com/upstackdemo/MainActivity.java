package astics.com.upstackdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private SearchView mSearchView;

    private RecyclerAdapter.OnRecyclerViewItemClickListener mOnListClickListener = new RecyclerAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onListItemClick() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();
        setupSearchView();
    }

    private void setupSearchView() {
        mSearchView = findViewById(R.id.search_view);
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.rv_list_of_gif);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter(this, mOnListClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }
}