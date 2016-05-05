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
public class Paciente {
    private static  int    codigo;  
    private static  String nome;
    private static  String ocupacao;
    private static  String naturalidade;
    private static  String pai;
    private static  String sexo;
    private static  String procedencia;
    private static  String estadoCivil;
    private static  String conjuge;
    private static  String nascimento;
    private static  String rg;
    private static  String mae;
    private static  String rua;
    private static  String bairro;
    private static  String cidade;
    private static  String numeroEndereco;
    private static  String cep;
    private static  String telefone;
    private static  String telefoneFixo;
    private static  int    listaNegra;  
    private static String  listaNegraJustificativa;
    private static boolean pacienteComAlta;
    
    
      public static int getCodigo() {
        return codigo;
    }

    public static void setCodigo(int codigo) {
        Paciente.codigo = codigo;
    }
    public static String getNaturalidade() {
        return naturalidade;
    }
   
     public  int getListaNegra() {
        return listaNegra;
    }
    
    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getConjuge() {
        return conjuge;
    }

    public int getEstadoCivil() {
        if(estadoCivil.equals("Solteiro")) return 0;
        else
            if(estadoCivil.equals("Casado")) return 1;
        else
                if(estadoCivil.equals("Viúvo")) return 2;
        else
                if(estadoCivil.equals("Divorciado")) return 3;
        return 0;
    }

    public String getListaNegraJustificativa() {
        return listaNegraJustificativa;
    }

    public String getMae() {
        return mae;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public String getPai() {
        return pai;
    }

    public int getProcedencia() {
        if(procedencia.equals("Zona Urbana")) return 0;
        else
        if(procedencia.equals("Zona Rural")) return 1;
     else
            return 0;
    }

    public static String getRg() {
        return rg;
    }

    public String getRua() {
        return rua;
    }

    public int getSexo() {
        if(sexo.equals("Masculino"))return 0;
        else
            if(sexo.equals("Feminino"))return 1;
            return 0;
    }

    public String getTelefone() {
        return telefone;
    }
     public static String getTelefoneFixo() {
        return telefoneFixo;
    }

    public static void setTelefoneFixo(String telefoneFixo) {
        Paciente.telefoneFixo = telefoneFixo;
    }
    
    public boolean getpacienteComAlta(){
        return pacienteComAlta;
        
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setListaNegraJustificativa(String listaNegraJustificativa) {
        this.listaNegraJustificativa = listaNegraJustificativa;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public void setPacienteComAlta(boolean pacienteComAlta) {
        this.pacienteComAlta = pacienteComAlta;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
     
    public static void setNaturalidade(String naturalidade) {
        Paciente.naturalidade = naturalidade;
    }
    
    
     public static void setListaNegra(int listaNegra) {
        Paciente.listaNegra = listaNegra;
    }
    
    
            
    
}
