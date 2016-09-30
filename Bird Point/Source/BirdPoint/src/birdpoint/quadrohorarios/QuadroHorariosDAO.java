/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.quadrohorarios;

import birdpoint.titulacao.*;
import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

public class QuadroHorariosDAO extends GenericDAO<QuadroHorarios> {

    public QuadroHorariosDAO() {
        super(QuadroHorarios.class);
    }

    public void salvar(QuadroHorarios quadroHorarios) {
        Object[] options = {"Sim", "Não"};
        if (quadroHorarios.getIdQuadroHorarios() == 0) {
            if (adicionar(quadroHorarios)) {
                JOptionPane.showMessageDialog(null, "Quadro de Horários cadastrado com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(quadroHorarios)) {
                JOptionPane.showMessageDialog(null, "Quadro de Horários editado com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }
}
