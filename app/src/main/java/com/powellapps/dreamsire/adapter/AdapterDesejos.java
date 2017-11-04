package com.powellapps.dreamsire.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powellapps.dreamsire.NovoDesejoActivity;
import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.utils.ConstantsUtils;

import java.util.ArrayList;

/**
 * Created by raphaelramos on 01/09/17.
 */

public class AdapterDesejos extends RecyclerView.Adapter<AdapterDesejos.DesejosViewHolder>{

    private ArrayList<Desejo> desejos = new ArrayList<>();
    private FragmentActivity activity;

    public AdapterDesejos(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public DesejosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_desejo, parent, false);
        return new DesejosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DesejosViewHolder holder, int position) {

        final Desejo desejo = desejos.get(position);
        holder.textViewTitulo.setText(desejo.getTitulo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(activity, NovoDesejoActivity.class);
                it.putExtra(ConstantsUtils.DESEJO, desejo);
                activity.startActivityForResult(it, 1);
            }
        });

        if(desejo.esta(activity.getString(R.string.realizado))){
            holder.textViewTitulo.setPaintFlags(holder.textViewTitulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textViewTitulo.setAlpha(0.9f);
            holder.textViewTitulo.setTextColor(Color.GRAY);
        }else if(desejo.esta(activity.getString(R.string.cancelado))){
            holder.textViewTitulo.setTextColor(Color.RED);
            holder.textViewTitulo.setPaintFlags(holder.textViewTitulo.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            holder.textViewTitulo.setAlpha(0.2f);
        }else{
            holder.textViewTitulo.setAlpha(1f);
            holder.textViewTitulo.setPaintFlags(holder.textViewTitulo.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            holder.textViewTitulo.setTextColor(Color.DKGRAY);
        }
     //
    }

    @Override
    public int getItemCount() {
        return desejos.size();
    }

    public void atualiza(ArrayList<Desejo> desejos) {
        this.desejos = desejos;
        this.notifyDataSetChanged();
    }

    public class DesejosViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitulo;

        public DesejosViewHolder(View itemView) {
            super(itemView);

            textViewTitulo = (TextView) itemView.findViewById(R.id.text_titulo);
        }
    }
}
