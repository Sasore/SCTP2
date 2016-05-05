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
public  class responsavelprontuario {
    private static int idResponsavel;

    public static int getIdResponsavel() {
        return idResponsavel;
    }

    public static void setIdResponsavel(int idResponsavel) {
        responsavelprontuario.idResponsavel = idResponsavel;
    }
    private static String nomeresponsavel;
    private static String telefoneCelular;
    private static String telefoneFixo;

    public static String getNomeresponsavel() {
        return nomeresponsavel;
    }

    public static void setNomeresponsavel(String nomeresponsavel) {
        responsavelprontuario.nomeresponsavel = nomeresponsavel;
    }

    public static String getTelefoneCelular() {
        return telefoneCelular;
    }

    public static void setTelefoneCelular(String telefoneCelular) {
        responsavelprontuario.telefoneCelular = telefoneCelular;
    }

    public static String getTelefoneFixo() {
        return telefoneFixo;
    }

    public static void setTelefoneFixo(String telefoneFixo) {
        responsavelprontuario.telefoneFixo = telefoneFixo;
    }
    
}
