package com.example.mypractice_ver3;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // 네비게이션 아이템 클릭해서 메인화면 왔을때, 뒤에 프래그먼트 삭제
    // 혹은 그냥 메인화면이면 뒷클릭시 앱 종료 - 이건 메인액티비티여서
    // 프래그먼트 스택 관리 - 첨에 영화상세 정보 갔다가 메인 버튼 눌러도 안감
    // 프래그먼트로 api 데이터 넘기기 - 혹은 processResponce 함수안에서 처리해야 하는듯?

    private AppBarConfiguration mAppBarConfiguration;

    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawer;

    Fragment_movieinfo info_fragment;
    Fragment_movielist list_fragment;

    API_movieinfo api_movieinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        //NavigationView navigationView = findViewById(R.id.nav_view);
        setNavigationViewListener();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.fragment_movielist, R.id.fragment_movieinfo)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);

        if(AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        info_fragment = new Fragment_movieinfo();
        list_fragment = new Fragment_movielist();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setNavigationViewListener() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean onNavigationItemSelected (MenuItem item) {

        switch (item.getItemId()) {
            case R.id.fragment_movieinfo:
                FragmentChange(0);
                break;
            case R.id.fragment_movielist:
                Toast.makeText(getApplicationContext(),"눌렀자나요~",Toast.LENGTH_SHORT).show();
                FragmentChange(1);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*@Override
    public void onBackPressed() {
        if(info_fragment != null)
        {
            getSupportFragmentManager().popBackStack();
            //info_fragment = null;
            toolbar.setTitle("영화 목록");
        }
        else {
            ActivityCompat.finishAffinity(this);
        }
    }*/

    public void FragmentChange(int Fragment_number) {
        if (Fragment_number == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, info_fragment).commit();
            toolbar.setTitle("영화 상세 정보");
        }
    }

    public void sendRequest() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";
        url += "?" + "type=1";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponce(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    public void processResponce(String responce) {
        Gson gson = new Gson();
        api_movieinfo = gson.fromJson(responce, API_movieinfo.class);
        if (api_movieinfo.code == 200) {
            list_fragment.api_movieinfo = api_movieinfo;
        }
    }
}
