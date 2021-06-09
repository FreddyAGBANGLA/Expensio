package com.example.expensio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensio.Model.Transfert;

import java.util.List;

public class TransfertAdapter extends RecyclerView.Adapter<TransfertAdapter.ViewHolder> {
    Context context;
    List<Transfert> transfertList;
    RecyclerView recyclerView;
    // final View.OnClickListener onClickListener = new MyOnClickListener();

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_type, tv_desc, tv_montant, tv_date_time, tv_nom_compte_source, tv_nom_compte_destination;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_type = itemView.findViewById(R.id.view_type);
            tv_desc = itemView.findViewById(R.id.view_desc);
            tv_montant = itemView.findViewById(R.id.view_montant);
            tv_date_time = itemView.findViewById(R.id.view_date_time);
            tv_nom_compte_source = itemView.findViewById(R.id.view_nom_compte_source);
            tv_nom_compte_destination = itemView.findViewById(R.id.view_nom_compte_destination);
        }
    }

    public TransfertAdapter(Context context, List<Transfert> transfertList, RecyclerView recyclerView){
        this.context = context;
        this.transfertList = transfertList;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public TransfertAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_single_transfert, parent, false);
        // view.setOnClickListener(onClickListener);
        TransfertAdapter.ViewHolder viewHolder = new TransfertAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransfertAdapter.ViewHolder holder, int position) {
        Transfert transfert = transfertList.get(position);
        holder.tv_type.setText(R.string.transfert1);
        holder.tv_desc.setText(transfert.getDesc());
        holder.tv_montant.setText(String.valueOf(transfert.getMontant()));
        holder.tv_date_time.setText(String.format("%s, %s", transfert.getDate(), transfert.getHeure()));
        holder.tv_nom_compte_source.setText(transfert.getNom_compte_source());
        holder.tv_nom_compte_destination.setText(transfert.getNom_compte_destination());
    }

    @Override
    public int getItemCount() {
        return transfertList.size();
    }

}
