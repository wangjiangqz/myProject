package com.base.common.ema.ea.sms.socket.message;

import com.chinatricom.message.ctcserverea.CTCEaSubmitSMSResp;
import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISubmitRespPackage;

/**
 * ��������CTCPP�����·���Ӧ���ṹ
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 ����04:49:43
 */
public class EaSubmitResp implements ISubmitRespPackage {

    public CTCEaSubmitSMSResp mStub = new CTCEaSubmitSMSResp();


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

    public String getMsgId() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getSuccPhones() {
        // TODO Auto-generated method stub
        return null;
    }

    public int getResult() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getResultDesc() {
        // TODO Auto-generated method stub
        return null;
    }


}
