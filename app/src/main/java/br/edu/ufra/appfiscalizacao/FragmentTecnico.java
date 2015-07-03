package br.edu.ufra.appfiscalizacao;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import br.edu.ufra.appfiscalizacao.activity.DetalheActivity;
import br.edu.ufra.appfiscalizacao.adapter.TecnicoAdapter;
import br.edu.ufra.appfiscalizacao.application.StringURL;
import br.edu.ufra.appfiscalizacao.application.VolleyApplication;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;


public class FragmentTecnico extends Fragment {

    Gson gson;

    List<Tecnico> tecnicos = null;
    TextView mText;
    TecnicoAdapter adapter = null;
    ListView listtecnicos;
    StringURL url = new StringURL();
    ProgressDialog mProgressDialog;
    Context context = null;

    public FragmentTecnico() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_tecnicos, container, false);

        listtecnicos = (ListView) rootview.findViewById(R.id.listtecnicos);

        gson = new Gson();
        mText = (TextView) rootview.findViewById(R.id.txtjson);


        mProgressDialog = ProgressDialog.show(context,"Baixando lista", "Por favor, aguarde enquanto a lista é atualizada...", false, true);

        new Thread() {
            public void run() {
                try{
                    getjsonArray();
                    //esse � apenas um m�todo de teste que pode ser demorado
                    Thread.sleep(10 * 1000);//10segundos ou 10000
                } catch (Exception e) {
                    Log.e("tag", e.getMessage());
                }
                //encerra progress dialog
                mProgressDialog.dismiss();
            }
        }.start();

        return rootview;
    }


    public void getjsonArray() {


        System.out.println("Baixando JSONARRAY");
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                url.URLTecnicos,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(final JSONArray response) {

                        int i;
                        tecnicos = new ArrayList<>();

                        for (i = 0; i < response.length(); i++) {
                            try {
                                JSONObject mTecnicoItem = response.getJSONObject(i);
                                System.out.println("Estabelecimento Item -> " + mTecnicoItem);
                                Tecnico tecnico = gson.fromJson(String.valueOf(mTecnicoItem), Tecnico.class);
                                tecnicos.add(tecnico);

                                //tecnico.setNome(mTecnicoItem.getString("nome"));
                                //System.out.println(tecnico.getNome());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                        for (Tecnico t : tecnicos) {
                            System.out.println(t.getId());
                        }
                        Toast.makeText(getActivity().getBaseContext(), "Lista atualizada com sucesso", Toast.LENGTH_SHORT).show();

                        adapter = new TecnicoAdapter(getActivity().getBaseContext(), tecnicos);
                        listtecnicos.setAdapter(adapter);
                        listtecnicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Tecnico item = (Tecnico) adapter.getItem(position);
                                int idtecnico = item.getId();
                                System.out.println("ITEM--=>" + item.getId());
                                System.out.println("NOME--=>" + item.getNome());

                                Intent it = new Intent(getActivity().getBaseContext(), DetalheActivity.class);
                                it.putExtra("id", idtecnico);
                                startActivity(it);



                                /* Fragment chamando Fragment e passango argumento entre eles
                                FragmentMain mFragmentMain = new FragmentMain().newInstance(((Tecnico) adapter.getItem(position)).getNome());
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.container, mFragmentMain);
                                ft.addToBackStack(null);
                                ft.commit();
                                */

                            }
                        });


                    }


                }

                ,
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        mText.setText(error.toString());
                        Toast.makeText(getActivity().getBaseContext(), "Erro ao atualizar Lista ", Toast.LENGTH_SHORT).show();
                    }
                });


        //int socketTimeout = 30000;//30 seconds - change to what you want
        // RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        // arrayRequest.setRetryPolicy(policy);


        VolleyApplication.getsInstance().getmRequestQueue().add(arrayRequest);

    }
}
