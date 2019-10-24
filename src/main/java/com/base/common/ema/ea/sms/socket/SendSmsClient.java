package com.base.common.ema.ea.sms.socket;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Encoder;

import com.base.common.ema.ea.sms.socket.message.SubmitMsg;

/**
 * ����CTC-SMS API�ͻ��˽ӿ�
 * 
 * @author 8521
 * @version 1.0
 */
public class SendSmsClient {
    private static SendSmsClient instance = null;

    public static EaCtcppChannel _chan = null;

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
     * @param ea
     *            �������� 0�����ܣ�1des3����
     * @param keyFilePaht
     *            ��Կ�ļ�·������
     * @author: 8521
     * @date: 2012-4-13 ����03:57:47
     */
    public int init(String host, int port, String user, String passwd, int ea) {
        if (_chan != null && _chan.isConnected()) {
            return 0;
        }
        // ��ز�������
        EAMSCallback callback = new EAMSCallback();
        int wndSize = 16;
        int timeout = 60 * 1000; // ���ӳ�ʱʱ��
        int trys = 3;
        int speed = 0;
        // ����ͨ��
        _chan = new EaCtcppChannel(host, port, user, passwd, ea, wndSize, timeout, trys, speed, callback);
        return _chan.start();
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
    public void sendSMS(String phoneNumber, String smsContext, String smsType, int subCode, int priority) {
        try {
            SubmitMsg _msg = new SubmitMsg();
            BASE64Encoder baseEncoder = new BASE64Encoder();
            Document resDocument = DocumentHelper.createDocument();
            Element rootElement = resDocument.addElement("body");
            rootElement.addElement("phoneNumber").setText(phoneNumber);
            rootElement.addElement("smsType").setText(smsType);
            rootElement.addElement("priority").setText("" + priority);
            rootElement.addElement("smsId").setText("");
            rootElement.addElement("content").setText(baseEncoder.encode(smsContext.getBytes("utf-8")));
            rootElement.addElement("subCode").setText("" + subCode);
            rootElement.addElement("sendTime").setText("");
            _msg.setSmsMtMessage(rootElement.asXML());
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
            _chan.Submit(_msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
