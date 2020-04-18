CREATE TABLE `authentication_database`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `active` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `authentication_database`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `authentication_database`.`user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_uer_id_idx` (`user_id` ASC),
  INDEX `fk_role_id_idx` (`role_id` ASC),
  CONSTRAINT `fk_uer_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `authentication_database`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `authentication_database`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

INSERT INTO `authentication_database`.`role` (`role`) VALUES ('ADMIN');
INSERT INTO `authentication_database`.`role` (`role`) VALUES ('USER');
