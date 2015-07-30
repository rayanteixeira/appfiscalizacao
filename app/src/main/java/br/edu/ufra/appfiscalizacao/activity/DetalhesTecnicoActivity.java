package br.edu.ufra.appfiscalizacao.activity;


import android.support.v7.app.ActionBar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.dao.TecnicoDAO;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;
import br.edu.ufra.appfiscalizacao.rn.TecnicoRN;


public class DetalhesTecnicoActivity extends ActionBarActivity {


    TextView txvNome, txvMatricula, txvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Tecnico tecnico = (Tecnico) getIntent().getSerializableExtra("tecnico");

        //ActionBar actionBar = getSupportActionBar();

        //if (actionBar != null){
        //  actionBar.setDisplayHomeAsUpEnabled(true);
        // actionBar.setHomeButtonEnabled(true);
        //}

        //Bundle extras = getIntent().getExtras();
        //idtecnico = extras.getInt("idtecnico");
        //Tecnico tecnico = rn.obterId(idtecnico);
        //System.out.println(tecnico);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


        txvNome = (TextView) findViewById(R.id.txvNomeTec);
        txvMatricula = (TextView) findViewById(R.id.txvMatriculaTec);
        txvEmail = (TextView) findViewById(R.id.txvEmailTec);

        txvNome.setText(tecnico.getNome());
        txvMatricula.setText(tecnico.getMatricula());
        txvEmail.setText(tecnico.getEmail());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_settings) {
        // return true;
        // }

        finish();
        return super.onOptionsItemSelected(item);
    }
}