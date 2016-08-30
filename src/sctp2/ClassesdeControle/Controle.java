/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.ClassesdeControle;

import java.sql.SQLException;
import java.util.ArrayList;
import sctp2.BancodeDados.conexao;
import sctp2.Cadastros.*;

/**
 *
 * @author Adriano Esta Classe efetua as chamadas as funções de Banco de dados
 */
public class Controle {

    boolean retorno = false;//variavel usada para retorno das funções a seguir

    public boolean GravaPacienteSimples() throws ClassNotFoundException {
        CadastroSimplesdePaciente acesso = new CadastroSimplesdePaciente();
        //-----Inserção dos dados em uma lista-----------------
        ArrayList<String> PacienteSimples = new ArrayList<String>();
        PacienteSimples.add(acesso.getNomePaciente());
        PacienteSimples.add(acesso.getTelefone());
        PacienteSimples.add(acesso.getNascimento());
        PacienteSimples.add(acesso.getRg());
        PacienteSimples.add(acesso.getPrincipalQueixa());
        PacienteSimples.add(acesso.getTratamentoSolicitado());
        //-------------------------------------
        conexao acessoBD = new conexao();
        retorno = acessoBD.GravarPacienteSimples(PacienteSimples);//chamada a função da classe de banco de dados para a gravação dos dados
        return retorno;

    }///FUNÇÃO PRONTA E FUNCIONADO!

    //esta funcoao grava todos os dados do novo paciente
//-------------------------fUNÇÕES PARA GRAVAÇÃO DE CADASTRO DE PACIENTE----------------------------------------------
    //Esta função é o metodo publico para gravar dados fornecidos na interface
    public boolean ProntuarioGrava() throws ClassNotFoundException, SQLException {
        //ESTA FUNÇÃO SERVE PARA CHAMAR OS METODOS DE GRAVAÇÃO DA INTERFACE DE CADASTRO, CADA FUNÇÃO ABAIXO EFETUA A GRAVAÇÃO DE UM MODULO DE CADASTRO
        System.out.println("iniciando gravação dos dados");
        GravaPaciente();
        GravaProntuario();
        GravaAmnase();
        GravaDoencasSistemiscas();
        GravaHabitos();
        GravatratamentosNecessarios();

        return retorno;
    }
//--------------------------------------------------------------------------------------

    private boolean GravaPaciente() throws ClassNotFoundException {
        Paciente acesso = new Paciente();//instanciando a classe paciente
        conexao acessoConexao = new conexao();
        //---------------------------------------
        int[] Pacientevetor = new int[10];//este vetor amarzena os dados numericos do paciente
        ArrayList<String> PacienteList = new ArrayList<>();//Lista para armazenar os dados do paciente que sao do tipo String
        //-------------------------------------------
        Pacientevetor[3] = boolToInt(acesso.getpacienteComAlta());//converte booleano para 0 ou 1
        PacienteList.add(acesso.getRg());
        PacienteList.add(acesso.getNome());
        PacienteList.add(acesso.getNascimento());
        PacienteList.add(acesso.getNaturalidade());
        Pacientevetor[0] = (acesso.getSexo());
        PacienteList.add(acesso.getOcupacao());
        Pacientevetor[2] = acesso.getProcedencia();
        PacienteList.add(acesso.getMae());
        PacienteList.add(acesso.getPai());
        Pacientevetor[1] = acesso.getEstadoCivil();
        PacienteList.add(acesso.getConjuge());
        PacienteList.add(acesso.getCidade());
        PacienteList.add(acesso.getRua());
        PacienteList.add(acesso.getBairro());
        PacienteList.add(acesso.getNumeroEndereco());
        PacienteList.add(acesso.getCep());
        PacienteList.add(acesso.getTelefone());
        Pacientevetor[4] = acesso.getListaNegra();//lista negra (0 ou 1)
        PacienteList.add(acesso.getListaNegraJustificativa());
        retorno = acessoConexao.GravaNovoPaciente(PacienteList, Pacientevetor);

        return retorno;
    }
     public boolean AtualizaPaciente() throws ClassNotFoundException {
        Paciente acesso = new Paciente();//instanciando a classe paciente
        conexao acessoConexao = new conexao();
        //---------------------------------------
        int[] Pacientevetor = new int[10];//este vetor amarzena os dados numericos do paciente
        ArrayList<String> PacienteList = new ArrayList<>();//Lista para armazenar os dados do paciente que sao do tipo String
        //-------------------------------------------
        Pacientevetor[3] = boolToInt(acesso.getpacienteComAlta());//converte booleano para 0 ou 1
        PacienteList.add(acesso.getRg());
        PacienteList.add(acesso.getNome());
        PacienteList.add(acesso.getNascimento());
        PacienteList.add(acesso.getNaturalidade());
        Pacientevetor[0] = (acesso.getSexo());
        PacienteList.add(acesso.getOcupacao());
        Pacientevetor[2] = acesso.getProcedencia();
        PacienteList.add(acesso.getMae());
        PacienteList.add(acesso.getPai());
        Pacientevetor[1] = acesso.getEstadoCivil();
        PacienteList.add(acesso.getConjuge());
        PacienteList.add(acesso.getCidade());
        PacienteList.add(acesso.getRua());
        PacienteList.add(acesso.getBairro());
        PacienteList.add(acesso.getNumeroEndereco());
        PacienteList.add(acesso.getCep());
        PacienteList.add(acesso.getTelefone());
        Pacientevetor[4] = acesso.getListaNegra();//lista negra (0 ou 1)
        PacienteList.add(acesso.getListaNegraJustificativa());
        Pacientevetor[5]=acesso.getCodigo();//codigo do paciente
        retorno = acessoConexao.AtualizaPaciente(PacienteList, Pacientevetor);

        return retorno;
    }

