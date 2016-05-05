/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sctp2.BancodeDados;

import com.mysql.jdbc.PreparedStatement;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sctp2.ClassesdeControle.Anamnese;
import sctp2.ClassesdeControle.DoencasSistemicas;
import sctp2.ClassesdeControle.Habitos;
import sctp2.ClassesdeControle.TratamentosNecessarios;
import sctp2.Pesquisar.Paciente.HistoricoPaciente;
import sctp2.Pesquisar.Pesquisar;
import sctp2.Pesquisar.PesquisarProntuario;

/**
 *
 * @author Adriano
 */
public class ConexaoPacotePesquisar {

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

    public int[] PesquisarProntuarioPaciente(String codigo) throws ClassNotFoundException {
        int[] retorno = new int[2];
        Connection con = null;
        String sql = "select pront_Numero,pront_Status from prontuario where referencia_RG_PAC=?;";

        try {
            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, codigo);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {

                retorno[0] = rs.getInt("pront_Numero");
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
    }//Fim da função PesquisarProntuario

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

    public ArrayList<Pesquisar> PesquisarResponsavelProntuario(String text, int tipo) throws ClassNotFoundException {
        String sql = "select id,resp_nome,resp_celular,resp_telefone_fixo from responsavelpeloprontuario where resp_nome like ?;";
        Connection con = null;
        ArrayList<Pesquisar> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<Pesquisar>();//criando novo array
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, "%" + text + "%");
            ResultSet rs = smt.executeQuery();//efetuando a busca
            while (rs.next()) {
                Pesquisar pesquisarsmt = new Pesquisar();
                pesquisarsmt.setCodigo(rs.getInt("id"));
                pesquisarsmt.setNome(rs.getString("resp_nome"));
                pesquisarsmt.setTelefone(rs.getString("resp_celular"));
                pesquisarsmt.setTelefonefixo(rs.getString("resp_telefone_fixo"));
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

    public ArrayList<Pesquisar> PesquisarProntuarioPacienteDetalhes(String valor) throws ClassNotFoundException {
        String sql = "select id,resp_nome,resp_celular,resp_telefone_fixo from responsavelpeloprontuario where id like ?;";
        Connection con = null;
        ArrayList<Pesquisar> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<Pesquisar>();//criando novo array
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, valor);
            ResultSet rs = smt.executeQuery();//efetuando a busca
            while (rs.next()) {
                Pesquisar pesquisarsmt = new Pesquisar();
                pesquisarsmt.setCodigo(rs.getInt("id"));
                pesquisarsmt.setNome(rs.getString("resp_nome"));
                pesquisarsmt.setTelefone(rs.getString("resp_celular"));
                pesquisarsmt.setTelefonefixo(rs.getString("resp_telefone_fixo"));
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

    public ArrayList<PesquisarProntuario> PesquisarProntuario(String codigo) throws ClassNotFoundException {
        String sql = "SELECT pront_Numero,pront_AlunoEmprestado,pront_TelefoneAluno,pront_Status,pront_responsavel_prontuario, paciente.pac_Nome "
                + "FROM prontuario join paciente where paciente.pac_RG=referencia_RG_PAC  and prontuario.pront_Numero=?;";
        Connection con = null;
        ArrayList<PesquisarProntuario> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<PesquisarProntuario>();//criando novo array
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, codigo);
            ResultSet rs = smt.executeQuery();
            PesquisarProntuario pesquisarsmt = new PesquisarProntuario();
            while (rs.next()) {
                String verificavazio = "";
                pesquisarsmt.setProntuario(rs.getString("pront_Numero"));
                pesquisarsmt.setNome(rs.getString("pront_AlunoEmprestado"));
                pesquisarsmt.setTelefone(rs.getString("pront_TelefoneAluno"));
                pesquisarsmt.setPaciente(rs.getString("paciente.pac_Nome"));//Para não ter que criar um novo campo utilizei pai para receber o nome do paciente
                pesquisarsmt.setStatus(rs.getInt("pront_Status"));

                //Caso o nome do aluno esteja vazio ele será preenchido no if abaixo
                verificavazio = rs.getString("pront_AlunoEmprestado");//se o nome estiver vazio será chamado a função para pesquisar na tabela responsavelpeloprontuario
                if (verificavazio == null || verificavazio.trim().equals("")) {
                    String[] resposta = new String[3];//recebera o retorno da função
                    resposta = PesquisaResponsavelProtuario(rs.getInt("pront_responsavel_prontuario"));
                    pesquisarsmt.setNome(resposta[0]);
                    pesquisarsmt.setTelefone(resposta[1]);
                    pesquisarsmt.setTelefoneFixo(resposta[1]);
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

    public ArrayList<PesquisarProntuario> PesquisarProntuariopelorg(String rg) throws ClassNotFoundException {
        String sql = "SELECT pront_Numero,pront_AlunoEmprestado,pront_TelefoneAluno,pront_Status,pront_responsavel_prontuario, paciente.pac_Nome, pront_Informacoes "
                + "FROM prontuario join paciente where paciente.pac_RG=referencia_RG_PAC and prontuario.referencia_RG_PAC=?;";
        Connection con = null;
        ArrayList<PesquisarProntuario> ListarPesquisa;//array que recebera o resultado da pesquisa
        ListarPesquisa = new ArrayList<PesquisarProntuario>();//criando novo array
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rg);
            ResultSet rs = smt.executeQuery();
            PesquisarProntuario pesquisarsmt = new PesquisarProntuario();
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
                    String[] resposta = new String[3];//recebera o retorno da função
                    resposta = PesquisaResponsavelProtuario(rs.getInt("pront_responsavel_prontuario"));
                    pesquisarsmt.setNome(resposta[0]);
                    pesquisarsmt.setTelefone(resposta[1]);
                    pesquisarsmt.setTelefoneFixo(resposta[1]);
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

    public boolean AtualizaStatusProntuario(String id) throws SQLException, ClassNotFoundException {
        String sql = "update prontuario set pront_Status=0 where pront_Numero=?;";
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

    //private ArrayList<PesquisarProntuario> PesquisaResponsavelProtuario(int id) throws ClassNotFoundException {
    private String[] PesquisaResponsavelProtuario(int id) throws ClassNotFoundException {
        Connection con = null;
        String[] resposta = new String[3];
        String sql = "select resp_nome,resp_celular,resp_telefone_fixo from responsavelpeloprontuario where id=?;";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, id);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                resposta[0] = rs.getString("resp_nome");
                resposta[1] = rs.getString("resp_celular");
                resposta[2] = rs.getString("resp_telefone_fixo");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocorreu uma falha ao conectar com o banco de dados, tente novamente em alguns minutos ou verifique a conexao!");
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return resposta;
    }

    public boolean EmprestaProntuario(String prontuario, String nome) throws ClassNotFoundException {
        Connection con = null;
        boolean retorno = false;
        String sql = "update prontuario set pront_Status=1, pront_responsavel_prontuario=?,pront_AlunoEmprestado='',pront_TelefoneAluno=''  where pront_numero=?;";
        try {
            con = getConnection();//criando variavel de conexao
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, nome);
            smt.setString(2, prontuario);
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

    public ArrayList<TratamentosNecessarios> NecessidadesdoPaciente(String iDpaciente) throws ClassNotFoundException, SQLException {
        Connection con = null;
        boolean retorno = false;
        ArrayList<sctp2.ClassesdeControle.TratamentosNecessarios> ListarPesquisa;
        ListarPesquisa = new ArrayList<sctp2.ClassesdeControle.TratamentosNecessarios>();//criando novo array
        String sql = "SELECT `nec_Cod`, `nec_ProfilaxiaSimples`, `nec_referencia_rg`, `nec_RaspagemPolimentoCoronario`, "
                + "`nec_CirurgiaPeriodontal`, `nec_ExodontiaSimples`, `nec_ExodontiaMolar`, `nec_ExodontiaIncluso`, `nec_Amalgama`,"
                + " `nec_Resina`, `nec_RMF`, `nec_Endodontiauniebirradicular`, `nec_EndodontiaTrirradicular`, `nec_CoroaTotal`, "
                + "`nec_PonteFixa3Elementos`, `nec_Pontefixa4elementos`, `nec_Pontefixamaisque4elementos`, `nec_PPR`, `nec_ProteseTotalPar`, "
                + "`nec_ProtesePPR`, `nec_Protese`, `nec_TerapiaPeriodontal`, `nec_EndodontiaBirradicular`, `nec_DTM`, `nec_Estomatologia` "
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
            };
            ListarPesquisa.add(pesquisarsmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPacotePesquisar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }

        return ListarPesquisa;
    }

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
            Logger.getLogger(ConexaoPacotePesquisar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }

    public ArrayList<Anamnese> ListarAnamnese(String iDpaciente) throws ClassNotFoundException, SQLException {
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
            smt.setString(1, iDpaciente);
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
            Logger.getLogger(ConexaoPacotePesquisar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
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
            Logger.getLogger(ConexaoPacotePesquisar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConexaoPacotePesquisar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;
    }

    public ArrayList<HistoricoPaciente> PesquisarHistórico(int codigopaciente) throws ClassNotFoundException, SQLException {
        String sql = "SELECT `Id` ,paciente.pac_Nome, `CodigoPaciente` , `codigotratamento`,Rg_Paciente , `Queixa` , InicioTratamento  , FimdoTratamento, `Responsavel_tratamento`\n"
                + "FROM `historicodopaciente` join paciente\n"
                + "WHERE  paciente.pac_Cod=historicodopaciente.CodigoPaciente and `CodigoPaciente` =?;";
        Connection con = null;
        sctp2.Pesquisar.Paciente.HistoricoPaciente pesquisarsmt = new sctp2.Pesquisar.Paciente.HistoricoPaciente();
        ArrayList<sctp2.Pesquisar.Paciente.HistoricoPaciente> ListarPesquisa;
        ListarPesquisa = new ArrayList<sctp2.Pesquisar.Paciente.HistoricoPaciente>();//criando novo array
        try {

            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setInt(1, codigopaciente);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                pesquisarsmt.setCodigopaciente(rs.getInt("CodigoPaciente"));
                pesquisarsmt.setCodigotratamento(rs.getInt("codigotratamento"));
                pesquisarsmt.setQueixa(rs.getString("Queixa"));
                pesquisarsmt.setIniciotratamento(rs.getDate("InicioTratamento"));
                pesquisarsmt.setFimtratamento(rs.getDate("FimdoTratamento"));
                pesquisarsmt.setResponsaveltratamento(rs.getString("Responsavel_tratamento"));
                pesquisarsmt.setNomepaciente(rs.getString("paciente.pac_Nome"));
                pesquisarsmt.setRgPaciente(rs.getString("Rg_Paciente"));
            }
            ListarPesquisa.add(pesquisarsmt);

        } catch (SQLException e) {
            Logger.getLogger(ConexaoPacotePesquisar.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(con);
        }
        return ListarPesquisa;

    }

    public ArrayList<TratamentosNecessarios> PesquisarHistóricoTratamentos(String rg) throws ClassNotFoundException {
        System.out.println("função PesquisarHistóricoTratamentos"+rg);
        String sql = "SELECT `nec_Cod`, `nec_ProfilaxiaSimples`, `nec_referencia_rg`, `nec_RaspagemPolimentoCoronario`, `nec_CirurgiaPeriodontal`, `nec_ExodontiaSimples`, `nec_ExodontiaMolar`, `nec_ExodontiaIncluso`, `nec_Amalgama`, `nec_Resina`, `nec_RMF`, `nec_Endodontiauniebirradicular`, `nec_EndodontiaTrirradicular`, `nec_CoroaTotal`, `nec_PonteFixa3Elementos`, `nec_Pontefixa4elementos`, `nec_Pontefixamaisque4elementos`, `nec_PPR`, `nec_ProteseTotalPar`, `nec_ProtesePPR`, `nec_Protese`, `nec_TerapiaPeriodontal`, `nec_EndodontiaBirradicular`, `nec_DTM`, `nec_Estomatologia`, `Historico_codigoTratamento` FROM `historiconecessidade` WHERE `nec_referencia_rg`=?";
        Connection con = null;
        sctp2.ClassesdeControle.TratamentosNecessarios pesquisarsmt = new sctp2.ClassesdeControle.TratamentosNecessarios();
        ArrayList<sctp2.ClassesdeControle.TratamentosNecessarios> ListarPesquisa;
        ListarPesquisa = new ArrayList<sctp2.ClassesdeControle.TratamentosNecessarios>();//criando novo array
        try {

            con = getConnection();
            PreparedStatement smt = (PreparedStatement) con.prepareStatement(sql);
            smt.setString(1, rg);
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                pesquisarsmt.setCodigo(rs.getInt("nec_Cod"));
                pesquisarsmt.setProfilaxiaSimples(rs.getInt("nec_ProfilaxiaSimples"));
                System.out.println("profilaxia: " + rs.getInt("nec_ProfilaxiaSimples"));
                pesquisarsmt.setRg(rs.getString("nec_referencia_rg"));
                pesquisarsmt.setRaspagemEPoliCCoronario(rs.getInt("nec_RaspagemPolimentoCoronario"));
                pesquisarsmt.setCirugiaPeridontal(rs.getInt("nec_CirurgiaPeriodontal"));
                pesquisarsmt.setExodontiaSimples(rs.getInt("nec_ExodontiaSimples"));
                pesquisarsmt.setExodontia3Molar(rs.getInt("nec_ExodontiaMolar"));
                pesquisarsmt.setExodontiaIncluso(rs.getInt("nec_ExodontiaIncluso"));
                pesquisarsmt.setAmalgama(rs.getInt("nec_Amalgama"));
                pesquisarsmt.setResina(rs.getInt("nec_Resina"));
                pesquisarsmt.setRmf(rs.getInt("nec_RMF"));
                pesquisarsmt.setEndontiaUnirradicular( rs.getInt("nec_Endodontiauniebirradicular"));
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
                 pesquisarsmt.setEndodontiaBirradicular( rs.getInt("nec_EndodontiaBirradicular"));
                 pesquisarsmt.setDtm(rs.getInt("nec_DTM"));
                 pesquisarsmt.setEstomatologia(rs.getInt("nec_Estomatologia"));
                 pesquisarsmt.setHistorico_codigoTratamento(rs.getInt("Historico_codigoTratamento"));
            };
            ListarPesquisa.add(pesquisarsmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoPacotePesquisar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection(con);
        }

        return ListarPesquisa;
    }
}
