package br.com.controle.veiculos.exception.handler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.controle.veiculos.exception.EntidadeNaoEncontradoException;
import br.com.controle.veiculos.exception.RegraException;
import br.com.controle.veiculos.exception.handler.Problemas.Campo;

@ControllerAdvice
public class StatusValidacoesExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(EntidadeNaoEncontradoException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(RegraException ex, WebRequest req) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		return handleExceptionInternal(ex, getProblem(ex, status), new HttpHeaders(), status, req);

	}

	@ExceptionHandler(RegraException.class)
	public ResponseEntity<Object> handleNegocio(RegraException ex, WebRequest req) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return handleExceptionInternal(ex, getProblem(ex, status), new HttpHeaders(), status, req);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Campo> campos = new ArrayList<Problemas.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String campo = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			campos.add(new Problemas.Campo(campo, mensagem));
		}

		Problemas problemas = new Problemas();

		problemas.setStatus(status.value());
		problemas.setTitulo("Um ou mais campos estão inválidos por gentileza Faça o preenchimento correto e tente novamente");
		problemas.setDataHora(OffsetDateTime.now());
		problemas.setCampos(campos);		

		return super.handleExceptionInternal(ex, problemas, headers, status, request);
	}

	private Problemas getProblem(RegraException ex, HttpStatus httpStatus) {
		Problemas problemas = new Problemas();

		problemas.setStatus(httpStatus.value());
		problemas.setTitulo(ex.getMessage());
		problemas.setDataHora(OffsetDateTime.now());

		return problemas;

	}

}
