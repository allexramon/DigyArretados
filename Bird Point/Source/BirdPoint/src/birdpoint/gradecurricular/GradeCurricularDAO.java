/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.gradecurricular;

import birdpoint.semestre.*;
import birdpoint.curso.Curso;
import birdpoint.professor.Professor;
import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class GradeCurricularDAO extends GenericDAO<GradeCurricular>{

    public GradeCurricularDAO() {
        super(GradeCurricular.class);
    }
    
     public void salvar(GradeCurricular gradeCurricular) {
        Object[] options = {"Sim", "Não"};
        if (gradeCurricular.getIdGradeCurricular()== 0) {
            adicionar(gradeCurricular);
            JOptionPane.showMessageDialog(null, "Grade Curricular cadastrada com sucesso!");
        } else {
            if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
                atualizar(gradeCurricular);
                JOptionPane.showMessageDialog(null, "Grade Curricular editada com sucesso!!");
            } else {
                JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
            }

        }
    }
    
}
