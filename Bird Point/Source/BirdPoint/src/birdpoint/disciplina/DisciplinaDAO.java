/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.disciplina;

import birdpoint.curso.Curso;
import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class DisciplinaDAO extends GenericDAO<Disciplina>{

    public DisciplinaDAO() {
        super(Disciplina.class);
    }
    
    public void salvar(Disciplina disciplina) {
        if (disciplina.getIdDisciplina()== 0) {
            adicionar(disciplina);
            JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso!");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo realizar essa edição"
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                atualizar(disciplina);
                JOptionPane.showMessageDialog(null, "Disciplina editada com sucesso!!");
            } else {
                JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
            }

        }
    }
    
}
