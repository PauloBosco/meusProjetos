package br.com.lanhouse.model.pessoas;

import br.com.lanhouse.model.e.ETipoCliente;

public class Admin extends Usuario {



    public Admin() {
    }

    public Admin(int idUsuario, String nome, String login, String senha, ETipoCliente tipoCliente) {
        super(idUsuario, nome, login, senha, tipoCliente);
    }

    public void addHora(Cliente c, int tempo){
        c.getUsoTotal().setQtidadeMinutosTempo(tempo);
    }
}
