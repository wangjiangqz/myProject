package com.base.common.ema.ctcserver.sms;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.slidewindow.SMSCallback;
import com.chinatricom.smsclient.message.DeliverMsg;
import com.chinatricom.smsclient.message.ReportMsg;
import com.chinatricom.smsclient.message.SubmitMsg;

/**
 * �������������ύ״̬��״̬���棬���л�д�࣬<br>
 * �����ύ״̬��״̬���棬���ж���ص���Ӧ�ķ������ͻ���ֻ��Ҫ�ڷ����д����ȡ����Ϣ����
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-4-13 ����04:03:43
 */
public class CqBSMSCallback implements SMSCallback {

    /**
     * ��������������ж��ţ����ŵĸ����ֶζ�����DeliverMsg
     */
    public boolean onDeliverSMS(IDeliverMsg arg0) {
        DeliverMsg msg = (DeliverMsg) arg0;
        if (msg != null) {
            System.out.println("�յ�deliverMsg:phone=" + msg.getFrom() + ",����=" + msg.getMsg() + ",to=" + msg.getTo());
            // ���Խ����ж�����⣬������еȣ������ȷ�����У�֮���첽������Щ���ж��ţ���Ϊ��������ʱ�䣬Ӱ���շ����ܡ�
        }
        return true;
    }

    /**
     * ������������·����ŵ�״̬���棬��ͨ��msgid���������ж��� result : 0�������ɹ� 1�����ȴ����� 2����ʧ��
     */
    public boolean onMTReportSMS(IReportMsg arg0) {
        ReportMsg msg = (ReportMsg) arg0;
        if (msg != null) {
            System.out.println("�յ�repot: result==============================" + msg.getResult()
                    + "");
            // ���Խ�״̬������⣬������еȣ����ǽ����ȷ�����У�֮���첽������Щ״̬���棬��Ϊ��������ʱ�䣬Ӱ���շ����ܡ�
        }
        return true;
    }

    /**
     * ��������������ж��ŵ�response��SubmitMsg��������result��msgId,�����msg����_chan.submit(_msg)
     * �����_msg�� result = 0 ��ʾ�ɹ��� ������Ϊʧ�ܡ�
     */
    public boolean onSubmitedSMS(ISubmitMsg arg0) {
        SubmitMsg msg = (SubmitMsg) arg0;
        if (msg != null) {
            System.out.println("�յ�submit response==============================:result" + "="
                    + msg.getResult() + ", msgid=" + msg.getMsgId());
            // ���Խ��ύ״̬��⣬������еȣ����ǽ����ȷ�����У�֮���첽������Щ�ύ״̬��Ϣ����Ϊ��������ʱ�䣬Ӱ���շ����ܡ�
        }
        return true;
    }
}
