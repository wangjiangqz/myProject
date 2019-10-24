package com.base.common.ema.jwsserver.smsnotify;

import javax.jws.WebService;

import org.apache.log4j.Logger;

/**
 * �������� WEBSERVICE���Žӿ�<br>
 * ��������ͨ6.0���͹��������ж��ţ�״̬����
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-17 ����04:34:15
 */
@WebService(endpointInterface = "com.ctc.ema.jwsserver.smsnotify.ISmsOperatorNotify")
public class SmsOperatorNotifyImp implements ISmsOperatorNotify {
    private static Logger log = Logger.getLogger(SmsOperatorNotifyImp.class);

    public boolean getReport(String account, ReportMessageResDetail[] reportMessageArray) {

        if (reportMessageArray != null && reportMessageArray.length > 0) {
            log.info("------------------------------------------------------------------------------");
            log.info("���յ��������͹�����״̬����:�� " + reportMessageArray.length + " ��");
            for (ReportMessageResDetail reportMessageResDetail : reportMessageArray) {
                log.info("���ű�ʶ(smsid)=" + reportMessageResDetail.getSmsId());
                log.info("�ֻ�����(phoneNumber)=" + reportMessageResDetail.getPhoneNumber());
                log.info("����״̬(stat)=" + reportMessageResDetail.getStat());
                log.info("����״̬����(statDes)=" + reportMessageResDetail.getStatDes());
            }
            log.info("------------------------------------------------------------------------------");
        }
        return true;
    }

    public boolean getSms(String account, MoMessageResDetail[] moMessageArray) {
        if (moMessageArray != null && moMessageArray.length > 0) {
            log.info("------------------------------------------------------------------------------");
            log.info("���յ��������͹��������ж���:�� " + moMessageArray.length + " ��");
            for (MoMessageResDetail moMessageResDetail : moMessageArray) {
                log.info("��������(content)=" + moMessageResDetail.getContent());
                log.info("�ֻ�����(phoneNumber)=" + moMessageResDetail.getPhoneNumber());
                log.info("����(subCode)=" + moMessageResDetail.getSubCode());
            }
            log.info("------------------------------------------------------------------------------");
        }
        return true;
    }

}