    private boolean GravaProntuario() throws ClassNotFoundException {
        Prontuario acesso = new Prontuario();//instancia a classe prontuario
        Paciente acessoP = new Paciente();//instancia acesso a classe paciente
        conexao acessoCon = new conexao();//cria conexao com o banco de dados
        //======================================
        ArrayList<String> ProntuarioList = new ArrayList<>();
        int StatusProntuario,idresponsavelprontuario;
        //------------------------------------------
        System.out.println("testadno RG: "+acessoP.getRg());
        ProntuarioList.add(acessoP.getRg());
        ProntuarioList.add(acesso.getNumeroProntuario());//1
        ProntuarioList.add(acesso.getNomeresponsavelProntuario());
        ProntuarioList.add(acesso.getTelefoneResponsavelProntuario());
        ProntuarioList.add(acesso.getInformacoesProntuario());
        StatusProntuario = acesso.getStatusProntuario();
        idresponsavelprontuario=acesso.getIdResponsavelProntuario();
        acessoCon.GravaProntuario(ProntuarioList, StatusProntuario,idresponsavelprontuario);
        return retorno;
    }

    private boolean GravaAmnase() throws ClassNotFoundException {
        Anamnese acesso = new Anamnese();
        conexao conecta = new conexao();
        //-----------------------------------
        ArrayList<String> AnamneseList = new ArrayList<>();
        boolean[] AnamneseVetor = new boolean[10];
        int[] vetorAnamneseInteiro = new int[10];
        Paciente PegaRG = new Paciente();

        //--------------------------------------------------
        AnamneseList.add(sctp2.ClassesdeControle.Anamnese.getPrincipalQueixa());//0
        AnamneseList.add(acesso.getSofreAlgumaDoencaDescricao());//1
        AnamneseList.add(acesso.getEmTratamentoMedicoDescricao());//2
        AnamneseList.add(acesso.getPossuiAlergiasDescricao());//3
        AnamneseList.add(acesso.getUsoDeAlgumaMedicacaoDescricao());//4
        AnamneseList.add(acesso.getPossuiAlergiasDescricao());//5
        AnamneseList.add(acesso.getJaFoiOperadoDescricao());//6
        AnamneseList.add(acesso.getProblemasComHemorragiaDescricao());//7
        AnamneseList.add(PegaRG.getRg());//8
        AnamneseVetor[0] = acesso.isSofreAlgumaDoenca();
        AnamneseVetor[1] = acesso.isEmTratamentoMedico();
        AnamneseVetor[2] = acesso.isUsoDeAlgumaMedicacao();
        AnamneseVetor[3] = acesso.isPossuiAlergias();
        AnamneseVetor[4] = acesso.isJaFoiOperado();
        AnamneseVetor[5] = acesso.isProblemasComHemorragia();
        AnamneseVetor[6] = acesso.isProblemasComCicatrizacao();
        AnamneseVetor[7] = acesso.isGravidez();
        AnamneseVetor[8] = acesso.isProblemasComAnestesia();
        for (int i = 0; i < 10; i++) {
            vetorAnamneseInteiro[i] = boolToInt(AnamneseVetor[i]);//converte os dados booleanos para inteiro
        }
        conecta.GravaAnamnese(AnamneseList, vetorAnamneseInteiro);

        return retorno;

    }

