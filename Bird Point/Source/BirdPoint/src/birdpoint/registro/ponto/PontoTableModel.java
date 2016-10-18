/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.registro.ponto;

import birdpoint.horario.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PontoTableModel extends AbstractTableModel {

    private List<Ponto> pontos = new ArrayList<>();
    private String[] colunas = {"Professor", "Hora", "Tipo de Registro"};
    SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm:ss");

    public PontoTableModel(List<Ponto> ponto) {
        this.pontos = ponto;
    }

    @Override
    public int getRowCount() {
        return pontos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ponto ponto = pontos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ponto.getProfessor().getNomeProfessor();
            case 1:
                return formatarHora.format(ponto.getDataPontoCompleta());
            case 2:
                return ponto.getTipoBatida();

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
