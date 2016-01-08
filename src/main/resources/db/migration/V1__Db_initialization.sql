

create table currency_exchange_rate (
    id int not null auto_increment primary key,
    currency_name varchar(100) not null,
    conversion_rate  varchar(100) not null,
    base_currency  varchar(100) not null,
    sync_date  DATETIME not null
);