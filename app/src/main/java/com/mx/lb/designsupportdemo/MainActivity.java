package com.mx.lb.designsupportdemo;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private RelativeLayout parentLayout;
    private FloatingActionButton fab,fabLocal,fabFavorite;

    private ImageView imageView;
    private NavigationView navigationView;

    //标识FloatingActionButton的打开状态
    private int FAB_STATE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();



    }


    private void initView() {
        drawerLayout= (DrawerLayout) findViewById(R.id.draw_layout);

        parentLayout= (RelativeLayout) findViewById(R.id.parent_layout);

        imageView= (ImageView) findViewById(R.id.image);

        navigationView= (NavigationView) findViewById(R.id.navigation);
        fab= (FloatingActionButton) findViewById(R.id.fab);
        fabLocal= (FloatingActionButton) findViewById(R.id.fab_local);
        fabFavorite= (FloatingActionButton) findViewById(R.id.fab_favorite);

        fab.setOnClickListener(this);
        fabLocal.setOnClickListener(this);
        fabFavorite.setOnClickListener(this);


        //navigationView选中Item处理,为了代码整齐些，就放在一个函数里了
        handNavigationView();

    }

    private void handNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            //用于辨别此前是否已有选中条目
            MenuItem preMenuItem;
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                if (preMenuItem!=null)
                    preMenuItem.setChecked(false);
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                    preMenuItem=item;

                switch (item.getItemId()){
                    case R.id.navigation_item1:
                        imageView.setImageResource(R.mipmap.bg_one);
                        break;
                    case R.id.navigation_item2:
                        imageView.setImageResource(R.mipmap.bg_two);
                        break;
                    case R.id.navigation_item3:
                        imageView.setImageResource(R.mipmap.bg_three);
                        break;
                    case R.id.navigation_sub_item1:
                        imageView.setImageResource(R.mipmap.bg_four);
                        break;
                    case R.id.navigation_sub_item2:
                        imageView.setImageResource(R.mipmap.bg_five);
                        break;
                    case R.id.navigation_sub_item3:
                        imageView.setImageResource(R.mipmap.bg_default);
                        break;
                }

                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fab:
                if(FAB_STATE==1){
                    fab.setImageResource(R.mipmap.ic_clear_white_24dp);
                    fabLocal.setVisibility(View.VISIBLE);
                    fabFavorite.setVisibility(View.VISIBLE);
                    FAB_STATE=0;
                    break;
                }
                  if(FAB_STATE==0){
                    fab.setImageResource(R.mipmap.ic_add_white_24dp);
                    fabFavorite.setVisibility(View.GONE);
                    fabLocal.setVisibility(View.GONE);
                    FAB_STATE=1;
                      break;
                }
                break;
            case R.id.fab_local:
                //弹出SnackBar，仅仅显示文字消息的SnackBar

                //注意第一个参数，需要一个合适的父视图
                Snackbar.make(parentLayout,"你点击了FAB_LOCAL",Snackbar.LENGTH_LONG)
                .setAction("换个美女", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageView.setImageResource(R.mipmap.bg_six);
                    }
                }).show();
                break;
            case R.id.fab_favorite:
                //设置字体的颜色
                Snackbar.make(parentLayout,"你点击了FAB_FAVORITE",Snackbar.LENGTH_LONG)
                        .setAction("UNdo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO
                            }
                        })
                        .show();

                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
