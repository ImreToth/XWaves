CREATE TABLE IF NOT EXISTS `user` (`id` int(10) NOT NULL auto_increment,
                                   `username` varchar(255),
                                   `password` varchar(255),
                                   `email` varchar(255),
                                   `datetime` date,
                                   PRIMARY KEY( `id` ));

CREATE TABLE IF NOT EXISTS `hero` (
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

CREATE TABLE IF NOT EXISTS `monster` (
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

CREATE TABLE IF NOT EXISTS `item` (
	`id` int(10) NOT NULL auto_increment,
	`name` varchar(255),
	`target` varchar(255),
	`ability` varchar(255),
	`abilityvalue` int(10),
	`useable` int(10),
        `cost` int(10) ,
	PRIMARY KEY( `id` )
);

CREATE TABLE IF NOT EXISTS `games` (
	`id` int(10) NOT NULL auto_increment,
	`name` varchar(255),
	`gamemaster` varchar(255),
	`palyer1` varchar(255),
	`player2` varchar(255),
	`player3` varchar(255),
	`playernumber` int(10),
        `nextplayer` varchar(255),
	PRIMARY KEY( `id` )
);

INSERT INTO `user`(`id`, `username`, `password`, `email`, `datetime` ) VALUES (1,'admin','admin','admin@admin.hu', NOW() );

INSERT INTO `monster`(`id`, `name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed` , `cost` ) VALUES (1,'banshee','melee',2,2,3,2,2,15);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('centaur','ranged',3,2,3,1,3,20);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('crystalgolem','melee',2,1,1,1,1,15);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('deepelf','melee',1,1,1,1,1,5);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('demilich','magic',3,1,2,1,4,20);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('devilkin','melee',4,3,2,3,2,40);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('dragonwhelp','ranged',2,2,3,3,5,20);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('draugr','melee',4,3,2,4,2,40);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('fireelemental','magic',5,3,2,4,5,60);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('frogling','ranged',2,3,2,3,4,25);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('goblin','melee',4,4,2,2,2,30);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('goblinrunts','melee',5,2,3,1,5,60);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('goblinveteran','melee',6,4,2,3,2,75);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('goblinwarrior','melee',4,5,4,5,2,35);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('imp','ranged',2,1,2,1,6,20);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('lich','magic',5,4,4,5,3,40);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('minotaur','melee',6,6,4,5,3,90);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('nightmother','magic',3,4,2,2,5,50);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('ooze','melee',1,4,2,6,3,25);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('orc','melee',6,6,5,5,5,200);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('ratling','melee',3,4,3,4,4,75);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('treefolk','magic',4,4,5,5,2,60);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('voidelemental','magic',5,4,4,5,5,100);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('werewolfcub','melee',4,5,2,4,6,125);
INSERT INTO `monster`(`name`, `attacktype`, `attack`, `health`, `stamina`, `defense`, `speed`,`cost`) VALUES ('zombie','melee',4,2,5,2,5,85);

INSERT INTO `hero`(`id`,`name`, `type`, `attack`, `health`, `stamina`, `defense`, `speed`) VALUES (1,'barbarian','melee',5,5,2,6,3);
INSERT INTO `hero`(`name`, `type`, `attack`, `health`, `stamina`, `defense`, `speed`) VALUES ('bard','magic',3,4,4,3,3);
INSERT INTO `hero`(`name`, `type`, `attack`, `health`, `stamina`, `defense`, `speed`) VALUES ('paladin','melee',3,5,2,5,2);
INSERT INTO `hero`(`name`, `type`, `attack`, `health`, `stamina`, `defense`, `speed`) VALUES ('priest','magic',2,4,3,4,3);
INSERT INTO `hero`(`name`, `type`, `attack`, `health`, `stamina`, `defense`, `speed`) VALUES ('ranger','ranged',4,3,5,3,5);
INSERT INTO `hero`(`name`, `type`, `attack`, `health`, `stamina`, `defense`, `speed`) VALUES ('rogue','melee',3,3,3,3,3);
INSERT INTO `hero`(`name`, `type`, `attack`, `health`, `stamina`, `defense`, `speed`) VALUES ('warrior','melee',5,4,3,3,3);
INSERT INTO `hero`(`name`, `type`, `attack`, `health`, `stamina`, `defense`, `speed`) VALUES ('wizard','magic',4,3,4,3,3);

INSERT INTO `item`(`id`,`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES (1,'armor','self','defense',3,1,20);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('axe','self','attack',2,1,10);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('axe2','self','attack',4,1,25);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('axeDouble','self','attack',3,1,15);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`,`cost`) VALUES ('axeDouble2','self','attack',5,1,30);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('bow','self','stamina',4,1,20);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('helmet','self','defense',2,1,15);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('potionBlue','self','attack',1,5,5);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('potionRed','self','health',1,5,5);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('potionGreen','self','stamina',1,5,5);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('scroll','allFriendly','allAbility',1,10,10);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('shield','self','defense',2,1,15);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('tome','target','health',2,-1,20);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('tools','target','defense',1,-1,10);
INSERT INTO `item`(`name`, `target`, `ability`, `abilityvalue`,`useable`, `cost`) VALUES ('wand','target','health',4,-1,35);
