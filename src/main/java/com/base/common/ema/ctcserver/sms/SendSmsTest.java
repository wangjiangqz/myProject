package com.base.common.ema.ctcserver.sms;

public class SendSmsTest {
    /**
     * ����������main�������
     * 
     * @param args
     * @author: 8521
     * @date: 2012-6-11 ����01:56:02
     */
    public static void main(String[] args) {
        // ��ʼ��һ�ξͿ���,����ģʽ
//        SendSmsClient.getInstance().init("127.0.0.1", 28030, "zh", "zh.com");
        SendSmsClient.getInstance().init("112.25.150.108", 28010, "oa", "123.com");

        // ���� ��02��ʾ����ҵ�����ͱ�ʶ���й���Ա�ṩ
        SendSmsClient.getInstance().sendSMS("18912661598,15000792799", "�Ժ���Ų���002", "01");
    }
}
