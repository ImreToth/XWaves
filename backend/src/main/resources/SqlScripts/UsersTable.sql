CREATE TABLE IF NOT EXISTS `Users` (`id` int(10) NOT NULL auto_increment,
                                    `username` varchar(255),
                                    `password` varchar(255),
                                    `email` varchar(255),
                                    `regtime` date,
                                    PRIMARY KEY( `id` ));