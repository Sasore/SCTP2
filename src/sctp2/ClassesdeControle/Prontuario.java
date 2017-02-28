/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.ClassesdeControle;

import java.sql.Date;

/**
 *
 * @author Adriano
 */
public class Prontuario {
    protected static  String NumeroProntuario;
    protected static  String NomeresponsavelProntuario;
    protected static  int StatusProntuario;
    protected static  String InformacoesProntuario;
    protected static  String TelefoneResponsavelProntuario;
    protected static  int IdResponsavelProntuario;
    protected static String rgPaciente;
    protected static  Date  dataEmprestimo;
    protected static  Date dataDevolucao;
    protected static String localizacaoProntuario;

    public static String getLocalizacaoProntuario() {
        return localizacaoProntuario;
    }

    public static void setLocalizacaoProntuario(String localizacaoProntuario) {
        Prontuario.localizacaoProntuario = localizacaoProntuario;
    }
    
    

    public static void setIdResponsavelProntuario(String IdResponsavelProntuario) {
        int valor= Integer.parseInt(IdResponsavelProntuario);
        Prontuario.IdResponsavelProntuario = valor;
    }

    public static String getInformacoesProntuario() {
        return InformacoesProntuario;
    }

    public static String getNumeroProntuario() {
        return NumeroProntuario;
    }

    public int getStatusProntuario() {
        return StatusProntuario;
    }

    public String getTelefoneResponsavelProntuario() {
        return TelefoneResponsavelProntuario;
    }

    public String getNomeresponsavelProntuario() {
        return NomeresponsavelProntuario;
    }

    public void setInformacoesProntuario(String InformacoesProntuario) {
        this.InformacoesProntuario = InformacoesProntuario;
    }

    public void setNomeresponsavelProntuario(String NomeresponsavelProntuario) {
        this.NomeresponsavelProntuario = NomeresponsavelProntuario;
    }

    public void setNumeroProntuario(String NumeroProntuario) {
        this.NumeroProntuario = NumeroProntuario;
    }

    public void setStatusProntuario(String StatusProntuario) {
        System.out.println("Prontuario status Setprontuario: "+StatusProntuario);
        if(StatusProntuario.equals("Disponível"))this.StatusProntuario = 0;
        else
        if(StatusProntuario.equals("Emprestado"))this.StatusProntuario = 1;
        else
            if(StatusProntuario.equals("Reservado"))this.StatusProntuario=2;
        
        
    }

    public static Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public static void setDataEmprestimo(Date dataEmprestimo) {
        Prontuario.dataEmprestimo = dataEmprestimo;
    }

    public static Date getDataDevolucao() {
        return dataDevolucao;
    }

    public static void setDataDevolucao(Date dataDevolucao) {
        Prontuario.dataDevolucao = dataDevolucao;
    }
    

    public void setTelefoneResponsavelProntuario(String TelefoneResponsavelProntuario) {
        this.TelefoneResponsavelProntuario = TelefoneResponsavelProntuario;
    }

    public static int getIdResponsavelProntuario() {
        return IdResponsavelProntuario;
    }

    public static String getRgresponsavel() {
        return rgPaciente;
    }

    public static void setRgresponsavel(String rgPaciente) {
        Prontuario.rgPaciente = rgPaciente;
    }

            
    
}
