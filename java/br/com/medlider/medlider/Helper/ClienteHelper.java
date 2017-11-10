package br.com.medlider.medlider.Helper;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import br.com.medlider.medlider.R;
import br.com.medlider.medlider.control.CadastroClienteActivity;
import br.com.medlider.medlider.model.Cliente;

/**
 * Created by Filipe Firmino on 31/10/2017.
 */

public class ClienteHelper {
    private final TextView campoTextoTituloCliente;
    private final EditText campoNomeCliente;
    private final EditText campoCPFCliente;
    private final EditText campoRGCliente;
    private final EditText campoTelefoneCliente;
    private final EditText campoEnderecoCliente;
    private final RadioButton campoSexoFemiCliente;
    private final RadioButton campoSexoMascCliente;
    private final EditText campoIdadeCliente;
    private Cliente cliente;


    public ClienteHelper(CadastroClienteActivity cadastroClienteActivity) {

        campoTextoTituloCliente = (TextView) cadastroClienteActivity.findViewById(R.id.tela_cadastro_cliente_texto_titulo);
        campoNomeCliente = (EditText) cadastroClienteActivity.findViewById(R.id.tela_cadastro_cliente_nome);
        campoCPFCliente = (EditText) cadastroClienteActivity.findViewById(R.id.tela_cadastro_cpf_cliente);
        campoRGCliente = (EditText) cadastroClienteActivity.findViewById(R.id.tela_cadastro_cliente_rg);
        campoTelefoneCliente = (EditText) cadastroClienteActivity.findViewById(R.id.tela_cadastro_cliente_telefone);
        campoEnderecoCliente = (EditText) cadastroClienteActivity.findViewById(R.id.tela_cadastro_cliente_endereco);
        campoSexoFemiCliente = (RadioButton) cadastroClienteActivity.findViewById(R.id.tela_cadastro_cliente_feminino);
        campoSexoMascCliente = (RadioButton) cadastroClienteActivity.findViewById(R.id.tela_cadastro_cliente_masculino);
        campoIdadeCliente = (EditText) cadastroClienteActivity.findViewById(R.id.tela_cadastro_cliente_idade);
        cliente = new Cliente();
    }

    public Cliente PegaCliente(){
        cliente.setNome(campoNomeCliente.getText().toString());
        cliente.setCPF(campoCPFCliente.getText().toString());
        cliente.setRG(campoRGCliente.getText().toString());
        cliente.setTelefone(campoTelefoneCliente.getText().toString());
        cliente.setEndereco(campoEnderecoCliente.getText().toString());
        if(campoSexoFemiCliente.isChecked()){
            cliente.setSexo(campoSexoFemiCliente.getText().toString());
        }else{
            cliente.setSexo(campoSexoMascCliente.getText().toString());
        }
        cliente.setIdade(campoIdadeCliente.getText().toString());
        return cliente;
    }

    public void PreencheCadastroCliente(Cliente cliente) {
        campoTextoTituloCliente.setText("Editar cliente");
        campoNomeCliente.setText(cliente.getNome());
        campoCPFCliente.setText(cliente.getCPF());
        campoRGCliente.setText(cliente.getRG());
        campoTelefoneCliente.setText(cliente.getTelefone());
        campoEnderecoCliente.setText(cliente.getEndereco());
        campoIdadeCliente.setText(cliente.getIdade());
        String pegaSexo = cliente.getSexo();
        if(pegaSexo.equals("Masculino")){
            campoSexoMascCliente.setChecked(true);
        }else{
            campoSexoFemiCliente.setChecked(true);
        }
        this.cliente = cliente;
    }
}