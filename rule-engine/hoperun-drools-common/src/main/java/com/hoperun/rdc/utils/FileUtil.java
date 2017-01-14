package com.hoperun.rdc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: FileUtil
 * @Description:
 * @author YinChang-bao
 * @date Nov 9, 2015 1:15:33 PM
 *
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	private FileUtil() {
	}

	public static List<File> iterateFolder(String path, List<String> suffixes) {
		logger.info("Iterate From Folder: {}",path);
		int fileNum = 0, folderNum = 0;
		File file = new File(path);
		if (file.exists()) {
			LinkedList<File> list = new LinkedList<File>();

			List<File> targetFiles = new ArrayList<File>();

			File[] files = file.listFiles();
			for (File file2 : files) {
				if (file2.isDirectory()) {
					logger.debug("folder:" + file2.getAbsolutePath());
					list.add(file2);
					fileNum++;
				} else if (suffixes != null && suffixes.contains(suffix(file2))) {
					logger.debug("file:" + file2.getAbsolutePath());
					targetFiles.add(file2);
					folderNum++;
				} else if (suffixes == null) {
					logger.debug("file:" + file2.getAbsolutePath());
					targetFiles.add(file2);
					folderNum++;
				}
			}
			File temp_file;
			while (!list.isEmpty()) {
				temp_file = list.removeFirst();
				files = temp_file.listFiles();
				for (File file2 : files) {
					if (file2.isDirectory()) {
						logger.debug("folder:" + file2.getAbsolutePath());
						list.add(file2);
						fileNum++;
					} else if (suffixes != null && suffixes.contains(suffix(file2))) {
						logger.debug("file:" + file2.getAbsolutePath());
						targetFiles.add(file2);
						folderNum++;
					} else if (suffixes == null) {
						logger.debug("file:" + file2.getAbsolutePath());
						targetFiles.add(file2);
						folderNum++;
					}
				}
			}
			return targetFiles;
		} else {
			logger.debug("folder not exist!");
		}
		logger.debug("folder contains sub-folder :" + folderNum + ", and file:" + fileNum);
		return null;
	}

	public static String suffix(File file) {
		if (file == null || file.isDirectory())
			return null;

		String name = file.getName();
		if (!StringUtil.isEmpty(name) && name.lastIndexOf(".") > -1)
			return name.substring(name.lastIndexOf("."));
		else
			return name;
	}

	public static File checkOrmkdirs(String folder) {
		File file = new File(folder);
		if (!file.exists())
			file.mkdirs();
		return file;

	}

	public static String getMd5Value(File file) throws FileNotFoundException {
		String value = null;
		FileInputStream in = new FileInputStream(file);
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
		return value;
	}

}
