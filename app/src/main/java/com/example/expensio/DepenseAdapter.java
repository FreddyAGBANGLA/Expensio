package com.example.expensio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensio.Model.Depense;

import java.util.List;

public class DepenseAdapter extends RecyclerView.Adapter<DepenseAdapter.ViewHolder>{
    Context context;
    List<Depense> depenseList;
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

    public DepenseAdapter(Context context, List<Depense> depenseList, RecyclerView recyclerView){
        this.context = context;
        this.depenseList = depenseList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public DepenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_single_depense, parent, false);
        // view.setOnClickListener(onClickListener);
        DepenseAdapter.ViewHolder viewHolder = new DepenseAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DepenseAdapter.ViewHolder holder, int position) {
        Depense depense = depenseList.get(position);
        holder.tv_type.setText(R.string.depense);
        holder.tv_desc.setText(depense.getDesc());
        holder.tv_categorie.setText(depense.getCategorie());
        holder.tv_montant.setText(String.valueOf(depense.getMontant()));
        holder.tv_date_time.setText(String.format("%s, %s", depense.getDate(), depense.getHeure()));
        holder.tv_nom_compte.setText(depense.getNom_compte());
    }

    @Override
    public int getItemCount() {
        return depenseList.size();
    }

    /*
    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildLayoutPosition(v);
            String item = depenseList.get(itemPosition).getCategorie();
            Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
        }
    }
     */
}
