-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.16


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sctp
--

CREATE DATABASE IF NOT EXISTS sctp;
USE sctp;

--
-- Definition of table `doesist`
--

DROP TABLE IF EXISTS `doesist`;
CREATE TABLE `doesist` (
  `cod_pac_DoeSist` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `doe_FebreReumatica` int(10) unsigned NOT NULL,
  `doe_ProblemasRenais` int(10) unsigned NOT NULL,
  `doe_ProblemasRespiratorios` int(10) unsigned NOT NULL,
  `doe_ProblemasArtReumatismo` int(10) unsigned NOT NULL,
  `doe_HipertensaoArterial` int(10) unsigned NOT NULL,
  `doe_ProblemasCardiacos` int(10) unsigned NOT NULL,
  `doe_ProblemasGastricos` int(10) unsigned NOT NULL,
  `doe_ProblemasAlergicos` int(10) unsigned NOT NULL,
  `doe_Diabetes` int(10) unsigned NOT NULL,
  `doe_PesoDoenSiste` int(10) unsigned DEFAULT NULL,
  `doe_ReferenciaPaciente` varchar(45) NOT NULL DEFAULT 'Vazio',
  PRIMARY KEY (`cod_pac_DoeSist`),
  UNIQUE KEY `FK_doesistRG` (`doe_ReferenciaPaciente`) USING BTREE,
  CONSTRAINT `FK_pac_RG` FOREIGN KEY (`doe_ReferenciaPaciente`) REFERENCES `paciente` (`pac_RG`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doesist`
--

/*!40000 ALTER TABLE `doesist` DISABLE KEYS */;
INSERT INTO `doesist` (`cod_pac_DoeSist`,`doe_FebreReumatica`,`doe_ProblemasRenais`,`doe_ProblemasRespiratorios`,`doe_ProblemasArtReumatismo`,`doe_HipertensaoArterial`,`doe_ProblemasCardiacos`,`doe_ProblemasGastricos`,`doe_ProblemasAlergicos`,`doe_Diabetes`,`doe_PesoDoenSiste`,`doe_ReferenciaPaciente`) VALUES 
 (1,0,0,0,0,0,0,1,0,0,NULL,'12714010'),
 (2,1,1,1,1,1,1,1,1,1,NULL,'2'),
 (3,0,0,0,0,0,0,0,0,0,NULL,'3');
/*!40000 ALTER TABLE `doesist` ENABLE KEYS */;


--
-- Definition of table `habitos`
--

DROP TABLE IF EXISTS `habitos`;
CREATE TABLE `habitos` (
  `cod_pac_habitos` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hab_Fuma` int(10) unsigned NOT NULL,
  `hab_FumaQuantoTempo` int(10) unsigned DEFAULT NULL,
  `hab_NumeroCigarros` int(10) unsigned DEFAULT NULL,
  `hab_TipoCigarro` int(10) unsigned DEFAULT NULL,
  `hab_Alcool` int(10) unsigned NOT NULL,
  `hab_BebeQuantoTempo` int(10) unsigned DEFAULT NULL,
  `hab_TipoBebida` int(10) unsigned DEFAULT NULL,
  `hab_FrequenciaBebida` int(10) unsigned DEFAULT NULL,
  `hab_PesoHabito` int(10) unsigned DEFAULT NULL,
  `hab_referencia_RG` varchar(45) NOT NULL,
  PRIMARY KEY (`cod_pac_habitos`),
  UNIQUE KEY `FK_habitos_RG` (`hab_referencia_RG`) USING BTREE,
  CONSTRAINT `FK_habitos_1` FOREIGN KEY (`hab_referencia_RG`) REFERENCES `paciente` (`pac_RG`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `habitos`
--

/*!40000 ALTER TABLE `habitos` DISABLE KEYS */;
INSERT INTO `habitos` (`cod_pac_habitos`,`hab_Fuma`,`hab_FumaQuantoTempo`,`hab_NumeroCigarros`,`hab_TipoCigarro`,`hab_Alcool`,`hab_BebeQuantoTempo`,`hab_TipoBebida`,`hab_FrequenciaBebida`,`hab_PesoHabito`,`hab_referencia_RG`) VALUES 
 (1,0,4,4,4,0,4,4,4,NULL,'12714010'),
 (2,1,1,1,1,1,1,1,1,NULL,'2'),
 (3,0,4,4,4,0,4,4,4,NULL,'3');
/*!40000 ALTER TABLE `habitos` ENABLE KEYS */;


--
-- Definition of table `necessidade`
--

DROP TABLE IF EXISTS `necessidade`;
CREATE TABLE `necessidade` (
  `nec_Cod` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nec_ProfilaxiaSimples` int(10) unsigned DEFAULT NULL,
  `nec_referencia_rg` varchar(45) NOT NULL,
  `nec_RaspagemPolimentoCoronario` int(10) unsigned DEFAULT NULL,
  `nec_CirurgiaPeriodontal` int(10) unsigned DEFAULT NULL,
  `nec_ExodontiaSimples` int(10) unsigned DEFAULT NULL,
  `nec_ExodontiaMolar` int(10) unsigned DEFAULT NULL,
  `nec_ExodontiaIncluso` int(10) unsigned DEFAULT NULL,
  `nec_Amalgama` int(10) unsigned DEFAULT NULL,
  `nec_Resina` int(10) unsigned DEFAULT NULL,
  `nec_RMF` int(10) unsigned DEFAULT NULL,
  `nec_Endodontiauniebirradicular` int(10) unsigned DEFAULT NULL,
  `nec_EndodontiaTrirradicular` int(10) unsigned DEFAULT NULL,
  `nec_CoroaTotal` int(10) unsigned DEFAULT NULL,
  `nec_PonteFixa3Elementos` int(10) unsigned DEFAULT NULL,
  `nec_Pontefixa4elementos` int(10) unsigned DEFAULT NULL,
  `nec_Pontefixamaisque4elementos` int(10) unsigned DEFAULT NULL,
  `nec_PPR` int(10) unsigned DEFAULT NULL,
  `nec_ProteseTotalPar` int(10) unsigned DEFAULT NULL,
  `nec_ProtesePPR` int(10) unsigned DEFAULT NULL,
  `nec_Protese` int(10) unsigned DEFAULT NULL,
  `nec_TerapiaPeriodontal` int(10) unsigned DEFAULT NULL,
  `nec_EndodontiaBirradicular` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`nec_Cod`),
  KEY `FK_necessidade_1` (`nec_referencia_rg`),
  CONSTRAINT `FK_necessidade_1` FOREIGN KEY (`nec_referencia_rg`) REFERENCES `paciente` (`pac_RG`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `necessidade`
--

/*!40000 ALTER TABLE `necessidade` DISABLE KEYS */;
INSERT INTO `necessidade` (`nec_Cod`,`nec_ProfilaxiaSimples`,`nec_referencia_rg`,`nec_RaspagemPolimentoCoronario`,`nec_CirurgiaPeriodontal`,`nec_ExodontiaSimples`,`nec_ExodontiaMolar`,`nec_ExodontiaIncluso`,`nec_Amalgama`,`nec_Resina`,`nec_RMF`,`nec_Endodontiauniebirradicular`,`nec_EndodontiaTrirradicular`,`nec_CoroaTotal`,`nec_PonteFixa3Elementos`,`nec_Pontefixa4elementos`,`nec_Pontefixamaisque4elementos`,`nec_PPR`,`nec_ProteseTotalPar`,`nec_ProtesePPR`,`nec_Protese`,`nec_TerapiaPeriodontal`,`nec_EndodontiaBirradicular`) VALUES 
 (1,1,'12714010',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
 (2,1,'2',1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1),
 (3,0,'3',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `necessidade` ENABLE KEYS */;


--
-- Definition of table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
CREATE TABLE `paciente` (
  `pac_RG` varchar(45) NOT NULL,
  `pac_Nome` varchar(220) NOT NULL,
  `pac_DataNasc` varchar(45) NOT NULL,
  `pac_Cod` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pac_Naturalidade` varchar(45) NOT NULL,
  `pac_Sexo` int(10) unsigned NOT NULL,
  `pac_Ocupacao` varchar(220) NOT NULL,
  `pac_Procedencia` int(10) unsigned NOT NULL,
  `pac_Mae` varchar(220) NOT NULL,
  `pac_Pai` varchar(220) NOT NULL,
  `pac_EstadoCivil` int(10) unsigned NOT NULL,
  `pac_Conjuge` varchar(220) DEFAULT NULL,
  `pac_Cidade` varchar(45) NOT NULL,
  `pac_Rua` varchar(45) NOT NULL,
  `pac_Bairro` varchar(45) NOT NULL,
  `pac_NumeroCasa` varchar(45) NOT NULL,
  `pac_Cep` varchar(45) NOT NULL,
  `pac_Telefone` varchar(15) NOT NULL,
  `pac_GrupoRisco` int(10) unsigned DEFAULT NULL,
  `pac_Alta` int(10) unsigned NOT NULL DEFAULT '0',
  `pac_listaNegra` int(10) unsigned NOT NULL,
  `pac_motivoLista` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`pac_Cod`) USING BTREE,
  UNIQUE KEY `ChaveSecundaria` (`pac_RG`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paciente`
--

/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` (`pac_RG`,`pac_Nome`,`pac_DataNasc`,`pac_Cod`,`pac_Naturalidade`,`pac_Sexo`,`pac_Ocupacao`,`pac_Procedencia`,`pac_Mae`,`pac_Pai`,`pac_EstadoCivil`,`pac_Conjuge`,`pac_Cidade`,`pac_Rua`,`pac_Bairro`,`pac_NumeroCasa`,`pac_Cep`,`pac_Telefone`,`pac_GrupoRisco`,`pac_Alta`,`pac_listaNegra`,`pac_motivoLista`) VALUES 
 ('12714010','Renier Figueiredo Padilha','   05   /   04   /1987',1,'Guanhães',0,'Estudante',0,'Maria do Carmo F. Padilha','Carlos Antônio Padilha',0,'Não Casado','Guanães','Wantuil Caldeira','Expansão','323','39740-000','(03)8812-5203',NULL,0,0,''),
 ('2','PACIENTE 2','   11   /   11   /1111',2,'PACIENTE 2',0,'PACIENTE 2',0,'PACIENTE 2','PACIENTE 2',0,'Não Casado','PACIENTE 2','PACIENTE 2','PACIENTE 2','23A','21111-111','(89)9999-9999',NULL,0,1,'Xingou'),
 ('3','PACIENTE 4','   11   /   11   /1111',3,'PACIENTE 4',0,'PACIENTE 4',0,'PACIENTE 4','PACIENTE 4',0,'Não Casado','PACIENTE 4','PACIENTE 4','PACIENTE 4','PACIENTE 4','     -   ','(11)1111-1111',NULL,0,0,'');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;


--
-- Definition of table `pacientesimples`
--

DROP TABLE IF EXISTS `pacientesimples`;
CREATE TABLE `pacientesimples` (
  `sim_Cod` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sim_Nome` varchar(80) NOT NULL,
  `sim_Telefone` varchar(15) NOT NULL,
  `sim_Nacimento` varchar(12) NOT NULL,
  `sim_RG` varchar(20) NOT NULL,
  `sim_Queixas` varchar(600) NOT NULL DEFAULT 'Nenhuma Queixa',
  `sim_TratamentoSolicitado` varchar(600) NOT NULL DEFAULT 'Nenhum Tratamento',
  PRIMARY KEY (`sim_Cod`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pacientesimples`
--

/*!40000 ALTER TABLE `pacientesimples` DISABLE KEYS */;
INSERT INTO `pacientesimples` (`sim_Cod`,`sim_Nome`,`sim_Telefone`,`sim_Nacimento`,`sim_RG`,`sim_Queixas`,`sim_TratamentoSolicitado`) VALUES 
 (1,'Renier ','(038)8812-5203','01/01/0001','55555','XXX','XXXX'),
 (2,'XXXXX','(111)1111-1111','11/11/0011','233','XXXXXX','XXXXXXX'),
 (3,'XXXX','(111)1111-1111','05/05/0004','14545','XXXXX','XXXXXX'),
 (4,'XX','(222)2222-2222','05/01/0092','9999','XXX','XXXX');
/*!40000 ALTER TABLE `pacientesimples` ENABLE KEYS */;


--
-- Definition of table `prontuario`
--

DROP TABLE IF EXISTS `prontuario`;
CREATE TABLE `prontuario` (
  `pront_cod` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `referencia_RG_PAC` varchar(45) NOT NULL,
  `pront_Status` int(10) unsigned DEFAULT '0',
  `pront_AlunoEmprestato` varchar(100) DEFAULT NULL,
  `pront_TelefoneAluno` varchar(15) DEFAULT NULL,
  `pront_Numero` varchar(45) NOT NULL,
  `pront_Informacoes` varchar(1500) DEFAULT 'Nenhuma Informaçao',
  PRIMARY KEY (`pront_cod`),
  UNIQUE KEY `prontuario_Numero` (`pront_Numero`),
  KEY `FK_prontuario_1` (`referencia_RG_PAC`),
  CONSTRAINT `FK_prontuario_1` FOREIGN KEY (`referencia_RG_PAC`) REFERENCES `paciente` (`pac_RG`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prontuario`
--

/*!40000 ALTER TABLE `prontuario` DISABLE KEYS */;
INSERT INTO `prontuario` (`pront_cod`,`referencia_RG_PAC`,`pront_Status`,`pront_AlunoEmprestato`,`pront_TelefoneAluno`,`pront_Numero`,`pront_Informacoes`) VALUES 
 (1,'12714010',0,'','(11)1111-1111','1',''),
 (2,'2',1,'PACIENTE 2','(11)1111-1111','2','PACIENTE 2PACIENTE 2PACIENTE 2PACIENTE 2'),
 (3,'3',0,'','(  )    -    ','3','');
/*!40000 ALTER TABLE `prontuario` ENABLE KEYS */;


--
-- Definition of table `psr`
--

DROP TABLE IF EXISTS `psr`;
CREATE TABLE `psr` (
  `psr_cod` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `psr_ref_paciente` varchar(25) DEFAULT NULL,
  `psr_primeiroSextante` varchar(1) DEFAULT NULL,
  `psr_segundoSextante` varchar(1) DEFAULT NULL,
  `psr_terceiroSextante` varchar(1) DEFAULT NULL,
  `psr_quartoSextante` varchar(1) DEFAULT NULL,
  `psr_quintoSextante` varchar(1) DEFAULT NULL,
  `psr_sextoSextante` varchar(1) DEFAULT NULL,
  `psr_informacoes` varchar(550) DEFAULT NULL,
  PRIMARY KEY (`psr_cod`),
  UNIQUE KEY `psr_ref_paciente` (`psr_ref_paciente`) USING BTREE,
  CONSTRAINT `FK_psr_1` FOREIGN KEY (`psr_ref_paciente`) REFERENCES `paciente` (`pac_RG`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `psr`
--

/*!40000 ALTER TABLE `psr` DISABLE KEYS */;
INSERT INTO `psr` (`psr_cod`,`psr_ref_paciente`,`psr_primeiroSextante`,`psr_segundoSextante`,`psr_terceiroSextante`,`psr_quartoSextante`,`psr_quintoSextante`,`psr_sextoSextante`,`psr_informacoes`) VALUES 
 (1,'12714010','2','0','2','3','4','X','Bla bla bla'),
 (2,'2','2','0','0','0','0','0','');
/*!40000 ALTER TABLE `psr` ENABLE KEYS */;


--
-- Definition of table `saude`
--

DROP TABLE IF EXISTS `saude`;
CREATE TABLE `saude` (
  `cod_pac_sa` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sa_QueixaPrincipal` varchar(600) NOT NULL,
  `sa_Doenca` int(10) unsigned NOT NULL,
  `sa_QuaisDoencas` varchar(150) DEFAULT 'nenhuma',
  `sa_TratamentoMedico` int(10) unsigned NOT NULL,
  `sa_Medicacao` int(10) unsigned NOT NULL,
  `sa_QuaisMedicacoes` varchar(1500) DEFAULT NULL,
  `sa_Alergia` int(10) unsigned NOT NULL,
  `sa_QuaisAlergias` varchar(45) DEFAULT NULL,
  `sa_Operado` int(10) unsigned NOT NULL,
  `sa_QuaisOperacoes` varchar(120) DEFAULT NULL,
  `sa_ProblemaAnestesia` int(10) unsigned NOT NULL,
  `sa_ProblemaHemorragia` int(10) unsigned NOT NULL,
  `sa_PesoSaude` int(10) unsigned DEFAULT NULL,
  `sa_ProblemaCicatrizacao` int(10) unsigned NOT NULL,
  `sa_Gravidez` int(10) unsigned NOT NULL,
  `sa_QuaisTratamentosMedicos` varchar(200) NOT NULL,
  `sa_ReferenciaRG` varchar(45) NOT NULL,
  PRIMARY KEY (`cod_pac_sa`),
  UNIQUE KEY `FK_saudeRG` (`sa_ReferenciaRG`) USING BTREE,
  CONSTRAINT `FK_saude_1` FOREIGN KEY (`sa_ReferenciaRG`) REFERENCES `paciente` (`pac_RG`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saude`
--

/*!40000 ALTER TABLE `saude` DISABLE KEYS */;
INSERT INTO `saude` (`cod_pac_sa`,`sa_QueixaPrincipal`,`sa_Doenca`,`sa_QuaisDoencas`,`sa_TratamentoMedico`,`sa_Medicacao`,`sa_QuaisMedicacoes`,`sa_Alergia`,`sa_QuaisAlergias`,`sa_Operado`,`sa_QuaisOperacoes`,`sa_ProblemaAnestesia`,`sa_ProblemaHemorragia`,`sa_PesoSaude`,`sa_ProblemaCicatrizacao`,`sa_Gravidez`,`sa_QuaisTratamentosMedicos`,`sa_ReferenciaRG`) VALUES 
 (1,'Sem queixa',0,'',0,0,'',0,'',0,'',0,0,NULL,0,0,'','12714010'),
 (2,'PACIENTE 2PACIENTE 2PACIENTE 2PACIENTE 2PACIENTE 2PACIENTE 2PACIENTE 2PACIENTE 2PACIENTE 2PACIENTE 2',1,'PACIENTE 2',1,1,'PACIENTE 2',1,'PACIENTE 2',1,'PACIENTE 2',1,1,NULL,1,1,'PACIENTE 2','2'),
 (3,'',0,'',0,0,'',0,'',0,'',0,0,NULL,0,0,'','3');
/*!40000 ALTER TABLE `saude` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `cod_usuario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `usu_nome` varchar(100) NOT NULL,
  `usu_senha` varchar(45) NOT NULL,
  `usu_tipo` int(10) unsigned DEFAULT NULL,
  `usu_telefone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`cod_usuario`,`usu_nome`,`usu_senha`,`usu_tipo`,`usu_telefone`) VALUES 
 (1,'Renier','qazwsx',NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
