drop procedure numeroAlumnos;
drop procedure matricularAlumnos;
delimiter $$
PROCEDURE `numeroAlumnos`(out numero int)
BEGIN
select count(*) from alumno  into numero;
END$$

create procedure `matricularAlumnos`
(in idmodulo int,in idalumno int,out matriculado int)
begin
declare numMod int default 0;
declare existeModulo int default 0;
declare existeAlumno int default 0;
declare ultimo int default 0;
declare existeTupla int default 0 ;
select count(*) from modulo_alumno 
 where codigo_modulo = idmodulo into numMod;
select count(*) from alumno 
 where expediente = idalumno into existeAlumno;
select count(*) from modulo
 where codigo = idmodulo into existeModulo ;
 select count(*) from modulo_alumno 
 where codigo_alumno = idAlumno and codigo_modulo = idModulo into existeTupla;
if numMod > 30 then
	set matriculado = 0;
else
	if existeModulo > 0 and existeAlumno > 0 and existeTupla < 0 then
		select max(idModulo_alumno) from modulo_alumno into ultimo;
		insert into modulo_alumno values(ultimo+1,idAlumno,idModulo);
        set matriculado = 1;
        else
			set matriculado = 0;
    end if;
end if;
end $$

delimiter;

