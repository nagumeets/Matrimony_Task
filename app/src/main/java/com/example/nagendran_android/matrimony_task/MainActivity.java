package com.example.nagendran_android.matrimony_task;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Album> listing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        listing = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://matrimony.billioncart.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APiService service = retrofit.create(APiService.class);
        Call<List<Album>> call=   service.getalbumdetails();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                List<Album> list = response.body();
                Album album = null;
                for (int i =0 ;i<list.size();i++){
                    album = new Album();
                    String name = list.get(i).getFirst_name();
                    String city = list.get(i).getCity();
                    String image = list.get(i).getProfile_pic();

                    album.setCity(city);
                    album.setFirst_name(name);
                    album.setProfile_pic(image);
                    listing.add(album);
                }

                AlbumsAdapter recyclerAdapter = new AlbumsAdapter(listing,ImageLoader.getInstance());
                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this,2);
                // RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                recyclerView.addItemDecoration(new GridSpacingdecoration(2, dpToPx(10), true));
                recyclerView.setLayoutManager(recyce);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);

                try {
                    Glide.with(getApplicationContext()).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("FailExc",e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.e("Fail","Fail Responce");
            }
        });

    }
    public class GridSpacingdecoration extends RecyclerView.ItemDecoration {

        private int span;
        private int space;
        private boolean include;

        public GridSpacingdecoration(int span, int space, boolean include) {
            this.span = span;
            this.space = space;
            this.include = include;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % span;

            if (include) {
                outRect.left = space - column * space / span;
                outRect.right = (column + 1) * space / span;

                if (position < span) {
                    outRect.top = space;
                }
                outRect.bottom = space;
            } else {
                outRect.left = column * space / span;
                outRect.right = space - (column + 1) * space / span;
                if (position >= span) {
                    outRect.top = space;
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}