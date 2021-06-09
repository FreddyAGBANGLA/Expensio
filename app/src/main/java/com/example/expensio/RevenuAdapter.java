package com.example.expensio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensio.Model.Revenu;

import java.util.List;

public class RevenuAdapter extends RecyclerView.Adapter<RevenuAdapter.ViewHolder> {
    Context context;
    List<Revenu> revenuList;
    RecyclerView recyclerView;
    // final View.OnClickListener onClickListener = new MyOnClickListener();

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_type, tv_categorie, tv_desc, tv_montant, tv_date_time, tv_nom_compte;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_type = itemView.findViewById(R.id.view_type);
            tv_desc = itemView.findViewById(R.id.view_desc);
            tv_categorie = itemView.findViewById(R.id.view_categorie);
            tv_montant = itemView.findViewById(R.id.view_montant);
            tv_date_time = itemView.findViewById(R.id.view_date_time);
            tv_nom_compte = itemView.findViewById(R.id.view_nom_compte);
        }
    }

    public RevenuAdapter(Context context, List<Revenu> revenuList, RecyclerView recyclerView){
        this.context = context;
        this.revenuList = revenuList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RevenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_single_revenu, parent, false);
        // view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RevenuAdapter.ViewHolder holder, int position) {
        Revenu revenu = revenuList.get(position);
        holder.tv_type.setText(R.string.revenu);
        holder.tv_desc.setText(revenu.getDesc());
        holder.tv_categorie.setText(revenu.getCategorie());
        holder.tv_montant.setText(String.valueOf(revenu.getMontant()));
        holder.tv_date_time.setText(String.format("%s, %s", revenu.getDate(), revenu.getHeure()));
        holder.tv_nom_compte.setText(revenu.getNom_compte());
    }

    @Override
    public int getItemCount() {
        return revenuList.size();
    }

    /*
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildLayoutPosition(v);
            String item = revenuList.get(itemPosition).getCategorie();
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
        }
    }

     */
}
