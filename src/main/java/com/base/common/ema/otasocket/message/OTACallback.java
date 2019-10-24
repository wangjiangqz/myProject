package com.base.common.ema.otasocket.message;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.slidewindow.SMSCallback;

/**
 * �������������ύ״̬��״̬���棬���л�д�࣬<br>
 * �����ύ״̬��״̬���棬���ж���ص���Ӧ�ķ������ͻ���ֻ��Ҫ�ڷ����д����ȡ����Ϣ����
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-4-13 ����04:03:43
 */
public class OTACallback implements SMSCallback {

    /**
     * ��������������ж��ţ����ŵĸ����ֶζ�����DeliverMsg
     */
    public boolean onDeliverSMS(IDeliverMsg arg0) {
        DeliverMsg msg = (DeliverMsg) arg0;
        if (msg != null) {
            System.out.println("�յ�deliverMsg:" + msg);
            System.out.println("srcTermID:" + msg.getDeliverSMS().getSrcTermID());
            System.out.println("destTermID:" + msg.getDeliverSMS().getDestTermID());
            System.out.println("msgContent:" + msg.getDeliverSMS().getMsgContent());
            System.out.println("recvTime:" + msg.getDeliverSMS().getRecvTime());
        }
        return true;
    }

    /**
     * ������������·����ŵ�״̬���棬��ͨ��msgid���������ж��� result : 0�������ɹ� 1�����ȴ����� 2����ʧ��
     */
    public boolean onMTReportSMS(IReportMsg arg0) {
        ReportMsg msg = (ReportMsg) arg0;
        if (msg != null) {
            System.out.println("�յ�report: " + msg);
            System.out.println("msgid: " + msg.getReport().getId());
            System.out.println("stat: " + msg.getReport().getStat());
            System.out.println("destTermID: " + msg.getDestTermID());
            System.out.println("srcTermID: " + msg.getSrcTermID());
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
            System.out.println("�յ�submit resp:" + msg.getSubmitSMSResp());
            System.out.println("DestTermID:" + msg.getDestTermID());
            System.out.println("MsgId:" + msg.getSubmitSMSResp().getMsgId());
            System.out.println("result:" + msg.getSubmitSMSResp().getResult());
            System.out.println("ResultDesc:" + msg.getSubmitSMSResp().getResultDesc());
        }
        return true;
    }
}
