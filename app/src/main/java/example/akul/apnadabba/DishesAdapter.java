package example.akul.apnadabba;

/**
 * Created by akul on 10-07-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by akul on 05-07-2016.
 */

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.myViewHolder> {

    private final LayoutInflater inflater;
    List<DishesMenu> data = Collections.emptyList();
    int count = 0;
    AdapterInterface buttonListener;

    public DishesAdapter(Context context, List<DishesMenu> data, AdapterInterface buttonListener) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.buttonListener = buttonListener;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_dish_view, parent, false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        DishesMenu current = data.get(position);

        holder.item1.setText(current.name);
        holder.item2.setText(current.Description);
        holder.item.setText(Integer.toString(data.get(position).counter));
        holder.item4.setText("Rs. " + Integer.toString(data.get(position).price));

        data.get(position).counter = 0;

        holder.item.setText(Integer.toString(data.get(position).counter));

        final MyDbHelper helper = MyDbHelper.getInstance(inflater.getContext().getApplicationContext());

        helper.getWritableDatabase();

        holder.imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).counter > 0) {
                    count--;
                    data.get(position).counter--;
                }
                holder.item.setText(Integer.toString(data.get(position).counter));
                helper.update(data.get(position).counter, data.get(position));
                buttonListener.buttonclicked(count);
            }
        });
        holder.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).counter < 10) {
                    count++;
                    data.get(position).counter++;
                }
                holder.item.setText(Integer.toString(data.get(position).counter));
                helper.update(data.get(position).counter, data.get(position));
                buttonListener.buttonclicked(count);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface AdapterInterface {
        void buttonclicked(int counter);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView item3;
        TextView item1, item2, item, item4;
        ImageButton imageButton1, imageButton2;

        public myViewHolder(View itemView) {
            super(itemView);
            item3 = (ImageView) itemView.findViewById(R.id.thumbnail);
            item1 = (TextView) itemView.findViewById(R.id.title);
            item2 = (TextView) itemView.findViewById(R.id.description);
            item4 = (TextView) itemView.findViewById(R.id.Price);
            item = (TextView) itemView.findViewById(R.id.counter);
            imageButton1 = (ImageButton) itemView.findViewById(R.id.imageButton1);
            imageButton2 = (ImageButton) itemView.findViewById(R.id.imageButton2);
        }
    }
}