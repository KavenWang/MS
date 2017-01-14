/*****************************************************************************
 *
 *                      HOPERUN PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to HopeRun
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from HopeRun.
 *
 *            Copyright (c) 2016 by HopeRun.  All rights reserved.
 *
 *****************************************************************************/
package com.hoperun.rdc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * ClassName: Serializer
 * Function: TODO ADD FUNCTION.
 * date: Dec 13, 2016 10:39:38 AM .
 * 
 * @author yin_changbao 
 * @version
 */
public class Serializer {

	private static final Logger logger = LoggerFactory.getLogger(Serializer.class);

	public static <T extends java.io.Serializable> byte[] serialize(T paramT) {
		if (paramT != null) {

			ByteArrayOutputStream bos = null;
			ObjectOutputStream oo = null;
			try {
				bos = new ByteArrayOutputStream();
				oo = new ObjectOutputStream(bos);
				oo.writeObject(paramT);
				return bos.toByteArray();

			} catch (Exception e) {
				logger.error("AccSerializer error ", e);
			} finally {
				if (bos != null)
					try {
						bos.close();
					} catch (IOException e) {
				}
				if (oo != null)
					try {
						oo.close();
					} catch (IOException e) {
				}
			}
		}
		return null;
	}
	
	public static <T extends java.io.Serializable> T deserialize(byte[] inputByte,ClassLoader classLoader) {
		if (inputByte != null) {

			ByteArrayInputStream bin = null;
			ObjectInputStream in = null;
			try {
				bin = new ByteArrayInputStream(inputByte);
				in = new CustomObjectInputStream(bin,classLoader);
				return (T)in.readObject();
			} catch (Exception e) {
				logger.error("AccSerializer error ", e);
			} finally {
				if (bin != null)
					try {
						bin.close();
					} catch (IOException e) {
				}
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
				}
			}
		}
		return null;
	}
	
}
