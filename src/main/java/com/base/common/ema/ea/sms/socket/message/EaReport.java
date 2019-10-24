package com.base.common.ema.ea.sms.socket.message;

import com.chinatricom.message.IDeliverMsg;
import com.chinatricom.message.IReportMsg;
import com.chinatricom.message.ctcserverea.CTCEaReport;
import com.chinatricom.slidewindow.IDeliverPackage;
import com.chinatricom.slidewindow.ISMSHead;

/**
 * ��������CTCPP״̬������ṹ
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 ����04:47:34
 */
public class EaReport implements IDeliverPackage {

    public CTCEaReport mStub = new CTCEaReport();

    public IDeliverMsg getDeliverMsg() {
        return null;
    }

    public IReportMsg getReportMsg() {
        ReportMsg msg = null;
        {
            msg = new ReportMsg();
            msg.setSmsReportMessage(mStub.getSmsReportMessage());
        }
        return msg;
    }

    public boolean isReport() {
        return true;
    }

    public byte[] getBytes() {
        return mStub.pack().getBytes();
    }

    public boolean parsePackage(byte[] bodyPack) {
        if (bodyPack != null)
            return mStub.unPack(new String(bodyPack));

        return true;
    }

    public void setHead(ISMSHead head) {
        EaHead _head = (EaHead) head;
        mStub.setHead(_head.mHead);
    }

}
