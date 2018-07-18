package com.xwj.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class ProtostuffTest {

	/** 产生一个随机的字符串 */
	public static String randomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int strlen = str.length();
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int num = random.nextInt(strlen);
			buf.append(str.charAt(num));
		}
		return buf.toString();
	}

	private static ResourceObj getObj(String name, String path, int contentSize) {
		ResourceObj obj = new ResourceObj(name, path, 14);
		obj.setContent(randomString(contentSize));
		return obj;
	}

	private static long speedTest(int contentSize, int size) {
		ResourceObj obj = getObj("lb.conf", "/home/admin/conf/lb", 22);
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++) {
			byte[] bytes = ProtoStuffUtil.serializer(obj);
			ProtoStuffUtil.deserializer(bytes, ResourceObj.class);
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	private static long speedTestOrg(int contentSize, int times) throws IOException, ClassNotFoundException {
		ResourceObj obj = getObj("lb.conf", "/home/admin/conf/lb", 22);
		long start = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] bytes = baos.toByteArray();

			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			ois.readObject();
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println(speedTestOrg(1000000000, 1));
		System.out.println(speedTest(1000000000, 1));
	}

}
