-- phpMyAdmin SQL Dump
-- version 3.5.8.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 06-03-2015 a las 09:39:38
-- Versión del servidor: 5.5.41-37.0-log
-- Versión de PHP: 5.4.23

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `justrunn_app`
--
CREATE DATABASE `justrunn_app` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `justrunn_app`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `sp_insert_gcmuser`$$
$$

DROP PROCEDURE IF EXISTS `sp_insert_user`$$
$$

DROP PROCEDURE IF EXISTS `sp_set_user_race`$$
$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_CATEGORY`
--

DROP TABLE IF EXISTS `RB_CATEGORY`;
CREATE TABLE IF NOT EXISTS `RB_CATEGORY` (
  `rbcategoryid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`rbcategoryid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `RB_CATEGORY`
--

INSERT INTO `RB_CATEGORY` (`rbcategoryid`, `name`, `date_created`) VALUES
(1, 'Road', '2014-08-16 00:00:00'),
(2, 'Trail', '2014-08-16 00:00:00'),
(3, 'Half Marathon', '2014-08-16 00:00:00'),
(4, 'Marathon', '2014-10-06 21:35:12'),
(5, 'Ultra Marathon', '2014-10-06 21:35:13'),
(6, 'Triathlon', '2014-10-06 21:35:13'),
(7, 'Duathlon', '2014-10-19 23:07:35');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_COMMENT_RACE`
--

