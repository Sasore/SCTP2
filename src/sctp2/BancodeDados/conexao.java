
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.BancodeDados;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sctp2.Cadastros.Prontuario;
import sctp2.ClassesdeControle.Anamnese;
import sctp2.ClassesdeControle.DoencasSistemicas;
import sctp2.ClassesdeControle.Habitos;
import sctp2.ClassesdeControle.TratamentosNecessarios;
import sctp2.Paciente.HistoricoDetratamentos;
import sctp2.Paciente.HistoricoPaciente;
import sctp2.Pesquisar.Pesquisar;
import sctp2.Pesquisar.PesquisarProntuarioStatico;
import sctp2.Pesquisar.PesquisarProntuario;
import sctp2.Pesquisar.ResponsavelProntuario;

/**
 *
 * @author Adriano
 */

//Conexao-------------------------------------------------------------------------------------------
public class conexao {

    private final String NovoPaciente = ("INSERT INTO paciente(pac_RG, pac_Nome, pac_DataNasc "
            + ", pac_Naturalidade, pac_Sexo, pac_Ocupacao, pac_Procedencia"
            + ", pac_Mae,pac_Pai, pac_EstadoCivil, pac_Conjuge, pac_Cidade"
            + ", pac_Rua, pac_Bairro, pac_NumeroCasa, pac_Cep, pac_Telefone, pac_Alta"
            + ",pac_listaNegra, pac_motivoLista,pac_Data_Cadastro,pac_inicio_tratamento)"
            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,CURDATE(),CURDATE())");

    private boolean retorno = false;

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con;
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sctp", "root", "");
        return con;
    }

    private void closeConnection(Connection con) {//fecha a conexão com o Banco de Dados
        try {
            con.close();
        } catch (SQLException e) {
            //System.out.println("Não foi possível conectar ao Banco de dados");
            JOptionPane.showMessageDialog(null, " (1) Não foi possível conectar ao Banco de dados; Aguarde alguns minutos e tente novamente!");
            e.printStackTrace();
        }
    }
    //logar------------------------------------------------------------------------------------------------------------------------------

    public boolean logar(String nome, String senha) throws ClassNotFoundException {
        String logar = "select usu_nome,usu_senha from usuario where usu_nome=? and usu_senha=?;";
        Connection con = null;
        try {

            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(logar);
            smt.setString(1, nome);
            smt.setString(2, senha);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                if (rs.getString("usu_nome").equals(nome) && (rs.getString("usu_senha").equals(senha))) {
                    System.out.println("conectado");
                }
                {
                    return true;//retorna true se o login estiver certo
                }

            }
            return false;//caso o login esteja errado retorna falso
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível conectar ao Banco de dados. \n Aguarde um minuto e tente novamente!");

            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;
    }
//Paciente--------------------------------------------------------------------------------------------------------------------------------------
    public boolean GravarPacienteSimples(ArrayList<String> Paciente) throws ClassNotFoundException {
        String sql = "insert into pacientesimples(sim_Nome,sim_Telefone,sim_Nascimento,sim_RG,sim_Queixas,sim_tratamentoSolicitado) values (?,?,?,?,?,?);";
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, Paciente.get(0));
            smt.setString(2, Paciente.get(1));
            smt.setString(3, Paciente.get(2));
            smt.setString(4, Paciente.get(3));
            smt.setString(5, Paciente.get(4));
            smt.setString(6, Paciente.get(5));
            retorno = smt.execute();
            return retorno;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados os dados do Paciente Simples, tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return false;
    }
    public ArrayList<Pesquisar> PesquisarPorPacientePorCPFRG(String cpfRg) throws ClassNotFoundException {
        Connection con = null;
        ArrayList<Pesquisar> ListarPesquisa = new ArrayList<Pesquisar>();
        String sql = "select pac_Cod,pac_Nome,pac_Telefone,pac_RG from paciente where pac_RG=?;";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1,cpfRg);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Pesquisar pesquisarsmt = new Pesquisar();
                pesquisarsmt.setCodigo(rs.getInt("pac_Cod"));
                pesquisarsmt.setNome(rs.getString("pac_Nome"));
                pesquisarsmt.setTelefone(rs.getString("pac_Telefone"));
                pesquisarsmt.setRg(rs.getString("pac_RG"));
                ListarPesquisa.add(pesquisarsmt);
            }
        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos!");
            e.printStackTrace();

        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
    public ArrayList<Pesquisar> PesquisarPorPaciente(String nome) throws ClassNotFoundException {
        Connection con = null;
        ArrayList<Pesquisar> ListarPesquisa = new ArrayList<Pesquisar>();
        String sql = "select pac_Cod,pac_Nome,pac_Telefone,pac_RG from paciente where pac_Nome like ?;";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, "%" + nome + "%");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Pesquisar pesquisarsmt = new Pesquisar();
                pesquisarsmt.setCodigo(rs.getInt("pac_Cod"));
                pesquisarsmt.setNome(rs.getString("pac_Nome"));
                pesquisarsmt.setTelefone(rs.getString("pac_Telefone"));
                pesquisarsmt.setRg(rs.getString("pac_RG"));
                ListarPesquisa.add(pesquisarsmt);
            }
        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos!");
            e.printStackTrace();

        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
  public ArrayList<Pesquisar> PesquisarProntuarioPacienteDetalhes(String valor) throws ClassNotFoundException {
        String sql = "SELECT `Id`, `nome_ResponsavelPeloProntuario`, `celular_ResponsavelPeloProntuario`, "
                + "`telefoneFixo_ResponsavelPeloProntuario`, `nomeProfessor_ResponsavelPeloProntuario`, "
                + "`TelefoneProfessor_ResponsavelPeloProntuario`, `celularProfessor_ResponsavelPeloProntuario`"
                + " FROM `responsavelpeloprontuario` WHERE `Id`=?;";
        Connection con = null;
        ArrayList<Pesquisar> ListarPesquisa = new ArrayList<>();//array que recebera o resultado da pesquisa
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, valor);
            ResultSet rs = smt.executeQuery();//efetuando a busca
            while (rs.next()) {
                Pesquisar pesquisarsmt = new Pesquisar();
                pesquisarsmt.setCodigo(rs.getInt("Id"));
                pesquisarsmt.setNome(rs.getString("nome_ResponsavelPeloProntuario"));
                pesquisarsmt.setTelefone(rs.getString("celular_ResponsavelPeloProntuario"));
                pesquisarsmt.setTelefonefixo(rs.getString("telefoneFixo_ResponsavelPeloProntuario"));
                pesquisarsmt.setNomeProfessor(rs.getString("nomeProfessor_ResponsavelPeloProntuario"));
                pesquisarsmt.setTelefoneFixoProfessor(rs.getString("TelefoneProfessor_ResponsavelPeloProntuario"));
                pesquisarsmt.setCelularProfessor(rs.getString("celularProfessor_ResponsavelPeloProntuario"));
                ListarPesquisa.add(pesquisarsmt);
            }

        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao Pesquisa o prontuário no banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
    public boolean GravaNovoPaciente(ArrayList<String> PacienteList, int[] PacienteVetor) throws ClassNotFoundException {//função que recebe os dados da classe control.GravaPaciente e efetua a gravação dos dados
        Connection con = null;
        try {
            con = getConnection();

            PreparedStatement smt = (PreparedStatement) con.prepareStatement(NovoPaciente);
            smt.setString(1, PacienteList.get(0));
            smt.setString(2, PacienteList.get(1));
            smt.setString(3, PacienteList.get(2));
            smt.setString(4, PacienteList.get(3));
            smt.setInt(5, PacienteVetor[0]);//sexo do paciente
            smt.setString(6, PacienteList.get(4));//ocupacao
            smt.setInt(7, PacienteVetor[2]);
            smt.setString(8, PacienteList.get(5));
            smt.setString(9, PacienteList.get(6));
            smt.setInt(10, PacienteVetor[1]);
            smt.setString(11, PacienteList.get(7));
            smt.setString(12, PacienteList.get(8));
            smt.setString(13, PacienteList.get(9));
            smt.setString(14, PacienteList.get(10));
            smt.setString(15, PacienteList.get(11));
            smt.setString(16, PacienteList.get(12));
            smt.setString(17, PacienteList.get(13));
            smt.setInt(18, PacienteVetor[3]);//paciente com alta
            smt.setInt(19, PacienteVetor[4]);
            smt.setString(20, PacienteList.get(14));//motivo da lista negra     
            smt.execute();
            return retorno;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados os dados do Paciente , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;

    }
    public void NovoTratamentodoPaciente(String rg,int status) throws ClassNotFoundException, SQLException {
     Connection con = null;
     String sql="UPDATE paciente set pac_Alta=?, `pac_inicio_tratamento`=CURDATE() where pac_RG=?";
     try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, status);
            smt.setString(2, rg);
            smt.executeUpdate();
    }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados as informações da tela de cadastro de tratamentos necessarios , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
}
    public ArrayList<Pesquisar> DetalhesdoPaciente(String codigo) throws ClassNotFoundException {
        Connection con = null;
        ArrayList<Pesquisar> ListarPesquisa = new ArrayList<Pesquisar>();
        String sql = "SELECT pac_RG, pac_Nome, pac_DataNasc, pac_Cod, pac_Naturalidade, pac_Sexo, pac_Ocupacao,pac_Procedencia, pac_Mae, pac_Pai,pac_EstadoCivil, pac_Conjuge, pac_Cidade, pac_Rua, pac_Bairro, pac_NumeroCasa, pac_Cep, pac_Telefone, pac_TelefoneFixo, pac_GrupoRisco, pac_Alta, pac_listaNegra, pac_motivoLista FROM paciente WHERE pac_RG=?";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, codigo);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                Pesquisar pesquisarsmt = new Pesquisar();
                pesquisarsmt.setRg(rs.getString("pac_RG"));
                pesquisarsmt.setNome(rs.getString("pac_Nome"));
                pesquisarsmt.setDataNascimento(rs.getString("pac_DataNasc"));
                pesquisarsmt.setCodigo(rs.getInt("pac_Cod"));
                pesquisarsmt.setNaturalidade(rs.getString("pac_Naturalidade"));
                pesquisarsmt.setSexo(rs.getInt("pac_Sexo"));
                pesquisarsmt.setOcupação(rs.getString("pac_Ocupacao"));
                pesquisarsmt.setProcedencia(rs.getInt("pac_Procedencia"));
                pesquisarsmt.setMae(rs.getString("pac_Mae"));
                pesquisarsmt.setPai(rs.getString("pac_Pai"));
                pesquisarsmt.setEstadoCivil(rs.getInt("pac_EstadoCivil"));
                pesquisarsmt.setConjugue(rs.getString("pac_Conjuge"));
                pesquisarsmt.setCidade(rs.getString("pac_Cidade"));
                pesquisarsmt.setRua(rs.getString("pac_Rua"));
                pesquisarsmt.setBairro(rs.getString("pac_Bairro"));
                pesquisarsmt.setNumeroCasa(rs.getString("pac_NumeroCasa"));
                pesquisarsmt.setCep(rs.getString("pac_Cep"));
                pesquisarsmt.setTelefone(rs.getString("pac_Telefone"));
                pesquisarsmt.setTelefonefixo(rs.getString("pac_TelefoneFixo"));
                pesquisarsmt.setGrupodeRisco(rs.getInt("pac_GrupoRisco"));
                pesquisarsmt.setPacienteAlta(rs.getInt("pac_Alta"));
                pesquisarsmt.setListaNegra(rs.getInt("pac_listaNegra"));
                pesquisarsmt.setMotivoListaNegra(rs.getString("pac_motivoLista"));
                ListarPesquisa.add(pesquisarsmt);
            }
        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos!");
            e.printStackTrace();

        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
    public boolean VerificaRgCpf(String rgCpf) throws ClassNotFoundException {
        Connection con = null;
        String sql = "SELECT `pac_Cod` FROM `paciente` WHERE `pac_RG`=?";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rgCpf);
            ResultSet rs = smt.executeQuery();
            int resultado=0;
            while(rs.next())resultado++;
            if(resultado>=1)return true;
            
                
            
        }catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos!");
            e.printStackTrace();

        } finally {
            closeConnection(con);
        }
     return false;
    }
    public boolean MudaStatusListaNegraPaciente(String codigoPaciente) throws SQLException, ClassNotFoundException {//retira o usuario da lista negra
        //esta funcao tira o paciente da lista negra
        Connection con = null;
        boolean retorno = false;
        String sql = "update paciente set pac_listaNegra=0, pac_MotivoLista=\"\" where pac_RG=?;";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, codigoPaciente);
            smt.executeUpdate();
            retorno = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados os dados do Paciente , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;

    }
     public boolean MudaStatusListaNegraPaciente(String rg, String motivo) throws SQLException, ClassNotFoundException {//retira o usuario da lista negra
        //Esta função colocar o paciente na lista negra    
        Connection con = null;
        boolean retorno = false;
        String sql = "update paciente set pac_listaNegra=1, pac_MotivoLista=? where pac_RG=?;";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, motivo);
            smt.setString(2, rg);
            smt.executeUpdate();
            retorno = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados os dados do Paciente , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;

    }
