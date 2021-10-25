-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : Dim 24 oct. 2021 à 23:48
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
-- Base de données : `projetjava`
--

-- --------------------------------------------------------

--
-- Structure de la table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
CREATE TABLE IF NOT EXISTS `consultation` (
  `id_consultation` int(25) NOT NULL AUTO_INCREMENT,
  `medecin_id` int(25) NOT NULL,
  `ordonnance_id` int(25) NOT NULL,
  `date_consultation` date NOT NULL,
  `prestation_lib` varchar(255) NOT NULL,
  PRIMARY KEY (`id_consultation`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `dossiermedical`
--

DROP TABLE IF EXISTS `dossiermedical`;
CREATE TABLE IF NOT EXISTS `dossiermedical` (
  `id_dossier` int(11) NOT NULL AUTO_INCREMENT,
  `consultation_id` int(11) NOT NULL,
  `prestation_id` int(11) NOT NULL,
  PRIMARY KEY (`id_dossier`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

DROP TABLE IF EXISTS `medecin`;
CREATE TABLE IF NOT EXISTS `medecin` (
  `id_medecin` int(11) NOT NULL AUTO_INCREMENT,
  `sexe` varchar(25) NOT NULL,
  `domaine` varchar(255) NOT NULL,
  PRIMARY KEY (`id_medecin`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ordonnance`
--

DROP TABLE IF EXISTS `ordonnance`;
CREATE TABLE IF NOT EXISTS `ordonnance` (
  `id_ordonnance` int(11) NOT NULL AUTO_INCREMENT,
  `medicaments` varchar(255) NOT NULL,
  `posologie` varchar(255) NOT NULL,
  PRIMARY KEY (`id_ordonnance`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `id_patient` int(11) NOT NULL AUTO_INCREMENT,
  `sexe` varchar(25) NOT NULL,
  `antecedentsMedic` varchar(255) NOT NULL,
  PRIMARY KEY (`id_patient`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `prestation`
--

DROP TABLE IF EXISTS `prestation`;
CREATE TABLE IF NOT EXISTS `prestation` (
  `id_prestation` int(11) NOT NULL AUTO_INCREMENT,
  `nom_complet` varchar(255) NOT NULL,
  `date_prestation` date NOT NULL,
  `resultat` varchar(255) NOT NULL,
  PRIMARY KEY (`id_prestation`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

DROP TABLE IF EXISTS `rendezvous`;
CREATE TABLE IF NOT EXISTS `rendezvous` (
  `id_rendezVous` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) NOT NULL,
  `typeMedecin` varchar(255) NOT NULL,
  `typePrestation` varchar(255) NOT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`id_rendezVous`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `nomComplet` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
