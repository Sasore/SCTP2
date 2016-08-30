/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.ClassesdeControle;

/**
 *
 * @author Adriano
 */
public class Prontuario {
    private static  String NumeroProntuario;
    private static  String NomeresponsavelProntuario;
    private static  int StatusProntuario;
    private static  String InformacoesProntuario;
    private static  String TelefoneResponsavelProntuario;
    private static  int IdResponsavelProntuario;
    private static String rgresponsavel;

    public static void setIdResponsavelProntuario(String IdResponsavelProntuario) {
        int valor= Integer.parseInt(IdResponsavelProntuario);
        Prontuario.IdResponsavelProntuario = valor;
    }

    public String getInformacoesProntuario() {
        return InformacoesProntuario;
    }

    public String getNumeroProntuario() {
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
        
        
    }

    public void setTelefoneResponsavelProntuario(String TelefoneResponsavelProntuario) {
        this.TelefoneResponsavelProntuario = TelefoneResponsavelProntuario;
    }

    public static int getIdResponsavelProntuario() {
        return IdResponsavelProntuario;
    }

    public static String getRgresponsavel() {
        return rgresponsavel;
    }

    public static void setRgresponsavel(String rgresponsavel) {
        Prontuario.rgresponsavel = rgresponsavel;
    }

            
    
}