    private boolean GravaDoencasSistemiscas() throws SQLException, ClassNotFoundException {
        DoencasSistemicas acesso = new DoencasSistemicas();
        conexao Conectar = new conexao();
        int[] DoencasSistemicasVetor = new int[9];
        boolean retorno = false;
        //------------------------------------------------
        DoencasSistemicasVetor[0] = acesso.getFebreReumatica();
        DoencasSistemicasVetor[1] = acesso.getProblemasRenais();
        DoencasSistemicasVetor[2] = acesso.getProblemasRespiratorios();
        DoencasSistemicasVetor[3] = acesso.getReumatismo();
        DoencasSistemicasVetor[4] = acesso.getDiabetes();
        DoencasSistemicasVetor[5] = acesso.getHipertensaoArterial();
        DoencasSistemicasVetor[6] = acesso.getProblemasCardiacos();
        DoencasSistemicasVetor[7] = acesso.getProblemasGastricos();
        DoencasSistemicasVetor[8] = acesso.getProblemasAlergicos();
        String RG = sctp2.ClassesdeControle.Paciente.getRg();
        retorno = Conectar.GravaDoencasSistemicas(DoencasSistemicasVetor, RG);
        if (retorno == true) {
            return true;
        } else {
            return false;
        }
    }

    private boolean GravaHabitos() throws ClassNotFoundException {
        Habitos acesso = new Habitos();
        conexao conectar = new conexao();
        int[] HabitosTabacoVetor = new int[5];
        int[] HabitosAlcoolVetor = new int[5];
        String RG = sctp2.ClassesdeControle.Paciente.getRg();
        boolean retorno;
        //----------------------------------------------        
        HabitosTabacoVetor[0] = acesso.getFuma();
        HabitosTabacoVetor[1] = acesso.getFumaHaQuantoTempo();
        HabitosTabacoVetor[2] = acesso.getFumoQuantidadePorDia();
        HabitosTabacoVetor[3] = acesso.getFumoTipodeCigarro();
        HabitosAlcoolVetor[0] = acesso.getBebidaAlcolica();
        HabitosAlcoolVetor[1] = acesso.getBebidaAlcoolicaHaQuantoTempo();
        HabitosAlcoolVetor[2] = acesso.getBebidaAlcoolicaFrequencia();
        HabitosAlcoolVetor[3] = acesso.getBebidaAlcoolicaTipodeBebida();
        for(int i=0; i<HabitosAlcoolVetor.length;i++)System.out.println("habitos[]"+HabitosAlcoolVetor[i]);
        retorno = conectar.GravaHabitos(HabitosTabacoVetor, HabitosAlcoolVetor, RG);
        return retorno;
    }

