/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.anoexercicio;

import birdpoint.horario.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class AnoExercicioTableModel extends AbstractTableModel {

    private List<AnoExercicio> anosExercicios = new ArrayList<>();
    private String[] colunas = {"Código", "Ano Exercício", "Ano Exercício Atual", "Gerar Horário Automático"};

    public AnoExercicioTableModel(List<AnoExercicio> anoExercicio) {
        this.anosExercicios = anoExercicio;
    }

    @Override
    public int getRowCount() {
        return anosExercicios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AnoExercicio anoExercicio = anosExercicios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return anoExercicio.getIdAnoExercicio();
            case 1:
                return anoExercicio.getNomeAnoExercicio();
            case 2:
                if (anoExercicio.isAnoExercicioAtual()) {
                    return "Sim";
                } else {
                    return "Não";
                }
            case 3:
                if (anoExercicio.isGerarHorarioAutomatico()) {
                    return "Sim";
                } else {
                    return "Não";
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

        }
        return null;
    }

}
