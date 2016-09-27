/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.titulacao;

import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class TitulacaoDAO extends GenericDAO<Titulacao> {

    public TitulacaoDAO() {
        super(Titulacao.class);
    }

    public void salvar(Titulacao titulacao) {
        Object[] options = {"Sim", "Não"};
        if (titulacao.getIdTitulacao() == 0 && adicionar(titulacao)) {
            JOptionPane.showMessageDialog(null, "Titulação cadastrada com sucesso!");
        } else {
            if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION
                    &&  atualizar(titulacao)) {
                JOptionPane.showMessageDialog(null, "Titulação editada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
            }

        }
    }
}
