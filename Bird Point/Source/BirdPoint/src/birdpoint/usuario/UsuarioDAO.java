/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.usuario;

import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public void salvar(Usuario usuario) {
        if (usuario.getIdUsuario() == 0) {
            adicionar(usuario);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        } else {
            atualizar(usuario);
            JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
        }
    }

}