    private boolean GravatratamentosNecessarios() throws ClassNotFoundException {
        TratamentosNecessarios acesso = new TratamentosNecessarios();
        sctp2.BancodeDados.conexao conectar = new sctp2.BancodeDados.conexao();
        int[] tratamentosNVetor = new int[23];
        sctp2.ClassesdeControle.Paciente PegaRg = new sctp2.ClassesdeControle.Paciente();
        String rg = PegaRg.getRg();

        //-----------------------------------------------------------
        tratamentosNVetor[0] = acesso.getAmalgama();
        tratamentosNVetor[1] = acesso.getCirugiaPeridontal();
        tratamentosNVetor[2] = acesso.getCoroaTotal();
        tratamentosNVetor[3] = acesso.getDtm();
        tratamentosNVetor[4] = acesso.getEndodontiaBirradicular();
        tratamentosNVetor[5] = acesso.getEndontiaUnirradicular();
        tratamentosNVetor[6] = acesso.getEndodontiaTrirradicular();
        tratamentosNVetor[7] = acesso.getEstomatologia();
        tratamentosNVetor[8] = acesso.getExodontia3Molar();
        tratamentosNVetor[9] = acesso.getExodontiaIncluso();
        tratamentosNVetor[10] = acesso.getProfilaxiaSimples();
        tratamentosNVetor[11] = acesso.getPonteFixa3Elementos();
        tratamentosNVetor[12] = acesso.getPonteFixa4Elementos();
        tratamentosNVetor[13] = acesso.getPonteFixaMaisQue4Elementos();
        tratamentosNVetor[14] = acesso.getProtese();
        tratamentosNVetor[15] = acesso.getProtesetotal();
        tratamentosNVetor[16] = acesso.getProtese_ppr();
        tratamentosNVetor[17] = acesso.getPpr();
        tratamentosNVetor[18] = acesso.getRaspagemEPoliCCoronario();
        tratamentosNVetor[19] = acesso.getResina();
        tratamentosNVetor[20] = acesso.getRmf();
        tratamentosNVetor[21] = acesso.getTerrapiaOeriodDeSuporte();
        tratamentosNVetor[22] = acesso.getExodontiaSimples();
        retorno = conectar.GravaTratamentoNecessario(tratamentosNVetor, rg);

        return true;
    }
    public void GravaResponsavelProntuario() throws ClassNotFoundException{//Esta Função normalmente sera chamada pela interface Cadastro de Aluno/Responsavel
        sctp2.ClassesdeControle.responsavelprontuario acesso= new sctp2.ClassesdeControle.responsavelprontuario();
        sctp2.BancodeDados.conexao conecta= new sctp2.BancodeDados.conexao();
        //----------------------------------------------------------------
        String nome=acesso.getNomeresponsavel();
        String celular=acesso.getTelefoneCelular();
        String telefonefixo=acesso.getTelefoneFixo();
        String nomeProfessor=acesso.getNomeProfessorResponsavel();
        String celularProfessor=acesso.getTelefoneCelularProfessor();
        String telefonefixoProfessor=acesso.getTelefoneFixoProfessor();
        //int retorno;
        //----------------------------------------------------------------
        
        //retorno=
                conecta.GravaresponsavelProntuario(nome,celular,telefonefixo,nomeProfessor,telefonefixoProfessor,celularProfessor);
        //if(retorno==true){
        sctp2.Principal.principal telaprincipal= new  sctp2.Principal.principal();
        //telaprincipal.setVisible(retorno);
        //}
        
        
        
    }
     public void AtualizaResponsavelProntuario() throws ClassNotFoundException{//Esta Função normalmente sera chamada pela interface Cadastro de Aluno/Responsavel
        sctp2.ClassesdeControle.responsavelprontuario acesso= new sctp2.ClassesdeControle.responsavelprontuario();
        sctp2.BancodeDados.conexao conecta= new sctp2.BancodeDados.conexao();
        //----------------------------------------------------------------
        String nome=acesso.getNomeresponsavel();
        String celular=acesso.getTelefoneCelular();
        String telefonefixo=acesso.getTelefoneFixo();
        String nomeProfessor=acesso.getNomeProfessorResponsavel();
        String celularProfessor=acesso.getTelefoneCelularProfessor();
        String telefonefixoProfessor=acesso.getTelefoneFixoProfessor();
        int id=acesso.getIdResponsavel();
        boolean retorno;
        //----------------------------------------------------------------
        
        retorno=conecta.AtualizaresponsavelProntuario(nome,celular,telefonefixo,nomeProfessor,celularProfessor,telefonefixoProfessor,id);
        if(retorno==true){
     //   sctp2.Principal.principal telaprincipal= new  sctp2.Principal.principal();
        //telaprincipal.setVisible(retorno);
        }
        
        
        
    }