//Prontuario----------------------------------------------------------------------------------------------------------------------------
    public boolean GravaProntuario(ArrayList<String> ProntuarioList, int StatusProntuario, int idResponsavelProntuario) throws ClassNotFoundException {
        //---------------------------------------------------------------------------------------------------------------------------- -\\
        // A partir da versão 2.0 do sctp os responsaveis pelo prontuario passaram a ser gravados em uma tabela separada, sendo assim     \\
        //   existem duas funções, uma que grava o responsável pelo prontuario e outra que grava o prontuario, esta grava o prontuario e a  \\
        // id do responsável, sendo assim o responsável pode  ser desvinculada do prontuario sem maiores problemas.                          //
        //---------------------------------------------------------------------------------------------------------------------------------==//

        String InsereProntuario = "INSERT INTO prontuario (referencia_RG_PAC ,pront_Status ,"
                + "  pront_Numero ,pront_Informacoes,pront_responsavel_prontuario) VALUES(?,?,?,?,?)";
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(InsereProntuario);
            smt.setString(1, ProntuarioList.get(0));//rg             
            smt.setInt(2, StatusProntuario);//status
            smt.setString(3, ProntuarioList.get(1));//numero pront
            smt.setString(4, ProntuarioList.get(4));//informac
            smt.setInt(5, idResponsavelProntuario);//id do responsavel
            smt.execute();
            System.out.println("Gravou prontuario");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados as informações da tela de cadastro de prontuario , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return retorno;
    }
     public int[] PesquisarProntuarioPaciente(String codigo) throws ClassNotFoundException {
        int[] retorno = new int[2];
        Connection con = null;
        String sql = "select pront_Cod,pront_Status from prontuario where referencia_RG_PAC=?;";

        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, codigo);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {

                retorno[0] = rs.getInt("pront_Cod");
                retorno[1] = rs.getInt("pront_Status");

            }

            return retorno;
        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos!");
            e.printStackTrace();

        } finally {
            closeConnection(con);
        }
        return retorno;
    }//Fim da função PesquisarProntuarioStatico
     //private ArrayList<PesquisarProntuario> PesquisaResponsavelProtuario(int id) throws ClassNotFoundException {
   
     private ArrayList<PesquisarProntuario> PesquisaResponsavelProtuario(String id) throws ClassNotFoundException {
        Connection con = null;
        ArrayList<PesquisarProntuario> listarPesquisa = new ArrayList<>();
        String sql = "SELECT `Id`, `nome_ResponsavelPeloProntuario`, `celular_ResponsavelPeloProntuario`, `telefoneFixo_ResponsavelPeloProntuario`,"
                + " `nomeProfessor_ResponsavelPeloProntuario`, `TelefoneProfessor_ResponsavelPeloProntuario`, `celularProfessor_ResponsavelPeloProntuario` FROM `responsavelpeloprontuario` WHERE `Id`=?";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, id);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                PesquisarProntuario pesquisasmt= new PesquisarProntuario();
                pesquisasmt.setNome(rs.getString("nome_ResponsavelPeloProntuario"));
                pesquisasmt.setCelularProfessor(rs.getString("celular_ResponsavelPeloProntuario"));
                pesquisasmt.setTelefoneFixo(rs.getString("telefoneFixo_ResponsavelPeloProntuario"));
                pesquisasmt.setIdResponsavel(rs.getInt("Id"));
                pesquisasmt.setNomeProfessor(rs.getString("nomeProfessor_ResponsavelPeloProntuario"));
                pesquisasmt.setTelefoneProfessor(rs.getString("TelefoneProfessor_ResponsavelPeloProntuario"));
                pesquisasmt.setCelularProfessor(rs.getString("celularProfessor_ResponsavelPeloProntuario"));
                listarPesquisa.add(pesquisasmt);
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return listarPesquisa;
    }
     public ArrayList<PesquisarProntuario> PesquisarProntuario(String codigo) throws ClassNotFoundException {
         String sql = "SELECT "
                + "`pront_cod`, `pront_Numero`, `referencia_RG_PAC`, `pront_Status`,"
                + " `pront_AlunoEmprestado`, `pront_TelefoneAluno`, `pront_Informacoes`,DataEmprestimo_Prontuario,DataDevolver_Prontuario, "
                + "`pront_responsavel_prontuario`, `reservadopara_Prontuario`, paciente.pac_Nome\n"
                + "FROM `prontuario` \n"
                + "JOIN paciente\n"
                + "WHERE paciente.pac_RG=referencia_RG_PAC and prontuario.pront_cod=?";
        Connection con = null;
        ArrayList<PesquisarProntuario> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<PesquisarProntuario>();//criando novo array
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, codigo);
            ResultSet rs = smt.executeQuery();
            
            while (rs.next()) {
                PesquisarProntuario pesquisarsmt = new PesquisarProntuario();
                String verificavazio = "";
                pesquisarsmt.setCodigoProntuario(rs.getInt("pront_cod"));
                pesquisarsmt.setProntuario(rs.getString("pront_Numero"));
                pesquisarsmt.setNome(rs.getString("pront_AlunoEmprestado"));
                pesquisarsmt.setTelefone(rs.getString("pront_TelefoneAluno"));
                pesquisarsmt.setInformacoes(rs.getString("pront_Informacoes"));
                pesquisarsmt.setIdUsuarioReservado(rs.getInt("reservadopara_Prontuario"));
                pesquisarsmt.setPaciente(rs.getString("paciente.pac_Nome"));//Para não ter que criar um novo campo utilizei pai para receber o nome do paciente
                pesquisarsmt.setStatus(rs.getInt("pront_Status"));
                pesquisarsmt.setDataEmprestimo(rs.getDate("DataEmprestimo_Prontuario"));
                pesquisarsmt.setDataDevolução(rs.getDate("DataDevolver_Prontuario"));
                pesquisarsmt.setPront_AlunoEmprestado(rs.getInt("pront_AlunoEmprestado"));
                pesquisarsmt.setResponsavelProntuario(rs.getInt("pront_responsavel_prontuario"));

                //Caso o nome do aluno esteja vazio ele será preenchido no if abaixo
                verificavazio = rs.getString("pront_AlunoEmprestado");//se o nome estiver vazio será chamado a função para pesquisar na tabela responsavelpeloprontuario
                if (verificavazio == null || verificavazio.trim().equals("")) {
                    ArrayList<PesquisarProntuario> ListarPesquisaAuxiliar= new ArrayList<>();
                    ListarPesquisaAuxiliar = PesquisaResponsavelProtuario(Integer.toString(rs.getInt("pront_responsavel_prontuario")));
                    pesquisarsmt.setNome(ListarPesquisaAuxiliar.get(0).getNome());
                    pesquisarsmt.setTelefone(ListarPesquisaAuxiliar.get(0).getTelefoneFixo());
                    pesquisarsmt.setTelefoneFixo(ListarPesquisaAuxiliar.get(0).getTelefoneFixo());
                    pesquisarsmt.setNomeProfessor(ListarPesquisaAuxiliar.get(0).getNomeProfessor());
                    pesquisarsmt.setTelefoneProfessor(ListarPesquisaAuxiliar.get(0).getTelefoneProfessor());
                    pesquisarsmt.setCelularProfessor(ListarPesquisaAuxiliar.get(0).getCelularProfessor());
                    
                };
                ListarPesquisa.add(pesquisarsmt);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
     public ArrayList<PesquisarProntuario> PesquisarProntuarioporNomePaciente(String nomePaciente) throws ClassNotFoundException {
        Connection con = null;
        ArrayList<PesquisarProntuario> ListarPesquisa=new ArrayList();//array que recebera o resultado da pesquisa
   String sql = "SELECT `pront_cod`, `pront_Numero`, "
                + "`referencia_RG_PAC`, `pront_Status`, "
                + "`pront_AlunoEmprestado`, `pront_TelefoneAluno`, "
                + "`pront_Informacoes`, `pront_responsavel_prontuario`, "
                + "`reservadopara_Prontuario`, `DataEmprestimo_Prontuario`, "
                + "`DataDevolver_Prontuario`,paciente.pac_Nome FROM `prontuario`"
                + " JOIN paciente ON paciente.pac_RG=prontuario.referencia_RG_PAC "
                + "WHERE paciente.pac_Nome LIKE ?";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
             smt.setString(1, nomePaciente+"%");
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                sctp2.Pesquisar.PesquisarProntuario pesquisarsmt = new sctp2.Pesquisar.PesquisarProntuario();
                String verificavazio = "";
                pesquisarsmt.setCodigoProntuario(rs.getInt("pront_cod"));
                pesquisarsmt.setProntuario(rs.getString("pront_Numero"));
                pesquisarsmt.setNome(rs.getString("pront_AlunoEmprestado"));
                pesquisarsmt.setTelefone(rs.getString("pront_TelefoneAluno"));
                pesquisarsmt.setInformacoes(rs.getString("pront_Informacoes"));
                pesquisarsmt.setIdUsuarioReservado(rs.getInt("reservadopara_Prontuario"));
                pesquisarsmt.setPaciente(rs.getString("paciente.pac_Nome"));//Para não ter que criar um novo campo utilizei pai para receber o nome do paciente
                pesquisarsmt.setStatus(rs.getInt("pront_Status"));
                pesquisarsmt.setDataEmprestimo(rs.getDate("DataEmprestimo_Prontuario"));
                pesquisarsmt.setDataDevolução(rs.getDate("DataDevolver_Prontuario"));
                pesquisarsmt.setPront_AlunoEmprestado(rs.getInt("pront_AlunoEmprestado"));
                pesquisarsmt.setResponsavelProntuario(rs.getInt("pront_responsavel_prontuario"));
                
                //Caso o nome do aluno esteja vazio ele será preenchido no if abaixo
                verificavazio = rs.getString("pront_AlunoEmprestado");//se o nome estiver vazio será chamado a função para pesquisar na tabela responsavelpeloprontuario
                if (verificavazio == null || verificavazio.trim().equals("")) {
                    ArrayList<PesquisarProntuario> ListarPesquisaAux= new ArrayList<>();
                    ListarPesquisaAux = PesquisaResponsavelProtuario(Integer.toString(rs.getInt("pront_responsavel_prontuario")));
                    pesquisarsmt.setNome(ListarPesquisaAux.get(0).getNome());
                    pesquisarsmt.setTelefone(ListarPesquisaAux.get(0).getTelefone());
                    pesquisarsmt.setTelefoneFixo(ListarPesquisaAux.get(0).getTelefoneFixo());
                    pesquisarsmt.setNomeProfessor(ListarPesquisaAux.get(0).getNomeProfessor());
                    pesquisarsmt.setTelefoneProfessor(ListarPesquisaAux.get(0).getTelefoneProfessor());
                    pesquisarsmt.setCelularProfessor(ListarPesquisaAux.get(0).getCelularProfessor());
                    
                };
                ListarPesquisa.add(pesquisarsmt);
            }
        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos!");
            e.printStackTrace();

        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
      //Esta função retorna todos os prontuários emprestados para uma pessoa
    public ArrayList<sctp2.Pesquisar.ResponsavelProntuario> PesquisarTodosProntuariosResponsavel(String idResponsavel) throws ClassNotFoundException {
        String sql = "SELECT \n"
                + "t1.pront_cod, \n"
                + "t1.pront_Numero, \n"
                + "t1.referencia_RG_PAC,\n"
                + "t1.pront_Status,\n"
                + "t1.pront_AlunoEmprestado, \n"
                + "t1.pront_TelefoneAluno, \n"
                + "t1.pront_Informacoes, \n"
                + "t1.pront_responsavel_prontuario, \n"
                + "t1.reservadopara_Prontuario, \n"
                + "t1.DataEmprestimo_Prontuario, \n"
                + "t1.DataDevolver_Prontuario ,\n"
                + "t2.Id,\n"
                + "t2.nome_ResponsavelPeloProntuario,\n"
                + "t2.celular_ResponsavelPeloProntuario,\n"
                + "t2.telefoneFixo_ResponsavelPeloProntuario,\n"
                + "t2.nomeProfessor_ResponsavelPeloProntuario,\n"
                + "t2.TelefoneProfessor_ResponsavelPeloProntuario,\n"
                + "t2.celularProfessor_ResponsavelPeloProntuario\n"
                + "\n"
                + "FROM `prontuario` AS t1\n"
                + "INNER JOIN responsavelpeloprontuario AS t2\n"
                + "ON t2.Id=t1.pront_responsavel_prontuario\n"
                + "WHERE  t2.Id=?";
        Connection con = null;
        ArrayList<sctp2.Pesquisar.ResponsavelProntuario> ListarPesquisa = new ArrayList<>();//array que recebera o resultado da pesquisa
        
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, idResponsavel);
            ResultSet rs = smt.executeQuery();//efetuando a busca
            while (rs.next()) {
                sctp2.Pesquisar.ResponsavelProntuario responsavelsmt = new sctp2.Pesquisar.ResponsavelProntuario();
                responsavelsmt.setNumeroProntuario(rs.getString("pront_Numero"));
                responsavelsmt.setInformacoesProntuario(rs.getString("pront_Informacoes"));
                responsavelsmt.setNomeresponsavelProntuario(rs.getString("pront_AlunoEmprestado"));
                responsavelsmt.setStatusProntuario(rs.getString("pront_Status"));
                responsavelsmt.setTelefoneResponsavelProntuario(rs.getString("pront_TelefoneAluno"));
                responsavelsmt.setRgPaciente(rs.getString("referencia_RG_PAC"));
                responsavelsmt.setIdResponsavel(rs.getInt("Id"));
                responsavelsmt.setNomeresponsavel(rs.getString("nome_ResponsavelPeloProntuario"));
                responsavelsmt.setTelefoneCelular(rs.getString("celular_ResponsavelPeloProntuario"));
                responsavelsmt.setTelefoneFixo(rs.getString("telefoneFixo_ResponsavelPeloProntuario"));
                responsavelsmt.setNomeProfessorResponsavel(rs.getString("nomeProfessor_ResponsavelPeloProntuario"));
                responsavelsmt.setTelefoneCelularProfessor(rs.getString("celularProfessor_ResponsavelPeloProntuario"));
                responsavelsmt.setTelefoneFixoProfessor(rs.getString("TelefoneProfessor_ResponsavelPeloProntuario"));
                ListarPesquisa.add(responsavelsmt);
            }
            
        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();

        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
    public boolean EmprestaProntuario(String prontuario, String idResponsavel, Date dataEmprestimo, Date dataDevolver) throws ClassNotFoundException {
        Connection con = null;
        boolean retorno = false;
        String sql = "update prontuario set pront_Status=1,"
                + " pront_responsavel_prontuario=?,DataEmprestimo_Prontuario=?,DataDevolver_Prontuario=?,"
                + "pront_AlunoEmprestado='',pront_TelefoneAluno=''  where pront_cod=?;";

        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, idResponsavel);
            smt.setDate(2, dataEmprestimo);
            smt.setDate(3, dataDevolver);
            smt.setString(4, prontuario);
            smt.executeUpdate();
            System.out.println("executou");
            retorno = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados os dados do Paciente , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;
    }
     public boolean AtualizaStatusProntuario(String id) throws SQLException, ClassNotFoundException {
        String sql = "update prontuario set pront_Status=0 where pront_cod=?;";
        Connection con = null;
        boolean retorno = false;
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, id);
            smt.executeUpdate();
            retorno = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados os dados do Paciente , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;

    }
     public ArrayList<PesquisarProntuarioStatico> PesquisarProntuariopelorg(String rg) throws ClassNotFoundException {
        String sql = "SELECT  pront_cod,pront_Numero,pront_AlunoEmprestado,pront_TelefoneAluno,pront_Status,pront_responsavel_prontuario, paciente.pac_Nome, pront_Informacoes "
                + "FROM prontuario join paciente where paciente.pac_RG=referencia_RG_PAC and prontuario.referencia_RG_PAC=?;";
        Connection con = null;
        ArrayList<PesquisarProntuarioStatico> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<PesquisarProntuarioStatico>();//criando novo array
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rg);
            ResultSet rs = smt.executeQuery();
            PesquisarProntuarioStatico pesquisarsmt = new PesquisarProntuarioStatico();
            while (rs.next()) {
                String verificavazio = "";
                pesquisarsmt.setCodigoProntuario(rs.getInt("pront_cod"));
                pesquisarsmt.setProntuario(rs.getString("pront_Numero"));
                pesquisarsmt.setNome(rs.getString("pront_AlunoEmprestado"));
                pesquisarsmt.setTelefone(rs.getString("pront_TelefoneAluno"));
                pesquisarsmt.setPaciente(rs.getString("paciente.pac_Nome"));//Para não ter que criar um novo campo utilizei pai para receber o nome do paciente
                pesquisarsmt.setStatus(rs.getInt("pront_Status"));
                pesquisarsmt.setInformacoes(rs.getString("pront_Informacoes"));

                //Caso o nome do aluno esteja vazio ele será preenchido no if abaixo
                verificavazio = rs.getString("pront_AlunoEmprestado");//se o nome estiver vazio será chamado a função para pesquisar na tabela responsavelpeloprontuario
                if (verificavazio == null || verificavazio.trim().equals("")) {
                    ArrayList<PesquisarProntuario> ListarPesquisaAux= new ArrayList<>();
                    ListarPesquisaAux = PesquisaResponsavelProtuario(Integer.toString(rs.getInt("pront_responsavel_prontuario")));
                    pesquisarsmt.setNome(ListarPesquisaAux.get(0).getNome());
                    pesquisarsmt.setTelefone(ListarPesquisaAux.get(0).getTelefone());
                    pesquisarsmt.setTelefoneFixo(ListarPesquisaAux.get(0).getTelefoneFixo());
                    pesquisarsmt.setResponsavelProntuario(ListarPesquisaAux.get(0).getIdResponsavel());
                    pesquisarsmt.setNomeProfessor(ListarPesquisaAux.get(0).getNomeProfessor());
                    pesquisarsmt.setCelularProfessor(ListarPesquisaAux.get(0).getCelularProfessor());
                    pesquisarsmt.setTelefoneProfessor(ListarPesquisaAux.get(0).getTelefoneProfessor());
                  
                };
                ListarPesquisa.add(pesquisarsmt);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
     private ArrayList<PesquisarProntuarioStatico> PesquisarProntuarioRg(String rg) throws ClassNotFoundException {
         //A diferença desta função para a de cima é que ela retorna o id do responsavel
       String sql = "SELECT pront_Numero,pront_AlunoEmprestado,pront_TelefoneAluno,pront_Status,"
               + "pront_responsavel_prontuario, paciente.pac_Nome, pront_Informacoes "
                + "FROM prontuario join paciente where paciente.pac_RG=referencia_RG_PAC and prontuario.referencia_RG_PAC=?;";
        Connection con = null;
        ArrayList<PesquisarProntuarioStatico> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<PesquisarProntuarioStatico>();//criando novo array
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rg);
            ResultSet rs = smt.executeQuery();
            PesquisarProntuarioStatico pesquisarsmt = new PesquisarProntuarioStatico();
            while (rs.next()) {
                String verificavazio = "";
                pesquisarsmt.setProntuario(rs.getString("pront_Numero"));
                pesquisarsmt.setNome(rs.getString("pront_AlunoEmprestado"));
                pesquisarsmt.setTelefone(rs.getString("pront_TelefoneAluno"));
                pesquisarsmt.setPaciente(rs.getString("paciente.pac_Nome"));//Para não ter que criar um novo campo utilizei pai para receber o nome do paciente
                pesquisarsmt.setStatus(rs.getInt("pront_Status"));
                pesquisarsmt.setInformacoes(rs.getString("pront_Informacoes"));
                

                //Caso o nome do aluno esteja vazio ele será preenchido no if abaixo
                verificavazio = rs.getString("pront_AlunoEmprestado");//se o nome estiver vazio será chamado a função para pesquisar na tabela responsavelpeloprontuario
                if (verificavazio == null || verificavazio.trim().equals("")) {
                    ArrayList<PesquisarProntuario> ListarPesquisaAux= new ArrayList<>();
                    ListarPesquisaAux = PesquisaResponsavelProtuario(Integer.toString(rs.getInt("pront_responsavel_prontuario")));
                    pesquisarsmt.setNome(ListarPesquisaAux.get(0).getNome());
                    pesquisarsmt.setTelefone(ListarPesquisaAux.get(0).getTelefone());
                    pesquisarsmt.setTelefoneFixo(ListarPesquisaAux.get(0).getTelefoneFixo());
                    pesquisarsmt.setResponsavelProntuario(ListarPesquisaAux.get(0).getIdResponsavel());
                    pesquisarsmt.setNomeProfessor(ListarPesquisaAux.get(0).getNomeProfessor());
                    pesquisarsmt.setCelularProfessor(ListarPesquisaAux.get(0).getCelularProfessor());
                    pesquisarsmt.setTelefoneProfessor(ListarPesquisaAux.get(0).getTelefoneProfessor());
                    
                  
                };
                ListarPesquisa.add(pesquisarsmt);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }

    public boolean ReservarProntuario(String prontuario, String idResponsavel, Date datainicio, Date dataFim) throws ClassNotFoundException {
        Connection con = null;
        boolean retorno = false;
        String sql = "UPDATE `prontuario` SET `pront_Status`=2, `pront_AlunoEmprestado`='',`pront_TelefoneAluno`='',"
                + "`pront_responsavel_prontuario`=?,"
                + "`reservadopara_Prontuario`=?, `DataEmprestimo_Prontuario`=?, "
                + "`DataDevolver_Prontuario`=?\n"
                + "WHERE `pront_cod`=?";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, idResponsavel);
            smt.setString(2, idResponsavel);
            smt.setDate(3, datainicio);
            smt.setDate(4, dataFim);
            smt.setString(5, prontuario);
            smt.executeUpdate();
            System.out.println("executou");
            retorno = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível reservar no Banco de dados os dados o prontuário ,Se o erro persistir contace o suporte.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;

    }
        public boolean VerificaProntuariosAtrasados(String idResponsavel) throws ClassNotFoundException, SQLException {
        String sql = "SELECT "
                + "`pront_cod`, `pront_Numero`, "
                + "`referencia_RG_PAC`, `pront_Status`, "
                + "`pront_AlunoEmprestado`, `pront_TelefoneAluno`, "
                + "`pront_Informacoes`, `pront_responsavel_prontuario`, "
                + "`reservadopara_Prontuario`, `DataEmprestimo_Prontuario`, "
                + "`DataDevolver_Prontuario` "
                + "FROM `prontuario` join responsavelpeloprontuario\n"
                + "on responsavelpeloprontuario.Id=prontuario.pront_responsavel_prontuario\n"
                + "WHERE `DataDevolver_Prontuario`>now()"
                + " AND `pront_Status`=1"
                + "And responsavelpeloprontuario.Id=?";
        Connection con = null;
        con = getConnection();
        PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
        smt.setString(1, idResponsavel);
        ResultSet rs = smt.executeQuery();
        if (rs == null) {
            System.out.println("usuario nao tem nada atrasado");
        } else {
            System.out.println("usuario tem trem atrasado");
        }
        return true;

    }
            public int CancelaReservaProntuario(int codigoProntuario) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE "
                + "`prontuario` "
                + "SET `pront_Status`=0,"
                + "`reservadopara_Prontuario`=0,"
                + "`DataEmprestimo_Prontuario`=NULL,"
                + "`DataDevolver_Prontuario`=NULL "
                + "WHERE `pront_cod`=?";
        Connection con = null;
        con = getConnection();
        PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
        smt.setInt(1, codigoProntuario);
        int retorno = smt.executeUpdate();
        return retorno;
    }

