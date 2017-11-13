CREATE TABLE IF NOT EXISTS `Heroes` (
	`id` int(10) NOT NULL auto_increment,
	`name` varchar(255),
	`type` varchar(255),
	`attack` int(10),
	`health` int(10),
	`stamina` int(10),
	`defense` int(10),
	`speed` int(10),
	PRIMARY KEY( `id` )
);

CREATE TABLE IF NOT EXISTS `Monsters` (
	`id` int(10) NOT NULL auto_increment,
	`name` varchar(255),
	`attacktype` varchar(255),
	`attack` int(10),
	`health` int(10),
	`stamina` int(10),
	`defense` int(10),
	`speed` int(10),
        `cost` int(10) ,
	PRIMARY KEY( `id` )
);

CREATE TABLE IF NOT EXISTS `Items` (
	`id` int(10) NOT NULL auto_increment,
	`name` varchar(255),
	`target` varchar(255),
	`ability` varchar(255),
	`abilityvalue` int(10),
	`useable` int(10),
        `cost` int(10) ,
	PRIMARY KEY( `id` )
);