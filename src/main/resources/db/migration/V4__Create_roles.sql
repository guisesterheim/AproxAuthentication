INSERT INTO `authentication_database`.`role` (`role`) VALUES ('ROLE_ADMIN');
INSERT INTO `authentication_database`.`role` (`role`) VALUES ('ROLE_USER');
INSERT INTO `authentication_database`.`role` (`role`) VALUES ('ROLE_API');
INSERT INTO `authentication_database`.`user` (`email`, `password`, `active`) VALUES ('admin@test.com', '$2a$10$MMR51uBmbeVFVwDnjV0pn.6UtTaj7GV.V.bsdZc99jUpsZpvSdDv.', '1');
INSERT INTO `authentication_database`.`user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `authentication_database`.`user_role` (`user_id`, `role_id`) VALUES ('1', '2');
INSERT INTO `authentication_database`.`user_role` (`user_id`, `role_id`) VALUES ('1', '3');