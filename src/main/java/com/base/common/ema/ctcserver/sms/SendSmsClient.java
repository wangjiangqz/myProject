package com.base.common.ema.ctcserver.sms;

import com.chinatricom.smsclient.ctcpp.CtcppChannel;
import com.chinatricom.smsclient.message.SubmitMsg;

/**
 * ����CTC-SMS API�ͻ��˽ӿ�
 * 
 * @author 8521
 * @version 1.0
 */
public class SendSmsClient {
    private static SendSmsClient instance = null;

    public static CtcppChannel _chan = null;

    private SendSmsClient() {
    }

    public static SendSmsClient getInstance() {
        if (instance == null)
            instance = new SendSmsClient();
        return instance;
    }

    /**
     * ������������ʼ��ͨ��
     * 
     * @param host
     *            �����ip
     * @param port
     *            ����˶˿�
     * @param user
     *            �˻�
     * @param passwd
     *            ����
     * @author: 8521
     * @date: 2012-4-13 ����03:57:47
     */
    public int init(String host, int port, String user, String passwd) {
        if (_chan != null && _chan.isConnected()) {
            return 0;
        }
        // ��ز�������
        CqBSMSCallback callback = new CqBSMSCallback();
        int wndSize = 16;
        int timeout = 60 * 1000; // ���ӳ�ʱʱ��
        int trys = 3;
        int speed = 0;
        // ����ͨ��
        _chan = new CtcppChannel(host, port, user, passwd, 1, wndSize, timeout, trys, speed, callback, 0, 0, 1, 1);
        return _chan.start();
    }

    /**
     * ��������������ͨ��ͨ��
     * 
     * @param host
     *            �����ip
     * @param port
     *            ����˶˿�
     * @param user
     *            �˻�
     * @param passwd
     *            ����
     * @author: 8521
     * @date: 2012-4-13 ����03:57:47
     */
    public int reset(String host, int port, String user, String passwd) {
        if (_chan != null && _chan.isConnected()) {
            _chan.stop();
        }
        return init(host, port, user, passwd);
    }

    /**
     * �·�����,���̰߳�ȫ
     * 
     * @param phoneNumber
     *            �ֻ���
     * @param smsContext
     *            �·�����
     * @param smsType
     *            ��������
     */
    public void sendSMS(String phoneNumber, String smsContext, String smsType) {
        try {
            SubmitMsg _msg = new SubmitMsg();
            _msg.setTo(phoneNumber);
            _msg.setMsg(smsContext);
            _msg.setFrom("12345");
            _msg.setServiceId("");
            int i = 0;
            while (!_chan.isConnected()) {
                try {
                    i++;
                    System.out.println("�ȴ�ͨ������......");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    if (i > 20) {
                        System.out.println("ͨ������ʧ��,�˳�");
                        System.exit(0);
                    }
                }

            }
            _chan.submit(_msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
