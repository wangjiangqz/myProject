package com.base.common.ema.otasocket;

import java.util.Random;

import com.base.common.ema.otasocket.message.OTACallback;
import com.base.common.ema.otasocket.message.OTAppChannel;
import com.base.common.ema.otasocket.message.SubmitMsg;

/**
 * ����CTC-SMS API�ͻ��˽ӿ�
 * 
 * @author 8521
 * @version 1.0
 */
public class SendSmsClient {
    private static SendSmsClient instance = null;

    private static OTAppChannel _chan = null;

    public static SendSmsClient getInstance(String host, int port, String user, String passwd) {
        if (instance == null)
            instance = new SendSmsClient(host, port, user, passwd);
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
     * @param ea
     *            �������� 0�����ܣ�1des3����
     * @param keyFilePaht
     *            ��Կ�ļ�·������
     * @author: 8521
     * @date: 2012-4-13 ����03:57:47
     */
    private SendSmsClient(String host, int port, String user, String passwd) {
        if (_chan != null && _chan.isConnected()) {
            return;
        }
        // ��ز�������
        OTACallback callback = new OTACallback();
        int wndSize = 16;
        int timeout = 60 * 1000; // ���ӳ�ʱʱ��
        int trys = 3;
        int speed = 0;
        int maxSinglemsg = 70;
        int longSMSmaxSinglemsg = 67;
        // ����ͨ��
        _chan = new OTAppChannel(host, port, user, passwd, wndSize, timeout, trys, speed, maxSinglemsg,
                longSMSmaxSinglemsg, callback);
        _chan.start();
    }

    public boolean isActive() {
        int i = 0;
        while (!_chan.isActive() || !_chan.isConnected()) {
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
        return true;
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
     * @param subCode
     *            ����
     * @param priority
     *            ���ȼ�
     */
    public void sendSMS(String phoneNumber, String smsContext, String serviceId, String srcTermID, int priority) {
        try {
            SubmitMsg _msg = new SubmitMsg();
            _msg.setDestTermID(phoneNumber);
            _msg.setDestTermIDCount(phoneNumber.split(",").length);
            _msg.setServiceId(serviceId);
            _msg.setPriority(priority);
            _msg.setMsgContent(smsContext);
            _msg.setSrcTermID(srcTermID);
            _msg.setNeedReport(1);
            _msg.setMsgFormat(246);
            _msg.setIdxOfLongSms(1);
            _msg.setCountOfLongSms(1);
            _chan.Submit(_msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queryreport(String inmsg) {
        _chan.queryReport(inmsg);
    }

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
        int port = 28850;
        String user = "ota";
        String pass = "ota.com";
        SendSmsClient client = SendSmsClient.getInstance(host, port, user, pass);
        if (client.isActive()) {
            // ����
            for (int i = 0; i < 1; i++) {
                client.sendSMS("15000792799", "��", "02", "1234", 5);
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
//            try {
//                Thread.sleep(200);
//                client.queryreport("��ѯͳ��");
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
        }

    }
}
