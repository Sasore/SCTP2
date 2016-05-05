/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.Pesquisar;

/**
 *
 * @author Adriano Local
 */
public class PesquisarProntuario {

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        PesquisarProntuario.nome = nome;
    }

    public static String getTelefone() {
        return telefone;
    }

    public static void setTelefone(String telefone) {
        PesquisarProntuario.telefone = telefone;
    }

    public static String getProntuario() {
        return prontuario;
    }

    public static void setProntuario(String prontuario) {
        PesquisarProntuario.prontuario = prontuario;
    }

    public static String getPaciente() {
        return paciente;
    }

    public static void setPaciente(String paciente) {
        PesquisarProntuario.paciente = paciente;
    }

    public static String getStatus() {
        if (status == 0) {
            return "Disponível";
        } else {
            if (status == 1) {
                return "Emprestado";
            } else {
                return "Indeterminado";
            }
        }
    }

    public static String getTelefoneFixo() {
        return telefoneFixo;
    }

    public static void setTelefoneFixo(String telefoneFixo) {
        PesquisarProntuario.telefoneFixo = telefoneFixo;
    }
    public static void setStatus(int status) {
        PesquisarProntuario.status = status;
    }

    public static String getInformacoes() {
        return informacoes;
    }

    public static void setInformacoes(String informacoes) {
        PesquisarProntuario.informacoes = informacoes;
    }
    
    private static String nome;
    private static String telefone;
    private static String telefoneFixo;
    private static String prontuario;
    private static String paciente;
    private static String informacoes;
    private static int status;

}
