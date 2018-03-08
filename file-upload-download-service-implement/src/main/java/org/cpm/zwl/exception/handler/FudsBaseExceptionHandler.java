package org.cpm.zwl.exception.handler;

import javax.servlet.http.HttpServletRequest;
import org.cpm.zwl.commons.log.factories.ZwlLogFactory;
import org.cpm.zwl.commons.response.JsonResponse;
import org.cpm.zwl.commons.utils.JsonResponseUtil;
import org.cpm.zwl.exception.FudsBaseException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FudsBaseExceptionHandler {

  private final Logger logger = ZwlLogFactory.getLogger(FudsBaseExceptionHandler.class);

  @SuppressWarnings("unchecked")
  @ExceptionHandler(value = FudsBaseException.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public JsonResponse<String> handlerError(HttpServletRequest req, FudsBaseException e) {
    logger.error(e.getErrorCode(), e);
    JsonResponse<String> response = JsonResponseUtil.getFail(e.getErrorCode(), e.getMessage());
    return response;
  }
}
