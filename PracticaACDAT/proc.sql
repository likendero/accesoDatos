drop procedure numeroAlumnos;
delimiter $$
PROCEDURE `numeroAlumnos`(out numero int)
BEGIN
select count(*) from alumno  into numero;
END$$

create procedure `matricularAlumnos`
(in modulo int,in alumno int,out matriculado int)
begin
set numMod = 0;

select count(*) from modulo_alumno 
 where codigo_modulo = modulo into numMod;

if numMod > 30 then
	set matriculado = 0;
else

end if;



end $$
delimiter;



