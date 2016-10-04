package birdpoint.anoexercicio;

import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;


public class AnoExercicioDAO extends GenericDAO<AnoExercicio> {

    public AnoExercicioDAO() {
        super(AnoExercicio.class);
    }

    public void salvar(AnoExercicio anoExercicio) {
        Object[] options = {"Sim", "Não"};
        if (anoExercicio.getIdAnoExercicio()== 0) {
            if (adicionar(anoExercicio)) {
                JOptionPane.showMessageDialog(null, "Ano Exercício cadastrado com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(anoExercicio)) {
                JOptionPane.showMessageDialog(null, "Ano Exercício editado com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }
}
