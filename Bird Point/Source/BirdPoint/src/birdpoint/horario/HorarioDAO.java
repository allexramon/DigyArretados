/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.horario;

import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class HorarioDAO extends GenericDAO<Horario> {

    public HorarioDAO() {
        super(Horario.class);
    }

    public void salvar(Horario horario) {
        Object[] options = {"Sim", "Não"};
        if (horario.getIdHorario() == 0) {
            if (adicionar(horario)) {
                JOptionPane.showMessageDialog(null, "Horário cadastrado com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(horario)) {
                JOptionPane.showMessageDialog(null, "Horário editado com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }
}
