package phansa.phaiboon.showtun;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import phansa.phaiboon.showtun.fragment.ReadEbookFragment;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Initial View
        initialView();


        //Add Fragment
        if (savedInstanceState==null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFragmentService, new ReadEbookFragment())
                    .commit();
        }


        //ReadEbook Controller
        readEbookController();

        //Show My Location
        showMyLocation(R.id.txtShowLocationDrawer);


    } // onCreate

    private void showMyLocation(int txtShowLocationDrawer) {
        TextView textView = (TextView) findViewById(txtShowLocationDrawer);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceActivity.this, MapsActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });
    }

    private void readEbookController() {
        showMyLocation(R.id.txtReadEbookDrawer);

    }

    private void initialView() {

        //Setup ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolBarService);
        setSupportActionBar(toolbar);

        //Setup Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerMenu);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                ServiceActivity.this,
                drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }//Initial View

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}// Main Class
