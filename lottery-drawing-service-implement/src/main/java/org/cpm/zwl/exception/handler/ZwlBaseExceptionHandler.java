package org.cpm.zwl.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.cpm.zwl.commons.exceptions.ZwlBaseException;
import org.cpm.zwl.commons.log.factories.ZwlLogFactory;
import org.cpm.zwl.commons.response.JsonResponse;
import org.cpm.zwl.commons.utils.JsonResponseUtil;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ZwlBaseExceptionHandler {
	private final Logger logger = ZwlLogFactory.getLogger(ZwlBaseExceptionHandler.class);
	
	@ExceptionHandler(value = ZwlBaseException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse<String> handlerError(HttpServletRequest req, ZwlBaseException e) {
		logger.error(e.getErrorCode(), e);
		JsonResponse<String> response = JsonResponseUtil.getFail(e.getErrorCode(), e.getMessage());
        return response;
    }
}
