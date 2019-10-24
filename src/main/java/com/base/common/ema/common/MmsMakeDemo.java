package com.base.common.ema.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.chinatricom.mmsfactory.Frame;
import com.chinatricom.mmsfactory.MmsFactory;

public class MmsMakeDemo {
	private static final Logger log = Logger.getLogger(MmsMakeDemo.class);

	/**
	 * ��������demo
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		makeMMS();
	}

	/**
	 * ��������demo
	 */

	public static void makeMMS() {
		String foldPath = "D:\\mmstest\\";
		// smil �ļ���
		String smil = UUID.randomUUID() + ".smil";
		// �زĴ��·�������籾�� �У�D:\mmstest\��Ŀ¼���Ѿ��� a.jpg,a.txt, b.jpg,b.txt,�����ļ�
		// ��Ҫ�� a.jpg,a.txt ���ɵ�һ֡��b.jpg,b.txt ���ɵڶ�֡
		// ������ɺ�,�ڸ�Ŀ¼�� ��������һ����Ӧ��smil�ļ���
		// [ smil�ļ�, a.jpg,a.txt,b.jpg,b.txt]�⼸���ļ���ͬ���һ�����Ų�Ʒ

		List<Frame> frameList = new ArrayList<Frame>();
		// ��һ֡
		Frame frame1 = new Frame();
		// ��֡ͼƬ(·����D:\mmstest\a.jpg)
		frame1.setMImage("a.jpg");
		// ��֡�ı�(·����D:\mmstest\a.txt)
		frame1.setMTxt("a.txt");
		// ��֡����ʱ��
		frame1.setTime(5000);
		// ��֡�ı�λ�ã�1 �ı�λ��ͼƬ�·�,0 �ı�λ��ͼƬ�Ϸ���
		frame1.setTxtPosition(1);
		frameList.add(frame1);
		// �ڶ�֡
		Frame frame2 = new Frame();
		frame2.setMImage("b.jpg");
		frame2.setMTxt("b.txt");
		frame2.setTime(500);
		frame2.setTxtPosition(0);
		frameList.add(frame2);
		// ���ɲ�Ʒ�ļ���smil�ļ���
		MmsFactory mms = new MmsFactory();
		mms.setFrameList(frameList);
		mms.setSmilOutPutPath(foldPath + smil);
		boolean resflg = mms.generateSmil();
		if (resflg)
			log.info("���������ɹ���smil ·����" + foldPath + smil);

	}
}
