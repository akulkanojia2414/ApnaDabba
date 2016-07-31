package example.akul.apnadabba;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    List<DishesMenu> data = Collections.emptyList();
    private CartAdapter adapter;
    private RecyclerView recyclerView;

    public List<DishesMenu> getData() {
        List<DishesMenu> data;
        List<DishesMenu> data1 = new ArrayList<DishesMenu>();
        final MyDbHelper helper = MyDbHelper.getInstance(this.getApplicationContext());
        helper.getWritableDatabase();
        data = helper.getAllContacts();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).counter != 0) {
                data1.add(data.get(i));
            }
        }
        return data1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new CartAdapter(this, getData());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        layoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Respond to the action bar's Up/Home button

            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
