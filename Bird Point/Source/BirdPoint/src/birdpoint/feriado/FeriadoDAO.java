/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.feriado;

import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano Lima
 */
public class FeriadoDAO extends GenericDAO<Feriado> {

    public FeriadoDAO() {
        super(Feriado.class);
    }

    public void salvar(Feriado feriado) {
        Object[] options = {"Sim", "Não"};
        if (feriado.getIdFeriado()== 0) {
            if (adicionar(feriado)) {
                JOptionPane.showMessageDialog(null, "Feriado cadastrado com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(feriado)) {
                JOptionPane.showMessageDialog(null, "Feriado editado com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }

}
