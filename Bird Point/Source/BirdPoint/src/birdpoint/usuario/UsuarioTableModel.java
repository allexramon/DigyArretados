/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.usuario;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class UsuarioTableModel extends AbstractTableModel {

    private List<Usuario> usuarios = new ArrayList<>();
    private String[] colunas = {"Nome", "Login", "Senha", "Tipo Acesso", "ID"};

    public UsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return usuario.getNomeUsuario();
            case 1:
                return usuario.getLoginUsuario();
            case 2:
                return usuario.getSenhaUsuario();
            case 3:
                return usuario.getTipoDeAcessoUsuario();
            case 4:
                return usuario.getIdUsuario();

        }
        return null;
    }

    public String getColumnName(int index) {
        switch (index) {
            case 0:
                return colunas[0];
            case 1:
                return colunas[1];
            case 2:
                return colunas[2];
            case 3:
                return colunas[3];
            case 4:
                return colunas[4];

        }
        return null;
    }

}
