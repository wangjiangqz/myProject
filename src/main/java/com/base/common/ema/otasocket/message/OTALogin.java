package com.base.common.ema.otasocket.message;

import com.chinatricom.slidewindow.ISMSHead;
import com.chinatricom.slidewindow.ISMSPackage;

/**
 * ��������CTCPP���Ӱ��ṹ
 * 
 * @version: 1.0
 * @author: 8522
 * @date: 2012-2-20 ����04:45:09
 */
public class OTALogin implements ISMSPackage {

    public com.chinatricom.message.otaserver.OTALogin mStub = new com.chinatricom.message.otaserver.OTALogin();

    public byte[] getBytes() {
        return mStub.pack();
    }

    public boolean parsePackage(byte[] bodyPack) {
        if (bodyPack != null)
            return mStub.unPack(bodyPack) ==0;

        return true;
    }

    public void setHead(ISMSHead head) {
        OTAHead _head = (OTAHead) head;
        mStub.setHead(_head.mHead);
    }

}
