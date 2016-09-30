/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.usuario;

import birdpoint.util.GenericDAO;
import birdpoint.util.HibernateUtil;
import javax.swing.JOptionPane;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Nóbrega
 */
public class UsuarioDAO extends GenericDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public void salvar(Usuario usuario) {
        Object[] options = {"Sim", "Não"};
        if (usuario.getIdUsuario() == 0) {
            if (adicionar(usuario)) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(usuario)) {
                JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }

    public Usuario autenticarUsuario(String login, String senha) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transacao = sessao.beginTransaction();
        Usuario usuario = (Usuario) sessao.createCriteria(Usuario.class).add(Restrictions.eq("senhaUsuario", senha)).add(Restrictions.eq("loginUsuario", login)).uniqueResult();
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuário ou Senha Inválidos!");
        } else {
            sessao.close();
            return usuario;
        }
        return usuario;
    }

}
