create user book_ex identified by book_ex;
grant connect, resource to book_ex;


--alter table board drop constraint board_num_pk;
--drop table board;

create table board (
    num        	number(10) not null,
    name        varchar2(20) not null,
    title       varchar2(100) not null,
    content     clob null,
    readcount   number(10) default 0 not null,
    writedate   date not null
);

alter table board add constraint board_num_pk primary key ( num );

insert into board values (1,'유재석','제목 테스트1','내용 테스트1',0,sysdate);
insert into board values (2,'이광수','제목 테스트2','내용 테스트2',1,sysdate);

commit;

select * from board;
