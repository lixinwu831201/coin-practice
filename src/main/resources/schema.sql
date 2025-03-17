create table coin(
  coinNo int not null AUTO_INCREMENT,
  coinType varchar(10) not null UNIQUE,
  coinChineseName varchar(10) not null,
  PRIMARY KEY (coinNo),
  CONSTRAINT coinTypeUnique UNIQUE(coinType)
);