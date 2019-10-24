package com.base.common.ema.ea.sms.socket;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.ISubmitMsg;
import com.chinatricom.slidewindow.SMSCallback;
import com.base.common.ema.ea.encry.des3.Encry_DES3CLt;
import com.base.common.ema.ea.sms.socket.message.DeliverMsg;
import com.base.common.ema.ea.sms.socket.message.ReportMsg;
import com.base.common.ema.ea.sms.socket.message.SubmitMsg;

/**
 * �������������ύ״̬��״̬���棬���л�д�࣬<br>
 * �����ύ״̬��״̬���棬���ж���ص���Ӧ�ķ������ͻ���ֻ��Ҫ�ڷ����д����ȡ����Ϣ����
 * 
 * @version: 1.0
 * @author: 8521
 * @date: 2012-4-13 ����04:03:43
 */
public class EAMSCallback implements SMSCallback {
    private byte[] key = null;

    /**
     * ��������������ж��ţ����ŵĸ����ֶζ�����DeliverMsg
     */
    public boolean onDeliverSMS(IDeliverMsg arg0) {
        DeliverMsg msg = (DeliverMsg) arg0;
        if (msg != null) {
            if (key != null) {
                System.out.println("�յ�deliverMsg:"
                        + Encry_DES3CLt.getInstance().getDesString(msg.getSmsMoMessage(), key));
            } else
                System.out.println("�յ�deliverMsg:" + msg.getSmsMoMessage());
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
            if (key != null) {
                System.out.println("�յ�report: "
                        + Encry_DES3CLt.getInstance().getDesString(msg.getSmsReportMessage(), key));
            } else
                System.out.println("�յ�report: " + msg.getSmsReportMessage());
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
            if (key != null) {
                System.out.println("�յ�submit resp:"
                        + Encry_DES3CLt.getInstance().getDesString(msg.getSubmitSMSResp().getSmsMtMessageRes(),
                                key));
            } else
                System.out.println("�յ�submit resp:" + msg.getSubmitSMSResp().getSmsMtMessageRes());
            // ���Խ��ύ״̬��⣬������еȣ����ǽ����ȷ�����У�֮���첽������Щ�ύ״̬��Ϣ����Ϊ��������ʱ�䣬Ӱ���շ����ܡ�
        }
        return true;
    }

    /**
     * @return key : return the property key.
     */
    public byte[] getKey() {
        return key;
    }

    /**
     * @param key
     *            : set the property key.
     */
    public void setKey(byte[] key) {
        this.key = key;
    }
}
