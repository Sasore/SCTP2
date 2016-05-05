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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriano
 */
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

    public boolean GravaTratamentoNecessario(int[] tratamentosNVetor, String rg) throws ClassNotFoundException {
        Connection con = null;

        String sql = ("insert into necessidade(\n"
                + "`nec_Amalgama`,`nec_CirurgiaPeriodontal`,`nec_CoroaTotal`,`nec_DTM`,`nec_EndodontiaBirradicular`,\n"
                + "`nec_Endodontiauniebirradicular`,`nec_EndodontiaTrirradicular`,`nec_Estomatologia`,`nec_ExodontiaMolar`,\n"
                + "`nec_ExodontiaIncluso`,`nec_ProfilaxiaSimples`,`nec_PonteFixa3Elementos`,`nec_Pontefixa4elementos`,\n"
                + "`nec_Pontefixamaisque4elementos`,`nec_Protese`,`nec_ProteseTotalPar`,`nec_ProtesePPR`,`nec_PPR`,\n"
                + "`nec_RaspagemPolimentoCoronario`,`nec_Resina`,`nec_RMF`,\n"
                + "`nec_TerapiaPeriodontal`,nec_ExodontiaSimples,`nec_referencia_rg`)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
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
            smt.setString(24, rg);
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

    public int GravaresponsavelProntuario(String nome, String celular, String telefonefixo) throws ClassNotFoundException {
        Connection con = null;
        int id = 0;
        ResultSet rs;

        String sql = ("insert into responsavelpeloprontuario(resp_nome,resp_celular,resp_telefone_fixo) values (?,?,?);");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, nome);
            smt.setString(2, celular);
            smt.setString(3, telefonefixo);
            smt.execute();
            retorno = true;
            System.out.println("Gravou Aluno/Responsavel");
            //------------a partir daqui a é feita uma pesquisa para saber qual o ID do responsavel que foi gravado----------------------------------------
            sql = "select id from responsavelpeloprontuario where resp_nome=? and resp_celular=?;";
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

    public boolean AtualizaresponsavelProntuario(String nome, String celular, String telefonefixo, int id) throws ClassNotFoundException {
        Connection con = null;
        String sql = ("update  responsavelpeloprontuario set resp_nome=?,resp_celular=?,resp_telefone_fixo =? where id=?;");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, nome);
            smt.setString(2, celular);
            smt.setString(3, telefonefixo);
            smt.setInt(4, id);
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

    public boolean EncerraTratamento(String text) throws ClassNotFoundException {
        Connection con = null;
        String sql = ("UPDATE `paciente` SET `pac_Alta`=1, pac_Fim_tratamento=now() WHERE `pac_RG`=?;");
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, text);
            smt.executeUpdate();
            retorno = true;
            System.out.println("Encerrou tratamento");
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return retorno;
    }

    public boolean GravaNoHistorico(int codigo, String responsavel_tratamento, String rg) throws ClassNotFoundException, SQLException {
        boolean retorno = false;
        Connection con = null;
        Date inicioTratamento = null, fimTratamento = null;
        String queixa = null;
        String sql = "select distinct pac_cod,pac_inicio_tratamento,pac_Fim_tratamento,paciente.pac_RG, saude.sa_QueixaPrincipal from paciente join prontuario,responsavelpeloprontuario,saude where \n"
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
            GravaNoHistoricoNecessidade(rg, numeroDoTratamento);
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return 1;
    }

    public boolean GravaNoHistoricoNecessidade(String rg, int numeroDoTratamento) throws ClassNotFoundException, SQLException {
        Connection con = null, con2;
        String sql = "SELECT `nec_Cod`, `nec_ProfilaxiaSimples`, `nec_referencia_rg`, `nec_RaspagemPolimentoCoronario`, `nec_CirurgiaPeriodontal`, `nec_ExodontiaSimples`, `nec_ExodontiaMolar`, `nec_ExodontiaIncluso`, `nec_Amalgama`, `nec_Resina`, `nec_RMF`, `nec_Endodontiauniebirradicular`, `nec_EndodontiaTrirradicular`, `nec_CoroaTotal`, `nec_PonteFixa3Elementos`, `nec_Pontefixa4elementos`, `nec_Pontefixamaisque4elementos`, `nec_PPR`, `nec_ProteseTotalPar`, `nec_ProtesePPR`, `nec_Protese`, `nec_TerapiaPeriodontal`, `nec_EndodontiaBirradicular`, `nec_DTM`, `nec_Estomatologia` FROM `necessidade` WHERE `nec_referencia_rg`=?";
        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rg);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                //-------------------------------copia os dados do paciente na tabela necessidade para a tabela historico necessidade------------------------------------------------------------
                con2 = getConnection();
                String sql2 = "INSERT INTO `historiconecessidade`( `nec_ProfilaxiaSimples`, `nec_referencia_rg`, `nec_RaspagemPolimentoCoronario`, "
                        + "`nec_CirurgiaPeriodontal`, `nec_ExodontiaSimples`, `nec_ExodontiaMolar`, `nec_ExodontiaIncluso`, `nec_Amalgama`, `nec_Resina`,"
                        + " `nec_RMF`, `nec_Endodontiauniebirradicular`, `nec_EndodontiaTrirradicular`, `nec_CoroaTotal`, `nec_PonteFixa3Elementos`, "
                        + "`nec_Pontefixa4elementos`, `nec_Pontefixamaisque4elementos`, `nec_PPR`, `nec_ProteseTotalPar`, `nec_ProtesePPR`, `nec_Protese`, "
                        + "`nec_TerapiaPeriodontal`, `nec_EndodontiaBirradicular`, `nec_DTM`, `nec_Estomatologia`, `Historico_codigoTratamento`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement smt2 = (PreparedStatement) con.prepareStatement(sql2);
                smt2.setInt(1, rs.getInt("nec_ProfilaxiaSimples"));
                smt2.setString(2, rs.getString("nec_referencia_rg"));
                smt2.setInt(3, rs.getInt("nec_RaspagemPolimentoCoronario"));
                smt2.setInt(4, rs.getInt("nec_CirurgiaPeriodontal"));
                smt2.setInt(5, rs.getInt("nec_ExodontiaSimples"));
                smt2.setInt(6, rs.getInt("nec_ExodontiaMolar"));
                smt2.setInt(7, rs.getInt("nec_ExodontiaIncluso"));
                smt2.setInt(8, rs.getInt("nec_Amalgama"));
                smt2.setInt(9, rs.getInt("nec_Resina"));
                smt2.setInt(10, rs.getInt("nec_RMF"));
                smt2.setInt(11, rs.getInt("nec_Endodontiauniebirradicular"));
                smt2.setInt(12, rs.getInt("nec_EndodontiaTrirradicular"));
                smt2.setInt(13, rs.getInt("nec_CoroaTotal"));
                smt2.setInt(14, rs.getInt("nec_PonteFixa3Elementos"));
                smt2.setInt(15, rs.getInt("nec_Pontefixa4elementos"));
                smt2.setInt(16, rs.getInt("nec_Pontefixamaisque4elementos"));
                smt2.setInt(17, rs.getInt("nec_PPR"));
                smt2.setInt(18, rs.getInt("nec_ProteseTotalPar"));
                smt2.setInt(19, rs.getInt("nec_ProtesePPR"));
                smt2.setInt(20, rs.getInt("nec_Protese"));
                smt2.setInt(21, rs.getInt("nec_TerapiaPeriodontal"));
                smt2.setInt(22, rs.getInt("nec_EndodontiaBirradicular"));
                smt2.setInt(23, rs.getInt("nec_DTM"));
                smt2.setInt(24, rs.getInt("nec_Estomatologia"));
                smt2.setInt(25, numeroDoTratamento);
                smt2.execute();
                System.out.println("gravou historico necessidade");
                //-------------------------------------------------------------------------------------------------------
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algo deu errado ao fazer uma busca no banco de dados, tente novamente!");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }

        return true;
    }
}
