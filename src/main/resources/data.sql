--INSERT INTO `coin` VALUES (1,'USD','美金'),(2,'GBP','英鎊'),(3,'EUR','歐元')
--上面方式會造成後續新增PK重複

MERGE INTO `coin` (coinType, coinChineseName) KEY (coinType) VALUES ('USD','美金');
MERGE INTO `coin` (coinType, coinChineseName) KEY (coinType) VALUES ('GBP','英鎊');
MERGE INTO `coin` (coinType, coinChineseName) KEY (coinType) VALUES ('EUR','歐元');