//Anamnese ou doencas----------------------------------------------------------------------------------------------------------------------------------------------
    public boolean GravaAnamnese(ArrayList<String> AnamneseList, int[] AnamneseVetor) throws ClassNotFoundException {

        Connection con = null;
        String sql2 = "insert into saude(\n"
                + "                  `sa_QueixaPrincipal`, `sa_Doenca`, `sa_QuaisDoencas`, `sa_TratamentoMedico`, `sa_QuaisTratamentosMedicos`, \n"
                + "                 `sa_Medicacao`, `sa_QuaisMedicacoes`, `sa_Alergia`, `sa_QuaisAlergias`, `sa_Operado`, `sa_QuaisOperacoes`, `sa_ProblemaAnestesia`, \n"
                + "                 `sa_ProblemaHemorragia`, `sa_ProblemaCicatrizacao`, `sa_Gravidez`, `sa_ReferenciaRG`)\n"
                + "                 values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql2);

            smt.setString(1, AnamneseList.get(0));//principal Queixa
            smt.setInt(2, AnamneseVetor[0]);//doença
            smt.setString(3, AnamneseList.get(1));//descrição da doença
            smt.setInt(4, AnamneseVetor[1]);//Tratamento medico
            smt.setString(5, AnamneseList.get(2));//tratamento medico descrição
            smt.setInt(6, AnamneseVetor[2]);//medicação
            smt.setString(7, AnamneseList.get(4));//descrição da medicação
            smt.setInt(8, AnamneseVetor[3]);//Alergia
            smt.setString(9, AnamneseList.get(5));//descrição da alergia
            smt.setInt(10, AnamneseVetor[4]);//operado
            smt.setString(11, AnamneseList.get(6));//descrição da operação
            smt.setInt(12, AnamneseVetor[8]);//anestesia
            smt.setInt(13, AnamneseVetor[5]);//hemorragia
            smt.setInt(14, AnamneseVetor[6]);//cicatrização
            smt.setInt(15, AnamneseVetor[7]);//Gravidez
            smt.setString(16, AnamneseList.get(8));//RG
            smt.execute();
            System.out.println("Gravou A Anamnese");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados as informações da tela de cadastro de prontuario , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;
    }
     public boolean NovaAnamnese(ArrayList<String> AnamneseList, int[] AnamneseVetor) throws ClassNotFoundException {
//Esta função será chamada quando o paciente receber um novo tratamento (ou seja um tratamento ja´tera sido encerrado)
        Connection con = null;
        String sql2 = "UPDATE `saude` SET `sa_QueixaPrincipal`=?,`sa_Doenca`=?,`sa_QuaisDoencas`=?,\n" +
"`sa_TratamentoMedico`=?,`sa_QuaisTratamentosMedicos`=?,`sa_Medicacao`=?,`sa_QuaisMedicacoes`=?,`sa_Alergia`=?,`sa_QuaisAlergias`=?,\n" +
"`sa_Operado`=?,`sa_QuaisOperacoes`=?,`sa_ProblemaAnestesia`=?,`sa_ProblemaHemorragia`=?,\n" +
"`sa_ProblemaCicatrizacao`=?,`sa_Gravidez`=?\n" +
" WHERE `sa_ReferenciaRG`=?";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql2);

            smt.setString(1, AnamneseList.get(0));//principal Queixa
            smt.setInt(2, AnamneseVetor[0]);//doença
            smt.setString(3, AnamneseList.get(1));//descrição da doença
            smt.setInt(4, AnamneseVetor[1]);//Tratamento medico
            smt.setString(5, AnamneseList.get(2));//tratamento medico descrição
            smt.setInt(6, AnamneseVetor[2]);//medicação
            smt.setString(7, AnamneseList.get(4));//descrição da medicação
            smt.setInt(8, AnamneseVetor[3]);//Alergia
            smt.setString(9, AnamneseList.get(5));//descrição da alergia
            smt.setInt(10, AnamneseVetor[4]);//operado
            smt.setString(11, AnamneseList.get(6));//descrição da operação
            smt.setInt(12, AnamneseVetor[8]);//anestesia
            smt.setInt(13, AnamneseVetor[5]);//hemorragia
            smt.setInt(14, AnamneseVetor[6]);//cicatrização
            smt.setInt(15, AnamneseVetor[7]);//Gravidez
            smt.setString(16, AnamneseList.get(8));//RG
            smt.executeUpdate();
            System.out.println("Gravou A Anamnese");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados as informações da tela de cadastro de prontuario , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;
    }//fim da atualização Anamenese
      public ArrayList<DoencasSistemicas> DoencasdoPaciente(String iDpaciente) throws ClassNotFoundException {
        Connection con = null;
        boolean retorno = false;
        ArrayList<sctp2.ClassesdeControle.DoencasSistemicas> ListarPesquisa;
        ListarPesquisa = new ArrayList<sctp2.ClassesdeControle.DoencasSistemicas>();//criando novo array
        String sql = "SELECT `cod_pac_DoeSist`, `doe_FebreReumatica`, `doe_ProblemasRenais`, `doe_ProblemasRespiratorios`,"
                + " `doe_ProblemasArtReumatismo`, `doe_HipertensaoArterial`, `doe_ProblemasCardiacos`, `doe_ProblemasGastricos`,"
                + " `doe_ProblemasAlergicos`, `doe_Diabetes`, `doe_ReferenciaPaciente` FROM `doesist` WHERE "
                + "`doe_ReferenciaPaciente` =?";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, iDpaciente);
            ResultSet rs = smt.executeQuery();
            sctp2.ClassesdeControle.DoencasSistemicas pesquisarsmt = new sctp2.ClassesdeControle.DoencasSistemicas();
            while (rs.next()) {
                pesquisarsmt.setFebreReumatica(rs.getBoolean("doe_FebreReumatica"));
                pesquisarsmt.setProblemasRenais(rs.getBoolean("doe_ProblemasRenais"));
                pesquisarsmt.setProblemasRespiratorios(rs.getBoolean("doe_ProblemasRespiratorios"));
                pesquisarsmt.setReumatismo(rs.getBoolean("doe_ProblemasArtReumatismo"));
                pesquisarsmt.setDiabetes(rs.getBoolean("doe_Diabetes"));
                pesquisarsmt.setHipertensaoArterial(rs.getBoolean("doe_HipertensaoArterial"));
                pesquisarsmt.setProblemasCardiacos(rs.getBoolean("doe_ProblemasCardiacos"));
                pesquisarsmt.setProblemasGastricos(rs.getBoolean("doe_ProblemasGastricos"));
                pesquisarsmt.setProblemasAlergicos(rs.getBoolean("doe_ProblemasAlergicos"));
            };
            ListarPesquisa.add(pesquisarsmt);
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
      public ArrayList<Anamnese> ListarAnamnese(String rg) throws ClassNotFoundException, SQLException {
        Connection con = null;
        boolean retorno = false;
        ArrayList<sctp2.ClassesdeControle.Anamnese> ListarPesquisa;
        ListarPesquisa = new ArrayList<sctp2.ClassesdeControle.Anamnese>();//criando novo array
        String sql = "SELECT `cod_pac_sa`, `sa_QueixaPrincipal`, `sa_Doenca`, `sa_QuaisDoencas`, `sa_TratamentoMedico`,"
                + " `sa_Medicacao`, `sa_QuaisMedicacoes`, `sa_Alergia`, `sa_QuaisAlergias`, `sa_Operado`, `sa_QuaisOperacoes`, "
                + "`sa_ProblemaAnestesia`, `sa_ProblemaHemorragia`, `sa_PesoSaude`, `sa_ProblemaCicatrizacao`, `sa_Gravidez`, "
                + "`sa_QuaisTratamentosMedicos`, `sa_ReferenciaRG` FROM `saude` WHERE `sa_ReferenciaRG`=?";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rg);
            ResultSet rs = smt.executeQuery();
            sctp2.ClassesdeControle.Anamnese pesquisarsmt = new sctp2.ClassesdeControle.Anamnese();
            while (rs.next()) {
                pesquisarsmt.setPrincipalQueixa(rs.getString("sa_QueixaPrincipal"));
                pesquisarsmt.setSofreAlgumaDoenca(rs.getBoolean("sa_Doenca"));
                pesquisarsmt.setSofreAlgumaDoencaDescricao(rs.getString("sa_QuaisDoencas"));
                pesquisarsmt.setEmTratamentoMedico(rs.getBoolean("sa_TratamentoMedico"));
                pesquisarsmt.setEmTratamentoMedicoDescricao(rs.getString("sa_QuaisTratamentosMedicos"));
                pesquisarsmt.setUsoDeAlgumaMedicacao(rs.getBoolean("sa_Medicacao"));
                pesquisarsmt.setUsoDeAlgumaMedicacaoDescricao(rs.getString("sa_QuaisMedicacoes"));
                pesquisarsmt.setPossuiAlergias(rs.getBoolean("sa_Alergia"));
                pesquisarsmt.setPossuiAlergiasDescricao(rs.getString("sa_QuaisAlergias"));
                pesquisarsmt.setJaFoiOperado(rs.getBoolean("sa_Operado"));
                pesquisarsmt.setJaFoiOperadoDescricao(rs.getString("sa_QuaisOperacoes"));
                pesquisarsmt.setProblemasComCicatrizacao(rs.getBoolean("sa_ProblemaCicatrizacao"));
                pesquisarsmt.setGravidez(rs.getBoolean("sa_Gravidez"));
                pesquisarsmt.setProblemasComAnestesia(rs.getBoolean("sa_ProblemaAnestesia"));
                pesquisarsmt.setProblemasComHemorragia(rs.getBoolean("sa_ProblemaHemorragia"));
            };
            ListarPesquisa.add(pesquisarsmt);

        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
