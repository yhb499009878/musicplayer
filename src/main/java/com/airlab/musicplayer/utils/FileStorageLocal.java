package com.airlab.musicplayer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component("fileStorageLocal")
public class FileStorageLocal {
	private static final Log log = LogFactory.getLog(FileStorageLocal.class);

	public static final String DATE_FORMAT_LONG = "yyyyMMdd_HHmmss_SSS";

	private String dufaultUserDataDirecory = "data";

	private static final String FILE_ROOT_PATH = Constant.FILE_ROOT_PATH;

	// 得到文件根路径
	// 配置文件的获取？指定绝对路径
	private String fileRootPath;

	private Random random = new Random(System.nanoTime());

	@PostConstruct
	public void init() {
		this.setFileRootPath(FILE_ROOT_PATH);
	}

	public String getFileRootPath1() {
		return fileRootPath;
	}

	public void setFileRootPath(String fileRootPath) {
		this.fileRootPath = fileRootPath;
	}

	public String saveUserFile(long userId, String fileName, String ext, byte[] fileContent) throws Exception {

		fileName = StringTools.isNullOrWhiteSpace(fileName) ? "Empty" : fileName;
		ext = StringTools.isNullOrWhiteSpace(ext) ? "zip" : ext;

		StringBuilder sb = new StringBuilder();

		// 计算文件相对路径，形式为: 0~99\0~99\\userId\yyyyMM\由日期时间、内容名称、内容Id、扩展名 拼接成的文件名
		// 例如: 1\10\100915\20121012\20101216_150101_0000001-data-1.mm7
		sb.append(mkdirByUserId("", userId, new Date())).append(DateTools.formatNow(DATE_FORMAT_LONG))
				.append(getRandom()).append('-').append(fileName).append('-').append('.').append(ext);

		String fileNameWithPath = sb.toString();

		// 不能是绝对路径
		if (FileTools.isAbsolutePath(fileNameWithPath)) {
			throw new Exception("不能是绝对路径");
		}
		// 得到文件全路径
		String fullFileName = getFullFileName(fileNameWithPath);

		FileTools.checkMkdir(fullFileName);

		OutputStream os = new FileOutputStream(fullFileName);
		try {
			os.write(fileContent);
		} finally {
			try {
				os.close();
			} catch (Exception e) {
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("SaveUserFile - end, fullFileName: " + fullFileName);
		}
		// 返回相对路径，便于多个应用之间使用绝对路径不同的共享目录对同一个文件进行文件操作
		return fileNameWithPath;
	}

	public byte[] readUserFile(String filePath) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("ReadUserFile - begin, filePath: " + filePath);
		}

		// 得到文件的全路径
		filePath = getFullFileName(filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			log.warn("文件不存在，filePath： " + filePath);
			return null;
		}

		byte[] res = FileUtils.readFileToByteArray(file);

		if (log.isDebugEnabled()) {
			log.debug("ReadUserFile - end, filePath: " + filePath + ", res.Length: " + res.length);
		}

