package com.base.common.ema.jwsserver.smsnotify;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * ������������ӿڶ��Ų���
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-16 ����10:47:44
 */
@XmlRootElement(name = "com.ctc.ema.jwsserver.notify.sms.MoMessageResDetail")
public class MoMessageResDetail {

    /**
     * �ֻ�����
     */
    private String phoneNumber;

    /**
     * ��������
     */
    private String content;

    /**
     *��չ��
     */
    private String subCode;

    /**
     *����ʱ��
     */
    private Date revTime;

    /**
     * ����
     */
    private String demo;

    /**
     * @return phoneNumber : return the property phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber : set the property phoneNumber.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return content : return the property content.
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content : set the property content.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return subCode : return the property subCode.
     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * @param subCode : set the property subCode.
     */
    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    /**
     * @return revTime : return the property revTime.
     */
    public Date getRevTime() {
        return revTime;
    }

    /**
     * @param revTime : set the property revTime.
     */
    public void setRevTime(Date revTime) {
        this.revTime = revTime;
    }

    /**
     * @return demo : return the property demo.
     */
    public String getDemo() {
        return demo;
    }

    /**
     * @param demo : set the property demo.
     */
    public void setDemo(String demo) {
        this.demo = demo;
    }

   
}