//Doencas Sistemicas-------------------------------------------------------------------------------------------------------------------------------
    public boolean GravaDoencasSistemicas(int[] vetorDoencas, String RG) throws SQLException, ClassNotFoundException {
        Connection con = null;

        String sql = ("INSERT INTO doesist (doe_FebreReumatica"
                + ",doe_ProblemasRenais, doe_ProblemasRespiratorios,doe_ProblemasArtReumatismo"
                + ",doe_Diabetes,doe_HipertensaoArterial,doe_ProblemasCardiacos,doe_ProblemasGastricos"
                + ",doe_ProblemasAlergicos,doe_ReferenciaPaciente) "
                + "values(?,?,?,?,?,?,?,?,?,?)");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);

            smt.setInt(1, vetorDoencas[0]);
            smt.setInt(2, vetorDoencas[1]);
            smt.setInt(3, vetorDoencas[2]);
            smt.setInt(4, vetorDoencas[3]);
            smt.setInt(5, vetorDoencas[4]);
            smt.setInt(6, vetorDoencas[5]);
            smt.setInt(7, vetorDoencas[6]);
            smt.setInt(8, vetorDoencas[7]);
            smt.setInt(9, vetorDoencas[8]);
            smt.setString(10, RG);
            smt.execute();
            System.out.println("Gravou Doenças Sistemicas");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados as informações da tela de cadastro Doenças Sistêmicas , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return true;
    }
