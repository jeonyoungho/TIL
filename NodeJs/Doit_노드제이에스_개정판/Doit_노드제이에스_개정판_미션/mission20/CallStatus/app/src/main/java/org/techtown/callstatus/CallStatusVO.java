package org.techtown.callstatus;

/**
 * CallStatus VO
 *
 */

public class CallStatusVO {

    int status;
    String mobile;

    public CallStatusVO(int status, String mobile) {
        this.status = status;
        this.mobile = mobile;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "CallStatusVO{" +
                "status=" + status +
                ", mobile='" + mobile + '\'' +
                '}';
    }

}
