/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.semestre;

import birdpoint.curso.Curso;
import birdpoint.professor.Professor;
import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class SemestreDAO extends GenericDAO<Semestre> {

    public SemestreDAO() {
        super(Semestre.class);
    }

    public void salvar(Semestre semestre) {
        Object[] options = {"Sim", "Não"};
        if (semestre.getIdSemestre() == 0) {
            if (adicionar(semestre)) {
                JOptionPane.showMessageDialog(null, "Semestre cadastrado com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(semestre)) {
                JOptionPane.showMessageDialog(null, "Semestre editado com sucesso!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }

}