//Habitos-------------------------------------------------------------------------------------------------------------
    public boolean GravaHabitos(int[] HabitosTabacoVetor, int[] HabitosAlcoolVetor, String rg) throws ClassNotFoundException {
        Connection con = null;

        String sql = ("INSERT INTO habitos (hab_Fuma, hab_FumaQuantoTempo, hab_NumeroCigarros"
                + ",hab_TipoCigarro,hab_Alcool,hab_BebeQuantoTempo,hab_FrequenciaBebida,hab_TipoBebida"
                + ",hab_referencia_RG)"
                + "values(?,?,?,?,?,?,?,?,?)");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, HabitosTabacoVetor[0]);
            smt.setInt(2, HabitosTabacoVetor[1]);
            smt.setInt(3, HabitosTabacoVetor[2]);
            smt.setInt(4, HabitosTabacoVetor[3]);
            smt.setInt(5, HabitosAlcoolVetor[0]);
            smt.setInt(6, HabitosAlcoolVetor[1]);
            smt.setInt(7, HabitosAlcoolVetor[2]);
            smt.setInt(8, HabitosAlcoolVetor[3]);
            smt.setString(9, rg);
            smt.execute();
            System.out.println("Gravou Habitos");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados as informações da tela de cadastro Habitos , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return true;
    }
    public ArrayList<Habitos> ListarHabitosCigarro(String iDpaciente) throws ClassNotFoundException, SQLException {
        Connection con = null;
        boolean retorno = false;
        ArrayList<sctp2.ClassesdeControle.Habitos> ListarPesquisa;
        ListarPesquisa = new ArrayList<sctp2.ClassesdeControle.Habitos>();//criando novo array
        String sql = "SELECT `cod_pac_habitos`, `hab_Fuma`, `hab_FumaQuantoTempo`, `hab_NumeroCigarros`,"
                + " `hab_TipoCigarro`, `hab_Alcool`, `hab_BebeQuantoTempo`, `hab_TipoBebida`, `hab_FrequenciaBebida`, "
                + "`hab_PesoHabito`, `hab_referencia_RG` FROM `habitos` WHERE `hab_referencia_RG`=?;";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, iDpaciente);
            ResultSet rs = smt.executeQuery();
            sctp2.ClassesdeControle.Habitos pesquisarsmt = new sctp2.ClassesdeControle.Habitos();
            while (rs.next()) {
                pesquisarsmt.setFuma(rs.getInt("hab_Fuma"));
                pesquisarsmt.setFumaHaQuantoTempo(rs.getInt("hab_FumaQuantoTempo"));
                pesquisarsmt.setFumoQuantidadePorDia(rs.getInt("hab_NumeroCigarros"));
                pesquisarsmt.setFumoTipodeCigarro(rs.getInt("hab_TipoCigarro"));

            };

            ListarPesquisa.add(pesquisarsmt);
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
    
    public ArrayList<Habitos> ListarHabitosBebida(String iDpaciente) throws ClassNotFoundException {
        Connection con = null;
        boolean retorno = false;
        ArrayList<sctp2.ClassesdeControle.Habitos> ListarPesquisa;
        ListarPesquisa = new ArrayList<sctp2.ClassesdeControle.Habitos>();//criando novo array
        String sql = "SELECT  `hab_Alcool`, `hab_BebeQuantoTempo`, `hab_TipoBebida`, `hab_FrequenciaBebida`, "
                + "`hab_PesoHabito`, `hab_referencia_RG` FROM `habitos` WHERE `hab_referencia_RG`=?;";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, iDpaciente);
            ResultSet rs = smt.executeQuery();
            sctp2.ClassesdeControle.Habitos pesquisarsmt = new sctp2.ClassesdeControle.Habitos();
            while (rs.next()) {
                pesquisarsmt.setBebidaAlcolica(rs.getInt("hab_Alcool"));
                pesquisarsmt.setBebidaAlcoolicaHaQuantoTempo(rs.getInt("hab_BebeQuantoTempo"));
                pesquisarsmt.setBebidaAlcoolicaTipodeBebida(rs.getInt("hab_TipoBebida"));
                pesquisarsmt.setBebidaAlcoolicaFrequencia(rs.getInt("hab_FrequenciaBebida"));

            };

            ListarPesquisa.add(pesquisarsmt);
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }

