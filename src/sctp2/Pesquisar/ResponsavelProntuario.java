/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.Pesquisar;

import java.sql.Date;

/**
 *
 * @author soare
 */
public class ResponsavelProntuario {
     private  int idResponsavel;
    private  String nomeresponsavel;
    private  String telefoneCelular;
    private  String telefoneFixo;
    private  String nomeProfessorResponsavel;
    private  String telefoneCelularProfessor;
    private  String telefoneFixoProfessor;
     private   String NumeroProntuario;
    private   String NomeresponsavelProntuario;
    private   int StatusProntuario;
    private   String InformacoesProntuario;
    private   String TelefoneResponsavelProntuario;
    private   int IdResponsavelProntuario;
    private  String rgPaciente;
    private   Date  dataEmprestimo;
    private   Date dataDevolucao;

    public int getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(int idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    public String getNomeresponsavel() {
        return nomeresponsavel;
    }

    public void setNomeresponsavel(String nomeresponsavel) {
        this.nomeresponsavel = nomeresponsavel;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getNomeProfessorResponsavel() {
        return nomeProfessorResponsavel;
    }

    public void setNomeProfessorResponsavel(String nomeProfessorResponsavel) {
        this.nomeProfessorResponsavel = nomeProfessorResponsavel;
    }

    public String getTelefoneCelularProfessor() {
        return telefoneCelularProfessor;
    }

    public void setTelefoneCelularProfessor(String telefoneCelularProfessor) {
        this.telefoneCelularProfessor = telefoneCelularProfessor;
    }

    public String getTelefoneFixoProfessor() {
        return telefoneFixoProfessor;
    }

    public void setTelefoneFixoProfessor(String telefoneFixoProfessor) {
        this.telefoneFixoProfessor = telefoneFixoProfessor;
    }

    public String getNumeroProntuario() {
        return NumeroProntuario;
    }

    public void setNumeroProntuario(String NumeroProntuario) {
        this.NumeroProntuario = NumeroProntuario;
    }

    public String getNomeresponsavelProntuario() {
        return NomeresponsavelProntuario;
    }

    public void setNomeresponsavelProntuario(String NomeresponsavelProntuario) {
        this.NomeresponsavelProntuario = NomeresponsavelProntuario;
    }

    public String getStatusProntuario() {
    if (StatusProntuario == 0) {
            return "Disponível";
        } else {
            if (StatusProntuario == 1) {
                return "Emprestado";
            }else
                if(StatusProntuario==2){
                    return "Reservado";
                }
            else {
                return "Indeterminado";
            }
        }
    }

   public void setStatusProntuario(String StatusProntuario) {
        System.out.println("Prontuario status Setprontuario: "+StatusProntuario);
        if(StatusProntuario.equals("Disponível"))this.StatusProntuario = 0;
        else
        if(StatusProntuario.equals("Emprestado"))this.StatusProntuario = 1;
        else
            if(StatusProntuario.equals("Reservado"))this.StatusProntuario=2;
        
        
    }

    public String getInformacoesProntuario() {
        return InformacoesProntuario;
    }

    public void setInformacoesProntuario(String InformacoesProntuario) {
        this.InformacoesProntuario = InformacoesProntuario;
    }

    public String getTelefoneResponsavelProntuario() {
        return TelefoneResponsavelProntuario;
    }

    public void setTelefoneResponsavelProntuario(String TelefoneResponsavelProntuario) {
        this.TelefoneResponsavelProntuario = TelefoneResponsavelProntuario;
    }

    public int getIdResponsavelProntuario() {
        return IdResponsavelProntuario;
    }

    public void setIdResponsavelProntuario(int IdResponsavelProntuario) {
        this.IdResponsavelProntuario = IdResponsavelProntuario;
    }

    public String getRgPaciente() {
        return rgPaciente;
    }

    public void setRgPaciente(String rgPaciente) {
        this.rgPaciente = rgPaciente;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

}