		return res;
	}

	public byte[] readUserFile(String filePath, Long position, Long length) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("ReadUserFile - begin, filePath: " + filePath + " position: " + position + " length: " + length);
		}

		// 得到文件的全路径
		filePath = getFullFileName(filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			log.warn("文件不存在，filePath： " + filePath);
			return null;
		}

		// 如果长度为空，则读到末尾
		if (length == null) {
			length = file.length() - position;
		}

		byte[] res = new byte[length.intValue()];
		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
		try {
			randomAccessFile.seek(position);
			int len = randomAccessFile.read(res);
			if (len < length) {
				byte[] tempBytes = new byte[len];
				System.arraycopy(res, 0, tempBytes, 0, len);
				res = tempBytes;
			}
		} finally {
			try {
				if (randomAccessFile != null) {
					randomAccessFile.close();
				}
			} catch (Exception e) {
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("ReadUserFile - end, filePath: " + filePath + ", res.Length: " + res.length);
		}

		return res;
	}

	public File mergeFiles(String outFile, List<String> files) throws Exception {
		FileChannel outChannel = null;
		FileChannel inChannel = null;
		File file = null;
		try {
			outFile = getFullFileName(outFile);
			outChannel = new FileOutputStream(outFile).getChannel();
			for (String f : files) {
				f = getFullFileName(f);
				if (inChannel != null) {
					inChannel.close();
				}
				inChannel = new FileInputStream(f).getChannel();
				ByteBuffer bb = ByteBuffer.allocate(1024 * 4);
				while (inChannel.read(bb) != -1) {
					bb.flip();
					outChannel.write(bb);
					bb.clear();
				}
			}
			file = new File(outFile);
		} catch (Exception e) {
			log.error("合并文件失败：", e);
			throw e;
		} finally {
			try {
				if (inChannel != null) {
					inChannel.close();
				}
			} catch (Exception e) {
			}
			try {
				if (outChannel != null) {
					outChannel.close();
				}
			} catch (Exception e) {
			}
		}
		return file;
	}

	public void removeFile(List<String> files) {
		try {
			for (String filePath : files) {
				// 得到文件的全路径
				String fullFilePath = getFullFileName(filePath);
				log.debug("fullFilePath:" + fullFilePath);
				FileTools.deleteFile(fullFilePath);
			}
		} catch (Exception e) {
			log.error("删除文件失败：", e);
		}
	}

	public void removeFile(String file) {
		List<String> files = new ArrayList<String>();
		files.add(file);
		removeFile(files);
	}

	public String md5Hex(String file) throws Exception {
		String fullFilePath = getFullFileName(file);
		InputStream is = new FileInputStream(fullFilePath);
		String md5 = DigestUtils.md5Hex(is);
		is.close();
		return md5;
	}

	public File getFile(String filePath) {
		String fullFilePath = getFullFileName(filePath);
		File file = new File(fullFilePath);
		return file;
	}

	public String getFullFileName(String fileNameWithPath) {
		// 若是全路径名，直接返回
		if (FileTools.isAbsolutePath(fileNameWithPath)) {
			return fileNameWithPath;
		}
		// 得到上级目录的全路径

		String fileRootPathAbsolute = fileRootPath;
		// // 得到文件全路径
		return FileTools.combineFileName(fileRootPathAbsolute, fileNameWithPath);

	}

	public String getDefaultUserDataDirecory() {
		return dufaultUserDataDirecory;
	}

	private String mkdirByUserId(String level_0, long userId, Date createTime) throws IOException {
		int level_1_sum = 100000;// 一级目录总人数
		int level_2_sum = 1000;// 二级目录总人数
		if (StringUtils.isNotBlank(level_0))
			level_0 = level_0.trim();
		else
			level_0 = getDefaultUserDataDirecory();// "UserData";
		int level_1 = (int) (userId / level_1_sum);// 一级目录编号，一级目录下有level_1_sum人
		int level_2 = (int) ((userId - level_1 * level_1_sum) / level_2_sum);// 二级目录编号(0~99)，二级目录下有level_2_sum人
		long level_3 = userId;
		String level_4 = DateTools.format(createTime, DateTools.DATE_FORMAT_YYYYMM);

		StringBuffer buffer = new StringBuffer();
		buffer.append(level_0).append(File.separator).append(level_1).append(File.separator).append(level_2)
				.append(File.separator).append(level_3).append(File.separator).append(level_4).append(File.separator);
		String filePath = buffer.toString();

		// 得到文件的全路径
		String fileFullPath = getFullFileName(filePath);
		File file = new File(fileFullPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return filePath;
	}

	/**
	 * 返回最大四位数的数字
	 * 
	 * @return
	 */
	private int getRandom() {
		return random.nextInt(10000);
	}
}
