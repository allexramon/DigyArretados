/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.feriado;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class FeriadoTableModel extends AbstractTableModel {

    private List<Feriado> feriados = new ArrayList<>();
    private String[] colunas = {"Código", "Data Feriado", "Nome Feriado"};

    public FeriadoTableModel(List<Feriado> feriado) {
        this.feriados = feriado;
    }

    @Override
    public int getRowCount() {
        return feriados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Feriado feriado = feriados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return feriado.getIdFeriado();
            case 1:
                return feriado.getDataFeriado();
            case 2:
                return feriado.getNomeFeriado();

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
