/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.professor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class ProfessorPontoTableModel extends AbstractTableModel {

    private List<Professor> professores = new ArrayList<>();
    private String[] colunas = {"Nome", "Email"};

    public ProfessorPontoTableModel(List<Professor> professor) {
        this.professores = professor;
    }

    @Override
    public int getRowCount() {
        return professores.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Professor professor = professores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return professor.getNomeProfessor();
            case 1:
                return professor.getEmailProfessor();
        }
        return null;
    }

    public String getColumnName(int index) {
        switch (index) {
            case 0:
                return colunas[0];
            case 1:
                return colunas[1];

        }
        return null;
    }

}
