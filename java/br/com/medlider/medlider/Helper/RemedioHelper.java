package br.com.medlider.medlider.Helper;

/**
 * Created by Filipe Firmino on 02/11/2017.
 */

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.medlider.medlider.R;
import br.com.medlider.medlider.control.CadastroRemedioActivity;
import br.com.medlider.medlider.model.Remedio;

public class RemedioHelper {

    private TextView campoTextoTituloRemedio;
    private EditText campoNomeRemedio;
    private EditText campoPrincipioAtivoRemedio;
    private Spinner  campoTipoDosagemRemedio;
    private EditText campoQuantidadeRemedio;
    private TextView campoDataValidadeRemedio;
    private EditText campoLoteRemedio;
    private Spinner  campoTipoRemedio;
    private EditText campoPrecoRemedio;

    private Remedio remedio;

    public RemedioHelper(CadastroRemedioActivity cadastroRemedioActivity) {

        campoTextoTituloRemedio = (TextView) cadastroRemedioActivity.findViewById(R.id.tela_cadastro_remedio_texto_titulo);
        campoNomeRemedio = (EditText) cadastroRemedioActivity.findViewById(R.id.tela_cadastro_remedio_nome);
        campoPrincipioAtivoRemedio = (EditText) cadastroRemedioActivity.findViewById(R.id.tela_cadastro_remedio_principio_ativo);
        campoTipoDosagemRemedio = (Spinner) cadastroRemedioActivity.findViewById(R.id.tela_cadastro_remedio_dosagem);
        campoQuantidadeRemedio = (EditText) cadastroRemedioActivity.findViewById(R.id.tela_cadastro_remedio_quantidade);
        campoDataValidadeRemedio = (TextView) cadastroRemedioActivity.findViewById(R.id.tela_cadastro_remedio_data_validade);
        campoLoteRemedio = (EditText) cadastroRemedioActivity.findViewById(R.id.tela_cadastro_remedio_lote);
        campoTipoRemedio = (Spinner) cadastroRemedioActivity.findViewById(R.id.tela_cadastro_remedio_tipo);
        campoPrecoRemedio = (EditText) cadastroRemedioActivity.findViewById(R.id.tela_cadastro_remedio_preco);

        remedio = new Remedio();
    }

    public Remedio PegaRemedio(){

        remedio.setNome(campoNomeRemedio.getText().toString());
        remedio.setPrincipioAtivo(campoPrincipioAtivoRemedio.getText().toString());
        remedio.setTipoDosagem(campoTipoDosagemRemedio.getSelectedItem().toString());
        remedio.setQuantidade(campoQuantidadeRemedio.getText().toString());
        remedio.setDataValidade(campoDataValidadeRemedio.getText().toString());
        remedio.setLote(campoLoteRemedio.getText().toString());
        remedio.setTipo(campoTipoRemedio.getSelectedItem().toString());
        remedio.setPreco(campoPrecoRemedio.getText().toString());

        return remedio;
    }

    public void PreencheCadastroRemedio(Remedio remedio){

        campoTextoTituloRemedio.setText("Editar remédio");
        campoNomeRemedio.setText(remedio.getNome());
        campoPrincipioAtivoRemedio.setText(remedio.getPrincipioAtivo());

        for (int i = 0; i < campoTipoDosagemRemedio.getCount(); i++) {
            if (campoTipoDosagemRemedio.getItemAtPosition(i).toString().equals(remedio.getTipoDosagem())) {
                campoTipoDosagemRemedio.setSelection(i);
            }
        }

        campoQuantidadeRemedio.setText(remedio.getQuantidade());
        campoDataValidadeRemedio.setText(remedio.getDataValidade());
        campoLoteRemedio.setText(remedio.getLote());
        campoPrecoRemedio.setText(remedio.getPreco());

        for (int i = 0; i < campoTipoRemedio.getCount(); i++) {
            if (campoTipoRemedio.getItemAtPosition(i).toString().equals(remedio.getTipo())) {
                campoTipoRemedio.setSelection(i);
            }
        }

        this.remedio = remedio;

    }
}
