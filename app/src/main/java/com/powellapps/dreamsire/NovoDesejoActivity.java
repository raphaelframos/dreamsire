package com.powellapps.dreamsire;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.powellapps.dreamsire.dao.DesejoDAO;
import com.powellapps.dreamsire.dao.UsuarioDao;
import com.powellapps.dreamsire.model.Desejo;
import com.powellapps.dreamsire.utils.ConstantsUtils;

public class NovoDesejoActivity extends AppCompatActivity {

    private static final String NOVO_DESEJO = "novo_desejo";
    private EditText editTextTitulo;
    private EditText editTextValor;
    private Spinner spinnerTipos;
    private Spinner spinnerStatus;
    private Button buttonSalvar;
    private DesejoDAO desejoDAO;
    private Desejo desejo;
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_desejo);
        desejoDAO = new DesejoDAO(this);
        usuarioDao = new UsuarioDao(this);
        definindoViews();
        buttonSalvar.setOnClickListener(salva);

        desejo = (Desejo) getIntent().getSerializableExtra(ConstantsUtils.DESEJO);
        if(desejo != null){
            editTextValor.setText(desejo.getValor().toString());
            editTextTitulo.setText(desejo.getTitulo());
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

    private View.OnClickListener salva = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.v(NOVO_DESEJO, "Quantidade de desejos no banco "
                    + desejoDAO.getQuantidadeDeDesejos());
            Desejo desejo = capturaDados();
            desejoDAO.salva(desejo);
            setResult(RESULT_OK);
            finish();
            Log.v(NOVO_DESEJO, "Quantidade de desejos no banco "
                    + desejoDAO.getQuantidadeDeDesejos());
        }
    };

    private Desejo capturaDados() {
        if(desejo == null) {
            desejo = new Desejo();
        }
        String titulo = editTextTitulo.getText().toString();
        String valor = editTextValor.getText().toString();
        String status = (String) spinnerStatus.getSelectedItem();
        String tipo = spinnerTipos.getSelectedItem().toString();
        desejo.setValor(valor);
        desejo.setTipo(tipo);
        desejo.setStatus(status);
        desejo.setTitulo(titulo);
        desejo.setIdUsuario(usuarioDao.getUsuario().getIdRedeSocial());
        return desejo;
    }

    private void definindoViews() {
        editTextTitulo = (EditText) findViewById(R.id.text_titulo);
        editTextValor = (EditText) findViewById(R.id.text_valor);
        spinnerStatus = (Spinner) findViewById(R.id.spinner_status);
        spinnerTipos = (Spinner) findViewById(R.id.spinner_tipos);
        buttonSalvar = (Button) findViewById(R.id.button_salvar);
    }
}
