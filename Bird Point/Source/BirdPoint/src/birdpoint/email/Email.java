/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.email;

import birdpoint.registro.ponto.Ponto;
import birdpoint.registro.ponto.PontoDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    List<Ponto> listaPonto = new ArrayList<>();
    PontoDAO pontoDAO = new PontoDAO();

    List<Ponto> listaFiltrada = new ArrayList<>();
    List<Ponto> listaFiltradaParcial = new ArrayList<>();

    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    Date dataHoraSistema;

    public void enviarEmail() {
        dataHoraSistema = new Date();
        listaPonto = new ArrayList<>();
        listaFiltrada = new ArrayList<>();
        listaPonto = pontoDAO.checkExists("dataPonto", formatarData.format(dataHoraSistema));
        for (Ponto ponto : listaPonto) {
            if ((ponto.getHoraEntradaPonto() == null || ponto.getHoraSaidaPonto() == null) && (ponto.getProfessor().isReceberEmail())) {
                listaFiltrada.add(ponto);
            }
        }
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("recursoshumanos@fvs.edu.br", "rh123456");
            }
        });
        /**
         * Ativa Debug para sessão
         */
        session.setDebug(false);
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("recursoshumanos@fvs.edu.br")); //Remetente

            for (Ponto ponto : listaFiltrada) {
                ponto.setEmailPonto(true);
                try {
                    if (ponto.getProfessor().getEmailProfessor() != null && !ponto.getProfessor().getEmailProfessor().equals("")) {
                        Address[] toUser = InternetAddress //Destinatário(s)
                                .parse(ponto.getProfessor().getEmailProfessor());
                        message.setRecipients(Message.RecipientType.TO, toUser);
                        message.setSubject("Ponto Eletrônico - Faculdade Vale do Salgado");//Assunto
                        message.setText("Olá Prof.(a) " + ponto.getProfessor().getNomeProfessor() + " !\n\n"
                                + "O sistema eletrônico Bird Point não registrou seu ponto nas aulas do dia: \n\nData da Ocorrência: "
                                + ponto.getDataPonto() + " (" + ponto.getDiaDaSemana() + ")"
                                + "\nTurno.: " + ponto.getTurnoPonto() + "\nHorário de Entrada.: " + ponto.getHoraEntradaPonto()
                                + "\nHorário de Saída.: " + ponto.getHoraSaidaPonto()
                                + "\nAno Exercício.: " + ponto.getAnoExercicio().getNomeAnoExercicio()
                                + "\n\nPor gentileza, dirija-se ao setor de Recursos Humanos para verificar esta pendência;"
                                + "\n\nEste é um e-mail automático, por favor, não responda!"
                                + "\n\nFábrica de Software Digy Arretados © 2016 - Todos os direitos reservados.");
                        Transport.send(message);
                    }
                } catch (Exception e) {
                }
              pontoDAO.atualizar(ponto);
            }
            System.out.println("Mensagens Enviadas com Sucesso!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
