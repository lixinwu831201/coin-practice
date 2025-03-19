CREATE TABLE IF NOT EXISTS coin(
  coinNo INT NOT NULL AUTO_INCREMENT,
  coinType VARCHAR(10) NOT NULL UNIQUE,
  coinChineseName VARCHAR(10) NOT NULL,
  PRIMARY KEY (coinNo),
  CONSTRAINT coinTypeUnique UNIQUE(coinType)
);