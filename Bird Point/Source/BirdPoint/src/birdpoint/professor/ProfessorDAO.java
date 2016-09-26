/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.professor;

import birdpoint.cidade.Cidade;
import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class ProfessorDAO extends GenericDAO<Professor> {

    public ProfessorDAO() {
        super(Professor.class);
    }

    public void salvar(Professor professor) {
        Object[] options = {"Sim", "Não"};
        if (professor.getIdProfessor()== 0 && adicionar(professor)) {
            JOptionPane.showMessageDialog(null, "Professor(a) cadastrado(a) com sucesso!");
        } else {
            if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION && 
                    atualizar(professor)) {
                JOptionPane.showMessageDialog(null, "Professor(a) editado(a) com sucesso!!");
            } else {
                JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
            }

        }
    }

}
