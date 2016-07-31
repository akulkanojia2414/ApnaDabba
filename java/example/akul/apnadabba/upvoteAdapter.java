package example.akul.apnadabba;

/**
 * Created by akul on 10-07-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Collections;
import java.util.List;

/**
 * Created by akul on 05-07-2016.
 */

public class upvoteAdapter extends RecyclerView.Adapter<upvoteAdapter.myViewHolder> {

    private final LayoutInflater inflater;
    List<DishesMenu> data = Collections.emptyList();

    public upvoteAdapter(Context context, List<DishesMenu> data) {
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
        holder.item.setText(String.valueOf(current.likes));
        holder.item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).likes > 0)
                    data.get(position).likes--;
                holder.item.setText(String.valueOf(data.get(position).likes));
                Backendless.Persistence.of(DishesMenu.class).remove(data.get(position), new AsyncCallback<Long>() {
                    public void handleResponse(Long response) {
                        // Contact has been deleted. The response is a time in milliseconds when the object was deleted
                        Log.d("Akul", "Great");
                    }

                    public void handleFault(BackendlessFault fault) {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                        Log.d("Akul", fault.getMessage());
                    }
                });
                Backendless.Persistence.save(data.get(position), new AsyncCallback<DishesMenu>() {
                    @Override
                    public void handleResponse(DishesMenu response) {
                        // Contact instance has been updated
                        Log.d("Akul", "Great");
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                        Log.d("Akul", fault.getMessage());
                    }
                });
            }
        });
        holder.item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.get(position).likes++;
                holder.item.setText(String.valueOf(data.get(position).likes));
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView item3;
        TextView item1, item2, item;
        ImageButton item4, item5;

        public myViewHolder(View itemView) {
            super(itemView);
            item3 = (ImageView) itemView.findViewById(R.id.thumbnail);
            item1 = (TextView) itemView.findViewById(R.id.title);
            item2 = (TextView) itemView.findViewById(R.id.description);
            item = (TextView) itemView.findViewById(R.id.Likes);
            item4 = (ImageButton) itemView.findViewById(R.id.imageButton3);
            item5 = (ImageButton) itemView.findViewById(R.id.imageButton4);
        }
    }
}
