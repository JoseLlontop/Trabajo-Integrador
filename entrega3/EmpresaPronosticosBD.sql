-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 25-04-2023 a las 00:20:43
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `EmpresaPronosticosBD`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Pronostico`
--

CREATE TABLE `Pronostico` (
  `idPronostico` smallint(100) NOT NULL,
  `participante` varchar(100) NOT NULL,
  `equipo1` varchar(100) NOT NULL,
  `gana1` char(1) NOT NULL,
  `empate` char(1) NOT NULL,
  `gana2` char(1) NOT NULL,
  `Equipo2` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Pronostico`
--

INSERT INTO `Pronostico` (`idPronostico`, `participante`, `equipo1`, `gana1`, `empate`, `gana2`, `Equipo2`) VALUES
(1, 'Mariana', 'Argentina', 'X', '', '', 'Ecuador'),
(2, 'Mariana', 'Polonia', '', 'X', '', 'Mexico'),
(3, 'Pedro', 'Argentina', 'X', '', '', 'Ecuador'),
(4, 'Pedro', 'Polonia', '', 'X', '', 'Mexico'),
(5, 'Mariana', 'Peru', '', '', 'X', 'Colombia'),
(6, 'Mariana', 'Chile', '', '', 'X', 'Mexico'),
(7, 'Pedro', 'Peru', '', '', 'X', 'Colombia'),
(8, 'Pedro', 'Chile', '', '', 'X', 'Mexico');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Pronostico`
--
ALTER TABLE `Pronostico`
  ADD PRIMARY KEY (`idPronostico`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Pronostico`
--
ALTER TABLE `Pronostico`
  MODIFY `idPronostico` smallint(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