//Tratamentos Necessarios------------------------------------------------------------------------------------------------------------------
    public boolean GravaTratamentoNecessario(int[] tratamentosNVetor, String rg) throws ClassNotFoundException {
        Connection con = null;

        String sql = ("insert into necessidade(\n"
                + "`inicioTratamento_Necessidade`,"
                + "`nec_Amalgama`,"
                + "`nec_CirurgiaPeriodontal`,"
                + "`nec_CoroaTotal`,"
                + "`nec_DTM`,"
                + "`nec_EndodontiaBirradicular`,\n"
                + "`nec_Endodontiauniebirradicular`,`nec_EndodontiaTrirradicular`,`nec_Estomatologia`,`nec_ExodontiaMolar`,\n"
                + "`nec_ExodontiaIncluso`,`nec_ProfilaxiaSimples`,`nec_PonteFixa3Elementos`,`nec_Pontefixa4elementos`,\n"
                + "`nec_Pontefixamaisque4elementos`,`nec_Protese`,`nec_ProteseTotalPar`,`nec_ProtesePPR`,`nec_PPR`,\n"
                + "`nec_RaspagemPolimentoCoronario`,`nec_Resina`,`nec_RMF`,\n"
                + "`nec_TerapiaPeriodontal`,nec_ExodontiaSimples,`nec_PonteFixa`,`nec_PonteFixaMaisQueTresElementos`,"
                + "`nec_RaspagemSub`, `nec_RaspagemSupra`,`nec_referencia_rg`)"
                + "values(CURDATE(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, tratamentosNVetor[0]);
            smt.setInt(2, tratamentosNVetor[1]);
            smt.setInt(3, tratamentosNVetor[2]);
            smt.setInt(4, tratamentosNVetor[3]);
            smt.setInt(5, tratamentosNVetor[4]);
            smt.setInt(6, tratamentosNVetor[5]);
            smt.setInt(7, tratamentosNVetor[6]);
            smt.setInt(8, tratamentosNVetor[7]);
            smt.setInt(9, tratamentosNVetor[8]);
            smt.setInt(10, tratamentosNVetor[9]);
            smt.setInt(11, tratamentosNVetor[10]);
            smt.setInt(12, tratamentosNVetor[11]);
            smt.setInt(13, tratamentosNVetor[12]);
            smt.setInt(14, tratamentosNVetor[13]);
            smt.setInt(15, tratamentosNVetor[14]);
            smt.setInt(16, tratamentosNVetor[15]);
            smt.setInt(17, tratamentosNVetor[16]);
            smt.setInt(18, tratamentosNVetor[17]);
            smt.setInt(19, tratamentosNVetor[18]);
            smt.setInt(20, tratamentosNVetor[19]);
            smt.setInt(21, tratamentosNVetor[20]);
            smt.setInt(22, tratamentosNVetor[21]);
            smt.setInt(23, tratamentosNVetor[22]);
            smt.setInt(24, tratamentosNVetor[23]);
            smt.setInt(25, tratamentosNVetor[24]);
            smt.setInt(26, tratamentosNVetor[25]);
            smt.setInt(27, tratamentosNVetor[26]);
            smt.setString(28, rg);
            smt.execute();
            System.out.println("Gravou Tratamento");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados as informações da tela de cadastro de tratamentos necessarios , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return true;
    }
     public int NovoTratamentoNecessario(String[] tratamentosNVetor, String rg) throws ClassNotFoundException {
        Connection con = null;
        int retorno = 0;
        //------------Criando o sql dinâmicamente-----------------------------------------------------------------
        String sql = "UPDATE `necessidade` SET `inicioTratamento_Necessidade`= CURRENT_DATE() ";
        for (int i = 0; i < tratamentosNVetor.length; i++) {
            if (tratamentosNVetor[i] != null) {
                
                sql = sql + " ," + tratamentosNVetor[i] + "=1";
            }
        }
        sql = sql + " where `nec_referencia_rg`='" + rg + "';";
        //-------------------------fim da criação do SQL--------------------------------------------
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            retorno= smt.executeUpdate();
            System.out.println("Atualizou Tratamento");
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados as informações da tela de cadastro de tratamentos necessarios , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
       if(retorno>0) return 1;
       else
           return 0;
    }
       public ArrayList<Pesquisar> PesquisarNecessidade(String necessidade, boolean selected) throws ClassNotFoundException {
        String sql = "SELECT pac_Cod,pac_Nome,pac_Telefone ,pac_listaNegra FROM paciente  join necessidade where " + necessidade + "=1 and necessidade.nec_referencia_rg=paciente.pac_RG and pac_listaNegra=0  ;";
        String sqlComListaNegra = "SELECT pac_Cod,pac_Nome,pac_Telefone ,pac_listaNegra FROM paciente  join necessidade where " + necessidade + "=1 and necessidade.nec_referencia_rg=paciente.pac_RG ;";
        if (selected == true) {
            sql = sqlComListaNegra;
        }
        Connection con = null;
        ArrayList<Pesquisar> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<Pesquisar>();//criando novo array
        System.out.println(sql);
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = smt.executeQuery();//efetuando a busca
            while (rs.next()) {
                Pesquisar pesquisarsmt = new Pesquisar();
                pesquisarsmt.setCodigo(rs.getInt("pac_Cod"));
                pesquisarsmt.setNome(rs.getString("pac_Nome"));
                pesquisarsmt.setTelefone(rs.getString("pac_Telefone"));
                pesquisarsmt.setListaNegra(rs.getInt("pac_listaNegra"));
                ListarPesquisa.add(pesquisarsmt);
            }
        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
       public ArrayList<TratamentosNecessarios> NecessidadesdoPaciente(String iDpaciente) throws ClassNotFoundException, SQLException {
        Connection con = null;
        boolean retorno = false;
        ArrayList<sctp2.ClassesdeControle.TratamentosNecessarios> ListarPesquisa;
        ListarPesquisa = new ArrayList<sctp2.ClassesdeControle.TratamentosNecessarios>();//criando novo array
        String sql = "SELECT `nec_Cod`, `nec_ProfilaxiaSimples`, `nec_referencia_rg`, `nec_RaspagemPolimentoCoronario`, "
                + "`nec_CirurgiaPeriodontal`, `nec_ExodontiaSimples`, `nec_ExodontiaMolar`, `nec_ExodontiaIncluso`, `nec_Amalgama`,"
                + " `nec_Resina`, `nec_RMF`, `nec_Endodontiauniebirradicular`, `nec_EndodontiaTrirradicular`, `nec_CoroaTotal`, "
                + "`nec_PonteFixa3Elementos`, `nec_Pontefixa4elementos`, `nec_Pontefixamaisque4elementos`, `nec_PPR`, `nec_ProteseTotalPar`, "
                + "`nec_ProtesePPR`, `nec_Protese`, `nec_TerapiaPeriodontal`, `nec_EndodontiaBirradicular`, `nec_DTM`, `nec_Estomatologia`,"
                + "`nec_PonteFixa`,`nec_PonteFixaMaisQueTresElementos`,`nec_RaspagemSub`, `nec_RaspagemSupra` "
                + "FROM `necessidade` WHERE  nec_referencia_rg=?;";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, iDpaciente);
            ResultSet rs = smt.executeQuery();
            sctp2.ClassesdeControle.TratamentosNecessarios pesquisarsmt = new sctp2.ClassesdeControle.TratamentosNecessarios();
            while (rs.next()) {
                pesquisarsmt.setCodigo(rs.getInt("nec_Cod"));
                pesquisarsmt.setProfilaxiaSimples(rs.getInt("nec_ProfilaxiaSimples"));
                pesquisarsmt.setRg(rs.getString("nec_referencia_rg"));
                pesquisarsmt.setRaspagemEPoliCCoronario(rs.getInt("nec_RaspagemPolimentoCoronario"));
                pesquisarsmt.setCirugiaPeridontal(rs.getInt("nec_CirurgiaPeriodontal"));
                pesquisarsmt.setExodontiaSimples(rs.getInt("nec_ExodontiaSimples"));
                pesquisarsmt.setExodontia3Molar(rs.getInt("nec_ExodontiaMolar"));
                pesquisarsmt.setExodontiaIncluso(rs.getInt("nec_ExodontiaIncluso"));
                pesquisarsmt.setAmalgama(rs.getInt("nec_Amalgama"));
                pesquisarsmt.setResina(rs.getInt("nec_Resina"));
                pesquisarsmt.setRmf(rs.getInt("nec_RMF"));
                pesquisarsmt.setEndontiaUnirradicular(rs.getInt("nec_EndodontiaTrirradicular"));
                pesquisarsmt.setEndodontiaTrirradicular(rs.getInt("nec_EndodontiaTrirradicular"));
                pesquisarsmt.setCoroaTotal(rs.getInt("nec_CoroaTotal"));
                pesquisarsmt.setPonteFixa3Elementos(rs.getInt("nec_PonteFixa3Elementos"));
                pesquisarsmt.setPonteFixa4Elementos(rs.getInt("nec_Pontefixa4elementos"));
                pesquisarsmt.setPonteFixaMaisQue4Elementos(rs.getInt("nec_Pontefixamaisque4elementos"));
                pesquisarsmt.setPpr(rs.getInt("nec_PPR"));
                pesquisarsmt.setProtesetotal(rs.getInt("nec_ProteseTotalPar"));
                pesquisarsmt.setProtese_ppr(rs.getInt("nec_ProtesePPR"));
                pesquisarsmt.setProtese(rs.getInt("nec_Protese"));
                pesquisarsmt.setTerrapiaOeriodDeSuporte(rs.getInt("nec_TerapiaPeriodontal"));
                pesquisarsmt.setPonteFixa(rs.getInt("nec_PonteFixa"));
                pesquisarsmt.setPonteFixaMaisQueTresElementos(rs.getInt("nec_PonteFixaMaisQueTresElementos"));
                pesquisarsmt.setRaspagemSub(rs.getInt("nec_RaspagemSub"));
                pesquisarsmt.setRaspagemSupra(rs.getInt("nec_RaspagemSupra"));
            };
            ListarPesquisa.add(pesquisarsmt);
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }

        return ListarPesquisa;
    }
//ResponsavelProntuario----------------------------------------------------------------------------------------------------------------------
    public int GravaresponsavelProntuario(String nome, String celular, String telefonefixo,String nomeProfessor, String telefoneFixoProfessor, String celularProfessor) throws ClassNotFoundException {
        Connection con = null;
        int id = 0;
        ResultSet rs;

        String sql = ("INSERT INTO `responsavelpeloprontuario`(`nome_ResponsavelPeloProntuario`, `celular_ResponsavelPeloProntuario`, "
                + "`telefoneFixo_ResponsavelPeloProntuario`, `nomeProfessor_ResponsavelPeloProntuario`, `TelefoneProfessor_ResponsavelPeloProntuario`, "
                + "`celularProfessor_ResponsavelPeloProntuario`) VALUES (?,?,?,?,?,?)");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, nome);
            smt.setString(2, celular);
            smt.setString(3, telefonefixo);
            smt.setString(4, nomeProfessor);
            smt.setString(5, telefoneFixoProfessor);
            smt.setString(6, celularProfessor);
            smt.execute();
            retorno = true;
            System.out.println("Gravou Aluno/Responsavel");
            //------------a partir daqui a é feita uma pesquisa para saber qual o ID do responsavel que foi gravado----------------------------------------
            sql = "select id from responsavelpeloprontuario where nome_ResponsavelPeloProntuario=? and celular_ResponsavelPeloProntuario=?;";
            PreparedStatement smt2 = (PreparedStatement) con.prepareStatement(sql);
            smt2.setString(1, nome);
            smt2.setString(2, celular);
            rs = smt2.executeQuery();
            System.out.println("chegou aqui");
            while (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados as informações da tela de cadastro de Aluno/Responsável , tente novamente em breve.");
            e.printStackTrace();
            retorno = false;
        } finally {
            closeConnection(con);
        }
        return id;
    }

    public boolean AtualizaresponsavelProntuario(String nome, String celular, String telefonefixo, String nomeProfessor, String celularProfessor, String telefoneProfessor,int id) throws ClassNotFoundException {
        Connection con = null;
        String sql = ("UPDATE `"
                + "responsavelpeloprontuario` SET `nome_ResponsavelPeloProntuario`=?,`celular_ResponsavelPeloProntuario`=?,`"
                + "telefoneFixo_ResponsavelPeloProntuario`=?,`nomeProfessor_ResponsavelPeloProntuario`=?,"
                + "`TelefoneProfessor_ResponsavelPeloProntuario`=?,`celularProfessor_ResponsavelPeloProntuario`=? WHERE `Id`=?");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, nome);
            smt.setString(2, celular);
            smt.setString(3, telefonefixo);
            smt.setString(4, nomeProfessor);
            smt.setString(5, telefoneProfessor);            
            smt.setString(6, celularProfessor);
            smt.setInt(7, id);
            smt.executeUpdate();
            retorno = true;
            System.out.println("Atualizou Aluno/Responsavel");
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return retorno;

    }//final da classe conexao

    public boolean ExcluiresponsavelProntuario(int idDoResponsavel) throws ClassNotFoundException {
        Connection con = null;
        String sql = ("delete from   responsavelpeloprontuario where id=?;");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, idDoResponsavel);
            smt.execute();
            retorno = true;
            System.out.println("Excluiu Aluno/Responsavel");
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return retorno;
    }
    public ArrayList<Pesquisar> PesquisarResponsavelProntuario(String text, int tipo) throws ClassNotFoundException {
        String sql = "SELECT `Id`, `nome_ResponsavelPeloProntuario`, `celular_ResponsavelPeloProntuario`, "
                + "        `telefoneFixo_ResponsavelPeloProntuario`, `nomeProfessor_ResponsavelPeloProntuario`, "
                + "         `TelefoneProfessor_ResponsavelPeloProntuario`, `celularProfessor_ResponsavelPeloProntuario` "
                + "FROM `responsavelpeloprontuario` WHERE nome_ResponsavelPeloProntuario LIKE ?;";
        Connection con = null;
        ArrayList<Pesquisar> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<>();//criando novo array
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, "%" + text + "%");
            ResultSet rs = smt.executeQuery();//efetuando a busca
            while (rs.next()) {
                Pesquisar pesquisarsmt = new Pesquisar();
                pesquisarsmt.setCodigo(rs.getInt("id"));
                pesquisarsmt.setNome(rs.getString("nome_ResponsavelPeloProntuario"));
                pesquisarsmt.setTelefone(rs.getString("celular_ResponsavelPeloProntuario"));
                pesquisarsmt.setTelefonefixo(rs.getString("telefoneFixo_ResponsavelPeloProntuario"));
                pesquisarsmt.setNomeProfessor(rs.getString("nomeProfessor_ResponsavelPeloProntuario"));
                pesquisarsmt.setTelefoneFixoProfessor(rs.getString("TelefoneProfessor_ResponsavelPeloProntuario"));
                pesquisarsmt.setCelularProfessor(rs.getString("celularProfessor_ResponsavelPeloProntuario"));
                ListarPesquisa.add(pesquisarsmt);
            }

        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();

        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
    public ArrayList<Pesquisar> PesquisarResponsavelProntuario(int id) throws ClassNotFoundException {
        String sql = "SELECT `Id`, `nome_ResponsavelPeloProntuario`, `celular_ResponsavelPeloProntuario`, "
                + "        `telefoneFixo_ResponsavelPeloProntuario`, `nomeProfessor_ResponsavelPeloProntuario`, "
                + "         `TelefoneProfessor_ResponsavelPeloProntuario`, `celularProfessor_ResponsavelPeloProntuario` "
                + "FROM `responsavelpeloprontuario` WHERE Id= ?;";
        Connection con = null;
        ArrayList<Pesquisar> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<>();//criando novo array
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, id);
            ResultSet rs = smt.executeQuery();//efetuando a busca
            while (rs.next()) {
                Pesquisar pesquisarsmt = new Pesquisar();
                pesquisarsmt.setCodigo(rs.getInt("id"));
                pesquisarsmt.setNome(rs.getString("nome_ResponsavelPeloProntuario"));
                pesquisarsmt.setTelefone(rs.getString("celular_ResponsavelPeloProntuario"));
                pesquisarsmt.setTelefonefixo(rs.getString("telefoneFixo_ResponsavelPeloProntuario"));
                pesquisarsmt.setNomeProfessor(rs.getString("nomeProfessor_ResponsavelPeloProntuario"));
                pesquisarsmt.setTelefoneFixoProfessor(rs.getString("TelefoneProfessor_ResponsavelPeloProntuario"));
                pesquisarsmt.setCelularProfessor(rs.getString("celularProfessor_ResponsavelPeloProntuario"));
                ListarPesquisa.add(pesquisarsmt);
            }

        } catch (SQLException e) {
            //System.out.println("Ocorreu um erro ao carrega a lista");
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();

        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }
