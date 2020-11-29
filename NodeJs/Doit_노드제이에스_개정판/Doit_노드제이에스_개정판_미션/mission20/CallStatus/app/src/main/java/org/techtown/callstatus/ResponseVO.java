package org.techtown.callstatus;

/**
 * Response VO
 */

public class ResponseVO {

    int code;
    String message;
    CallStatusVO status;

    public ResponseVO(int code, String message, CallStatusVO status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CallStatusVO getStatus() {
        return status;
    }

    public void setStatus(CallStatusVO status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

}
