package com.base.common.ema.ea.sms.socket;

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
        String host = "127.0.0.1";
        int port= 28080;
        String user  ="zh";
        String pass = "zh.com";
        int ea = 1;
        SendSmsClient.getInstance().init(host, port, user, pass,ea);
        // ����
        for (int i = 0; i < 1; i++) {
            SendSmsClient.getInstance().sendSMS("15000792799,15000792798,13282892222", "���Ų���", "02", 1234, 9);
        }

    }
}
