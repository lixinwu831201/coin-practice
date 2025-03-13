--INSERT INTO `coin` VALUES (1,'USD','美金'),(2,'GBP','英鎊'),(3,'EUR','歐元')
--上面方式會造成後續新增PK重複

INSERT INTO `coin` (coinType, coinChineseName) VALUES ('USD','美金');
INSERT INTO `coin` (coinType, coinChineseName) VALUES ('GBP','英鎊');
INSERT INTO `coin` (coinType, coinChineseName) VALUES ('EUR','歐元');