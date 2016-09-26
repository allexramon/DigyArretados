/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.gradecurricular;

import birdpoint.semestre.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class GradeCurricularTableModel extends AbstractTableModel {

    private List<GradeCurricular> grades = new ArrayList<>();
    private String[] colunas = {"Código", "Nome Grade", "Curso"};

    public GradeCurricularTableModel(List<GradeCurricular> grade) {
        this.grades = grade;
    }

    @Override
    public int getRowCount() {
        return grades.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        GradeCurricular grade = grades.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return grade.getIdGradeCurricular();
            case 1:
                return grade.getNomeGradeCurricular();
            case 2:
                return grade.getCurso().getNomeCurso();

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
