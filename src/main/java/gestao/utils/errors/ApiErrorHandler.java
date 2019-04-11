package gestao.utils.errors;

import com.fasterxml.jackson.databind.JsonMappingException;
import gestao.exceptions.bases.RecursoNaoEncontradoException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleNotFound(final EntityNotFoundException ex) {
		return this.buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
	}

	@ExceptionHandler(java.lang.IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(final java.lang.IllegalArgumentException ex) {
		return this.buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage()));
	}

	@ExceptionHandler(JsonMappingException.class)
	protected ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex) {
		List<ApiSubError> subErros =  Arrays.asList(new ApiValidationError(ex.getPathReference(), ex.getMessage()));
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Formato JSON inválido", subErros));
	}
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ApiSubError> subErros =  Arrays.asList(new ApiValidationError(ex.getRootCause().getLocalizedMessage(), ex.getMostSpecificCause().getLocalizedMessage()));
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Formato JSON inválido", subErros));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			return buildResponseEntity(new ApiError(status, "Formato JSON inválido", ex));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		 List<ApiSubError> subErros= ex.getConstraintViolations().stream()
				.map(x -> new ApiValidationError(
						x.getRootBeanClass().getSimpleName(),
						x.getPropertyPath().toString(),
						x.getInvalidValue(),
						x.getMessageTemplate()))
				.collect(Collectors.toList());
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Erro validação", subErros));
	}


	protected static ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
