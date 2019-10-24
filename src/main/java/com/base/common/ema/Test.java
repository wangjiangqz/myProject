package com.base.common.ema;

import java.net.URL;

import com.base.common.ema.common.MD5;
import com.base.common.ema.jwsserver.sms.ISmsOperator;
import com.base.common.ema.jwsserver.sms.MtMessage;
import com.base.common.ema.jwsserver.sms.MtMessageRes;
import com.base.common.ema.jwsserver.sms.SmsOperatorImpServiceLocator;


public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		   SmsOperatorImpServiceLocator locator = new SmsOperatorImpServiceLocator();
			try {
				//Web Service������ַ�����÷�������DNS������Ϊ192.168.2.2�������轫sms.cnca�滻Ϊ192.168.5.34
				ISmsOperator ioperator = locator.getSmsOperatorImpPort(new URL("http://192.168.5.34:8080/ema/webService/smsOper?wsdl"));  						
				// ���Ͷ���
				MtMessage msg = new MtMessage();
				msg.setContent("���Ų������ݣ�"); //���Ͷ�������
				String[] phoneNumber = new String[1];
				phoneNumber[0] = "15105194868";
				msg.setPhoneNumber(phoneNumber);
				MtMessageRes mtMessageRes = ioperator.sendSms("ccc_sp", MD5.MD5Encode("ccc_sp"), "01", msg);  //����Web Service�û��������룬01Ϊҵ�����ͺ���
				if ("r:000".equals(mtMessageRes.getSubStat())) {
					System.out.println("�ɹ�");
				}else{
					System.out.println("ʧ��");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
