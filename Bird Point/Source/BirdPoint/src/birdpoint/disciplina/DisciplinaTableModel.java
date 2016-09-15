/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.disciplina;

import birdpoint.curso.Curso;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class DisciplinaTableModel extends AbstractTableModel {

    private List<Disciplina> disciplinas = new ArrayList<>();
    private String[] colunas = {"Código", "Nome da Disciplina", "Semestre", "C.H", "Curso"};

    public DisciplinaTableModel(List<Disciplina> disciplina) {
        this.disciplinas = disciplina;
    }

    @Override
    public int getRowCount() {
        return disciplinas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Disciplina disciplina = disciplinas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return disciplina.getIdDisciplina();
            case 1:
                return disciplina.getNomeDisciplina();
            case 2:
                return disciplina.getSemestre().getNomeSemestre();
            case 3:
                return disciplina.getCargaHoraria();
            case 4:
                return disciplina.getSemestre().getCurso().getNomeCurso();
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
