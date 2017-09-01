package com.powellapps.dreamsire.adapter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.model.Desejo;

import java.util.ArrayList;

/**
 * Created by raphaelramos on 01/09/17.
 */

public class AdapterDesejos extends RecyclerView.Adapter<AdapterDesejos.DesejosViewHolder>{

    private ArrayList<Desejo> desejos = new ArrayList<>();

    @Override
    public DesejosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_desejo, parent, false);
        return new DesejosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DesejosViewHolder holder, int position) {

        Desejo desejo = desejos.get(position);
        holder.textViewTitulo.setText(desejo.getTitulo());
     //   holder.textViewTitulo.setPaintFlags(holder.textViewTitulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
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
