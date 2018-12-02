use instituto;
-- selects
select mo.nombre, al.nombre , al.apellidoP, al.apellidoM, ma.notaFinal
from modulo mo inner join modulo_alumno ma inner join alumno al on
mo.codigo = ma.codigo_modulo and ma.codigo_alumno = al.expediente
where mo.codigo = 1 order by ma.notaFinal  ;

select p.nombre, p.apellidoP, p.apellidoM from
alumno al inner join profesor p inner join modulo_alumno ma on
al.expediente = ma.codigo_alumno and ma.codigo_modulo = p.codigo_modulo
where al.expediente = 3;

select mo.nombre,count(ma.codigo_alumno) as ord from
modulo mo inner join modulo_alumno ma on mo.codigo = ma.codigo_modulo
group by ma.codigo_modulo order by ord;
