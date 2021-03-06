package br.edu.ufra.appfiscalizacao.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;


import br.edu.ufra.appfiscalizacao.FragmentVistoria;
import br.edu.ufra.appfiscalizacao.FragmentEstabelecimento;
import br.edu.ufra.appfiscalizacao.FragmentTecnico;
import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.rn.TecnicoRN;
import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.navigationliveo.NavigationActionBarLiveo;


public class MainActivity extends NavigationActionBarLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;
    public Estabelecimento estabelecimento;
    @Override
    public void onInt(Bundle savedInstanceState) {

        // User Information

        this.userName.setText("Açaí Pai D'égua");
        this.userEmail.setText("acaipaideguaufra@gmail.com");
        this.userPhoto.setImageResource(R.drawable.ic_photologo);
        this.userBackground.setImageResource(R.drawable.acaimao);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        //mHelpLiveo.add(getString(R.string.inbox), R.drawable.ic_inbox_black_24dp, 7);

        removeHeader();
        mHelpLiveo.addSubHeader(getString(R.string.menu)); //Item subHeader
        mHelpLiveo.add(getString(R.string.estabelecimento), R.drawable.ic_estabelecimento);
        mHelpLiveo.add(getString(R.string.vistoria), R.drawable.ic_vistoria);
       // mHelpLiveo.add(getString(R.string.tecnico), R.drawable.ic_tecnico);
        //mHelpLiveo.add(getString(R.string.equipamento), R.drawable.ic_equipamento);
        //mHelpLiveo.add(getString(R.string.spam), R.drawable.ic_report_black_24dp, 120);


        with(this).startingPosition(1) //Starting position in the list //onde é iniciado o marcador ao abrir o app
                .addAllHelpItem(mHelpLiveo.getHelp())


                        //{optional} - List Customization "If you remove these methods and the list will take his white standard color"
                        //.selectorCheck(R.drawable.selector_check) //Inform the background of the selected item color
                        //.colorItemDefault(R.color.nliveo_blue_colorPrimary) //Inform the standard color name, icon and counter
                        //.colorItemSelected(R.color.nliveo_purple_colorPrimary) //State the name of the color, icon and meter when it is selected
                .backgroundList(R.color.nliveo_white) //Inform the list of background color
                        //.colorLineSeparator(R.color.nliveo_transparent) //Inform the color of the subheader line.
                        //{optional} - SubHeader Customization
                .colorItemSelected(R.color.nliveo_purple_colorPrimary)
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)

                        //.colorLineSeparator(R.color.nliveo_blue_colorPrimary)

                .footerItem(R.string.sair, R.drawable.ic_sobre)

                        //{optional} - Footer Customization
                .footerNameColor(R.color.nliveo_purple_colorPrimary)
                .footerIconColor(R.color.nliveo_purple_colorPrimary)
                .footerBackground(R.color.nliveo_white)


                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)


                .build();


    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment = null;

        switch (position){
            case 1:
                mFragment = new FragmentEstabelecimento();
                break;
            case 2:
                 mFragment = new FragmentVistoria();
                break;
            case 3:
                mFragment = new FragmentTecnico();
            default:
        }


        FragmentManager mFragmentManager = getSupportFragmentManager();
        //Fragment mFragment = new FragmentMain().newInstance(mHelpLiveo.get(position).getName());

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "onClickPhoto :D", Toast.LENGTH_SHORT).show();
            closeDrawer();
        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           /*
            SharedPreferences sharedP=getSharedPreferences("LoginActivityPreferences",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedP.edit();

            editor.clear().commit();
            */
            TecnicoRN rn = new TecnicoRN(getBaseContext());
            rn.removerTecnicos();
            finish();
            closeDrawer();
        }
    };
}
