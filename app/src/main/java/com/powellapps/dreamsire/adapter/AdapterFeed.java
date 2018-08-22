package com.powellapps.dreamsire.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.powellapps.dreamsire.NovoDesejoActivity;
import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.model.DesejoFirebase;
import com.powellapps.dreamsire.model.Usuario;
import com.powellapps.dreamsire.utils.CircleTransform;
import com.powellapps.dreamsire.utils.ConstantsUtils;
import com.powellapps.dreamsire.utils.FirebaseUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by raphaelramos on 04/11/17.
 */

public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.FeedViewHolder>{
    private final FragmentActivity activity;
    ArrayList<Desejo> desejosFirebase;

    public AdapterFeed(FragmentActivity activity, ArrayList<Desejo> desejosFirebase) {
        this.desejosFirebase = desejosFirebase;
        this.activity = activity;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_feed, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {

        final Desejo desejoFirebase = desejosFirebase.get(position);

        try {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            holder.textViewNome.setText(currentUser.getDisplayName());
            holder.textViewStatus.setText(desejoFirebase.getStatus());
            holder.textViewDesejo.setText(desejoFirebase.getTitulo());
            Picasso.with(activity).load(currentUser.getPhotoUrl().toString()).transform(new CircleTransform())
                    .into(holder.imageViewFoto);
        }catch (Exception e){
        }

        /*
        if(usuarioCurtiu(desejoFirebase)){
            holder.imageViewCurtir.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_action_bom));
        }else{
            holder.imageViewCurtir.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_action_curtir));
        }

        holder.textViewCurtidas.setText(desejoFirebase.getCurtidas().size() + " " + activity.getString(R.string.curtidas));
        holder.imageViewCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(usuario.getIdRedeSocial().isEmpty()){
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                        alertDialog.setMessage("Você precisa estar logado para curtir.");
                        alertDialog.setNeutralButton("Logar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                             //   Intent it = new Intent(activity, GoogleLoginActivity.class);
                            //    activity.startActivityForResult(it, ConstantsUtils.LOGIN);
                            }
                        });
                        alertDialog.show();
                    }else {
                        desejoFirebase.defineCurtida(usuario.getIdRedeSocial());
                        FirebaseUtils.atualizaCurtidas(desejoFirebase);
                    }
                }catch (Exception e){
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
                    alertDialog.setMessage("Você precisa estar logado para curtir.");
                    alertDialog.setNeutralButton("Logar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                       //     Intent it = new Intent(activity, GoogleLoginActivity.class);
                          //  activity.startActivityForResult(it, ConstantsUtils.LOGIN);
                        }
                    });
                    alertDialog.show();
                }
            }
        });
        */

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(activity, NovoDesejoActivity.class);
                it.putExtra(ConstantsUtils.DESEJO, desejoFirebase);
                it.putExtra(ConstantsUtils.FEED, true);
                activity.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return desejosFirebase.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewFoto;
        private TextView textViewNome;
        private TextView textViewDesejo;
        private TextView textViewCurtidas;
        private TextView textViewStatus;
        private ImageView imageViewCurtir;

        public FeedViewHolder(View itemView) {
            super(itemView);

            imageViewFoto = (ImageView) itemView.findViewById(R.id.image_foto_feed);
            textViewDesejo = (TextView) itemView.findViewById(R.id.text_titulo_feed);
            textViewNome = (TextView) itemView.findViewById(R.id.text_nome_feed);
            textViewCurtidas = (TextView) itemView.findViewById(R.id.text_curtidas);
            imageViewCurtir = (ImageView) itemView.findViewById(R.id.image_curtir);
            textViewStatus = (TextView) itemView.findViewById(R.id.text_status);
        }
    }
}
