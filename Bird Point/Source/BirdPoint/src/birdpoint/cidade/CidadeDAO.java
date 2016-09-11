/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.cidade;

import birdpoint.titulacao.Titulacao;
import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano Lima
 */
public class CidadeDAO extends GenericDAO<Cidade> {

    public CidadeDAO() {
        super(Cidade.class);
    }

    public void salvar(Cidade cidade) {
        if (cidade.getIdCidade() == 0) {
            adicionar(cidade);
            JOptionPane.showMessageDialog(null, "Cidade cadastrada com sucesso!");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Deseja mesmo realizar essa edição? "
                    + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                atualizar(cidade);
                JOptionPane.showMessageDialog(null, "Cidade editada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
            }

        }
    }

}