DROP TABLE IF EXISTS `RB_COMMENT_RACE`;
CREATE TABLE IF NOT EXISTS `RB_COMMENT_RACE` (
  `rbcommentraceid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `comment` varchar(300) DEFAULT NULL,
  `poitns` double DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  `pos_points` int(11) DEFAULT '0',
  `neg_points` int(11) DEFAULT '0',
  `rbraceid` int(11) NOT NULL,
  `rbuserid` int(11) NOT NULL,
  PRIMARY KEY (`rbcommentraceid`),
  KEY `fk_RB_COMMENT_RACE_RB_RACE1_idx` (`rbraceid`),
  KEY `fk_RB_COMMENT_RACE_RB_USER1_idx` (`rbuserid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_COMMENT_TRAIN`
--

DROP TABLE IF EXISTS `RB_COMMENT_TRAIN`;
CREATE TABLE IF NOT EXISTS `RB_COMMENT_TRAIN` (
  `rbcommenttrain` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(300) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_modified` datetime NOT NULL,
  `pos_points` int(11) DEFAULT '0',
  `neg_points` int(11) DEFAULT '0',
  `rbtrainid` int(11) NOT NULL,
  `rbuserid` int(11) NOT NULL,
  PRIMARY KEY (`rbcommenttrain`),
  KEY `fk_RB_COMMENT_TRAIN_RB_TRAIN1_idx` (`rbtrainid`),
  KEY `fk_RB_COMMENT_TRAIN_RB_USER1_idx` (`rbuserid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_GCM_USERS`
--

DROP TABLE IF EXISTS `RB_GCM_USERS`;
CREATE TABLE IF NOT EXISTS `RB_GCM_USERS` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gcm_regid` text,
  `name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=54 ;

--
-- Volcado de datos para la tabla `RB_GCM_USERS`
--

INSERT INTO `RB_GCM_USERS` (`id`, `gcm_regid`, `name`, `email`, `created_at`) VALUES
(32, 'APA91bHV1F8r5lVO_dsJW4xieBlp962gM7gOT3KUgC0J1wkGnJTKelSh8Q0fA2VVZCW9Igh0oanfHGn3jyLjixudyUDVFDyG0HVih1ma5JO_PMwNd15l7hW1U48kZ1nTYXx-2TeKa4oY5K3MTBlXnu8od8KVYVDO8dSUkwnjk6Am-KM-VKKMQMQ', '10152472518728443', 'hikaru_182@hotmail.com', '2014-11-18 02:55:35'),
(38, 'APA91bFvKP0Ec0GvgIOPo9ovr-Ycweez2pk6zbNAg7X7x5TWKSy9aEQseya29jfyX2NTQB-YDashI6yU8v-mtKXV9SsVM00dyt-g8YgoQIe4tihjhkwpOQdTuBzrrDnqISt3HNYO0qX6BKssxx82GDCueMxQ-WGfOuJZbOhKsYkB0luXYKzai6ZBl0z7BGc6r-MFgQo8-w1T', '10205549303670940', 'alvaro-nunez4@hotmail.com', '2014-11-30 18:12:12'),
(39, 'APA91bHzk3nWymcPgZA2tvRE2PG5oDr3eYvlh9lwIyfbxVH0c2O8XcMt_u0k5xQKxoyd-GZ_5juJqXRX6gm79MxitFsaWndw9a64dGetRWbHgWB7b7P5KfwXVYpn3uo8zJaQlKsypuYTkgQCjoV2KJnfCiWDpNMWEcdhkBL8yplkaoqSkUV-yVA', '10154868880040057', 'pattylarin@hotmail.com', '2014-12-01 03:30:46'),
(48, 'APA91bHv148RCwjR6YpdBWAl697BJ6YzbkDJlbHoLuVsACBwOxNo0M_UOPyzS62m-lVqSv6Up2_JsFVy-jEnGp1irERYlbKlpkcfXuxP19CSL7KN-HZhmn4o4oFw9uXj5v0OhEOppkX4S4BLYGKxjSqvPZ3Df4QHLeXqwUFiJp_6uFS7joV91Dg', '857774240911064', 'ecalderon1991@gmail.com', '2014-12-08 20:22:03'),
(49, 'APA91bEGQmE8A2Gh4h90MvElpzazaj6k1XGxpVX7dmWr8SabNpyvaFbl6bkRvDdCOnZ_kvMvTNvLlzBtE-X-1-vJOix4D5NZlHrO3MX9UA9r-ISuJ-XG1mi6exrViBTbFrkPkXe_JofhT7VjQq9POPG5S1RJGH6x7LFZTkYTniVieN4e1gj_gI9Me5Zr4PeT6uYDAPWtpyZc', '895039157187352', 'alvinmelara16@gmail.com', '2014-12-09 23:03:52'),
(50, 'APA91bHp3SMHT_2LgV3gsjGkUEjUiFJgQpyBOjLEkSwTIr-j-c6PPteEYr05cECCWCki7JHWtS0Y9cCGPUy0b0WbpHBcQArM29vkliaMFgapkyLiwZ_PsUZ7o2stjIh-5LiaS-RvzbsYyTE5Tt-P0dWvOFDgeTT_seX5bixzWE_ejVjMFHHOVvw', '798447893538414', 'ecuellarsanchez180@gmail.com', '2014-12-13 16:29:49'),
(53, 'APA91bFT68X_Ihc_D9iY2p6DXkQC0yvmqPFmJlqRle994AgmFiEOIng7cEmgLwyDL0llafLcwdjl5epamDnSkxUUFXsKXBfZftTv24o_RaPxMy7KfpW5zhGlWVczNZWVDYElPYnRIFz_-Grli4et2jQz1kKKm3d2h4RZoQoh1Kze86pVCcEE0MCii_rVFT0pKexhEJOTEEJx', '735615096526564', 'fredd.seb8@gmail.com', '2015-03-05 21:41:06');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_GROUP`
--

DROP TABLE IF EXISTS `RB_GROUP`;
CREATE TABLE IF NOT EXISTS `RB_GROUP` (
  `rbgroupid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `location` varchar(150) DEFAULT NULL,
  `schedule` varchar(500) DEFAULT NULL,
  `description` mediumtext,
  `facebook` varchar(45) DEFAULT NULL,
  `twitter` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `photo` varchar(150) NOT NULL,
  `night` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`rbgroupid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Volcado de datos para la tabla `RB_GROUP`
--

INSERT INTO `RB_GROUP` (`rbgroupid`, `name`, `location`, `schedule`, `description`, `facebook`, `twitter`, `email`, `photo`, `night`, `status`, `date_created`, `date_modified`) VALUES
(1, 'Active Runners', 'Parque Central de  Antiguo Cuscatlán, La Libertad', 'Martes y Jueves 8:00pm', 'Active Runners nació en Abril del año 2012 inicialmente con el nombre de Antiguo Runners a raíz de la localidad de sus inicios. Un pequeño grupo de corredores aproximadamente de 10 integrantes salía a correr, liderados por Gerardo Calderón, fundador del grupo. En una de esas corridas nocturnas ocurrió un hecho lamentable ya que un corredor fue golpeado por un carro, afortunadamente no le paso nada grave al corredor. Este hecho unió más al grupo y colocaron el lema: “Yo corro por la seguridad del peatón y el deportista”. A través de los años se han incrementando sus integrantes, llegando a ser cientos de corredores por noche, provenientes de distintos lugares del país. A causa de esto el nombre del grupo pasó a llamarse Active Runners, que hoy en día continúa creciendo y ha llegado a convertirse en el movimiento de corredores más grande del país. Además de las corridas nocturnas Active Runners organiza carreras benéficas y se destaca que varios de sus integrantes se han convertido en  triatlonistas y maratonistas', 'Active Runnersv', '', 'active.runnersv@gmail.com', 'http://just-runners.com/images/groups/active_r/icon.jpg', 1, 1, '2014-08-31 00:00:00', '2014-12-04 10:28:28'),
(2, 'Trail Runners El Salvador', 'Centro Comercial Las Azaleas en San Salvador', 'Martes y Jueves, H: 4:50 a.m. Sábados G1: (Básico) G2: (Intermedio), G3: (Avanzado) . Lugar de salida: Entrada principal del Parque del Bicentenario. Hora de salida: 5:00-5:30 a.m.', 'Trail Runners el Salvador nació en 2011, con la finalidad de promover el deporte de Trail Running y desarrollar nuevos talentos en dicha modalidad. Hoy en día, contamos con atletas de alto rendimiento y de todo nivel con participaciones en las distintas competencias a nivel internacional,  en pasíses tales como: Argentina, Chile, Estados Unidos, Costa Rica, Nicaragua y Guatemala. De igual manera contamos con un entrenador personal, especializado en la rama.    ¿Quiénes somos? Somos un grupo de personas que compartimos la afición por correr en montaña. Hemos logrado formar un equipo de corredores, que nos divertimos y al mismo tiempo contribuimos a mejorar tanto a nivel individual como colectivo. Misión Fomentar e impulsar el deporte de Trail Running, combinado por valores, proyección social y un enfoque a cuidar nuestro entorno ecológico, interesados en buscar nuevos talentos para impulsarlos y contribuir a alcanzar nuevos retos deportivos. Orientar a las personas a la mejora continua y obtención de buenos resultados. Visión Ser el mejor equipo de “Trail” en El Salvador con proyección al área de Centro América, conformado por un grupo de atletas de todo nivel que se caracterizan por ser integrales consigo mismo y el entorno, reflejando la calidad humana en lo que hacemos. Código y valores  Un grupo de personas que aman el deporte al aire libre, que buscan la armonía de grupo, compañerismo genuino, y la búsqueda de las buenas normas hacia los demás Nuestros Valores fundamentales son: •	Respeto (hacia los demás y hacia el medioambiente)•	Solidaridad •	Trabajo en Equipo.  Informació de contacto: E-mail: TrailRunnersSv@gmail.com Tél. 7877-1971', 'Trail Runners El Salvador', NULL, NULL, 'http://just-runners.com/images/groups/trail_r/icon.jpg', 0, 1, '2014-08-31 00:00:00', '2014-12-05 09:09:33'),
(3, 'Movistar Runners', 'San Salvador, El Salvador del Mundo', '', 'A inicios de 2013 Movistar se une al programa de ''Empresa Saludable''. Con el apoyo de run El Salvador forman un equipo de corredores para entrenarlos para la carrera Night Run 2013 de Movistar. En 2014 los entrenos aumentaron su intensidad y el equipo se solidifica y brinda frutos conquistando retos cada vez mas desafiantes.\r\n\r\nEste equipo de corredores a sido un verdadero éxito y testigo del fruto de los entrenamientos y la perseverancia y prueba de ello es que la mayoría de sus integrantes ya son medio-maratonistas ya que conquistaron la ciudad de Guatemala en los 21K edición 2014.', '', NULL, NULL, 'http://just-runners.com/images/groups/movistar_r/icon.png', 1, 1, '2014-09-05 00:00:00', '2014-11-18 23:30:19'),
(4, 'Ahuachapán Runners', 'Ahuachapán, Quiosco del parque Concordia', 'Viernes a las 7:30 pm y Sábados a las 7:00 pm', 'Somos un grupo de personas aficionadas al deporte del correr, Siempre en ambiente de amistad no competitivo. Si tienes voluntad de correr ven y acompáñanos', 'Ahuachapán Runnersv', NULL, NULL, 'http://thedailyrunners.com/images/groups/ahuachapan_r/icon.jpg', 1, 1, '2014-11-30 12:26:19', NULL),
(5, 'Cafeta Runners', 'La Libertad,Santa Tecla , entrada principal del Cafetalón', 'Sabados a las 6:00 am ', 'Somos un grupo de personas que nos gusta realizar actividades físicas, especialmente correr ya sea por salud, mejorar nuestro estado físico, recreación o para alcanzar nuestras propias metas.', 'Grupo: Cafeta Runners', NULL, NULL, 'http://thedailyrunners.com/images/groups/cafeta_r/icon.jpg', 0, 1, '2014-11-30 12:43:28', NULL),
(7, 'Team IRON BODY running', 'Santa Ana. Gym Iron Body ubicado sobre 6C OTE entre  11y13 AV NTE', 'Jueves 6:45pm', 'Inició el 20 de junio de 2013. En el que un grupo de amigos, que asisten al Gimnasio Iron body deciden convivir de una manera diferente y saludable, a través de salidas a correr. Salidas que en poco tiempo se convirtieron en entrenos y que llamaron la atención de muchos amigos del mismo gimnasio de ahí depende el nombre "Team Iron Body Running Santa Ana”. Pues en ese entonces todos los integrantes asistían a dicho Gimnasio. Poco a poco los entrenos se convirtieron en una pasión al correr, en las que muchas personas deseaban incorporarse al grupo por tal razón se convirtió en un grupo abierto al que puede pertenecer cualquier persona sin importar su edad o experiencia en el running. Salimos a correr a diversas calles de Santa Ana en 3 grupos de diferentes categorías: principiantes, intermedios y avanzados. Durante el recorrido se brinda asistencia e hidratación y al finalizar un estiramiento. TEAM IRON BODY RUNNING no es un grupo de corredores es una familia en las que se comparte grandes momentos bajo el lema “No te detengas cuando estés cansado, hazlo cuando termine la carrera”.', 'Team IRON BODY running', NULL, NULL, 'http://thedailyrunners.com/images/groups/gymironbody_r/icon.jpg', 1, 1, '2014-12-02 20:21:30', NULL),
(6, 'BEERunners', 'San Salvador Centro Comercial Las Azaleas', 'Para mayor información escribe a nuestra Fanpage o a nuestro twitter  @BEERunners', 'Somos un grupo de maratonistas que fomentamos el deporte como forma de compartir y hacer amigos y que también fuera de la pista disfrutamos de un "parcito". Todo es para diversión sana, sin abusos. El grupo lo empezamos entre amigos oficialmente en Abril del 2013; la mayoría no corríamos más de cinco kilómetros a la vez y estábamos con sobrepeso desde entonces el grupo ha crecido; la mayoría nos hemos convertido en maratonistas y hemos logrado cambiar a un estilo de vida más sano. Participamos en las principales carreras de nuestro país y durante este año varios del grupo hemos participado en maratones y medias maratones internacionales (Cancún, San Francisco, New York, Dallas, Austin, Los Angeles, Miami, Guatemala, Cobán, Panamá, New Orleans, Chicago).', 'BEERunnerSV', NULL, NULL, 'http://thedailyrunners.com/images/groups/beer_r/icon.jpg', 0, 1, '2014-12-02 20:21:30', NULL),
(8, 'Betania Club de Atletismo', 'San Salvador Estadio Mágico González', NULL, 'Inició su recorrido en el atletismo nacional el lunes 1 de septiembre 2003. Nuestro  club  hay  personas que corren  tanto  por  salud   así  Cómo  también  atletas  del  alto  rendimiento  que  integran  la  selección  nacional y tanto  que   una  de  nuestras  ex atletas  asistió  a  Londres 2012. En este grupo de atletismo no se le cerró la puerta nadie desde el inicio sus integrantes en la mayoría eran jóvenes de los cantones alrededor de la ciudad de la palma Chalatenango. Hoy el grupo dejo sus raíces y la mayoría de sus integrantes son de la ciudad aunque todavía hay atletas que han dejado gran parte de su vida en este equipo desde su inicio, ahora podemos decir que el club de atletismo Betania que tiene entre sus integrantes atletas seleccionados nacionales de diferente categorías, atletas poseedores de records nacionales y sobre todo personas de bien a la sociedad. ', 'Usuario: Club De Atletismo Betania', NULL, NULL, 'http://thedailyrunners.com/images/groups/betania/icon.jpg', 0, 1, '2014-12-02 22:38:38', NULL),
(9, 'Addict Runners', 'Fnl Av Independencia Sur y 51 Cl en Gimnasio Megalife, Santa Ana', 'Miércoles  y Viernes desde 4:00 pm ... Fines de Semana (si no hay carreras), entrenos de fondos, con previo aviso de rutas.', ' Addict Runners, fundado el 22 de Marzo de 2013, por el atleta santaneco Néstor Martí. Hoy en día conformado por 50 atletas, quienes optaron por dos objetivos: “Acumular Kilómetros “y Ayudar a los menos desfavorecidos. Addict Runners  grupo de amigos que motiva a la práctica del deporte, a una vida saludable, a la solidaridad y sobre todo a divertirse  haciendo  deporte. Addict Runners  grupo de amigos que motiva a la práctica del deporte, a una vida saludable, a la solidaridad y sobre todo a divertirse  haciendo  deporte. “Ayúdanos a Sacar una Sonrisa” lema que utilizamos para realizar actividades altruistas para ayudar a las personas que más lo necesitan como: Ayuda al octavo piso del Hospital Nacional Benjamín Bloom, por dos años consecutivos se han otorgado donaciones al área de pediatría del Hospital Nacional San Juan de Dios de Santa Ana y organización de carreras para beneficios de entidades como: Club de Leones   y Asilo de ancianos “Narcisa Castillo” de la ciudad de Santa Ana. En Addict Runners hay diversidad de corredores desde los Principiantes – Intermedios –Avanzados. Entre los atletas más destacados de Addict Runners  están: Maratonista Femenino:   Rosa Irene Arana Girón Ultra Maratonistas: Miguel Sáenz y Yensi Alejandro Sáenz Maratonistas: Alejandro Fuentes, Carlos Padilla y Koky Salinas Orgullo de Addict  Runners.  Addict Runners espera a todos los que quieran tener disciplina deportiva, sin importar condición social, económica ni religiosa solo con el vicio de correr y “Acumular Kilómetros”.', 'Addict Runners', NULL, NULL, 'http://thedailyrunners.com/images/groups/addict_r/icon.jpg', 0, 1, '2014-12-09 11:57:53', NULL),
(10, 'After Office Runners', 'Parque Madre Selva de Santa Elena, La Libertad\n', 'Martes 6:30pm ', 'Somos un grupo de corredores, la mayoría provinientes de oficinas y negocios de toda la zona de Santa Elena.\nQueremos darle a las personas una oportunidad de empezar a correr en horario conveniente y en buena zona, formar\nnuevos corredores, somos un grupo  que queremos  formar o redimir a gente para esta tendencia de correr en la calle.\n\nEl grupo se formó a inicios del año 2014, estamos creciendo de a poco y la fortaleza del grupo es que no cambiamos los\nhorarios ni la hora, para que no tengamos problemas de ubicación y horarios.', 'After Office Runners', NULL, NULL, 'http://thedailyrunners.com/images/groups/afteroffice_r/icon.jpg', 1, 1, '2015-02-04 22:02:27', NULL),
(11, 'Club de Atletismo Fénix El Salvador', 'Sonsonate, Estadio de Izalco', 'Fénix realiza sus entrenamientos de grupo en el estadio de Izalco lunes miércoles y viernes, con supervisión del entrenador y martes y jueves solos en un horario de 6:00am a 7:30am martes jueves en Armenia en igual horario y sábados trabajo en equipo en algún lugar de Sonsonate en dependencia de la preparación en la que se encuentre o si es pista del estadio Mágico Gonzales en San Salvador', 'somos un grupo abierto que recibe a cualquier atleta que desee mejorar marcas y competir en un alto nivel, nuestro grupo es selectivo y ordenado compite en eventos avalados por la federación salvadoreña de atletismo y la federación internacional de atletismo, en la actualidad FÉNIX es el vigente campeón de el cross country federado. \ncountry federado. \n\nY campeón Centro americano de cross country  en la categoría Juvenil B masculina y femenina en la cual fue campeón de relevos, y absoluto por país. Contamos con atletas muy buenos que nos representaran en las competencias federadas de este año y en algunas de runners en las que allá dinero o algún premio importante. Fénix tiene atletas no solo de fondo si no de todas las pruebas ya sea velocidad, lanzamientos y pruebas combinadas.\n\nEl motivo surge, de manos del entrenador amante de las carreras que en su etapa de estudiante careció de oportunidades de ser reconocido en su natal Armenia, en donde era visto como un loco que corría por las calles, al salir de la universidad toma el reto de formar un club de atletismo en Armenia, en donde a sus inicios no tuvo colaboración de nadie solo de algunos padres que aceptaron el reto, niños de los cuales en este momento se encuentran entre los mejores del país. Nace como club de atletismo Armenia en el 2012, y poco a poco empezó a tener atletas de no solo Armenia si no que también de Izalco, motivo por el cual se decidió cambiar el nombre a FENIX,  que para nosotros es renacer desde, las cenizas, pero iniciando como campeones nacionales de cross. Iniciamos con 10 niños; en la actualidad es un club que cuenta con 35 atletas de diferentes partes del país.\n\nMotivo: correr por salud y darle un giro diferente a los niños y niñas de las zonas rurales y urbanas de Armenia e Izalco, en nuestro equipo la mayoría de los niños son de escasos recursos, ellos luchan por buscar una beca de estudio un viaje de representación del país, una beca deportiva y ser mejores personas día con día. \n', NULL, NULL, NULL, 'http://thedailyrunners.com/images/groups/fenix_r/icon.jpg', 0, 1, '2015-03-05 09:50:45', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_MEDALS`
--

DROP TABLE IF EXISTS `RB_MEDALS`;
CREATE TABLE IF NOT EXISTS `RB_MEDALS` (
  `rbusermedalsid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `photo` varchar(150) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`rbusermedalsid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_NEWS`
--

DROP TABLE IF EXISTS `RB_NEWS`;
CREATE TABLE IF NOT EXISTS `RB_NEWS` (
  `rbnewsid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `photo` varchar(150) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`rbnewsid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Volcado de datos para la tabla `RB_NEWS`
--

INSERT INTO `RB_NEWS` (`rbnewsid`, `title`, `description`, `url`, `photo`, `date_created`) VALUES
(2, '10 ejercicios para mejorar la eficiencia en carrera', 'Ejercicios', 'http://www.foroatletismo.com/ejercicios/ejercicios-mejorar-eficiencia-carrera/', 'http://just-runners.com/images/news/08/01.jpg', '2014-08-25 18:00:00'),
(3, 'Cafeina y ejercicio: una relación beneficiosa', 'Consejos', 'http://www.soymaratonista.com/23712/cafeina-y-ejercicio-una-relacion-beneficiosa', 'http://just-runners.com/images/news/08/02.jpg', '2014-08-25 00:00:00'),
(4, 'Preguntas frecuentes de los Corredores', 'Consejos', 'http://www.soymaratonista.com/91/preguntas-frecuentes', 'http://just-runners.com/images/news/08/03.jpg', '2014-08-25 00:00:00'),
(5, 'La publicidad para corredores prohibida | #Video', 'Publicidad', 'http://runfitners.com/2014/08/la-publicidad-para-corredores-prohibida-video/', 'http://just-runners.com/images/news/08/04.jpg', '2014-08-26 00:00:00'),
(6, 'Lo que un maratonista debe aprender de Game Of Thrones', NULL, 'http://runfitners.com/2014/07/lo-que-un-maratonista-debe-aprender-de-game-of-thrones/', 'http://just-runners.com/images/news/08/05.jpg', '2014-08-28 00:00:00'),
(7, 'El ''running'' llega a las vías del metro', 'Noticia', 'http://ccaa.elpais.com/ccaa/2014/08/29/catalunya/1409312816_004064.html', 'http://just-runners.com/images/news/08/06.jpg', '2014-08-29 00:00:00'),
(8, 'Dennis Kimetto, nuevo récord mundial de maraton', 'Keniano Dennis Kimmetto abre el el libro de los records para colocar su nueva marca en los 42 K corriendo', 'http://just-runners.com/index.php/10-cat-news/7-dennis-kimetto-maraton-record', 'http://just-runners.com/images/news/10/kimmetto.jpg', '2014-10-04 00:00:00'),
(9, 'Dieta sin Gluten, ¿Mejora el rendimiento físico?', NULL, 'http://thedailyrunners.com/index.php/menu-noticias/menu-sub1-mundo/86-dieta-sin-gluten', 'http://thedailyrunners.com/images/news/11/gluten.jpg', '2014-11-16 15:57:51'),
(10, 'Retomando el ritmo del entrenamiento', NULL, 'http://thedailyrunners.com/index.php/menu-noticias/menu-sub1-mundo/87-retomando-entrenos', 'http://thedailyrunners.com/images/news/11/retomando.jpg', '2014-11-16 15:57:51'),
(11, 'Buenas maneras en el correr', NULL, 'http://thedailyrunners.com/index.php/menu-noticias/menu-sub1-mundo/88-buenas-maneras-correr', 'http://thedailyrunners.com/images/news/11/buenasmaneras.jpg', '2014-11-16 15:57:52'),
(12, 'El ABC del buen corredor', NULL, 'http://thedailyrunners.com/index.php/menu-entrenamiento/men-i-tips/90-abc-buen-corredor', 'http://thedailyrunners.com/images/news/12/abcbuencorredor.jpg', '2014-12-04 20:52:38'),
(13, 'El coco: un fruto ideal', NULL, 'http://thedailyrunners.com/index.php/menu-salud/91-cocoideal', 'http://thedailyrunners.com/images/news/12/coco.jpg', '2014-12-04 21:10:23'),
(14, '¡Mantente activo en la oficina!', NULL, 'http://thedailyrunners.com/index.php/menu-salud/92-activoenoficina', 'http://thedailyrunners.com/images/news/12/oficina.jpg', '2014-12-04 21:18:23'),
(15, 'Plan para correr 10k en 40 minutos', NULL, 'http://thedailyrunners.com/index.php/menu-entrenamiento/planes/93-plan10ken40min', 'http://thedailyrunners.com/images/news/12/10k40.jpg', '2014-12-04 21:34:57'),
(16, '¿Qué pasa dentro de tu cuerpo cuando duermes?', NULL, 'http://thedailyrunners.com/index.php/menu-salud/94-qpasadormido', 'http://thedailyrunners.com/images/news/12/quepasacuandoduerme.jpg', '2014-12-04 21:55:35'),
(17, '¿Quién fue Filípides? La historia de la maratón', NULL, 'http://thedailyrunners.com/index.php/menu-noticias/menu-sub1-mundo/95-historia-maraton', 'http://thedailyrunners.com/images/news/12/Phidippides.jpg', '2014-12-17 14:21:57');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_ORGANIZER`
--

DROP TABLE IF EXISTS `RB_ORGANIZER`;
CREATE TABLE IF NOT EXISTS `RB_ORGANIZER` (
  `rborganizerid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `web_page` varchar(100) DEFAULT NULL,
  `facebook` varchar(45) DEFAULT NULL,
  `twitter` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_number` varchar(100) DEFAULT NULL,
  `points` double DEFAULT NULL,
  `account_status` int(11) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`rborganizerid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=27 ;

--
-- Volcado de datos para la tabla `RB_ORGANIZER`
--

INSERT INTO `RB_ORGANIZER` (`rborganizerid`, `name`, `web_page`, `facebook`, `twitter`, `email`, `phone_number`, `points`, `account_status`, `date_created`, `date_modified`) VALUES
(1, 'Run El Salvador', 'http://www.runelsalvador.com/', 'https://www.facebook.com/run.elsalvador', '@RunElSalvador', NULL, '2263 6464', 0, 1, '2014-08-16 00:00:00', NULL),
(2, 'Trail Runners', NULL, 'https://www.facebook.com/trailrunnerselsalvad', NULL, NULL, NULL, 0, 1, '2014-08-16 00:00:00', NULL),
(3, 'Running for Life', NULL, 'https://www.facebook.com/tito.carrera.79462?f', NULL, NULL, '2222-5643', 0, 1, '2014-08-16 00:00:00', NULL),
(4, 'Triatlon Costaman El Salvador', NULL, 'Triatlon Costaman El Salvador', NULL, 'carlostrisports@hotmail.com', '74680666,78024685, 73182461', 0, 1, '2014-08-16 00:00:00', NULL),
(5, 'Yo Amo ES', 'http://www.yoamoes.com/', 'Carreras y Maratones-YOAMOES', '@YOAMOSV', 'info@yoamoes.com', '2263-2757 , 2275-8279 Fax', 0, 1, '2014-08-16 00:00:00', NULL),
(6, 'Herbalife', 'http://www.herbalife.com.sv/', 'https://www.facebook.com/sv.herbalife', NULL, NULL, NULL, 0, 1, '2014-10-06 21:28:42', NULL),
(7, 'Running4Help', NULL, 'https://www.facebook.com/pages/Running4Help-E', NULL, 'running4helpelsalvador@gmail.com', NULL, 0, 1, '2014-10-06 22:01:58', NULL),
(8, 'Addict Runners', NULL, 'https://www.facebook.com/AddictRunners', '@AddictRunner_ES', NULL, NULL, 0, 1, '2014-10-06 22:13:07', NULL),
(9, 'Kumbres Guatemala', 'http://www.kumbresguatemala.com/', 'https://www.facebook.com/Kumbresdesafiodelava', NULL, NULL, NULL, 0, 1, '2014-10-07 16:26:07', NULL),
(10, 'Gatorade', NULL, NULL, NULL, NULL, NULL, 0, 1, '2014-11-15 23:05:21', NULL),
(11, 'Alcaldia De Ciudad Delgado', 'http://www.ciudaddelgado.gob.sv/', NULL, NULL, NULL, NULL, 0, 1, '2014-11-15 23:21:35', NULL),
(12, 'ACES', NULL, 'https://www.facebook.com/AutomovilClubdeElSal', NULL, NULL, NULL, 0, 1, '2014-11-15 23:33:51', NULL),
(13, 'Corredores del Istmo Panamá', 'http://www.corredoresdelistmo.com/', 'https://www.facebook.com/CorredoresDelIstmoPa', NULL, 'corredoresistmo@gmail.com', NULL, 0, 1, '2014-11-15 23:59:23', NULL),
(14, 'Particular', 'none', NULL, NULL, NULL, NULL, 0, 1, '2014-11-26 18:17:54', NULL),
(15, 'Go Runners El Salvador', 'none', 'https://www.facebook.com/gorunnerelsalvador', NULL, NULL, NULL, 0, 1, '2014-11-30 09:42:04', NULL),
(16, 'Taber Runners', 'none', 'https://www.facebook.com/pages/TABER-Runners-', NULL, NULL, NULL, 0, 1, '2014-11-30 10:14:50', NULL),
(17, 'Santa Ana Runners', 'none', 'https://www.facebook.com/SantaAnaRunners', NULL, NULL, NULL, 0, 1, '2014-11-30 11:51:02', NULL),
(18, 'Fast Runners', 'none', 'https://www.facebook.com/pages/Fast-Runners/7', NULL, NULL, NULL, 0, 1, '2014-12-06 15:57:40', NULL),
(19, 'Fair Play', NULL, 'https://www.facebook.com/complejofairplay', NULL, NULL, NULL, NULL, 1, '2014-12-07 21:30:11', NULL),
(20, 'Runners El Salvador', NULL, 'https://www.facebook.com/runnerselsalvador', NULL, NULL, NULL, 0, 1, '2014-12-26 15:06:17', NULL),
(21, '6-90 TRES', NULL, NULL, NULL, NULL, NULL, 0, 1, '2014-12-26 15:46:59', NULL),
(22, 'Federacion de Triatlon El Salvador', NULL, 'https://www.facebook.com/federacionsalvadoren', NULL, NULL, NULL, NULL, 1, '2015-01-16 22:17:37', NULL),
(23, 'Active Runners', NULL, 'https://www.facebook.com/ARunners', NULL, NULL, NULL, 0, 1, '2015-02-06 09:55:27', NULL),
(24, 'Team Iron Body Running', NULL, NULL, NULL, NULL, NULL, 0, 1, '2015-02-06 10:14:12', NULL),
(25, 'BAM', NULL, NULL, NULL, NULL, NULL, 0, 1, '2015-02-06 10:32:43', NULL),
(26, 'Desconocido Extrangero', NULL, NULL, NULL, NULL, NULL, 0, 1, '2015-02-10 17:05:51', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_PHOTO_GROUP`
--

DROP TABLE IF EXISTS `RB_PHOTO_GROUP`;
CREATE TABLE IF NOT EXISTS `RB_PHOTO_GROUP` (
  `rbphotogroupid` int(11) NOT NULL AUTO_INCREMENT,
  `photo` varchar(200) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `rbgroupid` int(11) NOT NULL,
  PRIMARY KEY (`rbphotogroupid`),
  KEY `fk_RB_PHOTO_GROUP_RB_GROUP1_idx` (`rbgroupid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=63 ;

--
-- Volcado de datos para la tabla `RB_PHOTO_GROUP`
--

INSERT INTO `RB_PHOTO_GROUP` (`rbphotogroupid`, `photo`, `type`, `description`, `date_created`, `rbgroupid`) VALUES
(17, 'http://just-runners.com/images/groups/trail_r/02.jpg', 1, NULL, '2014-08-31 00:00:00', 2),
(16, 'http://just-runners.com/images/groups/trail_r/01.jpg', 1, 'camisa oficial', '2014-08-31 00:00:00', 2),
(15, 'http://just-runners.com/images/groups/active_r/03.png', 1, 'foto grupal', '2014-08-31 00:00:00', 1),
(14, 'http://just-runners.com/images/groups/active_r/02.jpg', 1, 'banner', '2014-08-31 00:00:00', 1),
(13, 'http://just-runners.com/images/groups/active_r/01.jpg', 1, 'banner', '2014-08-31 00:00:00', 1),
(18, 'http://just-runners.com/images/groups/trail_r/03.jpg', 1, NULL, '2014-08-31 00:00:00', 2),
(19, 'http://just-runners.com/images/groups/trail_r/04.jpg', 1, NULL, '2014-08-31 00:00:00', 2),
(20, 'http://just-runners.com/images/groups/movistar_r/01.jpg', 1, 'Mediomaratonistas', '2014-09-05 00:00:00', 3),
(21, 'http://just-runners.com/images/groups/movistar_r/02.jpg', 1, 'Previo a los 21k Guatemala', '2014-09-05 00:00:00', 3),
(22, 'http://just-runners.com/images/groups/movistar_r/03.jpg', 1, '', '2014-09-05 00:00:00', 3),
(23, 'http://just-runners.com/images/groups/movistar_r/04.jpg', 1, 'Entreno', '2014-09-05 00:00:00', 3),
(24, 'http://thedailyrunners.com/images/groups/ahuachapan_r/01.jpg', 1, NULL, '2014-11-30 12:28:32', 4),
(25, 'http://thedailyrunners.com/images/groups/ahuachapan_r/02.jpg', NULL, NULL, '2014-11-30 12:28:32', 4),
(26, 'http://thedailyrunners.com/images/groups/ahuachapan_r/03.jpg', NULL, NULL, '2014-11-30 12:28:33', 4),
(27, 'http://thedailyrunners.com/images/groups/cafeta_r/01.jpg', NULL, NULL, '2014-11-30 12:44:44', 5),
(28, 'http://thedailyrunners.com/images/groups/cafeta_r/02.jpg', NULL, NULL, '2014-11-30 12:44:44', 5),
(29, 'http://thedailyrunners.com/images/groups/beer_r/01.jpg', NULL, NULL, '2014-12-02 19:45:47', 6),
(30, 'http://thedailyrunners.com/images/groups/beer_r/02.jpg', NULL, NULL, '2014-12-02 19:45:47', 6),
(31, 'http://thedailyrunners.com/images/groups/beer_r/03.jpg', NULL, NULL, '2014-12-02 19:45:47', 6),
(32, 'http://thedailyrunners.com/images/groups/beer_r/04.jpg', NULL, NULL, '2014-12-02 19:45:48', 6),
(33, 'http://thedailyrunners.com/images/groups/beer_r/05.jpg', NULL, NULL, '2014-12-02 19:45:48', 6),
(34, 'http://thedailyrunners.com/images/groups/beer_r/06.jpg', NULL, NULL, '2014-12-02 19:45:48', 6),
(35, 'http://thedailyrunners.com/images/groups/gymironbody_r/01.jpg', NULL, NULL, '2014-12-02 20:23:25', 7),
(36, 'http://thedailyrunners.com/images/groups/gymironbody_r/02.jpg', NULL, NULL, '2014-12-02 20:23:25', 7),
(37, 'http://thedailyrunners.com/images/groups/gymironbody_r/03.jpg', NULL, NULL, '2014-12-02 20:23:26', 7),
(38, 'http://thedailyrunners.com/images/groups/gymironbody_r/04.jpg', NULL, NULL, '2014-12-02 20:23:26', 7),
(39, 'http://thedailyrunners.com/images/groups/gymironbody_r/05.jpg', NULL, NULL, '2014-12-02 20:23:26', 7),
(40, 'http://thedailyrunners.com/images/groups/betania/00.jpg', NULL, NULL, '2014-12-02 22:40:24', 8),
(41, 'http://thedailyrunners.com/images/groups/betania/01.jpg', NULL, NULL, '2014-12-02 22:40:24', 8),
(42, 'http://thedailyrunners.com/images/groups/betania/02.jpg', NULL, NULL, '2014-12-02 22:40:24', 8),
(43, 'http://thedailyrunners.com/images/groups/betania/03.jpg', NULL, NULL, '2014-12-02 22:40:24', 8),
(44, 'http://thedailyrunners.com/images/groups/betania/04.jpg', NULL, NULL, '2014-12-02 22:40:25', 8),
(45, 'http://thedailyrunners.com/images/groups/active_r/04.jpg', NULL, NULL, '2014-12-08 12:15:32', 1),
(46, 'http://thedailyrunners.com/images/groups/active_r/05.jpg', NULL, NULL, '2014-12-08 12:15:32', 1),
(47, 'http://thedailyrunners.com/images/groups/addict_r/01.jpg', NULL, NULL, '2014-12-09 11:58:19', 9),
(48, 'http://thedailyrunners.com/images/groups/addict_r/02.jpg', NULL, NULL, '2014-12-09 11:58:20', 9),
(49, 'http://thedailyrunners.com/images/groups/addict_r/03.jpg', NULL, NULL, '2014-12-09 11:58:20', 9),
(50, 'http://thedailyrunners.com/images/groups/gymironbody_r/06.jpg', NULL, NULL, '2015-01-30 20:25:41', 7),
(51, 'http://thedailyrunners.com/images/groups/gymironbody_r/07.jpg', NULL, NULL, '2015-01-30 20:25:42', 7),
(52, 'http://thedailyrunners.com/images/groups/gymironbody_r/08.jpg', NULL, NULL, '2015-01-30 20:25:42', 7),
(53, 'http://thedailyrunners.com/images/groups/gymironbody_r/09.jpg', NULL, NULL, '2015-01-30 20:25:42', 7),
(54, 'http://thedailyrunners.com/images/groups/gymironbody_r/10.jpg', NULL, NULL, '2015-01-30 20:25:42', 7),
(55, 'http://thedailyrunners.com/images/groups/gymironbody_r/11.jpg', NULL, NULL, '2015-01-30 20:25:43', 7),
(56, 'http://thedailyrunners.com/images/groups/gymironbody_r/12.jpg', NULL, NULL, '2015-01-30 20:25:43', 7),
(57, 'http://thedailyrunners.com/images/groups/gymironbody_r/13.jpg', NULL, NULL, '2015-01-30 20:25:43', 7),
(58, 'http://thedailyrunners.com/images/groups/afteroffice_r/01.jpg', NULL, NULL, '2015-02-04 22:04:54', 10),
(59, 'http://thedailyrunners.com/images/groups/afteroffice_r/02.jpg', NULL, NULL, '2015-02-04 22:04:54', 10),
(60, 'http://thedailyrunners.com/images/groups/afteroffice_r/03.jpg', NULL, NULL, '2015-02-04 22:04:55', 10),
(61, 'http://thedailyrunners.com/images/groups/fenix_r/01.jpg', NULL, NULL, '2015-03-05 09:55:43', 11),
(62, 'http://thedailyrunners.com/images/groups/fenix_r/02.jpg', NULL, NULL, '2015-03-05 09:55:44', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_PHOTO_RACE`
--

DROP TABLE IF EXISTS `RB_PHOTO_RACE`;
CREATE TABLE IF NOT EXISTS `RB_PHOTO_RACE` (
  `rbphotoraceid` int(11) NOT NULL AUTO_INCREMENT,
  `photo` varchar(200) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `rbraceid` int(11) NOT NULL,
  PRIMARY KEY (`rbphotoraceid`),
  KEY `fk_RB_PHOTO_RB_RACE1_idx` (`rbraceid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=118 ;

--
-- Volcado de datos para la tabla `RB_PHOTO_RACE`
--

INSERT INTO `RB_PHOTO_RACE` (`rbphotoraceid`, `photo`, `type`, `description`, `date_created`, `rbraceid`) VALUES
(50, 'http://just-runners.com/images/races/2014/10/triatlon_costaman/02.jpg', 1, NULL, '2014-08-24 00:00:00', 5),
(49, 'http://just-runners.com/images/races/2014/10/triatlon_costaman/01.jpg', 1, 'cover', '2014-08-24 00:00:00', 5),
(48, 'http://just-runners.com/images/races/2014/09/vuelta_lago/05.jpg', NULL, NULL, '2014-08-24 00:00:00', 6),
(46, 'http://just-runners.com/images/races/2014/09/vuelta_lago/03.jpg', 1, NULL, '2014-08-24 00:00:00', 6),
(45, 'http://just-runners.com/images/races/2014/09/vuelta_lago/02.jpg', 1, NULL, '2014-08-24 00:00:00', 6),
(44, 'http://just-runners.com/images/races/2014/09/vuelta_lago/01.jpg', 1, 'cover', '2014-08-24 00:00:00', 6),
(43, 'http://just-runners.com/images/races/2014/09/reto_huizucar/02.jpg', 1, 'cover', '2014-08-24 00:00:00', 3),
(42, 'http://just-runners.com/images/races/2014/09/reto_huizucar/01.jpg', 1, NULL, '2014-08-24 00:00:00', 3),
(41, 'http://just-runners.com/images/races/2014/09/comsagua_trail_race/02.jpg', 1, 'cover', '2014-08-24 00:00:00', 1),
(39, 'http://just-runners.com/images/races/2014/08/reto_urbano/03.jpg', 1, NULL, '2014-08-24 00:00:00', 2),
(40, 'http://just-runners.com/images/races/2014/09/comsagua_trail_race/01.jpg', 1, '', '2014-08-24 00:00:00', 1),
(38, 'http://just-runners.com/images/races/2014/08/reto_urbano/02.jpg', 1, NULL, '2014-08-24 00:00:00', 2),
(37, 'http://just-runners.com/images/races/2014/08/reto_urbano/01.jpg', 1, 'cover', '2014-08-24 00:00:00', 2),
(51, 'http://just-runners.com/images/races/2014/10/triatlon_costaman/03.jpg', 1, NULL, '2014-08-24 00:00:00', 5),
(52, 'http://just-runners.com/images/races/2014/10/herbalife/01.jpg', 1, 'cover', '2014-10-06 21:39:53', 7),
(53, 'http://just-runners.com/images/races/2014/10/arroz/01.jpg', 1, 'cover', '2014-10-06 22:03:48', 8),
(54, 'http://just-runners.com/images/races/2014/10/ayudame_ayudar/01.jpg', 1, 'cover', '2014-10-06 22:22:47', 9),
(55, 'http://just-runners.com/images/races/2014/10/ayudame_ayudar/02.jpg', 1, NULL, '2014-10-06 22:22:47', 9),
(56, 'http://just-runners.com/images/races/2014/11/desafio_lava/01.png', 1, 'cover', '2014-10-07 16:30:41', 10),
(57, 'http://thedailyrunners.com/images/races/2014/11/avance_caminata/01.jpg', 1, NULL, '2014-11-16 16:36:29', 11),
(58, 'http://thedailyrunners.com/images/races/2014/11/eljabali2/01.jpg', 1, NULL, '2014-11-16 16:36:30', 14),
(59, 'http://thedailyrunners.com/images/races/2014/11/santiago/01.jpg', 1, NULL, '2014-11-16 16:36:31', 13),
(60, 'http://thedailyrunners.com/images/races/2014/11/neonrun/01.png', 1, NULL, '2014-11-16 16:36:32', 12),
(61, 'http://thedailyrunners.com/images/races/2014/11/neonrun/02.jpg', 1, NULL, '2014-11-16 16:36:32', 12),
(62, 'http://thedailyrunners.com/images/races/2014/11/maratonpanama/01.jpg', 1, NULL, '2014-11-16 16:36:33', 15),
(63, 'http://thedailyrunners.com/images/races/2014/12/concristoporpaz/01.jpg', 1, NULL, '2014-11-29 22:20:27', 17),
(64, 'http://thedailyrunners.com/images/races/2014/12/corrayuda/01.jpg', 1, NULL, '2014-11-29 22:20:28', 18),
(65, 'http://thedailyrunners.com/images/races/2014/12/corrayuda/02.jpg', 1, NULL, '2014-11-29 22:20:28', 18),
(66, 'http://thedailyrunners.com/images/races/2014/12/familyrun/01.jpg', 1, NULL, '2014-11-30 09:26:37', 19),
(67, 'http://thedailyrunners.com/images/races/2014/12/kmsonrrisas/01.jpg', 1, NULL, '2014-11-30 09:48:45', 20),
(68, 'http://thedailyrunners.com/images/races/2014/12/navirun/01.png', 1, NULL, '2014-11-30 10:16:30', 21),
(69, 'http://thedailyrunners.com/images/races/2014/12/porlaunidadypaz/01.jpg', 1, NULL, '2014-11-30 10:57:53', 22),
(70, 'http://thedailyrunners.com/images/races/2014/12/santaananight/01.jpg', 1, NULL, '2014-11-30 11:57:56', 23),
(71, 'http://thedailyrunners.com/images/races/2014/12/santaananight/02.jpg', 1, NULL, '2014-11-30 11:57:57', 23),
(72, 'http://thedailyrunners.com/images/races/2014/12/santaananight/03.jpg', 1, NULL, '2014-11-30 11:57:57', 23),
(73, 'http://thedailyrunners.com/images/races/2014/12/santaananight/04.jpg', 1, NULL, '2014-11-30 11:57:58', 23),
(74, 'http://thedailyrunners.com/images/races/2015/01/desafiojuayua/01.jpg', 1, NULL, '2014-11-30 15:24:17', 24),
(75, 'http://thedailyrunners.com/images/races/2014/12/kmporjuguetes/01.jpg', 1, NULL, '2014-12-06 16:02:11', 25),
(76, 'http://thedailyrunners.com/images/races/2014/12/granaerobica/01.jpg', 1, NULL, '2014-12-07 21:34:58', 26),
(77, 'http://thedailyrunners.com/images/races/2014/12/laclasicanavidenia/01.jpg', 1, NULL, '2014-12-07 21:48:41', 27),
(78, 'http://thedailyrunners.com/images/races/2014/12/aerobicanavidenia/01.jpg', 1, NULL, '2014-12-08 10:01:02', 28),
(79, 'http://thedailyrunners.com/images/races/2014/12/2joseguayabal/01.jpg', 1, NULL, '2014-12-08 13:10:49', 29),
(80, 'http://thedailyrunners.com/images/races/2014/12/entrenonoct/01.jpg', 1, NULL, '2014-12-10 12:29:37', 30),
(81, 'http://thedailyrunners.com/images/races/2014/12/entrenonoct/02.jpg', 1, NULL, '2014-12-10 12:29:37', 30),
(82, 'http://thedailyrunners.com/images/races/2014/12/entrenonoct/03.jpg', 1, NULL, '2014-12-10 12:29:37', 30),
(83, 'http://thedailyrunners.com/images/races/2014/12/unasa/01.jpg', NULL, NULL, '2014-12-16 13:43:56', 31),
(84, 'http://thedailyrunners.com/images/races/2014/12/unasa/02.jpg', NULL, NULL, '2014-12-16 13:43:57', 31),
(85, 'http://thedailyrunners.com/images/races/2014/12/unasa/03.jpg', NULL, NULL, '2014-12-16 13:43:58', 31),
(86, 'http://thedailyrunners.com/images/races/2014/12/unasa/04.jpg', NULL, NULL, '2014-12-16 13:44:02', 31),
(87, 'http://thedailyrunners.com/images/races/2014/12/runfornata/01.jpg', NULL, NULL, '2014-12-17 17:03:35', 32),
(88, 'http://thedailyrunners.com/images/races/2014/12/pornuevocuscatlan/01.jpg', NULL, NULL, '2014-12-18 16:17:01', 33),
(89, 'http://thedailyrunners.com/images/races/2014/12/navirun/02.jpg', NULL, NULL, '2014-12-19 10:22:16', 21),
(90, 'http://thedailyrunners.com/images/races/2014/12/navirun/03.jpg', NULL, NULL, '2014-12-19 10:22:16', 21),
(91, 'http://thedailyrunners.com/images/races/2014/12/navirun/04jpg', NULL, NULL, '2014-12-19 10:22:16', 21),
(92, 'http://thedailyrunners.com/images/races/2014/12/navirun/05.jpg', NULL, NULL, '2014-12-19 10:22:17', 21),
(93, 'http://thedailyrunners.com/images/races/2014/12/sansilvestre/01.jpg', NULL, NULL, '2014-12-26 15:20:26', 34),
(94, 'http://thedailyrunners.com/images/races/2015/01/UTAP/01.jpg', NULL, NULL, '2014-12-26 15:52:36', 35),
(95, 'http://thedailyrunners.com/images/races/2015/01/UTAP/02.jpg', NULL, NULL, '2014-12-26 15:52:36', 35),
(96, 'http://thedailyrunners.com/images/races/2015/01/UTAP/03.jpg', NULL, NULL, '2014-12-26 15:52:36', 35),
(97, 'http://thedailyrunners.com/images/races/2015/01/UTAP/04.jpg', NULL, NULL, '2014-12-26 15:52:37', 35),
(98, 'http://thedailyrunners.com/images/races/2015/01/UTAP/05.jpg', NULL, NULL, '2014-12-26 15:52:37', 35),
(99, 'http://thedailyrunners.com/images/races/2015/02/ilamatepec/01.jpg', NULL, NULL, '2015-01-01 20:55:45', 36),
(100, 'http://thedailyrunners.com/images/races/2015/02/21yoamoes/01.jpg', NULL, NULL, '2015-01-01 21:11:35', 37),
(101, 'http://thedailyrunners.com/images/races/2015/02/herbalifetri/01.jpg', NULL, NULL, '2015-01-01 21:21:56', 38),
(102, 'http://thedailyrunners.com/images/races/2015/02/triatloncostasol/triatloncostasol.jpg', NULL, NULL, '2015-01-16 22:20:38', 39),
(103, 'http://thedailyrunners.com/images/races/2015/02/triatloncostasol/02.jpg', NULL, NULL, '2015-02-04 22:22:30', 39),
(104, 'http://thedailyrunners.com/images/races/2015/02/21yoamoes/02.jpg', NULL, NULL, '2015-02-04 22:45:03', 37),
(105, 'http://thedailyrunners.com/images/races/2015/02/21yoamoes/03.jpg', NULL, NULL, '2015-02-04 22:45:03', 37),
(106, 'http://thedailyrunners.com/images/races/2015/02/21yoamoes/04.jpg', NULL, NULL, '2015-02-04 22:45:04', 37),
(107, 'http://thedailyrunners.com/images/races/2015/02/herbalifetri/02.jpg', NULL, NULL, '2015-02-04 22:55:22', 38),
(108, 'http://thedailyrunners.com/images/races/2015/02/enlazados/01.jpg', NULL, NULL, '2015-02-06 09:58:40', 40),
(109, 'http://thedailyrunners.com/images/races/2015/03/lasantenastrail/01.jpg', NULL, NULL, '2015-02-06 10:15:59', 41),
(110, 'http://thedailyrunners.com/images/races/2015/03/10kgt/01.jpg', NULL, NULL, '2015-02-06 10:35:46', 42),
(111, 'http://thedailyrunners.com/images/races/2015/03/beachrun/01.jpg', NULL, NULL, '2015-02-06 14:53:19', 43),
(112, 'http://thedailyrunners.com/images/races/2015/03/womanrace/01.jpg', NULL, NULL, '2015-02-09 23:03:08', 44),
(113, 'http://thedailyrunners.com/images/races/2015/03/retoquetzal/01.jpg', NULL, NULL, '2015-02-10 17:07:42', 45),
(114, 'http://thedailyrunners.com/images/races/2015/02/interamerican/01.jpg', NULL, NULL, '2015-02-18 09:17:25', 46),
(115, 'http://thedailyrunners.com/images/races/2015/02/interamerican/02.jpg', NULL, NULL, '2015-02-18 09:17:25', 46),
(116, 'http://thedailyrunners.com/images/races/2015/03/challengetriatlon/01.jpg', NULL, NULL, '2015-02-18 09:50:59', 47),
(117, 'http://thedailyrunners.com/images/races/2015/02/ilamatepec/02.jpg', NULL, NULL, '2015-03-02 10:16:32', 36);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_RACE`
--

DROP TABLE IF EXISTS `RB_RACE`;
CREATE TABLE IF NOT EXISTS `RB_RACE` (
  `rbraceid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `distance` varchar(100) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `country` varchar(150) DEFAULT NULL,
  `country_code` varchar(10) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `cost` varchar(30) DEFAULT NULL,
  `kit` varchar(150) DEFAULT NULL,
  `registration` mediumtext,
  `awards` varchar(200) DEFAULT NULL,
  `description` longtext,
  `benefit` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `sponsors` varchar(300) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `points` double DEFAULT NULL,
  `going` int(11) DEFAULT '0',
  `date_created` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  `rborganizerid` int(11) NOT NULL,
  `rbcategoryid` int(11) NOT NULL,
  PRIMARY KEY (`rbraceid`),
  KEY `fk_RB_RACE_RB_ORGANIZER1_idx` (`rborganizerid`),
  KEY `fk_RB_RACE_RB_CATEGORY1_idx` (`rbcategoryid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=48 ;

--
-- Volcado de datos para la tabla `RB_RACE`
--

INSERT INTO `RB_RACE` (`rbraceid`, `name`, `distance`, `location`, `country`, `country_code`, `date`, `start_time`, `cost`, `kit`, `registration`, `awards`, `description`, `benefit`, `status`, `sponsors`, `latitude`, `longitude`, `points`, `going`, `date_created`, `date_modified`, `rborganizerid`, `rbcategoryid`) VALUES
(1, 'Comasagua Trail Race', '8K y 21K', 'La Libertad, Comsagua', 'El Salvador', 'SV', '2014-09-28', '07:00:00', '8K = $15 / 21K = $25', 'Camisa, Medalla, Numero y Hidratacion y seguridad', 'Heartland Outdoors La Gran Via\r\n\r\nhttps://docs.google.com/forms/d/1UW3-2vgusMCU9VQRHvTzW10Z4OL_xNV2vnWGMMH2Pxc/viewform', 'Premios en efectivo', 'Carrera Trail en Comasagua  una aventura garantizada, A beneficio: de plan por ser niña', 1, '1', 'Gatorade,The North Face,Industrias 503,el Grafico, 102 Nueve, fitnesson,Telesis', 13.639753, -89.379019, 0, 0, '2014-08-16 00:00:00', '2014-08-25 00:00:00', 2, 2),
(2, 'Desafio Urbano', '5K y 10K', 'La Libertad, Centro comercial La Gran Via  (Parqueo 4to Nivel)', 'El Salvador', 'SV', '2014-08-17', '07:00:00', '$15', 'Camisa Dry Fit , Numero , Chip , Medalla , Seguro', 'Jaguar (Multiplaza) , GNC (Zona Rosa , Plaza Madero)', NULL, 'Carrera con obstáculos dentro el campo de la escuela militar', 0, '1', 'Toyota , Movistar, GNC, Rayovac, La Gran Via, Kenda, SISA, Dasal,Gatorade', 13.67777, -89.25512, 0, 0, '2014-08-16 00:00:00', NULL, 1, 1),
(3, 'Reto Huizúcar Atemoltli', '1K, 6K ,12K', 'La Libertad,Huizúcar  Casco Urbano', 'El Salvador', 'SV', '2014-09-07', '06:30:00', '$10', 'Camisa, Medalla, Numero y Squeeze', 'Estadio Magico Gonzalez, Tito Sport 17', NULL, 'Para todo publico', 0, '1', 'ECO, Cool, Atun Calvo, Titos Sport,El Diario de Hoy, Canal 12', 13.588793, -89.236332, 0, 0, '2014-08-21 00:00:00', NULL, 1, 1),
(5, 'Triatlón Costaman El Salvador 70.3', '1,900mts-90K-21K', 'Kilometro 78 Ave. Costa Del Sol San Luis la Herradura', 'El Salvador', 'SV', '2014-10-05', '05:00:00', '$100 / $135', 'Camisa', 'Bicimania (El Salvador,Guatemala-zona 10 y Majadas, Honduras San PedroSula y Tegucigalpa). Otros extrangeros deberan hacerlo atraves del facebook en el sitio oficial: Triatlon Costaman El Salvador', NULL, 'Categorías:\r\nHasta 19 años / 20 a 29 años / 30 a 39 años /40 a 49 años /50 o mas años/ Relevos\r\nTodas las categorías masculino y femenino\r\nCosto únicamente en dolares\r\nDel 20 de agosto al 20 de septiembre\r\n-Individual $100\r\n-Relevos $135\r\n\r\nSemana extraordinaria\r\nDel 22 al 27 de septiembre\r\n-Individual $125\r\n-Relevos $155\r\n\r\nPremiacion en efectivo para los primeros 3 lugares masculino y femenino de la general individual', 0, '1', 'Gatorade', 13.296986, -88.894006, 0, 0, '2014-08-16 00:00:00', '2014-08-25 00:00:00', 4, 6),
(6, 'Vuelta Al Lago de Coatepeque', '5k, 10k y 21k', 'Santa Ana , Lago de Coatepeque', 'El Salvador', 'SV', '2014-09-21', '07:00:00', '$12', 'Camiseta, hidratación, medalla y número', 'www.yoamoes.com y partir del martes 19 de agosto, San Salvador - TIENDA ADIDAS, sucursal, Multiplaza, Galerías y Metro centro.  A partir del 16 de Agosto en  Santa Ana - Farmacias Señora Santa Ana.', '21K (1er $200 , 2do $100 , 3ro $50) 10K (1er $100 , 2do $50 , 3ro $25) 5K (1ro,2do y 3ro Trofeo)', 'Transporte desde San Salvador es de $5 (no incluido) puedes también inscribirte en línea con la opción que incluye el transporte.  •   El punto de reunión para las personas que paguen transporte, será el parque externo de La Gran Vía, saliendo el domingo 21 de septiembre a las 4:45 am y retornando el mismo día a medio día.   Habrá cabañas disponibles para los que deseen llegar un día antes, contactar al 2263-2757.\r\n\r\nTendremos parqueo  externo frente al balneario en el cual llevaremos a cabo el evento (CONSTITUCION 1950) con seguridad.\r\n\r\nCupo limitado.\r\n\r\nNo te olvides de visitar nuestro sitio web y ser parte de las redes sociales que hemos habilitado para ti en Facebooky si tienes alguna pregunta nos puedes escribir a  info@yoamoes.com\r\n\r\n \r\nDESAFIATE Y CORRE CON NOSOTROS!', 0, '1', 'Powerade, Herbalife, EDH Deportes,102 Nueve, Kelloggs, Santa Ana Runners', 13.890722, -89.551899, 0, 0, '2014-08-16 00:00:00', '2014-08-25 00:00:00', 5, 3),
(7, 'Carrera 5k Herbalife', '5k y 2k', 'San Salvador CIFCO', 'El Salvador', 'SV', '2014-10-19', '06:30:00', '$12', 'Camiseta, medalla', 'Bicimania', NULL, 'Herbalife una nutricion para una vida mejor', 0, '1', 'Herbalife,Gatorade,Audi,ElectrolabMedic', 13.687465, -89.237994, 0, 0, '2014-10-06 21:36:32', NULL, 6, 1),
(8, 'Water Run', '1k, 5k ,10k', 'San Salvador, Paint Ball Navarra', 'El Salvador', 'SV', '2014-11-23', '07:30:00', '$12', 'Camisa, Squeeze,Bolsa,Pistola para agua, Toalla Facial,Numero y Chip', 'A partir del domingo 5 de octubre en Sportline Multiplaza, La gran via y Galerias', NULL, 'La carrera Más Divertida de todo El Salvador y a celebrar el Día Nacional del Arroz con un buena comilona; ¡Inscripciones Limitadas! , con respectiva hidratación, medalla y comida preparada con arroz ', 0, '1', 'Banco Promerica,Gatorade,Sportline, Nutrilite,Tigo Music,United,Galerias,Alive extra fuerte', 13.674606, -89.208382, 0, 0, '2014-10-06 22:02:44', '2014-11-15 23:08:15', 7, 2),
(10, 'Desafio de Lava', '10k y 21k', 'San Vicente Pacaya', 'Guatemala', 'GT', '2014-11-16', '07:00:00', 'Q210.00', 'Medalla, playera tecnica,chip, hidratacion,fruta y Entrada al volcan de Pacaya y laguna de Calderas', 'Agencias Banrural Cuenta No. 3-421-00219-6. Ingresa tus datos en le página web www.kumbresguatemala.com/desafiodelava2014', '. ', '7ptima Edicion del Desafio De Lava. Categorias Libre de 18 – 39 años y Master de 40 años en adelante. Ramas Masculino y Femenino. Fecha  y lugar de la expo: En la cual se hará entrega del kit a los atletas C.C. Los Próceres  8 y 9 DE NOVIEMBRE, 4to. Nivel, Food  Court.Sábado 8 de noviembre de 14:00  a 18:00 HORAS   CIERRE DE INSCRIPCION el 3 de noviembre.NO HABRAN INSCRIPCIONES EL DÍA DEL EVENTO, SIN EXCEPCION ALGUNA.  ATENCION: Medalla especial para los 21 Km. y medallas para 10 Km. a todo aquel que realice el recorrido completo y tenga sus marcas.  Se prohibirá el cambio de ruta el día del Evento, la penalización para el que lo haga será bajarlo de la ruta (al detectarlo) y si no vuelve a la ruta correcta se procederá a descalificarlo y no se le dará su medalla', 0, '1', 'Revive, Agua pura Salva Vidas,Imaginacion Real SA, Los Proceres', 15.783483, -90.230778, 0, 0, '2014-10-07 16:26:54', NULL, 9, 2),
(9, 'Ayudanos a Ayudar', '5k y 12k', 'Santa Ana, Parque Libertad', 'El Salvador', 'SV', '2014-10-26', '07:00:00', '$10', 'Camisa, Squeeze,Toalla', 'A partir del 6 de octubre en Sportline metrocentro Santa Ana', NULL, 'Carrera benefica, con tus $10 se compraremos un lote de silla de ruedas para  personas que lo necesitan "Acumulemos kms." contamos contigo!!!! ', 1, '1', 'Herbalife,Sudagrip,Hidralan a Beneficio de Lions International', 13.994963, -89.556694, 0, 0, '2014-10-06 22:21:09', NULL, 8, 1),
(11, 'Avance Caminata de Conciencia sobre la Diabetes', '1.3k 1.5k  , 8.5k , 17k', 'La Libertad, Parque Central de Antiguo Cuscatlan', 'El Salvador', 'SV', '2014-11-16', '06:30:00', '$10', 'Camisa, Numero,Medalla,Squeeze y certificado de participacion', 'Sears multiplaza, Farmacias Beethoven El Paseo,Santa Elena y Jardines de Cuscatlan,Sta Lucia Metrocentro ,Metrosur y Plaza Merliot', NULL, 'Desafio..Street & Cross country. Con Categorias: infantil hasta 13 años, Adulto mayor solo caminata, Juvenil, Libre , Master (40 años en adelante)  y Militar ', 0, '1', 'Cool,Yes,Atun Calvo,Club de Leones de Antiguo Cuscatlan, Running for Life, Alcaldia de Antiguo Cuscatlan', 13.673335, -89.241618, 0, 0, '2014-11-15 22:54:54', NULL, 3, 1),
(12, 'Neón RUN', '5k', 'San Salvador, CIFCO', 'El Salvador', 'SV', '2014-11-22', '18:00:00', '$25', 'Camiseta, Pintura, Pulsera luminosa, entrada a la Neon AfterParty y una bebida gratis', 'Puntos de venta TodoTicket', NULL, 'Queremos que vivas la experiencia que ha causado sensación a nivel mundial ; Neon Run. Prepárate para vivir una noche de jogging, neon, música y un After Party para celebrar la vida.', 0, '1', 'Tigo Music,Huawei,Gatorade', 13.687568, -89.237606, 0, 0, '2014-11-15 23:05:47', NULL, 10, 1),
(13, '52 Maratón Santiago', '3.5k , 6.8k , 13.6k , 20.2k', 'San Salvador, Ciudad Delgado, Plaza Mons. Romero', 'El Salvador', 'SV', '2014-11-23', '07:00:00', '2 Juguetes Nuevos', 'Numero y medalla', 'Alcaldia Municipal de Ciudad Delgado, Instituto municipal de los deportes imdepor o puedes llamar a la 2561-2105', NULL, 'Participa en la 52 edicion de la Maraton Santiago de Ciudad Delgado, la carrera mas historica de El Salvador. No te la puedes perder!', 1, '1', 'Alcaldia Municipal de Ciudad Delgado, ALBA, INDES,Gatorade,Premia reconocmientos', 13.731026, -89.161011, 0, 0, '2014-11-15 23:21:59', NULL, 11, 1),
(14, 'II Run El Jabalí', '3.5k , 7.0k , 10.5k', 'La Libertad, San Juan Opico El Jabalí', 'El Salvador', 'SV', '2014-11-30', '08:00:00', '$10', 'Camiseta, Número, e Hidratacion.', 'Sucursales ACES', NULL, 'Arranca el motor de tu corazón. Categorias: infantil, general, y pro. No te lo puedes perder!', 0, '1', 'Autodromo Internacional El Jabalí, ACES, Fia', 13.8102, -89.330725, 0, 1, '2014-11-15 23:34:24', '2014-11-29 21:37:28', 12, 1),
(15, 'XXXVIII Maratón Internacional Ciudad de Panamá', '21k y 42k', 'Ciudad de Panamá, Cinta Costera estacinamientos del Hotel Miramar', 'Panamá', 'PA', '2014-12-07', '05:00:00', '$60, maraton $35 media maraton', 'Camisa, Número , Medalla, Hidratacion', 'http://corredoresdelistmo.com/web/index.php?option=com_smartformer&formid=10&lang=es', NULL, 'Corre la maraton Internacional de Ciudad de Panama , con un recorrido expectacular', 0, '1', 'ASICS, Oderbretch', 8.975057, -79.526258, 0, 3, '2014-11-15 23:59:48', '2014-11-29 16:11:35', 13, 4),
(16, 'Run with Santa', '0.5k, 3k, 5k y 11k', 'San Salvador, El paseo, Centro comercial Galerías', 'El Salvador', 'SV', '2014-11-30', '07:00:00', '$10', 'Camisa, Bolsón Deportivo, Squeeze, Prop Navideño, JuegoJumex Chispazo,  Número y chip (el dia de la carrera hidratación y medalla)', 'Tiendas Sportline Multiplaza y Galerías', NULL, 'Corramos con Santa, ayúdanos a poner muchas sonrisas en los niños más necesitados del país. Categorias Infantil, Mascota, Familiar y General', 1, '1', 'Nutrilite, Running4Help, El Gráfico', 13.701828, -89.230016, 0, 1, '2014-11-26 17:27:48', '2014-11-29 16:10:26', 3, 1),
(17, 'Corriendo con Cristo por la PAZ', '3k y 8.4k', 'Santa Ana Norte, Metrocentro', 'El Salvador', 'SV', '2014-12-07', '07:00:00', '$5', '-Por confirmar-', 'Museo de UNASA, En parroquia Santa Bárbara, en Santa Ana norte, Calle José Mariano Mendez frente al ban ban', NULL, 'Carrera en pro contrucción Casa Parroquial Santa Ana Norte. Premio en efectivo para 8.4k y trofeo para 3k. Categorias: Infantil Libre Femenino, Libre Masculino, Veterano Femenino,Veterano Masculino', 1, '1', 'N/A', 13.998129, -89.552231, 0, 1, '2014-11-26 18:18:21', '2014-12-07 14:08:47', 14, 1),
(18, 'Corriendo para ayudar', '6k y 8k', 'Santa Ana, Final 15 AV Norte, Reparto Sihuatehuacan. Instalaciones del albergue para ancianos Jesus Pan de Vida', 'El Salvador', 'SV', '2014-12-28', '07:30:00', '$5 + Víveres', '-Por confirmar-', '-Por Confirmar- mas información marcar al 7736-0030', NULL, 'Carrera benéfica. Los fondos seran destinados para  la reparación de las instalaciones  y compra de víveres. Les invitamos a que visiten las instalaciones del Albergue Jesus pan de Vida para que conozcan la necesidades que ellos tienen y ademas este sera el lugar de la carrera ', 1, '1', 'Pizzeria Napolitana, Titos Sports,Acajarte', 13.9969, -89.550937, 0, 0, '2014-11-29 22:13:40', '2014-12-16 14:07:57', 14, 1),
(19, 'Family Run', '1k, 3k, 5k, 12k', 'La Libertad, Santa Elena, parque Madreselva', 'El Salvador', 'SV', '2014-12-07', '07:00:00', '$10', 'Camisa, Medalla y Número', 'Puntos de venta Vanilla Spoon Santa Elena y Masferrer Gift Abroad', NULL, 'Family Run te invita a que participes y contribuyas. A beneficio de Clinica Pediátrica Gift Abroad El Salvador', 1, '1', 'Gift Abroad, Trophy.Claro , Joven360 ,Revive', 13.663411, -89.255346, 0, 1, '2014-11-30 09:17:40', '2014-12-04 16:47:02', 14, 1),
(20, '1ra Carrera Kilómetros por sonrisas', '3k, 5k, 10k', 'San Salvador, Parque Bicentenario', 'El Salvador', 'SV', '2014-12-07', '08:00:00', '$10', 'Camisa, Medalla y Número', 'Centro Español. Dirección: Paseo General Escalón y 83 Avenida Norte, Colonia Escalón, San Salvador. Teléfonos:Tel. (503) 2264-3955', NULL, 'A beneficio de los niños(as)  del Hospital de Niños Benjamín Bloom y el Hogar de niño San Vicente de Paul. Además habran premios, rifas promocionales y mas', 1, '1', 'Alcaldia de San Salvador, Calvo, Del Sur,La Prensa Gráfica, Photo Runnersv, San Martín, Aquapura', 13.693109, -89.251535, 0, 0, '2014-11-30 09:44:16', NULL, 15, 2),
(21, 'NaviRunners', '3k, 7k 15k', 'San Salvador 75 av sur Col EscalónEdificio Gamaliel', 'El Salvador', 'SV', '2014-12-20', '15:30:00', 'Gratis', '-Por Confirmar-', 'Ven y participa', NULL, 'Participa en una carrera sin Igual, se premiara los primeros lugares y lo mejor de todo que es GRATIS...', 0, '1', 'Tabernáculo Bíblico Bautista Amigos de Israel', 13.701086, -89.232813, 0, 2, '2014-11-30 10:15:19', '2014-12-17 12:20:45', 16, 1),
(22, '1ra Carrera por la Unidad y Paz Familiar', '4k , 8k, 12k', 'Santa Ana, Parroquia Madre del Salvador', 'El Salvador', 'SV', '2014-12-14', '07:00:00', '$15', 'Camisa Número y Squeeze', 'Farmacia Señora Santa Ana: Sucursal #1 contiguo parque centro médico. Imprenta Najarro: Calle libertad oriente + 21 23 Av Sur.  Sportline Metrocentro', NULL, 'Caminata familiar 4k y carreras para categorias 8k y 12k. habrán premios, medallas para los primeros 3 lugares de cada categoria. Para 4k premio: Cenas familiares, y vales de compra de super. Para ganadores de 8k dotacion de tennis. Para ganadores de 12k, premios en efectivo 1er -$150 , 2do - $100 y 3ro $50', 0, '1', 'Encuentros Conyugales', 13.989625, -89.547131, 0, 0, '2014-11-30 10:55:18', NULL, 14, 1),
(23, 'Santa Ana Night: Corriendo por un juguete', '1.3k, 7.3k', 'Santa Ana, Parque Libertad', 'El Salvador', 'SV', '2014-12-12', '19:30:00', '$6 + un juguete', 'Camisa Número y Accesorios', 'Oficina Radio Soda Stereo', NULL, 'Vive una emocionante experiencia corriendo en Santa Ana de noche por una noble causa', 1, '1', 'Powerade,Disco Movil Fuerza 3, Pinturas CELCO, Santa Ana Runners, Grupo Samix', 13.994629, -89.556653, 0, 1, '2014-11-30 11:52:08', '2014-12-04 16:47:14', 17, 1),
(24, 'Desafío  Juayúa', '12k 18k', 'Sonsonate, Parque Central  de Juayúa', 'El Salvador', 'SV', '2015-01-11', '07:30:00', '$15', 'Camiseta Medalla, Número, Hidratación . Refrigerio y seguridad.', 'En tienda libre Metrocentro y Multiplaza, también en  línea a través de www.yoamoes.com', NULL, 'EL DESAFIÓ ESTA EN LA DIFICULTAD, CORRE POR TU LOGRO PERSONAL!! Recorrido espectacular, naturaleza y entornos lindos; 12 y 18 KM para corredores novatos, intermedios y con experiencia avanza en montañas. Entrega de Kit: Los días viernes 9  y sábado 10 de enero de 10am a 5pm en el lugar donde se inscribieron.  Los inscritos en línea deberán retirar su kit en Tienda Libre Multiplaza.  Habrá servicio de AUTOBUS de San Salvador.  Costo por transporte $7.00 SE PAGA POR ADELANTADO PARA RESERVAR EL CUPO (también lo puede hacer en línea www.yoamoes.com). Todos kits de corredor para el DESAFIÓ DE JUAYUA, estarán disponibles en TIENDA LIBRE MULTIPLAZA a partir de el viernes 9 de enero de 10:00 am a 7:00 pm y el sábado 10 de enero de 9:00 am a 1:00 pm. No se hará entrega de kits el sábado por la tarde o el día del evento. ', 0, '1', 'Yo Amo ES, Herbalife', 13.842794, -89.747103, 0, 0, '2014-11-30 15:20:10', '2015-01-01 20:23:49', 5, 2),
(25, '1ra Carrera kilometros por juguetes', '5k', 'Ahuachapán, Parque La Concordia', 'El Salvador', 'SV', '2014-12-14', '07:00:00', '$1 + un juguete', 'Hidratacion, seguridad y muchas sorpresas', 'Asamblea Legislativa de Ahuachapán Teléfono: (503) 2483 - 6600.  y Academia ALC', NULL, 'Ven y acompáñanos, ejercítate  y ayudanos a llevar alegría a muchos niños en esta época muy especial. ', 1, '1', 'Fast Runners, Powerade, ALC Asamblea Legislativa de Ahuachapán ', 13.91904, -89.848821, 0, 0, '2014-12-06 15:58:39', NULL, 18, 1),
(26, '1ra Gran Carrera Aeróbica', '3k, 6k y 12k', 'Santa Ana, Chalchuapa', 'El Salvador', 'SV', '2014-12-28', '07:00:00', '$10', 'Camisa y Número', 'Complejo deportivo Fair Play Chalchuapa y Sede Democracia Salvadoreña Santa Ana mas Info 2486-2104 o 2486-2105', NULL, 'Unamos nuestras manos para servir y nuestros corazones para dar. Habrán premios económicos a los primeros lugares de cada categoría', 1, '1', 'Fair Play, Banco Hipotecario, 4Life, Gas Tomza', 13.989084, -89.676418, 0, 0, '2014-12-07 21:30:32', NULL, 19, 1),
(27, 'La Clásica Navideña', '14k', 'San Salvador, Árbol de Dios', 'El Salvador', 'SV', '2014-12-24', '05:00:00', 'Gratis', 'n/a', 'n/a', NULL, 'Corredores se unen a celebrar las vísperas de navidad orando en catedral.Participa bajo tu propia responsabilidad. Ruta del Árbol de Dios,El Salvador del Mundo, Catedral, Primera Calle, Fuentes Beethoven y de regreso al Árbol de Dios', 0, '1', 'BMA', 13.697796, -89.250247, 0, 1, '2014-12-07 21:47:54', '2014-12-22 18:03:59', 14, 1),
(28, 'Carrera Aeróbica Navideña', '7k y 14k', 'Sonsonate, Redondel Col 14', 'El Salvador', 'SV', '2014-12-14', '07:00:00', 'Gratis', 'n/a', 'n/a', NULL, 'Categorias: Principiantes 7k +1 Vuelta  y Expertos 14k + 2 vueltas ', 0, '1', 'Gavinchi', 13.728837, -89.720458, 0, 0, '2014-12-08 09:57:01', NULL, 14, 1),
(29, 'II Carrera San José Guayabal', '2k y 10k ', 'Cuscatlán, San José Guayabal', 'El Salvador', 'SV', '2014-12-14', '07:30:00', 'Gratis', 'n/a', 'El dia del evento de 6am a 7am', NULL, 'Categoria Juvenil 14-18 años , libre de 19 años en adelante para 10 km , Catedoría infantil B hasta 11 años y  A de 12 a 13 años, para 2 km . En todas masculino y femenino. Habrán premios en efectivo.', 0, '1', 'Alcaldia San José Guayabal', 13.837943, -89.095471, 0, 0, '2014-12-08 13:08:34', NULL, 14, 1),
(30, 'Entreno Nocturno: Ayudanos a Sacar una Sonrisa', '7k', 'Santa Ana, Parque Libertad', 'El Salvador', 'SV', '2014-12-13', '18:30:00', '$2', 'n/a', 'n/a', NULL, 'A beneficio de: Pediatría Hospital San Juan de Dios ', 1, '1', 'Addict Runners, Jumex Sport', 13.994539, -89.556551, 0, 0, '2014-12-10 12:29:19', '2014-12-12 14:16:07', 8, 1),
(31, '2da Carrera UNASA', '5k y 10k', 'Santa Ana, Universidad Autónoma UNASA', 'El Salvador', 'SV', '2014-12-21', '07:00:00', '$5', 'Camisa, Número Squeeze y accesorios', 'Museo de anatomía humana de UNASA, Departamento de proyección social de UNASA. ', NULL, 'Ven y participa por una buena salud física y mental. La entrega de kit será del 1 al sábado 20 de diciembre. Habrán premios: Productos y promocionales de los patrocinadores, medallas, trofeos y dinero en efectivo.', 0, '1', 'UNASA,Gatorade, Termos del río, el Gráfico...', 13.976546, -89.589008, 0, 0, '2014-12-16 13:43:11', NULL, 14, 1),
(32, 'Run for Nata', '4k', 'La Libertad, Santa Elena, parque Madreselva', 'El Salvador', 'SV', '2014-12-20', '07:00:00', 'Donación: $10', 'n/a', 'n/a', NULL, 'Acompañanos a correr  por la recuperación de nuestra amiga Natasha,  quien se encuentra en estado crítico  debido a un accidente cerebrovasuclar, el 100% DEL COSTO DE LA CARRERA SERÁ DESTINADO  PARA SUS GASTOS MÉDICOS. Ayudemos utilicemos #Run4Nata', 1, '1', 'n/a', 13.663411, -89.255346, 0, 1, '2014-12-17 17:01:42', '2014-12-19 22:04:21', 14, 1),
(33, 'Corriendo por Nuevo Cuscatlán', '3k y 6k', 'La Libertad, Nuevo Cuscatlán, plaza central', 'El Salvador', 'SV', '2014-12-21', '06:30:00', '$10', 'n/a', 'Casa de la Cultura de Nuevo Cuscatlán, Depto de Desarrollo Urbano en Vía del Mar y Yamaha Escalón', NULL, 'Partiticpa en la carrera Corriendo por Nuevo Cuscatlán y colabora en el programa de los Jóvenes Becados PIES DESCALZOS que beneficia la niñez de nuestro municipio', 1, '1', 'Alcaldía de Nuevo Cuscatlán, Becad@s', 13.648179, -89.26512, 0, 0, '2014-12-18 16:16:30', NULL, 14, 1),
(34, 'San Silvestre, Un juguete una ilusión', '5k y 10k', 'La Libertad, Nuevo Cuscatlán, plaza central', 'El Salvador', 'SV', '2014-12-28', '07:00:00', 'Juguete Nuevo', 'n/a', 'El día del evento a partir de las 6:00 AM', NULL, 'San Silvestre es conocida como la última carrera del año, no te la puedes perder, por una buena causa ven y acompáñanos.', 1, '1', 'Club de Leones de Santa Telca y Runners El Salvador', 13.648179, -89.26512, 0, 0, '2014-12-26 15:06:32', '2014-12-28 08:01:48', 20, 1),
(35, 'Ultra Trail Atiquizaya Pathfinder', '15k , 30k y 70k', 'Ahuachapán, Atiquizaya', 'El Salvador', 'SV', '2015-01-24', '05:00:00', '15k $5, 30k $15 y 70k $20', 'Mayor info al 2414-4901', 'Laboratorio Clínico Vides (Anexo Centro Médico Santa Ana), Bicimania  El Salvador', NULL, 'Primera Ultra Trail de El Salvador, descubriendo nuevas rutas, nuevos senderos y majestuosos paisajes del occidente del país. Ruta de los Manantiales', 1, '1', '6-90 TRES, Atiquizaya', 13.974483, -89.755334, 0, 0, '2014-12-26 15:48:36', '2015-01-24 08:38:14', 21, 5),
(36, 'Reto del Volcán Ilamatepec', '11k y 21k', 'Santa Ana, Cerro Verde', 'El Salvador', 'SV', '2015-03-15', '07:30:00', '$35', 'Camisa TNF, Hidratación, Refrigerio y Seguridad', 'En línea www.yoamoes.com\no\nHeartland La Gran Vía', NULL, '¡Los lugares que descubrirás, si solo te atreves a explorar! Reto Volcán Ilamatepec 2015. Hay dos distancias 11 y 21k para diferente nivel de corredor. La distancia de 21km la sugerimos para corredores de montaña/trail con experiencia y para corredores de ciudad acostumbrados a retos difíciles y distancias largas, ya que es muy técnica y difícil y 4.5 a 5 horas para terminar. La distancia de 11km es para corredores de todo nivel, más que nada intermedios que buscan un desafío personal y una aventura sin igual.', 0, '1', 'Yo Amo ES\nThe North Face', 13.838665, -89.650649, 0, 0, '2015-01-01 20:49:48', '2015-03-02 10:06:43', 5, 2),
(37, 'Medio Maratón YO AMO ES', '11k y 21k', 'La Libertad, Santa Elena, Hotel Holiday Inn', 'El Salvador', 'SV', '2015-02-22', '07:00:00', '$15', 'Camisa Dry Fit, Medalla, Hidratación y Bolsa bagy para carro', 'En línea www.yoamoes.com\nEl Salvador:\n - Multiplaza\n - Galerías\n\nGuatemala:\n - Runners Place, 15 Ave 6-01 Zona 13 Century Plaza No 210', NULL, 'Participa en la Sexta edición de la Medio Maratón mas famosa de El Salvador, conocida por una ruta muy desafiante varias elevaciones. Se correrá con CHIP  para marcar tus tiempos con exactitud. Recorrido certificado por IAAF\n\nHabrá Expo Deportiva el día previo 21 de Febrero  de 8am a 5pm en el Hotel Holiday Inn San Salvador\n\n', 0, '1', 'Yo Amo ES\nPowarade\nTienda Libre\nEDH\nAleve\nClaro', 13.671443, -89.254623, 0, 3, '2015-01-01 21:07:46', '2015-02-21 20:51:01', 5, 3),
(38, 'Herbalife Triathlon 70.3', '1.9k (Nadando) 90k (Cicleando) y 21.1 k (Corriendo)', 'Usulután, Jiquilisco, Corral de Mulas', 'El Salvador', 'SV', '2015-02-22', '06:30:00', '$125', '[pendiente]', 'Bicimanía San Salvador', NULL, 'Premios\n\nTodas las Categorías\n1er Lugar $150, 2do y 3er lugar trofeos.\n\nGeneral Masculino\n1er Lugar $700\n2do Lugar $300\n3er Lugar $150\n\nGeneral Femenino\n1er Lugar $700\n2do Lugar $300\n3er Lugar $150', 0, '1', 'Herbalife\nBicimanía\nLa Prensa Gráfica\nJumex Sport\nLaser 92.9 y 90.1\nElectrolab Medic\nFemenina 102.5', 13.205424, -88.546134, 0, 0, '2015-01-01 21:20:59', NULL, 6, 6),
(39, 'Triatlón Costa del Sol', '750mts (Nadando) 20k (Cicleando) y 5k (Corriendo)', 'La Paz, Costa del Sol', 'El Salvador', 'SV', '2015-02-07', '15:00:00', '$15 Federados, $25 No Federado', 'Camisa, Medalla', 'Puedes inscribirte enviando un correo a: festri@hotmail.com con tu nombre y edad\n\nInscripción $ 15.00 federados no federados $ 25.00\ncosto de federarse $ 25 anuales.\n', NULL, 'Premiación a los tres primeros lugares de cada categoría.\n\nCategorías a competir:\n• 18-29 años\n• 30-39 años\n• 40-49 años\n• 50 años a mas \n• junior masculino \n• junior femenino \n• libre femenino \n• novatos femenino \n• novatos masculino\n• natación asistida. \n', 0, '1', 'Federación Salvadoreña Triatlón\nINDES\nThropy Mundo\nEndurance Multi Sports\nComité Olimpico El Salvador', 13.307119, -88.921375, 0, 0, '2015-01-16 22:18:25', '2015-02-04 22:19:52', 22, 6),
(40, 'Enlazados: Corrida Nocturna por la Amistad', '5k y 10k', 'La Libertad, Santa Elena, Parque de Madre Selva', 'El Salvador', 'SV', '2015-02-12', '20:00:00', 'Gratis', 'Lazo reflectivo con tu pareja', 'n/a', NULL, 'Celebramos la amistad corriendo! No te pierdas esta corrida gratuita el 12 de Febrero a las 8:00 pm en el Parque de Madre Selva. \nEntregaremos lazos reflectivos para correr en pareja; con tu amigo, amiga, novia, novio, hermana, primo etc... lo importante es correr #Enlazados! No te la pierdas!! ', 0, '1', 'Active Runners\nPowerade\nBimbo\nTransporte SOL\n', 13.663411, -89.255346, 0, 3, '2015-02-06 09:58:24', '2015-02-10 10:39:29', 23, 1),
(41, 'Las Antenas Trail Running', '8k y 16k', 'Santa Ana', 'El Salvador', 'SV', '2015-03-15', '07:00:00', '$8', 'Entrega 13 y 14 de Marzo', 'Iron Body Gym \nFebrero 23 a Marzo 9', NULL, 'Acompañanos en esta emocionante aventura en honor al 6to aniversario de Iron Body Gym', 0, '1', 'Team Iron Body Running\nAleros Running\nEl Salvador Sport', 0, 0, 0, 1, '2015-02-06 10:14:56', '2015-03-03 06:01:54', 24, 2),
(42, '10K Ciudad de Guatemala', '10k', 'Ciudad de Guatemala, 7ª avenida zona 4', 'Guatemala', 'GT', '2015-03-21', '18:50:00', 'Q150', 'Camisa, Medalla y mucho más', 'En línea: www.sportsandmarketing.com Física: \nMunicipalidad Central \nMini Muni Galerías Primma \nRegencia Norte \nTelefónica: \n22005252\n\nFECHA LÍMITE DE INSCRIPCIÓN:Miércoles 4 de febrero de 2015 \no al agotarse los cupos, lo que suceda antes.', NULL, 'Los 10K BAM de la Municipalidad de Guatemala es un evento de entretenimiento que está concebido como una oportunidad para animar a los participantes a iniciarse en la práctica del atletismo de una forma divertida y amena. \n\nEste no es un evento de competición sino un evento que permite a los atletas con más experiencia acompañar en el recorrido y animar a sus amigos que no han corrido en un evento masivo o bien que no se han iniciado en la práctica del atletismo por lo tanto NO HABRÁN premios en efectivo en ninguna de las categorías. \n\nTodos los participantes, oficialmente inscritos, que crucen la meta, recibirán una medalla conmemorativa.\n\nRECORRIDO: Salida y Meta 7ª avenida zona 4 a un costado de la Municipalidad de Guatemala. Por la 7ª avenida hasta la 6ª. calle de la z1 pasando frente al Palacio Nacional, para retornar por la 6ª avenida sobre el Paseo de la Sexta, la sexta avenida hasta la Ruta 6 de la zona 4, toma (contra vía) la 7ª Avenida y sigue hasta 13 calle zona 9 en donde conecta con la Avenida Reforma que, a contra vía lo llevará de nuevo a la ruta 6 (frente a la Iglesia Yurrita) para conectar de nuevo con la 7ª Avenida de la zona 4 hasta llegar a la Municipalidad de Guatemala.', 0, '1', 'BAM\nGatorade\nMunicipalidad de Guatemala', 14.62427, -90.514878, 0, 0, '2015-02-06 10:35:14', NULL, 25, 1),
(43, 'Beach Run', '5k, 10k y 15k', '[pendiente]', 'El Salvador', 'SV', '2015-03-28', '15:00:00', '[pendiente]', '[pendiente]', '[pendiente]', NULL, '[pendiente]', 0, '1', 'Run El Salvador', 0, 0, 0, 0, '2015-02-06 14:51:59', NULL, 1, 2),
(44, 'Woman Run', '3k ,5k y 10k', 'La Libertad, Antiguo Cuscatlán, Redondel D''AUbuisson', 'El Salvador', 'SV', '2015-03-08', '07:00:00', '$10', 'Camiseta, número, medalla, vincha y sticker', '- Estadio Mágico González\n- Almacenes Santa Lucía (Metro Sur, Metrocentro 3ra,4ta y 8va etapa. Plaza Merliot, Santa Ana, Unicentro Lourdes)', NULL, 'En conmemoración del da internacional de la mujer', 0, '1', 'Running for Life\nEco 95.3\nCool 89.3\nTitos Sport\nCalvo\nSta Lucía', 13.681761, -89.246704, 0, 0, '2015-02-09 22:55:34', NULL, 3, 0),
(45, 'Ultra Trail Run: El Reto del Quetzal', '118k en 4 etapas', 'Antigua Guatemala, Sacatepequez', 'Guatemala', 'GT', '2015-03-05', '07:00:00', '$550 con Hospedaje, $300 sin H', 'Kit con Hospedaje:\nDerecho a participar los 4 días de competencia\nMaletín oficial\nPlayera de El Reto\nGafete de identificación\nBrazalete de identificac', 'Guatemala: The North Face Miraflores y Oakland Mall\nEl Salvador: Heartland Outdoors', NULL, 'El Reto del Quetzal, es una carrera de resistencia donde cada atleta pondrá al máximo todas sus habilidades. Se competira individualmente o en parejas. La modalidad en parejas, cada participante debera hacer por lo menos una etapa completa.\n\nLa carrera empezara en Antigua Guatemala y terminara cuatro días después en Retalhuleu, son cuatro etapas en las cuales se acumularan los tiempos de cada una para sacar un resultado final.\n\nEl Reto del Quetzal esta diseñada para todos los corredores que este bien preparados física y mentalmente, estos pasaran por los lugares mas bellos de la región, desde áreas verdes del altiplano hasta los Volcanes activos de Quetzaltenango.\n\nEl tipo de clima será variado de frio a húmedo y de húmedo a Calor, habrá todo tipo de terreno como, terrecería (grava), asfalto, vereda y bosques.\n\nEn cada PC habrá un puesto de abastecimiento que contara con agua pura, bebida hidratante, frutas, sándwiches y barras nutricionales. Cada etapa contara con dos o tres Puestos de Control, para abastecer al atleta y ofrecerle asistencia medica.\n\nHabrán 3 categorías, (Individual Hombres, Individual Mujeres y en Parejas), siempre y cuando se llene el cupo mínimo de cada categoría de 5 participantes o 5 parejas. Menores de 18 años no podrán participar.\n\nTodos los días al finalizar la etapa se premiaran a los ganadores. El premio al equipo ganador de la clasificación general será dinero en efectivo y a las categorías restantes serán unas grandes sorpresas.\n\nLa carrera El Reto del Quetzal fue creada para satisfacer la necesidad del atleta extremo y además inculcar el cuidado de los lugares tan bellos que posee nuestra Guatemala.', 0, '1', 'Movisar\nNuun\nHonda', 14.557658, -90.734097, 0, 0, '2015-02-10 17:06:30', NULL, 26, 5),
(46, '1st Interamerican Run', '3k, 5k, 10k', 'Santa Ana', 'El Salvador', 'SV', '2015-02-28', '07:00:00', '$8', '[pendiente]', 'Escuela Interamericana\nCalle Monseñor Clemente Barrera Rivas, Santa Ana, El Salvador\n+503 2445 6400', '', 'Este 28 de febrero asistiremos a la carrera organizada, denominada:\n"Corriendo y Caminando por la Amistad 2015" de la Escuela Interamericana y organizada por Santa Ana Runners. \nEsta carrera cuenta con estricta seguridad, hidratación y logística adecuada. ', 0, '1', 'Santa Ana Runners\n Escuela Interamericana Santa Ana', 0, 0, 0, 0, '2015-02-18 09:07:34', '2015-02-25 14:04:09', 17, 1),
(47, 'Challenge Triathlon', '25.75k', 'La Paz, Costa del Sol, Hotel Pacific Paradise', 'El Salvador', 'SV', '2015-03-21', '15:00:00', '$15', '[pendiente]', 'festri@hotmail.com', NULL, 'La Federacion Salvadoreña Triatlón te invita a que disfrutes de este triatlón', 0, '1', 'Federacion Salvadoreña Triatlón', 13.298612, -88.903788, 0, 0, '2015-02-18 09:50:16', NULL, 22, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_REQUEST_LOG`
--

DROP TABLE IF EXISTS `RB_REQUEST_LOG`;
CREATE TABLE IF NOT EXISTS `RB_REQUEST_LOG` (
  `rbrequestlogid` int(11) NOT NULL AUTO_INCREMENT,
  `type_req` varchar(45) DEFAULT NULL,
  `page_req` varchar(45) DEFAULT NULL,
  `params` varchar(500) DEFAULT NULL,
  `user_agent` varchar(200) DEFAULT NULL,
  `date_request` datetime DEFAULT NULL,
  PRIMARY KEY (`rbrequestlogid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_SPONSOR`
--

DROP TABLE IF EXISTS `RB_SPONSOR`;
CREATE TABLE IF NOT EXISTS `RB_SPONSOR` (
  `rbsponsorid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `photo` varchar(150) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`rbsponsorid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_TRAIN`
--

DROP TABLE IF EXISTS `RB_TRAIN`;
CREATE TABLE IF NOT EXISTS `RB_TRAIN` (
  `rbtrainid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `location` varchar(150) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `going` int(11) DEFAULT '0',
  `level` varchar(45) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `rbuserid` int(11) NOT NULL,
  PRIMARY KEY (`rbtrainid`),
  KEY `fk_RB_TRAIN_RB_USER1_idx` (`rbuserid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_USER`
--

DROP TABLE IF EXISTS `RB_USER`;
CREATE TABLE IF NOT EXISTS `RB_USER` (
  `rbuserid` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `facebookid` varchar(90) DEFAULT NULL,
  `twitterid` varchar(100) DEFAULT NULL,
  `googleid` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `account_status` int(11) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `avatar_photo` varchar(150) DEFAULT NULL,
  `slogan` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`rbuserid`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=26 ;

--
-- Volcado de datos para la tabla `RB_USER`
--

INSERT INTO `RB_USER` (`rbuserid`, `first_name`, `last_name`, `email`, `password`, `phone_number`, `facebookid`, `twitterid`, `googleid`, `birthday`, `account_status`, `date_created`, `avatar_photo`, `slogan`) VALUES
(13, 'Freddy', 'Ayala', 'fredd.seb8@gmail.com', NULL, NULL, '735615096526564', NULL, NULL, NULL, 1, '2014-11-29 15:30:01', NULL, NULL),
(17, 'Efra', 'Calderon', 'ecalderon1991@gmail.com', NULL, NULL, '857774240911064', NULL, NULL, NULL, 1, '2014-12-08 13:13:54', NULL, NULL),
(21, 'Karla', 'Gonzo', 'hikaru_182@hotmail.com', NULL, NULL, '10152552775533443', NULL, NULL, NULL, 1, '2014-12-17 12:19:54', NULL, NULL),
(15, 'Kanka', 'Law', 'pattylarin@hotmail.com', NULL, NULL, '10154868880040057', NULL, NULL, NULL, 1, '2014-11-30 20:23:31', NULL, NULL),
(14, 'Alvaro', 'Nu?ez', 'alvaro-nunez4@hotmail.com', NULL, NULL, '10205549303670940', NULL, NULL, NULL, 1, '2014-11-30 11:11:22', NULL, NULL),
(16, 'H?ctor', 'Cabrera', 'manzanaconvodka@live.com', NULL, NULL, '10152430137601957', NULL, NULL, NULL, 1, '2014-12-06 16:46:42', NULL, NULL),
(18, 'Alvin', 'Melara', 'alvinmelara16@gmail.com', NULL, NULL, '895039157187352', NULL, NULL, NULL, 1, '2014-12-09 11:39:34', NULL, NULL),
(19, 'Ever Eraldo', 'Cuellar Sanchez', 'ecuellarsanchez180@gmail.com', NULL, NULL, '798447893538414', NULL, NULL, NULL, 1, '2014-12-13 09:24:07', NULL, NULL),
(20, '', '', '', NULL, NULL, '', NULL, NULL, NULL, 1, '2014-12-16 22:59:14', NULL, NULL),
(22, 'Dany', 'Godoy', 'null', NULL, NULL, '689890227789082', NULL, NULL, NULL, 1, '2015-01-28 06:34:16', NULL, NULL),
(23, 'Ren', 'Cadenas', 'potter-master@hotmail.com', NULL, NULL, '10206104400711990', NULL, NULL, NULL, 1, '2015-02-10 10:38:52', NULL, NULL),
(24, 'Irene', 'Ayala', 'irene_ayala8@hotmail.com', NULL, NULL, '10206527220524109', NULL, NULL, NULL, 1, '2015-02-16 20:36:46', NULL, NULL),
(25, 'Eduardo', 'Escobar', 'renescobar.sv@live.com', NULL, NULL, '1060315410651068', NULL, NULL, NULL, 1, '2015-03-03 06:01:37', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_USER_MEDALS`
--

DROP TABLE IF EXISTS `RB_USER_MEDALS`;
CREATE TABLE IF NOT EXISTS `RB_USER_MEDALS` (
  `rbusermedalsid` int(11) NOT NULL,
  `rbuserid` int(11) NOT NULL,
  PRIMARY KEY (`rbusermedalsid`,`rbuserid`),
  KEY `fk_RB_MEDALS_has_RB_USER_RB_USER1_idx` (`rbuserid`),
  KEY `fk_RB_MEDALS_has_RB_USER_RB_MEDALS1_idx` (`rbusermedalsid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_USER_RACES`
--

DROP TABLE IF EXISTS `RB_USER_RACES`;
CREATE TABLE IF NOT EXISTS `RB_USER_RACES` (
  `rbuserid` int(11) NOT NULL,
  `rbraceid` int(11) NOT NULL,
  PRIMARY KEY (`rbuserid`,`rbraceid`),
  KEY `fk_RB_USER_has_RB_RACE_RB_RACE1_idx` (`rbraceid`),
  KEY `fk_RB_USER_has_RB_RACE_RB_USER1_idx` (`rbuserid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `RB_USER_RACES`
--

INSERT INTO `RB_USER_RACES` (`rbuserid`, `rbraceid`) VALUES
(0, 40),
(8, 1),
(8, 2),
(8, 3),
(8, 5),
(8, 6),
(8, 8),
(8, 9),
(8, 10),
(8, 14),
(8, 15),
(9, 10),
(10, 15),
(12, 15),
(13, 16),
(13, 17),
(13, 19),
(13, 21),
(13, 23),
(13, 27),
(13, 32),
(13, 37),
(13, 40),
(15, 37),
(21, 21),
(23, 40),
(24, 37),
(25, 41);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_USER_SCORE`
--

DROP TABLE IF EXISTS `RB_USER_SCORE`;
CREATE TABLE IF NOT EXISTS `RB_USER_SCORE` (
  `rbuserscoreid` int(11) NOT NULL AUTO_INCREMENT,
  `distance` varchar(45) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `rbuserid` int(11) NOT NULL,
  PRIMARY KEY (`rbuserscoreid`),
  KEY `fk_RB_USER_SCORE_RB_USER1_idx` (`rbuserid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_USER_TRAIN`
--

DROP TABLE IF EXISTS `RB_USER_TRAIN`;
CREATE TABLE IF NOT EXISTS `RB_USER_TRAIN` (
  `rbuserid` int(11) NOT NULL,
  `rbtrainid` int(11) NOT NULL,
  PRIMARY KEY (`rbuserid`,`rbtrainid`),
  KEY `fk_RB_USER_has_RB_TRAIN_RB_TRAIN1_idx` (`rbtrainid`),
  KEY `fk_RB_USER_has_RB_TRAIN_RB_USER1_idx` (`rbuserid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RB_WINNER`
--

DROP TABLE IF EXISTS `RB_WINNER`;
CREATE TABLE IF NOT EXISTS `RB_WINNER` (
  `rbwinnerid` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) DEFAULT NULL,
  `group` varchar(100) DEFAULT NULL,
  `distance` varchar(45) DEFAULT NULL,
  `time` time DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `date_created` date DEFAULT NULL,
  `rbraceid` int(11) NOT NULL,
  PRIMARY KEY (`rbwinnerid`),
  KEY `fk_RB_WINNER_RB_RACE1_idx` (`rbraceid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
