package com.base.common.ema.ea.sms.socket.message;

import com.chinatricom.message.ctcserverea.CTCEaLogin;
import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISMSPackage;

/**
 * ��������CTCPP���Ӱ��ṹ
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 ����04:45:09
 */
public class EaLogin implements ISMSPackage {

    public CTCEaLogin mStub = new CTCEaLogin();

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
