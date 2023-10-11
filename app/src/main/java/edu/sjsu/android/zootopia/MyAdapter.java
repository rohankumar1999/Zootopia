package edu.sjsu.android.zootopia;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Animal> animals;
    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView thumbnail;
        public TextView animalName;
        public View layout;
        public ViewHolder(View v) {
            super(v);
            layout = v;
            thumbnail = (ImageView) v.findViewById(R.id.thumbnail);
            animalName = (TextView) v.findViewById(R.id.animalName);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Animal> myDataset) {
        animals = myDataset;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
// create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
// set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
// - get element from your dataset at this position
// - replace the contents of the view with that element
        final Animal animal = animals.get(position);
        holder.thumbnail.setImageResource(animal.imageRes);
        holder.animalName.setText(animal.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click, in this case, launching the detail activity
                if (position == animals.size() - 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Animal is very scary, Do you want to proceed? ").setPositiveButton(
                            "Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Context context = v.getContext();
                                    Intent intent = new Intent(context, AnimalDetail.class);
                                    intent.putExtra("name", animal.name);
                                    intent.putExtra("imageRes", animal.imageRes);
                                    intent.putExtra("description", animal.description);
                                    context.startActivity(intent);
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }

                    }).create().show();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, AnimalDetail.class);
                    intent.putExtra("name", animal.name);
                    intent.putExtra("imageRes", animal.imageRes);
                    intent.putExtra("description", animal.description);
                    context.startActivity(intent);
                }

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return animals.size();
    }
}
