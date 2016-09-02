/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.cidade;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Adriano Lima
 */
public class CidadeTableModel extends AbstractTableModel{

    private List<Cidade> cidade = new ArrayList<>();
    private String[] colunas = {"Nome", "CEP", "Estado"};

    public CidadeTableModel(List<Cidade> cidade) {
        this.cidade = cidade;
    }

    @Override
    public int getRowCount() {
        return cidade.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cidade cidade = cidade.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return cidade.getNomeCidade();
            case 1:
                return cidade.getCepCidade();
            case 2:
                return cidade.getEstadoCidade();

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
        }
        return null;
    }

}
 

