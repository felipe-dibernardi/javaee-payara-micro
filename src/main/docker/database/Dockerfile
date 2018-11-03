FROM mysql:5

MAINTAINER Felipe Di Bernardi S Thiago "felipe.dibernardi@organizafacil.com"

ENV MYSQL_DATABASE=condominium \
    MYSQL_ROOT_PASSWORD=password

ADD maven/init.sql /docker-entrypoint-initdb.d

CMD ["mysqld"]