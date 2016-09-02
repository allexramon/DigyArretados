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
public class ProfessorTableModel extends AbstractTableModel{
    private List<Professor> professores = new ArrayList<>();
    private String[] colunas = {"Nome Professor", "Rua","Bairro","Cidade","Telefone","Email","Titulação","Situação","ID"};

    public ProfessorTableModel(List<Professor> professor) {
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
                return professor.getRuaProfessor();
            case 2:
                return professor.getBairroProfessor();
            case 3:
                return professor.getCidadeProfessor();
            case 4:
                return professor.getTelefoneProfessor();
            case 5:
                return professor.getEmailProfessor();
            case 6:
                return professor.getTitulacaoProfessor();
            case 7:
                return professor.isSituacaoProfessor();
            case 8:
                return professor.getIdProfessor();
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
            case 7:
                return colunas[7];
            case 8:
                return colunas[8];


        }
        return null;
    }

  
}
