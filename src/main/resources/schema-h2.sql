create table Role(
   roleId     int not null auto_increment
  ,roleName   varchar(45) not null
  ,roleDesc   varchar(255)
  ,createdTm  timestamp
  ,createdBy  varchar(45)
  ,updatedTm  timestamp
  ,updatedBy  varchar(45)
  ,deleteFlag varchar(1)
  , primary key (roleId)
);