CREATE PROCEDURE `numeroAlumnos` (out numero int)
BEGIN
select count(*) from alumno into numero;
END
