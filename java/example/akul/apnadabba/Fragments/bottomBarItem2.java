package example.akul.apnadabba.Fragments;

/**
 * Created by akul on 04-07-2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.akul.apnadabba.DishesMenu;
import example.akul.apnadabba.MyDbHelper;
import example.akul.apnadabba.R;
import example.akul.apnadabba.upvoteAdapter;


public class bottomBarItem2 extends Fragment {
    public List<DishesMenu> mydata = new ArrayList<>();
    private upvoteAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.bottombaritem2, parent, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view);
        setData();
        adapter = new upvoteAdapter(getActivity(), mydata);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());


        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        layoutManager.scrollToPosition(0);

        recyclerView.setLayoutManager(layoutManager);
        return layout;
    }

    public void setData() {
        final MyDbHelper helper = MyDbHelper.getInstance(this.getActivity().getApplicationContext());
        helper.getWritableDatabase();
        mydata = helper.getAllContacts();
    }
}
