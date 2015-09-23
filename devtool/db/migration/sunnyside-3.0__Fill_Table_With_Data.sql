
INSERT INTO `user` (`role`, `username`, `password`, `is_active`) VALUES ('USER', 'jjims', 'user', true);
INSERT INTO `user` (`role`, `username`, `password`, `is_active`) VALUES ('USER', 'dradcliff', 'user', true);
INSERT INTO `user` (`role`, `username`, `password`, `is_active`) VALUES ('USER', 'bwayne', 'user', true);

INSERT INTO `profile` (`user_id`, `created`, `first_name`, `last_name`, `date_of_birth`) VALUES (1, CURRENT_TIMESTAMP(), "Admin", "Admin", CURRENT_DATE());
INSERT INTO `profile` (`user_id`, `created`, `first_name`, `last_name`, `date_of_birth`) VALUES (2, CURRENT_TIMESTAMP(), "David", "Sun", CURRENT_DATE());
INSERT INTO `profile` (`user_id`, `created`, `first_name`, `last_name`, `date_of_birth`) VALUES (3, CURRENT_TIMESTAMP(), "Jonas", "Jims", CURRENT_DATE());
INSERT INTO `profile` (`user_id`, `created`, `first_name`, `last_name`, `date_of_birth`) VALUES (4, CURRENT_TIMESTAMP(), "Daniel", "Radcliff", CURRENT_DATE());
INSERT INTO `profile` (`user_id`, `created`, `first_name`, `last_name`, `date_of_birth`) VALUES (5, CURRENT_TIMESTAMP(), "Bruce", "Wayne", CURRENT_DATE());

INSERT INTO `friends` (`profile_id`, `friend_profile_id`) VALUES (2, 3), (2, 4), (2, 5);
INSERT INTO `friends` (`profile_id`, `friend_profile_id`) VALUES (3, 2), (3, 5);

INSERT INTO `blog` (`profile_id`, `created`, `content`) VALUES (2, CURRENT_TIMESTAMP(), "Example text 1");
INSERT INTO `blog` (`profile_id`, `created`, `content`) VALUES (2, CURRENT_TIMESTAMP(), "Example text 2");
INSERT INTO `blog` (`profile_id`, `created`, `content`) VALUES (2, CURRENT_TIMESTAMP(), "Example text 3");
INSERT INTO `blog` (`profile_id`, `created`, `content`) VALUES (2, CURRENT_TIMESTAMP(), "Example text 4");
INSERT INTO `blog` (`profile_id`, `created`, `content`) VALUES (2, CURRENT_TIMESTAMP(), "Example text 5");

INSERT INTO `blog` (`profile_id`, `created`, `content`) VALUES (3, CURRENT_TIMESTAMP(), "Example text 1");
INSERT INTO `blog` (`profile_id`, `created`, `content`) VALUES (3, CURRENT_TIMESTAMP(), "Example text 2");
INSERT INTO `blog` (`profile_id`, `created`, `content`) VALUES (3, CURRENT_TIMESTAMP(), "Example text 3");