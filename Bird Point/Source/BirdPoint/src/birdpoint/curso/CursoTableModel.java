/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.curso;

import birdpoint.cidade.Cidade;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class CursoTableModel extends AbstractTableModel{
    
    private List<Curso> cursos = new ArrayList<>();
    private String[] colunas = {"Código", "Nome do Curso"};

    public CursoTableModel(List<Curso> curso) {
        this.cursos = curso;
    }

    @Override
    public int getRowCount() {
        return cursos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Curso curso = cursos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return curso.getIdCurso();
            case 1:
                return curso.getNomeCurso();
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
