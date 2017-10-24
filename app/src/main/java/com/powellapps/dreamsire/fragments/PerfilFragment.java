package com.powellapps.dreamsire.fragments;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Usuario;
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
        imageViewFoto = (ImageView) getView().findViewById(R.id.image_foto);
        textViewNome = (TextView) getView().findViewById(R.id.text_nome);
        textViewAbertos = (TextView) getView().findViewById(R.id.text_abertos);
        textViewCancelados = (TextView) getView().findViewById(R.id.text_cancelados);
        textViewRealizados = (TextView) getView().findViewById(R.id.text_realizados);

        usuarioDao = new UsuarioDao(getContext());
        desejoDAO = new DesejoDAO(getContext());

        Usuario usuario = usuarioDao.getUsuario();
        Picasso.with(getContext()).load(usuario.getFoto()).into(imageViewFoto);
        textViewNome.setText(usuario.getNome());
        textViewAbertos.setText(desejoDAO.getQuantidadeDeDesejos(usuario.getIdRedeSocial(), getString(R.string.aberto)));
        textViewCancelados.setText(desejoDAO.getQuantidadeDeDesejos(usuario.getIdRedeSocial(), getString(R.string.cancelado)));
        textViewRealizados.setText(desejoDAO.getQuantidadeDeDesejos(usuario.getIdRedeSocial(), getString(R.string.realizado)));

    }
}
