CREATE TABLE IF NOT EXISTS `Heroes` (
	`id` int(10) NOT NULL auto_increment,
	`name` varchar(255),
	`class` varchar(255),
	`attack` numeric(9,2),
	`health` numeric(9,2),
	`stamina` numeric(9,2),
	`defense` numeric(9,2),
	`speed` numeric(9,2),
	PRIMARY KEY( `id` )
);

CREATE TABLE IF NOT EXISTS `Monsters` (
	`id` int(10) NOT NULL auto_increment,
	`name` varchar(255),
	`attacktype` varchar(255),
	`attack` numeric(9,2),
	`health` numeric(9,2),
	`stamina` numeric(9,2),
	`defense` numeric(9,2),
	`speed` numeric(9,2),
	PRIMARY KEY( `id` )
);

CREATE TABLE IF NOT EXISTS `Quests` (
	`id` int(10) NOT NULL auto_increment,
	`name` varchar(255),
	`story` varchar(255),
	`monster1` varchar(255),
	`monster2` varchar(255),
	`monster3` varchar(255),
	`monster4` varchar(255),
	PRIMARY KEY( `id` )
);

CREATE TABLE IF NOT EXISTS `Items` (
	`id` int(10) NOT NULL auto_increment,
	`name` varchar(255),
	`wear` varchar(255),
	`ability` varchar(255),
	`abilityvalue` varchar(255),
	PRIMARY KEY( `id` )
);