package com.xiaocui.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class ByteUtils {
	private static Logger logger = Logger.getLogger(ByteUtils.class);
	
	public static byte[] getByteArrayFromInputStream(InputStream inputStream)
	{
		byte[] result = null;
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	    try
	    {
	    	byte[] buf = new byte[4096];  
		    int num = 0;  
		    while ((num = inputStream.read(buf,0,4096)) > 0) {  
		        swapStream.write(buf, 0, num);  
		    }  
		    result = swapStream.toByteArray();
	    }
	    catch(Exception ex)
	    {
	    	logger.error(ex);
	    }
	    finally {
	    	try {if(swapStream != null)swapStream.close();} catch (IOException e) {}
		}
	    
	    return result;
	}
	
	public static byte[] toBytes(short s) {
		byte[] temp = new byte[2];
		temp[1] = (byte) (s >> 8);
		temp[0] = (byte) (s >> 0);
		
		return temp;
	}
	
	public static byte[] toBytes(int x) {
		byte[] temp = new byte[4];
		
		temp[3] = (byte) (x >> 24);
		temp[2] = (byte) (x >> 16);
		temp[1] = (byte) (x >> 8);
		temp[0] = (byte) (x >> 0);
		
		return temp;
	}
	
	public static byte[] toBytes(long x) {
		byte[] temp = new byte[8];
		
		temp[7] = (byte) (x >> 56);
		temp[6] = (byte) (x >> 48);
		temp[5] = (byte) (x >> 40);
		temp[4] = (byte) (x >> 32);
		temp[3] = (byte) (x >> 24);
		temp[2] = (byte) (x >> 16);
		temp[1] = (byte) (x >> 8);
		temp[0] = (byte) (x >> 0);
		
		return temp;
	}
	
	public static short getShort(byte[] bb, int index) {
		return (short) (((bb[index + 1] << 8) | bb[index + 0] & 0xff));
	}

	public static int getInt(byte[] bb, int index) {
		return (int) ((((bb[index + 3] & 0xff) << 24)
				| ((bb[index + 2] & 0xff) << 16)
				| ((bb[index + 1] & 0xff) << 8) | ((bb[index + 0] & 0xff) << 0)));
	}
	
	public static long getLong(byte[] bb, int index) {
		return ((((long) bb[index + 7] & 0xff) << 56)
				| (((long) bb[index + 6] & 0xff) << 48)
				| (((long) bb[index + 5] & 0xff) << 40)
				| (((long) bb[index + 4] & 0xff) << 32)
				| (((long) bb[index + 3] & 0xff) << 24)
				| (((long) bb[index + 2] & 0xff) << 16)
				| (((long) bb[index + 1] & 0xff) << 8) | (((long) bb[index + 0] & 0xff) << 0));
	}
}
