package com.powellapps.dreamsire.fragments;


import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Usuario;
import com.powellapps.dreamsire.utils.CircleTransform;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


    private ImageView imageViewFoto;
    private UsuarioDao usuarioDao;
    private TextView textViewNome;
    private TextView textViewAbertos;
    private TextView textViewRealizados;
    private TextView textViewCancelados;
    private DesejoDAO desejoDAO;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        defineViews();
        setHasOptionsMenu(true);
        iniciaDao();
        Usuario usuario = usuarioDao.getUsuario();
        mostraDados(usuario);

    }

    private void iniciaDao() {
        usuarioDao = new UsuarioDao(getContext());
        desejoDAO = new DesejoDAO(getContext());
    }

    private void defineViews() {
        imageViewFoto = (ImageView) getView().findViewById(R.id.image_foto);
        textViewNome = (TextView) getView().findViewById(R.id.text_nome);
        textViewAbertos = (TextView) getView().findViewById(R.id.text_abertos);
        textViewCancelados = (TextView) getView().findViewById(R.id.text_cancelados);
        textViewRealizados = (TextView) getView().findViewById(R.id.text_realizados);
    }

    private void mostraDados(Usuario usuario) {
        try {
            textViewNome.setText(usuario.getNome());
            textViewAbertos.setText(desejoDAO.getQuantidadeDeDesejos(usuario.getIdRedeSocial(), getString(R.string.aberto)));
            textViewCancelados.setText(desejoDAO.getQuantidadeDeDesejos(usuario.getIdRedeSocial(), getString(R.string.cancelado)));
            textViewRealizados.setText(desejoDAO.getQuantidadeDeDesejos(usuario.getIdRedeSocial(), getString(R.string.realizado)));
            mostraFoto(usuario);
        }catch (Exception e){}
    }

    private void mostraFoto(Usuario usuario) {
        try {
            Picasso.with(getContext()).load(usuario.getFoto()).transform(new CircleTransform()).into(imageViewFoto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.perfil, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_deslogar:
                FirebaseAuth.getInstance().signOut();
                usuarioDao.deleta();
                mostraDados(new Usuario());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
