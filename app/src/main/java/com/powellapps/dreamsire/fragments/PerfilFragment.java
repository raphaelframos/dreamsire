package com.powellapps.dreamsire.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.powellapps.dreamsire.R;
import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.DesejoFirebase;
import com.powellapps.dreamsire.model.Usuario;
import com.powellapps.dreamsire.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {


    private static final int RC_SIGN_IN = 123;
    private ImageView imageViewFoto;
    private TextView textViewNome;
    private TextView textViewAbertos;
    private TextView textViewRealizados;
    private TextView textViewCancelados;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth auth;
    private static String TAG = "dreamsire";

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        defineViews();

        auth = FirebaseAuth.getInstance();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            mostraDados(user);
        }else{

// Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.GoogleBuilder().build());

// Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);

        }




    }

    private void defineViews() {
        imageViewFoto = (ImageView) getView().findViewById(R.id.image_foto);
        textViewNome = (TextView) getView().findViewById(R.id.text_nome);
        textViewAbertos = (TextView) getView().findViewById(R.id.text_abertos);
        textViewCancelados = (TextView) getView().findViewById(R.id.text_cancelados);
        textViewRealizados = (TextView) getView().findViewById(R.id.text_realizados);
    }

    private void mostraDados(FirebaseUser usuario) {
        try {
            textViewNome.setText(usuario.getDisplayName());
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Buscando desejos...");
            progressDialog.show();
            DesejoDAO desejoDAO = new DesejoDAO(getContext());
            textViewAbertos.setText(desejoDAO.getQuantidadeDeDesejos(getString(R.string.aberto)));
            textViewCancelados.setText(desejoDAO.getQuantidadeDeDesejos(getString(R.string.cancelado)));
            textViewRealizados.setText(desejoDAO.getQuantidadeDeDesejos(getString(R.string.realizado)));
            progressDialog.dismiss();
            mostraFoto(usuario.getPhotoUrl().toString());
        }catch (Exception e){}
    }

    private Map<String, String> getQuantidadeDeStatus(ArrayList<DesejoFirebase> desejosFirebase) {

        Integer aberto = 0;
        Integer cancelado = 0;
        Integer realizado = 0;
        for(DesejoFirebase desejoFirebase : desejosFirebase){
            if(desejoFirebase.getDesejo().getStatus().equalsIgnoreCase(getString(R.string.aberto))){
                aberto++;
            }else if(desejoFirebase.getDesejo().getStatus().equalsIgnoreCase(getString(R.string.cancelado))){
                cancelado++;
            }else{
                realizado++;
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put(getString(R.string.aberto), aberto.toString());
        map.put(getString(R.string.realizado), realizado.toString());
        map.put(getString(R.string.cancelado), cancelado.toString());
        return map;
    }

    private void mostraFoto(String photo) {
        try {
            Picasso.with(getContext()).load(photo).transform(new CircleTransform()).into(imageViewFoto);
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


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.v(TAG, "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            mostraDados(user);
                        } else {

                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
                Log.v(TAG, "Teste aqui");
            } catch (ApiException e) {

            }
        }
    }
}
