package example.akul.apnadabba;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import example.akul.apnadabba.Fragments.bottomBarItem1;
import example.akul.apnadabba.Fragments.bottomBarItem2;
import example.akul.apnadabba.Fragments.bottomBarItem3;
import example.akul.apnadabba.Fragments.bottomBarItem4;
import example.akul.apnadabba.Fragments.bottomBarItem5;


public class MainActivity extends AppCompatActivity {
    private BottomBar mBottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Always cast your custom Toolbar here, and set it as the ActionBar.
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noTopOffset();
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_recents) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem1()).commit();
                }
                if (menuItemId == R.id.bb_menu_favorites) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem2()).commit();
                }
                if (menuItemId == R.id.bb_menu_nearby) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem3()).commit();
                }
                if (menuItemId == R.id.bb_menu_friends) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem4()).commit();
                }
                if (menuItemId == R.id.bb_menu_food) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem5()).commit();
                }
            }


            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_recents) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem1()).commit();
                }
                if (menuItemId == R.id.bb_menu_favorites) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem2()).commit();
                }
                if (menuItemId == R.id.bb_menu_nearby) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem3()).commit();
                }
                if (menuItemId == R.id.bb_menu_friends) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem4()).commit();
                }
                if (menuItemId == R.id.bb_menu_food) {
                    // The user selected item number one.
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id, new bottomBarItem5()).commit();
                }
            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        //  mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(0, "#ed1f24");
        mBottomBar.mapColorForTab(1, "#ed1f24");
        mBottomBar.mapColorForTab(2, "#ed1f24");
        mBottomBar.mapColorForTab(3, "#ed1f24");
        mBottomBar.mapColorForTab(4, "#ed1f24");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cart) {
            startActivity(new Intent(this, CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
