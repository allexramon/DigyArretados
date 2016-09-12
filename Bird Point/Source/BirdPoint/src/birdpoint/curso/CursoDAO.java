/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.curso;

import birdpoint.cidade.Cidade;
import birdpoint.professor.Professor;
import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class CursoDAO extends GenericDAO<Curso>{

    public CursoDAO() {
        super(Curso.class);
    }
    
  public void salvar(Curso curso) {
        if (curso.getIdCurso() == 0) {
            adicionar(curso);
            JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso!");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo realizar essa edição? "
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                atualizar(curso);
                JOptionPane.showMessageDialog(null, "Curso editado com sucesso!!");
            } else {
                JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
            }

        }
    }
    
}
