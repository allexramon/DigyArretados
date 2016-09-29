/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.horario;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Adriano Lima
 */
public class HorarioTableModel extends AbstractTableModel {

    private List<Horario> horarios = new ArrayList<>();
    private String[] colunas = {"Código", "Horário Inicio", "Horário Fim", "Turno"};

    public HorarioTableModel(List<Horario> horario) {
        this.horarios = horario;
    }

    @Override
    public int getRowCount() {
        return horarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Horario horario = horarios.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return horario.getIdHorario();
            case 1:
                return horario.getHoraInicio();
            case 2:
                return horario.getHoraFim();
            case 3:
                return horario.getTurno();

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
