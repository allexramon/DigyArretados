/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.semestre;

import birdpoint.professor.Professor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class SemestreTableModel extends AbstractTableModel{
      private List<Semestre> semestres = new ArrayList<>();
    private String[] colunas = {"nome semestre", "curso","id semestre","nome semestre"};

    public SemestreTableModel(List<Semestre> semestre) {
        this.semestres = semestre;
    }
     @Override
    public int getRowCount() {
        return semestres.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)  {
        Semestre  semestre = semestres.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return semestre.getNomeSemestre();
            
              
            case 1:
                return semestre.getCurso();
            case 2:
                return semestre.getIdSemestre();
            case 3:
                return semestre.getNomeSemestre();
            
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
