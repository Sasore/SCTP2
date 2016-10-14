/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.Pesquisar;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Adriano Local
 */
public class PesquisarProntuario {
    private int   idResponsavel;
    private  String nome;
    private  String telefone;
    private  String telefoneFixo;
    private  String prontuario;
    private  String paciente;
    private  String informacoes;
    private Date     dataEmprestimo;
    private Date     dataDevolução;
    private int           codigoProntuario;
    private  int    status;
    private int          idUsuarioReservado;
    private int          pront_AlunoEmprestado;
    private int          responsavelProntuario;
    private String      nomeProfessor;
    private String      TelefoneProfessor;
    private String      celularProfessor;

    public  String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   

    
    public  String getProntuario() {
        return prontuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public  String getPaciente() {
        return paciente;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }


    public  String getStatus() {
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

    public  String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
     

    public  String getInformacoes() {
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

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
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
    
    
    

}
