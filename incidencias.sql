-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-05-2024 a las 17:58:48
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `appincidencias`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencias`
--

CREATE TABLE `incidencias` (
  `codigo` varchar(255) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `puesto` int(11) DEFAULT NULL,
  `problema` varchar(255) DEFAULT NULL,
  `fechaResolucion` date DEFAULT NULL,
  `resolucion` varchar(255) DEFAULT NULL,
  `fechaEliminacion` date DEFAULT NULL,
  `causaEliminacion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `incidencias`
--

INSERT INTO `incidencias` (`codigo`, `estado`, `puesto`, `problema`, `fechaResolucion`, `resolucion`, `fechaEliminacion`, `causaEliminacion`) VALUES
('26/05/2024-12:50-1', 'PENDIENTE', 1, 'No va', NULL, NULL, NULL, NULL),
('26/05/2024-12:52-1', 'PENDIENTE', 55, 'No arranca', NULL, NULL, NULL, NULL),
('26/05/2024-14:02-1', 'PENDIENTE', 44, 'No va wifi', NULL, NULL, NULL, NULL),
('26/05/2024-14:08-1', 'PENDIENTE', 78, 'Wifi', NULL, NULL, NULL, NULL),
('26/05/2024-15:24-1', 'PENDIENTE', 58, 'Reiniciar router', NULL, NULL, NULL, NULL),
('26/05/2024-16:02-1', 'PENDIENTE', 88, 'No va el wifi', NULL, NULL, NULL, NULL);
COMMIT;

-- Crear usuario y otorgar privilegios
CREATE USER 'irene'@'localhost' IDENTIFIED BY '12345';
GRANT ALL PRIVILEGES ON appincidencias.* TO 'irene'@'localhost';
FLUSH PRIVILEGES;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
