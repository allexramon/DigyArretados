/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.despesas;

import birdpoint.semestre.*;
import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Nóbrega
 */
public class DespesasDAO extends GenericDAO<Despesas> {

    public DespesasDAO() {
        super(Despesas.class);
    }

    public void salvar(Despesas despesa) {
        Object[] options = {"Sim", "Não"};
        if (despesa.getIdDespesa()== 0) {
            if (adicionar(despesa)) {
                JOptionPane.showMessageDialog(null, "Despesa cadastrada com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(despesa)) {
                JOptionPane.showMessageDialog(null, "Despesa editada com sucesso!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }

}
