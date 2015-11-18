package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.application.pojo.InspecaoPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.entidade.Inspecao;

/**
 * Created by geovane on 12/08/15.
 */
public class InspecaoAdapter extends BaseAdapter {

    private LayoutInflater mInflate;
    private List<InspecaoPOJO> inspecoes;

    public InspecaoAdapter(Context context, List<InspecaoPOJO> inspecoes) {
        this.mInflate = LayoutInflater.from(context);
        this.inspecoes = inspecoes;
    }

    @Override
    public int getCount() {
        return inspecoes.size();
    }

    @Override
    public Object getItem(int position) {
        return inspecoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        InspecaoPOJO inspecao = inspecoes.get(position);

        view = mInflate.inflate(R.layout.inspecao_model, null);
        TextView nomeEquipamento = (TextView) view.findViewById(R.id.nomeEquip);

        nomeEquipamento.setText(inspecao.getEquipamentoPOJO().getNome());

        return view;
    }
}