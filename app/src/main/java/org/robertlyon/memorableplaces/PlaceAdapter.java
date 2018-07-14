package org.robertlyon.memorableplaces;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {


    private Context mCtx;
    private List<Place> placeList;


    PlaceAdapter(Context mCtx, List<Place> places)
    {
        this.mCtx = mCtx;
        this.placeList = places;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recyclerview_places, null);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        Place place = placeList.get(position);
        holder.placeText.setText(place.getAddress());
        holder.placeText.setTag(place.getId());
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }


    class PlaceViewHolder extends RecyclerView.ViewHolder
    {
        TextView placeText;


        PlaceViewHolder(View itemView) {
            super(itemView);
            placeText = itemView.findViewById(R.id.placeText);
        }

    }
}
