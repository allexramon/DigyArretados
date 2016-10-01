/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.quadrohorarios;

import birdpoint.titulacao.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class QuadroHorariosTableModel extends AbstractTableModel {

    private List<QuadroHorarios> quadroHorarios = new ArrayList<>();
    private String[] colunas = {"Código", "Ano Exercício", "Semestre", "Curso", "Grade"};

    public QuadroHorariosTableModel(List<QuadroHorarios> quadroHorario) {
        this.quadroHorarios = quadroHorario;
    }

    @Override
    public int getRowCount() {
        return quadroHorarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        QuadroHorarios quadroHorario = quadroHorarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return quadroHorario.getIdQuadroHorarios();
            case 1:
                return quadroHorario.getAnoExercicio();
            case 2:
                return quadroHorario.getSemestre().getNomeSemestre();
            case 3:
                return quadroHorario.getCurso().getNomeCurso();
            case 4:
                return quadroHorario.getGradeCurricular().getNomeGradeCurricular();

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
