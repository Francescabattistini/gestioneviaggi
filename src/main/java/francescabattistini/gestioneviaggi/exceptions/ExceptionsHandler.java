package francescabattistini.gestioneviaggi.exceptions;

import francescabattistini.gestioneviaggi.PayloadDTO.ErrorsResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	public ErrorsResponseDto handleBadrequest(BadRequestException ex) {
		return new ErrorsResponseDto(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	public ErrorsResponseDto handleNotFound(NotFoundException ex) {
		return new ErrorsResponseDto(ex.getMessage(), LocalDateTime.now());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	public ErrorsResponseDto handleGeneric(Exception ex) {
		ex.printStackTrace();
		return new ErrorsResponseDto("problema errore interno del server", LocalDateTime.now());
	}
}