//Cadastro-----------------------------------------------------------------------------------------------------------------
    public boolean ExcluiCadastro(String codigoPaciente) throws ClassNotFoundException {
        Connection con = null;
        retorno = false;
        String sql = ("delete from   paciente where pac_RG=?;");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, codigoPaciente);
            smt.execute();
            retorno = true;//A operação deu certo
            System.out.println("Excluiu Paciente");
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return retorno;
    }
//Paciente----------------------------------------------------------------------------------------------------------------
    public boolean AtualizaPaciente(ArrayList<String> PacienteList, int[] Pacientevetor) throws ClassNotFoundException {
        String sql = ("update  paciente set pac_RG=?, pac_Nome=?, pac_DataNasc=? "
                + ", pac_Naturalidade=?, pac_Sexo=?, pac_Ocupacao=?, pac_Procedencia=?"
                + ", pac_Mae=?,pac_Pai=?, pac_EstadoCivil=?, pac_Conjuge=?, pac_Cidade=?"
                + ", pac_Rua=?, pac_Bairro=?, pac_NumeroCasa=?, pac_Cep=?, pac_Telefone=?, pac_Alta=?"
                + ",pac_listaNegra=?, pac_motivoLista=? where pac_Cod=?;");
        Connection con = null;
        retorno = false;
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, PacienteList.get(0));
            smt.setString(2, PacienteList.get(1));
            smt.setString(3, PacienteList.get(2));
            smt.setString(4, PacienteList.get(3));
            smt.setInt(5, Pacientevetor[0]);//sexo do paciente
            smt.setString(6, PacienteList.get(4));//ocupacao
            smt.setInt(7, Pacientevetor[2]);//procedencia
            smt.setString(8, PacienteList.get(5));
            smt.setString(9, PacienteList.get(6));
            smt.setInt(10, Pacientevetor[1]);
            smt.setString(11, PacienteList.get(7));
            smt.setString(12, PacienteList.get(8));
            smt.setString(13, PacienteList.get(9));
            smt.setString(14, PacienteList.get(10));
            smt.setString(15, PacienteList.get(11));
            smt.setString(16, PacienteList.get(12));
            smt.setString(17, PacienteList.get(13));
            smt.setInt(18, Pacientevetor[3]);//paciente com alta
            smt.setInt(19, Pacientevetor[4]);
            smt.setString(20, PacienteList.get(14));//motivo da lista negra  
            smt.setInt(21, Pacientevetor[5]);
            smt.executeUpdate();
            retorno = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "(2) Não foi possível gravar no Banco de dados os dados do Paciente , tente novamente em breve.");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return retorno;
    }
//Tratamento-----------------------------------------------------------------------------------------------------
    public boolean FinalizaTratamento(ArrayList<String>tratamentos,String rg) throws ClassNotFoundException, SQLException {
    Connection con=null;
    boolean retorno;
    retorno=GravaNoHistoricoNecessidade(rg,tratamentos);
    if(retorno==false){
        String sql="UPDATE `necessidade` SET ";
        for(int i=0;i<tratamentos.size();i++){
            sql=sql+tratamentos.get(i)+"=0";
            if(i<tratamentos.size()-1)sql=sql+",";
        }
        sql=sql+" WHERE `nec_referencia_rg`=?";
        con = getConnection();
        System.out.println("update "+sql);
        int retornoUpdate=0;
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rg);
            retornoUpdate=smt.executeUpdate();
            if(retornoUpdate==0){
                boolean retornoH;
                retornoH=GravaNoHistoricoNecessidade(rg, tratamentos);
                return retornoH;
            }
                    else return false;
        
        
        
    }
        return false;
    }
    public boolean VerificaAlta(String rg) throws ClassNotFoundException, SQLException {
        //Se o paciente terminar todos os tratamentos ele ganha alta.
        Connection con = null,con2=null;
            String sql="SELECT * FROM `necessidade` WHERE \n" +
                                "`nec_ProfilaxiaSimples`=0 AND\n" +
                                "`nec_RaspagemPolimentoCoronario`=0 AND\n" +
                                "`nec_CirurgiaPeriodontal`=0 AND\n" +
                                "`nec_ExodontiaSimples`=0 AND\n" +
                                "`nec_ExodontiaMolar`=0 AND\n" +
                                "`nec_ExodontiaIncluso`=0 AND\n" +
                                "`nec_Amalgama`=0 AND\n" +
                                "`nec_Resina`=0 AND\n" +
                                "`nec_RMF`=0 AND\n" +
                                "`nec_Endodontiauniebirradicular`=0 AND\n" +
                                "`nec_EndodontiaTrirradicular`=0 AND\n" +
                                "`nec_CoroaTotal`=0 AND\n" +
                                "`nec_PonteFixa3Elementos`=0 AND\n" +
                                "`nec_Pontefixa4elementos`=0 AND\n" +
                                "`nec_Pontefixamaisque4elementos`=0 AND\n" +
                                "`nec_PPR`=0 AND\n" +
                                "`nec_ProteseTotalPar`=0 AND\n" +
                                "`nec_ProtesePPR`=0 AND\n" +
                                "`nec_Protese`=0 AND\n" +
                                "`nec_TerapiaPeriodontal`=0 AND\n" +
                                "`nec_EndodontiaBirradicular`=0 AND\n" +
                                "`nec_DTM`=0 AND\n" +
                                "`nec_Estomatologia`=0 AND\n" +
                                "`nec_PonteFixa`=0 AND\n" +
                                "`nec_PonteFixaMaisQueTresElementos`=0 AND\n" +
                                "`nec_RaspagemSub`=0 AND\n" +
                                "`nec_RaspagemSupra`=0 AND\n" +
                                "\n" +
                                "`nec_referencia_rg`=?";
             con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rg);
            ResultSet rs = smt.executeQuery();
            int result=0;
            while(rs.next())result++;
            if(result>0){
                try{
                String sqlAlta="UPDATE `paciente` SET `pac_Alta`=1  WHERE `pac_RG`=?;";
                con2=getConnection();
                PreparedStatement smt2 = (PreparedStatement) con.prepareStatement(sqlAlta);
                smt2.setString(1, rg);
                int resultado= smt2.executeUpdate();
                if(resultado==0)return  true;
                }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Algo deu errado ao Atualizar o status do paciente!");
            }
            }
            System.out.println("resultado da alta: "+result);
            return false;
            
    }
//Historico-----------------------------------------------------------------------------------------------------
    public boolean GravaPacienteNoHistorico(int codigo, String responsavel_tratamento, String rg) throws ClassNotFoundException, SQLException {
        boolean retorno = false;
        Connection con = null;
        Date inicioTratamento = null, fimTratamento = null;
        String queixa = null;
        String sql = "select distinct pac_cod,pac_inicio_tratamento,pac_Fim_tratamento,paciente.pac_RG, "
                + "saude.sa_QueixaPrincipal from paciente join prontuario,responsavelpeloprontuario,saude where \n"
                + "pac_cod=? and  saude.sa_ReferenciaRG=paciente.pac_RG \n"
                + "and prontuario.referencia_RG_PAC=paciente.pac_RG;";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, codigo);
            System.out.println("codigo" + codigo);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                inicioTratamento = rs.getDate("pac_inicio_tratamento");
                fimTratamento = rs.getDate("pac_Fim_tratamento");
                queixa = rs.getString("sa_QueixaPrincipal");
            }
            int codigohistorico = GravaHistoricopaciente(rg, codigo, inicioTratamento, fimTratamento, queixa, responsavel_tratamento);
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return retorno;

    }

    private int NumerodeTratamentosConcluidos(int codigo) throws ClassNotFoundException, SQLException {
        //esta função faz a busca de quantos tratamentos existem para definir qual o numero do tratamento atual
        Connection con = null;
        int retorno = -1;
        String sql = "select count(*) as total from historicodopaciente where CodigoPaciente=?;";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, codigo);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                retorno = rs.getInt("total");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar conectar ao banco de dados!");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        if (retorno == 0) {
            retorno = 1;//primeiro tratamento finalizado
        } else {
            if (retorno > 0) {
                retorno = retorno + 1;//primeiro tratamento finalizado
            }
        }
        return retorno;

    }

    private int GravaHistoricopaciente(String rg, int codigo, Date inicioTratamento, Date fimTratamento, String queixa, String responsavel_tratamento) throws ClassNotFoundException, SQLException {
        //Esta função grava na tabela historicodopaciente os dados do tratamento atual
        Connection con = null;
        int numeroDoTratamento = NumerodeTratamentosConcluidos(codigo);//saber quantos tratamento foram concluidos
        String sql = "INSERT INTO `historicodopaciente`( `CodigoPaciente`,codigotratamento, `Queixa`, `InicioTratamento`, `FimdoTratamento`, `Responsavel_tratamento`,Rg_Paciente)"
                + " VALUES (?,?,?,?,?,?,?)";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, codigo);
            smt.setInt(2, numeroDoTratamento);
            smt.setString(3, queixa);
            smt.setDate(4, inicioTratamento);
            smt.setDate(5, fimTratamento);
            smt.setString(6, responsavel_tratamento);
            smt.setString(7, rg);
            smt.execute();
            System.out.println("Gravou histórico paciente");
