/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.Pesquisar;

import java.sql.Date;

/**
 *
 * @author Adriano Local
 */
public class PesquisarProntuarioStatico {

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        PesquisarProntuarioStatico.nome = nome;
    }

    public static String getTelefone() {
        return telefone;
    }

    public static void setTelefone(String telefone) {
        PesquisarProntuarioStatico.telefone = telefone;
    }

    public static String getProntuario() {
        return prontuario;
    }

    public static void setProntuario(String prontuario) {
        PesquisarProntuarioStatico.prontuario = prontuario;
    }

    public static String getPaciente() {
        return paciente;
    }

    public static void setPaciente(String paciente) {
        PesquisarProntuarioStatico.paciente = paciente;
    }

    public static String getStatus() {
        if (status == 0) {
            return "Disponível";
        } else {
            if (status == 1) {
                return "Emprestado";
            }else
                if(status==2){
                    return "Reservado";
                }
            else {
                return "Indeterminado";
            }
        }
    }

    public static String getTelefoneFixo() {
        return telefoneFixo;
    }

    public static void setTelefoneFixo(String telefoneFixo) {
        PesquisarProntuarioStatico.telefoneFixo = telefoneFixo;
    }

    public static void setStatus(int status) {
        PesquisarProntuarioStatico.status = status;
    }
     

    public static String getInformacoes() {
        return informacoes;
    }

    public int getIdUsuarioReservado() {
        return idUsuarioReservado;
    }

    public int getCodigoProntuario() {
        return codigoProntuario;
    }

    public Date getDataDevolução() {
        return dataDevolução;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public int getPront_AlunoEmprestado() {
        return pront_AlunoEmprestado;
    }

    public int getResponsavelProntuario() {
        return responsavelProntuario;
    }
    

    public static void setInformacoes(String informacoes) {
        PesquisarProntuarioStatico.informacoes = informacoes;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolução(Date dataDevolução) {
        this.dataDevolução = dataDevolução;
    }

    public void setCodigoProntuario(int codigoProntuario) {
        this.codigoProntuario = codigoProntuario;
    }

    public void setIdUsuarioReservado(int idUsuarioReservado) {
        this.idUsuarioReservado = idUsuarioReservado;
    }

    public void setPront_AlunoEmprestado(int pront_AlunoEmprestado) {
        this.pront_AlunoEmprestado = pront_AlunoEmprestado;
    }

    public void setResponsavelProntuario(int responsavelProntuario) {
        this.responsavelProntuario = responsavelProntuario;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getTelefoneProfessor() {
        return TelefoneProfessor;
    }

    public void setTelefoneProfessor(String TelefoneProfessor) {
        this.TelefoneProfessor = TelefoneProfessor;
    }

    public String getCelularProfessor() {
        return celularProfessor;
    }

    public void setCelularProfessor(String celularProfessor) {
        this.celularProfessor = celularProfessor;
    }
    
    
    private static String nome;
    private static String telefone;
    private static String telefoneFixo;
    private static String prontuario;
    private static String paciente;
    private static String informacoes;
    private Date     dataEmprestimo;
    private Date     dataDevolução;
    private int           codigoProntuario;
    private static int    status;
    private int          idUsuarioReservado;
    private int          pront_AlunoEmprestado;
    private int          responsavelProntuario;
    private String      nomeProfessor;
    private String      TelefoneProfessor;
    private String      celularProfessor;

}
