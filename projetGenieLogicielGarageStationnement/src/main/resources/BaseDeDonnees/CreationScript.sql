CREATE TABLE `client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `NumeroTel` int(10) NOT NULL,
  `NumeroCarte` bigint(20) NOT NULL,
  `Mail` varchar(255) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE KEY `Mail_UNIQUE` (`Mail`)
);