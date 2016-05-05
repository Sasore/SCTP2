/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.Pesquisar.Paciente;

import java.sql.Date;

/**
 *
 * @author Adriano Local
 */
public class HistoricoPaciente {
    Date iniciotratamento;
    Date fimtratamento;
    String queixa;
    int codigotratamento;
    String responsaveltratamento;
    String nomepaciente;
    String RgPaciente;
    int codigopaciente;

    

    public Date getFimtratamento() {
        return fimtratamento;
    }

    public void setFimtratamento(Date fimtratamento) {
        System.out.println("fim do tratamento "+fimtratamento);
        this.fimtratamento = fimtratamento;
    }

    public String getQueixa() {
        return queixa;
    }

    public void setQueixa(String queixa) {
        this.queixa = queixa;
    }

    public int getCodigotratamento() {
        return codigotratamento;
    }

    public void setCodigotratamento(int codigotratamento) {
        this.codigotratamento = codigotratamento;
    }

    public String getResponsaveltratamento() {
        return responsaveltratamento;
    }

    public void setResponsaveltratamento(String responsaveltratamento) {
        this.responsaveltratamento = responsaveltratamento;
    }

    public String getNomepaciente() {
        return nomepaciente;
    }

    public void setNomepaciente(String nomepaciente) {
        this.nomepaciente = nomepaciente;
    }

    public int getCodigopaciente() {
        return codigopaciente;
    }

    public void setCodigopaciente(int codigopaciente) {
        this.codigopaciente = codigopaciente;
    }

    public Date getIniciotratamento() {
        return iniciotratamento;
    }

    public void setIniciotratamento(Date iniciotratamento) {
        this.iniciotratamento = iniciotratamento;
    }

    public String getRgPaciente() {
        return RgPaciente;
    }

    public void setRgPaciente(String RgPaciente) {
        this.RgPaciente = RgPaciente;
    }

    

    
}
