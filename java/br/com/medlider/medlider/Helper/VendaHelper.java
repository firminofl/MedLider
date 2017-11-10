package br.com.medlider.medlider.Helper;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.medlider.medlider.R;
import br.com.medlider.medlider.control.ListarRemedioActivity;
import br.com.medlider.medlider.control.VenderRemedioActivity;
import br.com.medlider.medlider.model.Cliente;
import br.com.medlider.medlider.model.Remedio;
import br.com.medlider.medlider.model.Venda;

/**
 * Created by Filipe Firmino on 08/11/2017.
 */

public class VendaHelper {

    private EditText campoNomeRemedio;
    private Spinner  campoTipoRemedio;
    private EditText campoPrecoRemedio;
    private EditText campoQuantidadeRemedio;
    private TextView campoTotalVenda;
    private TextView campoNomeCliente;
    private TextView campoCPFCliente;

    private Venda venda;

    public VendaHelper(VenderRemedioActivity venderRemedioActivity) {

        campoNomeRemedio = (EditText) venderRemedioActivity.findViewById(R.id.tela_concluir_venda_nome_remedio);
        campoTipoRemedio = (Spinner) venderRemedioActivity.findViewById(R.id.tela_concluir_venda_tipo_remedio);
        campoPrecoRemedio = (EditText) venderRemedioActivity.findViewById(R.id.tela_concluir_venda_preco_remedio);
        campoQuantidadeRemedio = (EditText) venderRemedioActivity.findViewById(R.id.tela_concluir_venda_quantidade_remedio);
        campoTotalVenda = (TextView) venderRemedioActivity.findViewById(R.id.tela_concluir_venda_total);
        campoNomeCliente = (TextView) venderRemedioActivity.findViewById(R.id.tela_concluir_venda_nome_cliente);
        campoCPFCliente = (TextView) venderRemedioActivity.findViewById(R.id.tela_concluir_venda_CPF_cliente);

        venda = new Venda();
    }

    public Venda PegaVenda(){

        venda.setVendaNomeRemedio(campoNomeRemedio.getText().toString());
        venda.setVendaTipoRemedio(campoTipoRemedio.getSelectedItem().toString());
        venda.setVendaPrecoRemedio(campoPrecoRemedio.getText().toString());
        venda.setVendaQuantidadeRemedio(Integer.parseInt(campoQuantidadeRemedio.getText().toString()));
        venda.setVendaTotalVenda(campoTotalVenda.getText().toString());
        venda.setVendaNomeCliente(campoNomeCliente.getText().toString());
        venda.setVendaCPFCliente(campoCPFCliente.getText().toString());

        return venda;
    }

    public void PreencheVendaRemedio (Remedio remedio){

        campoNomeRemedio.setText(remedio.getNome());

        for (int i = 0; i < campoTipoRemedio.getCount(); i++) {
            if (campoTipoRemedio.getItemAtPosition(i).toString().equals(remedio.getTipo())) {
                campoTipoRemedio.setSelection(i);
            }
        }

        campoPrecoRemedio.setText(remedio.getPreco());
    }

    public void PreencheInfoCliente(Cliente cliente){
        campoNomeCliente.setText(cliente.getNome());
        campoCPFCliente.setText(cliente.getCPF());
    }

}
