ALTER TABLE  `evaluation`.`applicant` ADD COLUMN `test_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00';
ALTER TABLE  `evaluation`.`test_model` ADD COLUMN `minutesToFinish` int(11) NOT NULL DEFAULT 0;

UPDATE `evaluation`.`applicant` SET test_date = create_date;