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
public class responsavelprontuario extends Prontuario{

    private static int idResponsavel;
    private static String nomeresponsavel;
    private static String telefoneCelular;
    private static String telefoneFixo;
    private static String nomeProfessorResponsavel;
    private static String telefoneCelularProfessor;
    private static String telefoneFixoProfessor;

    public static int getIdResponsavel() {
        return idResponsavel;
    }

    public static void setIdResponsavel(int idResponsavel) {
        responsavelprontuario.idResponsavel = idResponsavel;
    }

    public static String getNomeProfessorResponsavel() {
        return nomeProfessorResponsavel;
    }

    public static String getTelefoneCelularProfessor() {
        return telefoneCelularProfessor;
    }

    public static String getTelefoneFixoProfessor() {
        return telefoneFixoProfessor;
    }

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

    public static void setNomeProfessorResponsavel(String nomeProfessorResponsavel) {
        responsavelprontuario.nomeProfessorResponsavel = nomeProfessorResponsavel;
    }

    public static void setTelefoneCelularProfessor(String telefoneCelularProfessor) {
        responsavelprontuario.telefoneCelularProfessor = telefoneCelularProfessor;
    }

    public static void setTelefoneFixoProfessor(String telefoneFixoProfessor) {
        responsavelprontuario.telefoneFixoProfessor = telefoneFixoProfessor;
    }

}
