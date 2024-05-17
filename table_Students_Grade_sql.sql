update users set role = 'admin' where username = 'Võ Minh Nghĩa';

select * from users

create table grade (
id int ,
masv nvarchar(50),
tienganh int ,
tinhoc int ,
gdtc int ,
primary key(id),
foreign key (masv) references students(masv)

)


create table students (
masv  nvarchar(50) ,
hoten nvarchar(500),
email nvarchar(500),
sodt  nvarchar(12),
gioitinh bit,
diachi nvarchar(500),
hinh nvarchar(500),
primary key (masv)
)