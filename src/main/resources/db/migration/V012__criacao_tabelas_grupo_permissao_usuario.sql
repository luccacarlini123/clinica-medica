create table grupo (
  id bigint not null auto_increment, 
  nome varchar(80) not null, 
  primary key (id)
) engine = InnoDB default charset=utf8;

create table grupo_permissao (
  grupo_id bigint not null,
  permissao_id bigint not null
) engine = InnoDB default charset=utf8;

create table grupo_usuario (
  usuario_id bigint not null,
  grupo_id bigint not null
) engine = InnoDB default charset=utf8;

create table permissao (
  id bigint not null auto_increment, 
  descricao varchar(80) not null, 
  nome varchar(80) not null, 
  primary key (id)
) engine = InnoDB default charset=utf8;

alter table 
  grupo_permissao 
add 
  constraint fk_grupo_permissao_permissao foreign key (permissao_id) references permissao (id);

alter table 
  grupo_permissao 
add 
  constraint fk_grupo_permissao_grupo foreign key (grupo_id) references grupo (id);

alter table 
  grupo_usuario 
add 
  constraint fk_grupo_usuario_grupo foreign key (grupo_id) references grupo (id);

alter table 
  grupo_usuario 
add 
  constraint fk_grupo_usuario_usuario foreign key (usuario_id) references usuario (id);