/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.titulacao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class TitulacaoTableModel extends AbstractTableModel {

    private List<Titulacao> titulacaos = new ArrayList<>();
    private String[] colunas = {"Código", "Titulação", "Valor da H/A"};

    public TitulacaoTableModel(List<Titulacao> titulacao) {
        this.titulacaos = titulacao;
    }

    @Override
    public int getRowCount() {
        return titulacaos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Titulacao titulacao = titulacaos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return titulacao.getIdTitulacao();
            case 1:
                return titulacao.getNome();
            case 2:
                return titulacao.getValorTitulacao();

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
