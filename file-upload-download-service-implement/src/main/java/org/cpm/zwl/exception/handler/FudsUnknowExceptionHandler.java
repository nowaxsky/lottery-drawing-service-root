package org.cpm.zwl.exception.handler;

import javax.servlet.http.HttpServletRequest;
import org.cpm.zwl.commons.constrants.ZwlBaseErrorCode;
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
public class FudsUnknowExceptionHandler {

  private final Logger logger = ZwlLogFactory.getLogger(FudsUnknowExceptionHandler.class);

  @SuppressWarnings("unchecked")
  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public JsonResponse<String> handlerError(HttpServletRequest req, Exception e) {
    logger.error(ZwlBaseErrorCode.UNKNOWN_ERROR.getErrorCode(), e);
    JsonResponse<String> response =
        JsonResponseUtil.getFail(ZwlBaseErrorCode.UNKNOWN_ERROR.getErrorCode(), e.getMessage());
    return response;
  }

}
