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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.model.Singleton;
import com.powellapps.dreamsire.model.Usuario;
import com.powellapps.dreamsire.utils.ConstantsUtils;
import com.powellapps.dreamsire.utils.FirebaseUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class NovoDesejoActivity extends AppCompatActivity {

    private static final String NOVO_DESEJO = "novo_desejo";
    private EditText editTextTitulo;
    private EditText editTextValor;
    private Spinner spinnerTipos;
    private Spinner spinnerStatus;
    private Spinner spinnerVisibilidade;
    private Button buttonSalvar;
    private DesejoDAO desejoDAO;
    private Desejo desejo;
    private UsuarioDao usuarioDao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_desejo);
        iniciaDao();
        definindoViews();
        buttonSalvar.setOnClickListener(salva);

        desejo = (Desejo) getIntent().getSerializableExtra(ConstantsUtils.DESEJO);
        if(estouEditando()){
            editTextValor.setText(desejo.getValor().toString());
            editTextTitulo.setText(desejo.getTitulo());
            spinnerTipos.setSelection(retornaPosicaoDoTipo(desejo.getTipo()));
            spinnerStatus.setSelection(retornaPosicaoDoStatus(desejo.getStatus()));
            spinnerVisibilidade.setSelection(retornaPosicaoDeVisibilidade(desejo.getVisibilidade()));
            getSupportActionBar().setTitle("Editando desejo");
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
        return desejo != null;
    }

    private int retornaPosicaoDoTipo(String tipo) {
        ArrayList<String> tipos = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.tipos)));
        return tipos.indexOf(tipo);
    }

    private int retornaPosicaoDeVisibilidade(String visibilidade) {
        ArrayList<String> lista = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.visibilidade)));
        return lista.indexOf(visibilidade);
    }

    private int retornaPosicaoDoStatus(String status) {
        ArrayList<String> tipos = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.status)));
        return tipos.indexOf(status);
    }

    private void iniciaDao() {
        desejoDAO = new DesejoDAO(this);
        usuarioDao = new UsuarioDao(this);
    }

    private View.OnClickListener salva = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (capturaDados()) {
                progressDialog = new ProgressDialog(NovoDesejoActivity.this);
                progressDialog.setMessage("Salvando desejo...");
                progressDialog.show();
                Long id = desejoDAO.salva(desejo);

                if (desejo.isPublico(getString(R.string.publico))) {
                    Usuario usuario = usuarioDao.getUsuario();
                    desejo.setId(id.intValue());
                    FirebaseUtils.salva(usuario, desejo);
                    finaliza();
                } else {
                    finaliza();
                }

            }
        }
    };

    private void finaliza() {
        setResult(RESULT_OK);
        finish();
        progressDialog.dismiss();
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
        String visibilidade = spinnerVisibilidade.getSelectedItem().toString();
        desejo.setValor(valor);
        desejo.setTipo(tipo);
        desejo.setStatus(status);
        desejo.setTitulo(titulo);
        desejo.setVisibilidade(visibilidade);
        desejo.setIdUsuario(usuarioDao.getUsuario().getIdRedeSocial());
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(estouEditando()){
            getMenuInflater().inflate(R.menu.editar_desejo, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public DialogInterface.OnClickListener removeDesejo = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            desejoDAO.remove(desejo.getId());
            finaliza();
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
        spinnerVisibilidade = (Spinner) findViewById(R.id.spinner_visibilidade);
    }
}
