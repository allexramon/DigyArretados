/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.despesas;

import birdpoint.semestre.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class DespesasTableModel extends AbstractTableModel {

    private List<Despesas> despesas = new ArrayList<>();
    private String[] colunas = {"Código", "Curso", "Mês", "Ano", "Total", "Resultado", "Situação"};

    public DespesasTableModel(List<Despesas> despesa) {
        this.despesas = despesa;
    }

    @Override
    public int getRowCount() {
        return despesas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Despesas despesa = despesas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return despesa.getIdDespesa();
            case 1:
                return despesa.getCurso().getNomeCurso();
            case 2:
                return despesa.getMesDispesa();
            case 3:
                return despesa.getAnoDispesa();
            case 4:
                return despesa.getTotalDespesa();
            case 5:
                return despesa.getResultadoDespesa();
            case 6:
                if (despesa.getResultadoDespesa() < 0) {
                    return "Prejuízo";
                } else {
                    return "Lucro";
                }

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
            case 5:
                return colunas[5];
            case 6:
                return colunas[6];

        }
        return null;
    }

}
