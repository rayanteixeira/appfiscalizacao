package br.edu.ufra.appfiscalizacao.rn;

import android.content.Context;

import br.edu.ufra.appfiscalizacao.dao.VistoriaDAO;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;

/**
 * Created by Rayan on 01/07/2015.
 */
public class VistoriaRN extends GenericRN<Vistoria>{

    VistoriaDAO dao;
    public VistoriaRN( Context context) {
        super(Vistoria.class, context);
        dao = new VistoriaDAO(context);

    }
}
