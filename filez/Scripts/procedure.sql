
delimiter //
create procedure nombres()
begin    
declare nombre cursor for 
	select empleados.apellido from empresaz.empleados;
open nombre;
loop 
end loop
end//
delimiter ;





drop procedure numero_empleados;
delimiter //
create procedure numero_empleados(out numero int)
begin    
select count(*) into numero from empleados ;

 

end//
delimiter ;



set @a as int;






