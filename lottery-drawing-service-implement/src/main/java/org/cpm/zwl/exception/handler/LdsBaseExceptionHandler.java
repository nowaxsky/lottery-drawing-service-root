package org.cpm.zwl.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.cpm.zwl.commons.log.factories.ZwlLogFactory;
import org.cpm.zwl.commons.response.JsonResponse;
import org.cpm.zwl.commons.utils.JsonResponseUtil;
import org.cpm.zwl.exception.LdsBaseException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LdsBaseExceptionHandler {

	private final Logger logger = ZwlLogFactory.getLogger(LdsBaseExceptionHandler.class);
	
	@ExceptionHandler(value = LdsBaseException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public JsonResponse<String> handlerError(HttpServletRequest req, LdsBaseException e) {
		logger.error(e.getErrorCode(),e);
		JsonResponse<String> response = JsonResponseUtil.getFail(e.getErrorCode(), e.getMessage());
        return response;
    }
}
