-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 22 jan. 2022 à 12:43
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `clinique_221`
--

-- --------------------------------------------------------

--
-- Structure de la table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
CREATE TABLE IF NOT EXISTS `consultation` (
  `id_consultation` int(25) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) NOT NULL,
  `medecin_id` int(25) NOT NULL,
  `ordonnance` text,
  `date_consultation` date NOT NULL,
  `prestation_lib` varchar(25) DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  `statut` varchar(25) NOT NULL,
  PRIMARY KEY (`id_consultation`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `consultation`
--

INSERT INTO `consultation` (`id_consultation`, `libelle`, `medecin_id`, `ordonnance`, `date_consultation`, `prestation_lib`, `patient_id`, `statut`) VALUES
(1, 'Generaliste', 8, NULL, '2022-01-26', NULL, 6, 'A FAIRE'),
(2, 'Generaliste', 9, NULL, '2022-01-26', NULL, 6, 'A FAIRE'),
(3, 'Dentiste', 10, NULL, '2022-01-26', NULL, 6, 'ANNULE'),
(4, 'Dentiste', 10, NULL, '2022-01-28', NULL, 4, 'A FAIRE'),
(5, 'Dentiste', 10, 'haki des rois 100mg/jour', '2022-01-22', 'Vasectomie', 4, 'TERMINEE');

-- --------------------------------------------------------

--
-- Structure de la table `prestation`
--

DROP TABLE IF EXISTS `prestation`;
CREATE TABLE IF NOT EXISTS `prestation` (
  `id_prestation` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) NOT NULL,
  `statut` varchar(255) NOT NULL,
  `date_prestation` date NOT NULL,
  `resultat` varchar(255) DEFAULT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`id_prestation`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `prestation`
--

INSERT INTO `prestation` (`id_prestation`, `libelle`, `statut`, `date_prestation`, `resultat`, `patient_id`) VALUES
(1, 'Analyse', 'ANNULE', '2022-01-21', NULL, 6),
(2, 'Radio', 'A FAIRE', '2022-01-25', NULL, 6),
(3, 'Radio', 'A FAIRE', '2022-01-27', NULL, 4),
(4, 'Détartrage dentaire', 'A FAIRE', '2022-01-29', NULL, 4),
(5, 'Chirurgie', 'TERMINEE', '2022-01-22', 'L\'implantation du nouveau rein a été un franc succès', 6),
(6, 'Chirurgie', 'A FAIRE', '2022-01-24', NULL, 4);

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
CREATE TABLE IF NOT EXISTS `rendezvous` (
  `id_rendezVous` int(11) NOT NULL AUTO_INCREMENT,
  `statut` varchar(25) NOT NULL,
  `service` varchar(255) NOT NULL,
  `typemp_id` int(11) NOT NULL,
  `date_rdv` date NOT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`id_rendezVous`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rendezvous`
--

INSERT INTO `rendezvous` (`id_rendezVous`, `statut`, `service`, `typemp_id`, `date_rdv`, `patient_id`) VALUES
(1, 'VALIDE', 'Consultation', 1, '2022-01-26', 6),
(2, 'VALIDE', 'Consultation', 2, '2022-01-26', 6),
(3, 'VALIDE', 'Prestation', 6, '2022-01-21', 6),
(4, 'REFUSE', 'Consultation', 3, '2022-01-26', 6),
(5, 'VALIDE', 'Prestation', 7, '2022-01-25', 6),
(6, 'VALIDE', 'Prestation', 8, '2022-01-26', 6),
(7, 'VALIDE', 'Consultation', 4, '2022-01-27', 6),
(8, 'VALIDE', 'Prestation', 8, '2022-01-24', 4),
(9, 'VALIDE', 'Consultation', 1, '2022-01-25', 4),
(10, 'VALIDE', 'Prestation', 7, '2022-01-27', 4),
(11, 'VALIDE', 'Consultation', 2, '2022-01-28', 4),
(12, 'VALIDE', 'Prestation', 9, '2022-01-29', 4),
(13, 'VALIDE', 'Consultation', 1, '2022-01-26', 4),
(14, 'REFUSE', 'Prestation', 6, '2022-01-21', 4),
(15, 'VALIDE', 'Consultation', 1, '2022-01-22', 6);

-- --------------------------------------------------------

--
-- Structure de la table `typemedecin`
--

DROP TABLE IF EXISTS `typemedecin`;
CREATE TABLE IF NOT EXISTS `typemedecin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `typemedecin`
--

INSERT INTO `typemedecin` (`id`, `libelle`) VALUES
(1, 'Generaliste'),
(2, 'Dentiste'),
(3, 'Ophtalmologue'),
(4, 'Rhumatologue'),
(5, 'Pédiatre');

-- --------------------------------------------------------

--
-- Structure de la table `typemp`
--

DROP TABLE IF EXISTS `typemp`;
CREATE TABLE IF NOT EXISTS `typemp` (
  `id_typemp` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) NOT NULL,
  PRIMARY KEY (`id_typemp`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `typemp`
--

INSERT INTO `typemp` (`id_typemp`, `libelle`) VALUES
(1, 'Generaliste'),
(2, 'Dentiste'),
(3, 'Ophtalmologue'),
(4, 'Rhumatologue'),
(5, 'Pédiatre'),
(6, 'Analyse'),
(7, 'Radio'),
(8, 'Chirurgie'),
(9, 'Détartrage dentaire');

-- --------------------------------------------------------

--
-- Structure de la table `typeprestation`
--

DROP TABLE IF EXISTS `typeprestation`;
CREATE TABLE IF NOT EXISTS `typeprestation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `typeprestation`
--

INSERT INTO `typeprestation` (`id`, `libelle`) VALUES
(6, 'Analyse'),
(7, 'Radio'),
(8, 'Chirurgie'),
(9, 'Détartrage dentaire');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_complet` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `matricule` varchar(25) DEFAULT NULL,
  `nci` varchar(25) DEFAULT NULL,
  `antecedents` text,
  `specialite` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom_complet`, `login`, `password`, `role`, `matricule`, `nci`, `antecedents`, `specialite`) VALUES
(1, 'Didier SEWAGNON', 'admin@gmail.com', 'admin', 'ROLE_ADMIN', NULL, NULL, NULL, NULL),
(3, 'abcd', 'efg@ij.k', 'lmn', 'ROLE_PATIENT', NULL, '1234', 'opq', NULL),
(4, 'esso kume', 'akume@gmail.com', 'akume01', 'ROLE_PATIENT', NULL, '6969', 'a été mangé par Kaido', NULL),
(5, 'qfhfs', 'sqbhb@dfj.u', '5678', 'ROLE_PATIENT', NULL, '', '', NULL),
(6, 'patient 1', 'patient@gmail.com', 'patient01', 'ROLE_PATIENT', NULL, '781854127', 'Asthmatique', NULL),
(7, 'secretaire', 'secretaire@gmail.com', 'secretaire01', 'ROLE_SECRETAIRE', NULL, '6789', NULL, NULL),
(8, 'generaliste 1', 'generaliste@gmail.com', 'generaliste01', 'ROLE_MEDECIN', NULL, '1011', NULL, 'generaliste'),
(9, 'generaliste 2', 'generaliste2@gmail.com', 'generaliste02', 'ROLE_MEDECIN', NULL, '1213', NULL, 'generaliste'),
(10, 'dentiste 1', 'dentiste@gmail.com', 'dentiste01', 'ROLE_MEDECIN', NULL, '1415', NULL, 'dentiste'),
(11, 'responsable 1', 'responsable@gmail.com', 'responsable01', 'ROLE_RP', NULL, '1617', NULL, NULL),
(12, 'patient 2', 'patient02@gmail.com', 'patient02', 'ROLE_PATIENT', NULL, '', '', NULL),
(13, 'William Camara', 'willcam@gmail.com', 'willcam01', 'ROLE_PATIENT', NULL, '96742635', 'bof', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