//            GravaNoHistoricoNecessidade(rg, numeroDoTratamento);
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return 1;
    }

    public boolean GravaNoHistoricoNecessidade(String rg, ArrayList<String> tratamentos) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ArrayList<Anamnese> ListaAnamnese;
        ArrayList<PesquisarProntuarioStatico> ListaProntuario;
        ArrayList<Pesquisar> ListaResponsavelProntuario;
        ListaAnamnese=ListarAnamnese(rg);//pega os dados da anamnese atual do paciente
        ListaProntuario=PesquisarProntuarioRg(rg);
        
        System.out.println("prontuario "+ListaProntuario.get(0).getResponsavelProntuario());
        //ListaResponsavelProntuario=PesquisarResponsavelProntuario(ListaProntuario.get(0).getPront_AlunoEmprestado());
        //System.out.println("codigo responsavel  "+ListaResponsavelProntuario.get(0).getCodigo());
        
        //---------------------------------Criação do sql dinâmico-------------------------
        String sql = "INSERT INTO `historiconecessidade`(nec_referencia_rg,";
        int incrementa = 0;//com esta variavel saberei quantos registros existem dentro do vetor;
        for (int i = 0; i < tratamentos.size(); i++) {
            if (tratamentos.get(i) != null) {
                sql = sql + tratamentos.get(i);

                sql = sql + ",";//para impedir que adicione uma virgula depois do ultimo atributo

                incrementa++;
            }
        }
        sql = sql + "dataFimTratamento_historicoNecessidade,queixa_historicoTratamento,responsavel_historicoTratamento) VALUES(" + rg + ",";
        for (int i = 0; i < incrementa; i++) {
            if (tratamentos.get(i) != null) {
                sql = sql + "1";
                sql = sql + ",";//para impedir que adicione uma virgula depois do ultimo parâmetro
            }
        }
        sql = sql + "CURRENT_DATE,?,?);";
        con = getConnection();
        PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //smt.setString(1, ListaAnamnese.get(0).getPrincipalQueixa());
        //smt.setString(2, ListaResponsavelProntuario.get(0).getNome());
        smt.setString(1, ListaAnamnese.get(0).getPrincipalQueixa());
        smt.setInt(2,ListaProntuario.get(0).getResponsavelProntuario());
        System.out.println("sql " + sql);
        boolean retorno = smt.execute();
        
        
        return retorno;
    }
    

    public ArrayList<HistoricoPaciente> PesquisarHistórico(int codigopaciente) throws ClassNotFoundException, SQLException {
        String sql = "SELECT `Id` ,paciente.pac_Nome, `CodigoPaciente` , `codigotratamento`,Rg_Paciente , `Queixa` , InicioTratamento  , FimdoTratamento, `Responsavel_tratamento`\n"
                + "FROM `historicodopaciente` join paciente\n"
                + "WHERE  paciente.pac_Cod=historicodopaciente.CodigoPaciente and `CodigoPaciente` =?;";
        Connection con = null;
        sctp2.Paciente.HistoricoPaciente pesquisarsmt;
        ArrayList<sctp2.Paciente.HistoricoPaciente> ListarPesquisa;
        ListarPesquisa = new ArrayList<>();//criando novo array
        try {

            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, codigopaciente);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                pesquisarsmt = new sctp2.Paciente.HistoricoPaciente();
                pesquisarsmt.setCodigopaciente(rs.getInt("CodigoPaciente"));
                pesquisarsmt.setCodigotratamento(rs.getInt("codigotratamento"));
                pesquisarsmt.setQueixa(rs.getString("Queixa"));
                pesquisarsmt.setIniciotratamento(rs.getDate("InicioTratamento"));
                pesquisarsmt.setFimtratamento(rs.getDate("FimdoTratamento"));
                pesquisarsmt.setResponsaveltratamento(rs.getString("Responsavel_tratamento"));
                pesquisarsmt.setNomepaciente(rs.getString("paciente.pac_Nome"));
                pesquisarsmt.setRgPaciente(rs.getString("Rg_Paciente"));
                ListarPesquisa.add(pesquisarsmt);
                
            }
        } catch (SQLException e) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(con);
        }

        return ListarPesquisa;

    }
    

    public ArrayList<HistoricoDetratamentos> PesquisarHistóricoTratamentos(String rg) throws ClassNotFoundException {
        String sql = "SELECT `nec_Cod`, `nec_referencia_rg`, `queixa_historicoTratamento`, "
                + "`responsavel_historicoTratamento`,responsavelpeloprontuario.nome_ResponsavelPeloProntuario, "
                + "`dataFimTratamento_historicoNecessidade`, `nec_ProfilaxiaSimples`, `nec_RaspagemPolimentoCoronario`, "
                + "`nec_CirurgiaPeriodontal`, `nec_ExodontiaSimples`, `nec_ExodontiaMolar`, `nec_ExodontiaIncluso`, `nec_Amalgama`, "
                + "`nec_Resina`, `nec_RMF`, `nec_Endodontiauniebirradicular`, `nec_EndodontiaTrirradicular`, `nec_CoroaTotal`,"
                + " `nec_PonteFixa3Elementos`, `nec_Pontefixa4elementos`, `nec_Pontefixamaisque4elementos`, `nec_PPR`,"
                + " `nec_ProteseTotalPar`, `nec_ProtesePPR`, `nec_Protese`, `nec_TerapiaPeriodontal`, `nec_EndodontiaBirradicular`, "
                + "`nec_DTM`, `nec_Estomatologia`, `Historico_codigoTratamento`, `nec_PonteFixa`, `nec_PonteFixaMaisQueTresElementos`, `"
                + "nec_RaspagemSub`, `nec_RaspagemSupra` FROM `historiconecessidade`\n" +
                    "JOIN responsavelpeloprontuario\n" +
                    "ON historiconecessidade.responsavel_historicoTratamento=responsavelpeloprontuario.Id\n" +
                    "WHERE `nec_referencia_rg`=?";
        Connection con = null;
        sctp2.Paciente.HistoricoDetratamentos pesquisarsmt;
        ArrayList<sctp2.Paciente.HistoricoDetratamentos> ListarPesquisa;
        ListarPesquisa = new ArrayList<>();//criando novo array
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rg);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                pesquisarsmt = new sctp2.Paciente.HistoricoDetratamentos();
                pesquisarsmt.setCodigo(rs.getInt("nec_Cod"));
                pesquisarsmt.setIdResponsavelPeloTratamento(rs.getInt("responsavel_historicoTratamento"));
                pesquisarsmt.setResponsavelPeloTratamento(rs.getString("responsavelpeloprontuario.nome_ResponsavelPeloProntuario"));
                pesquisarsmt.setQueixa(rs.getString("queixa_historicoTratamento"));
                pesquisarsmt.setProfilaxiaSimples(rs.getInt("nec_ProfilaxiaSimples"));
                pesquisarsmt.setRg(rs.getString("nec_referencia_rg"));
                pesquisarsmt.setRaspagemEPoliCCoronario(rs.getInt("nec_RaspagemPolimentoCoronario"));
                pesquisarsmt.setCirugiaPeridontal(rs.getInt("nec_CirurgiaPeriodontal"));
                pesquisarsmt.setExodontiaSimples(rs.getInt("nec_ExodontiaSimples"));
                pesquisarsmt.setExodontia3Molar(rs.getInt("nec_ExodontiaMolar"));
                pesquisarsmt.setExodontiaIncluso(rs.getInt("nec_ExodontiaIncluso"));
                pesquisarsmt.setAmalgama(rs.getInt("nec_Amalgama"));
                pesquisarsmt.setResina(rs.getInt("nec_Resina"));
                pesquisarsmt.setRmf(rs.getInt("nec_RMF"));
                pesquisarsmt.setEndontiaUnirradicular(rs.getInt("nec_Endodontiauniebirradicular"));
                pesquisarsmt.setEndodontiaTrirradicular(rs.getInt("nec_EndodontiaTrirradicular"));
                pesquisarsmt.setCoroaTotal(rs.getInt("nec_CoroaTotal"));
                pesquisarsmt.setPonteFixa3Elementos(rs.getInt("nec_PonteFixa3Elementos"));
                pesquisarsmt.setPonteFixa4Elementos(rs.getInt("nec_Pontefixa4elementos"));
                pesquisarsmt.setPonteFixaMaisQue4Elementos(rs.getInt("nec_Pontefixamaisque4elementos"));
                pesquisarsmt.setPpr(rs.getInt("nec_PPR"));
                pesquisarsmt.setProtesetotal(rs.getInt("nec_ProteseTotalPar"));
                pesquisarsmt.setProtese_ppr(rs.getInt("nec_ProtesePPR"));
                pesquisarsmt.setProtese(rs.getInt("nec_Protese"));
                pesquisarsmt.setTerrapiaOeriodDeSuporte(rs.getInt("nec_TerapiaPeriodontal"));
                pesquisarsmt.setEndodontiaBirradicular(rs.getInt("nec_EndodontiaBirradicular"));
                pesquisarsmt.setDtm(rs.getInt("nec_DTM"));
                pesquisarsmt.setEstomatologia(rs.getInt("nec_Estomatologia"));
                pesquisarsmt.setPonteFixa(rs.getInt("nec_PonteFixa"));
                pesquisarsmt.setPonteFixaMaisQueTresElementos(rs.getInt("nec_PonteFixaMaisQueTresElementos"));
                pesquisarsmt.setRaspagemSub(rs.getInt("nec_RaspagemSub"));
                pesquisarsmt.setRaspagemSupra(rs.getInt("nec_RaspagemSupra"));
                pesquisarsmt.setHistorico_codigoTratamento(rs.getInt("Historico_codigoTratamento"));
                pesquisarsmt.setFimTratamento(rs.getDate("dataFimTratamento_historicoNecessidade"));
                ListarPesquisa.add(pesquisarsmt);
            }
        } catch (SQLException e) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;

    }
    //-------------------------------------------FUNÇÕES DE ATUALIZAÇÃO--------------------------------------------------------------------------
   
   

     
    

    //--------------------------------------------------------------------------------------------------------------------------------------------

    

    

    

    

    
}