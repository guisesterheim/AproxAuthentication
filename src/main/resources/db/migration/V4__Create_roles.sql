INSERT INTO `authentication_database`.`role` (`role`) VALUES ('ROLE_ADMIN');
INSERT INTO `authentication_database`.`role` (`role`) VALUES ('ROLE_USER');
INSERT INTO `authentication_database`.`role` (`role`) VALUES ('ROLE_API');
INSERT INTO `authentication_database`.`user` (`email`, `password`, `active`) VALUES ('admin@test.com', '$2a$10$4YlZ4ohBwcmsIGW04KuTPe4X6npgXRmo3GYFxWusNq/63ovQqdya2', '1');
INSERT INTO `authentication_database`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT MAX(id) from authentication_database.`user`), (SELECT id FROM authentication_database.role WHERE role = 'ROLE_ADMIN'));
INSERT INTO `authentication_database`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT MAX(id) from authentication_database.`user`), (SELECT id FROM authentication_database.role WHERE role = 'ROLE_USER'));
INSERT INTO `authentication_database`.`user_role` (`user_id`, `role_id`) VALUES ((SELECT MAX(id) from authentication_database.`user`), (SELECT id FROM authentication_database.role WHERE role = 'ROLE_API'));