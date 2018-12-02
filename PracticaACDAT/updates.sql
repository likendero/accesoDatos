use instituto;
-- updates
update  alumno set nombre = ?, delegado = ? where expediente = ?;

update modulo_alumno set modulo_alumno.notaFinal = ? where codigo_alumno = ? and codigo_modulo = ?;