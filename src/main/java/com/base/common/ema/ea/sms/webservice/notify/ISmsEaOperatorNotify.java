package com.base.common.ema.ea.sms.webservice.notify;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/**
 * �������� WEBSERVICE���Žӿ�<br>
 * ��������ͨ6.0���͹��������ж���
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-17 ����04:34:15
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ISmsEaOperatorNotify {


    /**
     * ��������ͨ6.0���͹��������ж���
     * 
     */
    public String getSms(int ea ,String account,String smsMoMessage);
}