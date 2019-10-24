package com.base.common.ema.ea.sms.socket.message;

public class EaConstants {

    //
    public static final int CMT_ALREADYLOGIN = 1;

    public static final int CMT_AuthErr = 2; // �û�����Ч

    /**
     * ��ȡ��¼���˵��
     */
    public static String getLoginDesc(int status) {
        String str;
        switch (status) {
        case EaConstants.CMT_ALREADYLOGIN:
            str = "�ظ���¼��" + status + "��";
            break;
        case EaConstants.CMT_AuthErr:
            str = "��֤ʧ�ܡ�" + status + "��";
            break;
        default:
            str = "δ֪����" + status + "��";
            break;
        }
        return str;
    }

    /************** submitResp****************************8534 start *************/
    public static String getSubmitDesc(int status) {
        String str;
        switch (status) {
        case 0:
            str = "�ύ�ɹ�";
            break;
        case -1:
            str = "�����û���Ч";
            break;
        case -2:
            str = "�ύʧ��";
            break;
        case 98:
            str = "ϵͳ��æ";
            break;
        default:
            str = "δ֪����";
            break;
        }
        return str;
    }

    /************** submitResp****************************8534 end *************/

    /************** reportResp****************************8534 start *************/
    public static String getReportDesc(int status) {
        String str;
        switch (status) {
        case 0:
            str = "���ͳɹ�";
            break;
        case 98:
            str = "ϵͳ��æ";
            break;
        default:
            str = "����ʧ��";
            break;
        }
        return str;
    }
    /************** reportResp****************************8534 end *************/

}
