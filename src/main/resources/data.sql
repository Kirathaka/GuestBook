
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');

INSERT INTO `user` VALUES (1,'$2a$10$HwsVbtOt9kA2m30j31.Cb.StYPftglZNy3nc7yYTJHRtmW9ePCKP6','admin'),(2,'$2a$10$aZlgu/ZHCYc4GhGbA.jczuFmcJQdhMyoOfiCAVL5bMCwIdapymKba','micheal'),(3,'$2a$10$3QatwEZ1LiwZOfpfCr8Yx.eluH4rsTskC7PhF3o9NyD7yQsqH5TPa','jennifer'),(4,'$2a$10$d97XtrGqHaNWC8lUs7e97e2wvOVJQ7qpQOSRE8bOzANFFNdI50l/e','kenneth');

INSERT INTO `user_role` VALUES (1,1),(2,2),(3,2),(4,2);

INSERT INTO `entry` VALUES (1,'http://localhost:8090/downloadFile/sampleImage1.jpg','',2),(0,'','WOW! This is breaking the internet :)',3),(0,'','Thanks for this gift, developer.',4);