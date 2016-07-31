package example.akul.apnadabba;

/**
 * Created by akul on 10-07-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by akul on 05-07-2016.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.myViewHolder> {

    private final LayoutInflater inflater;
    List<DishesMenu> data = Collections.emptyList();

    public CartAdapter(Context context, List<DishesMenu> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.upvote, parent, false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        DishesMenu current = data.get(position);
        holder.item1.setText(current.name);
        holder.item2.setText(current.Description);


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView item3;
        TextView item1, item2, item;

        public myViewHolder(View itemView) {
            super(itemView);
            item3 = (ImageView) itemView.findViewById(R.id.thumbnail);
            item1 = (TextView) itemView.findViewById(R.id.title);
            item2 = (TextView) itemView.findViewById(R.id.description);
        }
    }
}