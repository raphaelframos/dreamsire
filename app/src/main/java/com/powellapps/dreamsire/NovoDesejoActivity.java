package com.powellapps.dreamsire;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.model.DesejoFirebase;
import com.powellapps.dreamsire.model.Usuario;
import com.powellapps.dreamsire.utils.ConstantsUtils;
import com.powellapps.dreamsire.utils.FirebaseUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NovoDesejoActivity extends AppCompatActivity {

    private EditText editTextTitulo;
    private EditText editTextValor;
    private EditText editTextDescricao;
    private Spinner spinnerTipos;
    private Spinner spinnerStatus;
    private Button buttonSalvar;
    private Desejo desejo;
    private ProgressDialog progressDialog;
    private boolean feed;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_desejo);
        definindoViews();
        buttonSalvar.setOnClickListener(salva);
        user = FirebaseAuth.getInstance().getCurrentUser();

      //  desejo = (Desejo) getIntent().getSerializableExtra(ConstantsUtils.DESEJO);
        feed = getIntent().getBooleanExtra(ConstantsUtils.FEED, false);
        if(estouEditando()){
            editTextValor.setText(desejo.getValor().toString());
            editTextTitulo.setText(desejo.getTitulo());
            editTextDescricao.setText(desejo.getDescricao());
            spinnerTipos.setSelection(retornaPosicaoDoTipo(desejo.getTipo()));
            spinnerStatus.setSelection(retornaPosicaoDoStatus(desejo.getStatus()));

            String title = "";
            if(false){
                title = "Editando desejo";
            }else{
                title = user.getDisplayName();
                buttonSalvar.setVisibility(View.GONE);
                editTextTitulo.setEnabled(false);
                editTextDescricao.setEnabled(false);
                editTextValor.setEnabled(false);
                spinnerStatus.setEnabled(false);
                spinnerTipos.setEnabled(false);

            }
            getSupportActionBar().setTitle(title);
        }

        spinnerTipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1){
                    editTextValor.setVisibility(View.GONE);
                }else{
                    editTextValor.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean estouEditando() {
        try{
            return !desejo.getId().isEmpty();
        }catch (Exception e){
            return false;
        }

    }

    private int retornaPosicaoDoTipo(String tipo) {
        ArrayList<String> tipos = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.tipos)));
        return tipos.indexOf(tipo);
    }

    private int retornaPosicaoDoStatus(String status) {
        ArrayList<String> tipos = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.status)));
        return tipos.indexOf(status);
    }

    private View.OnClickListener salva = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (capturaDados()) {
                progressDialog = new ProgressDialog(NovoDesejoActivity.this);
                progressDialog.setMessage("Salvando desejo...");
                progressDialog.show();

                desejo.setIdUsuario(user.getUid());
                if(estouEditando()){
                   // FirebaseUtils.atualiza(desejoFirebase);

                }else {
                    FirebaseUtils.salva(desejo);
                }
                finaliza();


            }
        }
    };

    private void finaliza() {
        setResult(RESULT_OK);
        finish();
        try {
            progressDialog.dismiss();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean capturaDados() {
        if(desejo == null) {
            desejo = new Desejo();
        }
        String titulo = editTextTitulo.getText().toString();

        if(titulo.isEmpty()){
            editTextTitulo.setError(getString(R.string.campo_branco));
            return false;
        }
        String valor = editTextValor.getText().toString();
        String status = (String) spinnerStatus.getSelectedItem();
        String tipo = spinnerTipos.getSelectedItem().toString();
        String descricao = editTextDescricao.getText().toString();
        desejo.setValor(valor);
        desejo.setTipo(tipo);
        desejo.setStatus(status);
        desejo.setTitulo(titulo);
        desejo.setDescricao(descricao);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(estouEditando() && !feed){
            getMenuInflater().inflate(R.menu.editar_desejo, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public DialogInterface.OnClickListener removeDesejo = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            try {
           //     FirebaseUtils.remove(desejoFirebase);
            }catch (Exception e){
             //   desejoDAO.remove(desejo.getId());
            }finally {
                finaliza();
            }

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_remover:

                android.app.AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setTitle("Atenção").setMessage("Deseja remover o desejo: " + desejo.getTitulo() + "?")
                        .setNegativeButton("Não", null).setPositiveButton("Sim", removeDesejo)
                        .create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void definindoViews() {
        editTextTitulo = (EditText) findViewById(R.id.text_titulo);
        editTextValor = (EditText) findViewById(R.id.text_valor);
        spinnerStatus = (Spinner) findViewById(R.id.spinner_status);
        spinnerTipos = (Spinner) findViewById(R.id.spinner_tipos);
        buttonSalvar = (Button) findViewById(R.id.button_salvar);
        editTextDescricao = (EditText) findViewById(R.id.text_descricao);
    }
}
