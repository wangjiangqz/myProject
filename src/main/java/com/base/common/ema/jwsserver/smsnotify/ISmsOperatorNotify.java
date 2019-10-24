package com.base.common.ema.jwsserver.smsnotify;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/**
 * �������� WEBSERVICE���Žӿ�<br>
 * ��������ͨ6.0���͹��������ж��ţ�״̬����
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-17 ����04:34:15
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface ISmsOperatorNotify {


    /**
     * ��������ͨ6.0���͹��������ж���
     * 
     * @param account
     *            �˻�
     * @param moMessageRes
     *            ������Ϣ
     */
    public boolean getSms(String account, MoMessageResDetail[] moMessageArray);

    /**
     * ��������ͨ6.0���͹�����״̬����
     * 
     * @param account
     *            �˻�
     * @param reportMessageRes
     *            ״̬����
     * @author: 8522
     * @date: 2012-2-17 ����04:38:05
     */
    public boolean  getReport(String account, ReportMessageResDetail[] reportMessageArray);


}