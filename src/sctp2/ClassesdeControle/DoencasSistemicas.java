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
public class DoencasSistemicas {
    private static boolean febreReumatica;
    private static boolean problemasRenais;
    private static boolean problemasRespiratorios;
    private static boolean reumatismo;
    private static boolean diabetes;
    private static boolean hipertensaoArterial;
    private static boolean problemasCardiacos;
    private static boolean problemasGastricos;
    private static boolean problemasAlergicos;

    public int getFebreReumatica() {
        if(febreReumatica==true)return 1;
        else
            
        return 0;
    }

    public int getHipertensaoArterial() {
        if(hipertensaoArterial==true)return 1;
        else
        return 0;
    }

    public int getDiabetes() {
        if(diabetes==true)return 1;
        else
        return 0;
    }

    public int getProblemasAlergicos() {
        if(problemasAlergicos==true)return 1;
        else
        return 0;
    }

    public int getProblemasCardiacos() {
        if(problemasCardiacos==true)return 1;
        else
        return 0;
    }

    public int getProblemasGastricos() {
        if(problemasGastricos==true)return 1;
        else
        return 0;
    }

    public int getProblemasRenais() {
        if(problemasRenais==true)return 1;
        else
        return 0;
    }

    public int getProblemasRespiratorios() {
        if(problemasRespiratorios==true)return 1;
        else
        return 0;
    }

    public int getReumatismo() {
        if(reumatismo==true)return 1;
        else
        return 0;
        
    }

    public void setDiabetes( boolean diabetes) {
        this.diabetes = diabetes;
        System.out.println(this.diabetes);
    }

    public void setFebreReumatica( boolean febreReumatica) {
        this.febreReumatica = febreReumatica;
    }

    public void setHipertensaoArterial(boolean hipertensaoArterial) {
        this.hipertensaoArterial = hipertensaoArterial;
        System.out.println("hipert "+hipertensaoArterial);
    }

    public void setProblemasAlergicos( boolean problemasAlergicos) {
        this.problemasAlergicos = problemasAlergicos;
    }

    public void setProblemasCardiacos( boolean problemasCardiacos) {
        this.problemasCardiacos = problemasCardiacos;
    }

    public void setProblemasGastricos( boolean problemasGastricos) {
        this.problemasGastricos = problemasGastricos;
    }

    public void setProblemasRenais( boolean problemasRenais) {
        this.problemasRenais = problemasRenais;
    }

    public void setProblemasRespiratorios( boolean problemasRespiratorios) {
        this.problemasRespiratorios = problemasRespiratorios;
    }

    public void setReumatismo( boolean reumatismo) {
        this.reumatismo = reumatismo;
    }
    
    
    
            
    
}