    int boolToInt(boolean b) {// Função que converte boolean em inteiro
        if (b) {
            return 1;
        } else {
            return 0;
        }
    }
private boolean NovaAmnase() throws ClassNotFoundException {
        Anamnese acesso = new Anamnese();
        conexao conecta = new conexao();
        Prontuario prontuario= new Prontuario();
        //-----------------------------------
        ArrayList<String> AnamneseList = new ArrayList<>();
        boolean[] AnamneseVetor = new boolean[10];
        int[] vetorAnamneseInteiro = new int[10];
        Paciente PegaRG = new Paciente();

        //--------------------------------------------------
        AnamneseList.add(sctp2.ClassesdeControle.Anamnese.getPrincipalQueixa());//0
        AnamneseList.add(acesso.getSofreAlgumaDoencaDescricao());//1
        AnamneseList.add(acesso.getEmTratamentoMedicoDescricao());//2
        AnamneseList.add(acesso.getPossuiAlergiasDescricao());//3
        AnamneseList.add(acesso.getUsoDeAlgumaMedicacaoDescricao());//4
        AnamneseList.add(acesso.getPossuiAlergiasDescricao());//5
        AnamneseList.add(acesso.getJaFoiOperadoDescricao());//6
        AnamneseList.add(acesso.getProblemasComHemorragiaDescricao());//7
        AnamneseList.add(prontuario.getRgresponsavel());//8
        AnamneseVetor[0] = acesso.isSofreAlgumaDoenca();
        AnamneseVetor[1] = acesso.isEmTratamentoMedico();
        AnamneseVetor[2] = acesso.isUsoDeAlgumaMedicacao();
        AnamneseVetor[3] = acesso.isPossuiAlergias();
        AnamneseVetor[4] = acesso.isJaFoiOperado();
        AnamneseVetor[5] = acesso.isProblemasComHemorragia();
        AnamneseVetor[6] = acesso.isProblemasComCicatrizacao();
        AnamneseVetor[7] = acesso.isGravidez();
        AnamneseVetor[8] = acesso.isProblemasComAnestesia();
        for (int i = 0; i < 10; i++) {
            vetorAnamneseInteiro[i] = boolToInt(AnamneseVetor[i]);//converte os dados booleanos para inteiro
        }
        conecta.NovaAnamnese(AnamneseList, vetorAnamneseInteiro);

        return retorno;

    }//fim da nova anamnese
//--------------------------------------------
private int NovotratamentosNecessarios() throws ClassNotFoundException {
    //Esta função grava um novo tratamento necessário, ela normalmente será chamada quando o primeiro tratamento já estiver sido encerrado
        TratamentosNecessarios acesso = new TratamentosNecessarios();
        sctp2.BancodeDados.conexao conectar = new sctp2.BancodeDados.conexao();
        int[] tratamentosNVetor = new int[23];
        String rg = sctp2.ClassesdeControle.Prontuario.getRgresponsavel();
        int retorno;

        //-----------------------------------------------------------
        tratamentosNVetor[0] = acesso.getAmalgama();
        tratamentosNVetor[1] = acesso.getCirugiaPeridontal();
        tratamentosNVetor[2] = acesso.getCoroaTotal();
        tratamentosNVetor[3] = acesso.getDtm();
        tratamentosNVetor[4] = acesso.getEndodontiaBirradicular();
        tratamentosNVetor[5] = acesso.getEndontiaUnirradicular();
        tratamentosNVetor[6] = acesso.getEndodontiaTrirradicular();
        tratamentosNVetor[7] = acesso.getEstomatologia();
        tratamentosNVetor[8] = acesso.getExodontia3Molar();
        tratamentosNVetor[9] = acesso.getExodontiaIncluso();
        tratamentosNVetor[10] = acesso.getProfilaxiaSimples();
        tratamentosNVetor[11] = acesso.getPonteFixa3Elementos();
        tratamentosNVetor[12] = acesso.getPonteFixa4Elementos();
        tratamentosNVetor[13] = acesso.getPonteFixaMaisQue4Elementos();
        tratamentosNVetor[14] = acesso.getProtese();
        tratamentosNVetor[15] = acesso.getProtesetotal();
        tratamentosNVetor[16] = acesso.getProtese_ppr();
        tratamentosNVetor[17] = acesso.getPpr();
        tratamentosNVetor[18] = acesso.getRaspagemEPoliCCoronario();
        tratamentosNVetor[19] = acesso.getResina();
        tratamentosNVetor[20] = acesso.getRmf();
        tratamentosNVetor[21] = acesso.getTerrapiaOeriodDeSuporte();
        tratamentosNVetor[22] = acesso.getExodontiaSimples();
        retorno = conectar.NovoTratamentoNecessario(tratamentosNVetor, rg);
        return retorno;
    }

//-----------------------------------------------
    public void ProntuarioGravaNovoTratamento() throws ClassNotFoundException, SQLException {//Após um tratamento ser encerrado, esta função pode ser chamada para gerar um
        String rg=new sctp2.ClassesdeControle.Prontuario().getRgresponsavel();
        //-----------------------------------------
        
        NovaAmnase();//Esta função prepara os dados para serem gravados no banco de dados
        NovotratamentosNecessarios();
        sctp2.BancodeDados.conexao acesso=new sctp2.BancodeDados.conexao();
        acesso.NovoTratamentodoPaciente(rg,0);//retira o paciente da alta
        
    }
}
