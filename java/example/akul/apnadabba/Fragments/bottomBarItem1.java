package example.akul.apnadabba.Fragments;

/**
 * Created by akul on 04-07-2016.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.akul.apnadabba.BadgeDrawable;
import example.akul.apnadabba.DishesAdapter;
import example.akul.apnadabba.DishesMenu;
import example.akul.apnadabba.MyDbHelper;
import example.akul.apnadabba.R;


public class bottomBarItem1 extends Fragment implements DishesAdapter.AdapterInterface{
    public List<DishesMenu> mydata = new ArrayList<>();
    public List<DishesMenu> temp = new ArrayList<>();
    private DishesAdapter adapter;
    private RecyclerView recyclerView;
    LayerDrawable icon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.bottombaritem1, parent, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        setData();
        setHasOptionsMenu(true);
        adapter = new DishesAdapter(getActivity(), (mydata),this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());


        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        layoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(layoutManager);


        return layout;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemCart = menu.findItem(R.id.cart);
        icon = (LayerDrawable) itemCart.getIcon();

    }

    public static void setBadgeCount(Context context, LayerDrawable icon, String count) {

        BadgeDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);
        if (reuse != null && reuse instanceof BadgeDrawable) {
            badge = (BadgeDrawable) reuse;
        } else {
            badge = new BadgeDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    public void setData() {
        final MyDbHelper helper = MyDbHelper.getInstance(this.getActivity().getApplicationContext());
        helper.getWritableDatabase();
        temp = helper.getAllContacts();
        for(int i =0;i<temp.size();i++)
        {
            if(temp.get(i).today == 1)
            {
                mydata.add(temp.get(i));
            }
        }
    }

    @Override
    public void buttonclicked(int counter) {
        setBadgeCount(this.getActivity(), icon, String.valueOf(counter));
    }
}