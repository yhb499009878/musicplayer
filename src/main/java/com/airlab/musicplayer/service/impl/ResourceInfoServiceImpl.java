package com.airlab.musicplayer.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlab.musicplayer.model.ResourceInfo;
import com.airlab.musicplayer.mysql.api.IResourceInfoDao;
import com.airlab.musicplayer.service.ResourceInfoService;
import com.airlab.musicplayer.utils.Constant;
import com.airlab.musicplayer.utils.FileStorageLocal;
import com.airlab.musicplayer.utils.MimeTypeMapper;
import com.airlab.musicplayer.utils.StringTools;
import com.google.common.base.Preconditions;

@Service
public class ResourceInfoServiceImpl implements ResourceInfoService {
	private static final Logger log = LoggerFactory.getLogger(ResourceInfoServiceImpl.class);

	private final static String UNZIP_TEMPDIR = "tmp"; // 临时解压文件目录

	private final static long RESOURCE_MAX_SIZE = Constant.getResourceMaxSize();

	private final static String RESOURCE_DOWNLOAD_ADDRESS = Constant.RESOURCE_DOWNLOAD_ADDRESS;

	@Autowired
	private FileStorageLocal fileStorageServiceImpl;

	@Autowired
	private IResourceInfoDao resourceInfoDao;

	public static final String PART_RESOURCE_KEY = "@";

	@Override
	public ResourceInfo addResource(Long userId, String fileName, String mimeType, String resourceId,
			byte[] fileContent) {
		// 验证输入参数
		Preconditions.checkArgument(StringUtils.isNotEmpty(resourceId), "resourceId为空");
		Preconditions.checkArgument(fileContent != null, "资源为空");
		Preconditions.checkArgument(fileContent.length <= RESOURCE_MAX_SIZE, "资源长度超过100m");
		ResourceInfo resource = null;
		// 将附件信息保存入资源服务器
		try {
			ResourceInfo resourceInfo = resourceInfoDao.selectByResourceId(resourceId);
			if (resourceInfo != null) {
				return resourceInfo;
			}
			resource = saveResource(userId, fileName, mimeType, resourceId, fileContent);
			try {
				resourceInfoDao.insert(resource);
			} catch (org.springframework.dao.DuplicateKeyException e) {
				resource = resourceInfoDao.selectByResourceId(resourceId);
			}
			String downloadPath = RESOURCE_DOWNLOAD_ADDRESS + resource.getFilePath();
			resource.setFilePath(downloadPath);
			return resource;
		} catch (IOException e) {
			return null;
		}
	}

	private ResourceInfo saveResource(Long userId, String fileName, String mimeType, String resourceId,
			byte[] fileContent) throws IOException {

		if (StringUtils.isEmpty(fileName)) {
			fileName = "default";
		}
		String ext = "";
		if (StringUtils.isNotEmpty(fileName) && fileName.lastIndexOf('.') > 0) {
			ext = FilenameUtils.getExtension(fileName).toLowerCase();
		}
		if (StringUtils.isNotEmpty(ext) && StringUtils.isEmpty(mimeType)) {
			// 文件有扩展名，但无mimetype，设置mimeType
			mimeType = MimeTypeMapper.getMimeTypeNameByExtName(ext);
		}
		if (StringUtils.isEmpty(ext) && StringUtils.isNotEmpty(mimeType)) {
			ext = MimeTypeMapper.getExtNameByMimeTypeName(mimeType);
		}
		String filePath = "";
		try {
			filePath = fileStorageServiceImpl.saveUserFile(userId, fileName, ext, fileContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String systemFileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
		ResourceInfo resource = new ResourceInfo();
		resource.setMimeType(StringUtils.defaultIfEmpty(mimeType, ""));
		resource.setUserId(userId);
		resource.setResourceId(resourceId);
		resource.setFileName(fileName);
		resource.setSystemFileName(systemFileName);
		resource.setFilePath(filePath);
		resource.setSize(fileContent.length);
		resource.setMd5(DigestUtils.md5Hex(fileContent));

		return resource;
	}

	@Override
	public ResourceInfo getResource(String resourceId) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(resourceId), "资源id不能为空");

		ResourceInfo resource = resourceInfoDao.selectByResourceId(resourceId);

		try {
			if (resource == null) {
				return null;
			}
			byte[] fileContent = fileStorageServiceImpl.readUserFile(resource.getFilePath());
			resource.setContent(fileContent);
			return resource;

		} catch (IOException e) {
			return null;
		}

	}


	@Override
	public ResourceInfo queryResourceInfo(String resourceId) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(resourceId), "资源id不能为空");

		ResourceInfo resource = resourceInfoDao.selectByResourceId(resourceId);

		if (resource != null) {
			String downloadPath = RESOURCE_DOWNLOAD_ADDRESS + File.separator + resource.getFilePath();
			resource.setFilePath(downloadPath);
		}
		return resource;

	}

	/**
	 * 获得指定文件的byte数组
	 */
	public byte[] getBytes(String filePath) {
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	@Override
	public ResourceInfo saveResourceInfo(ResourceInfo resourceInfo) {
		if (resourceInfo == null) {
			return null;
		}
		try {
			resourceInfo.setResourceId(StringTools.getUUID());
			resourceInfoDao.saveOrUpdate(resourceInfo);
			return resourceInfo;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ResourceInfo getResourceInfo(String resourceId) {
		return resourceInfoDao.selectByResourceId(resourceId);
	}

	@Override
	public byte[] getResourceContent(String filePath) {
		byte[] fileContent = null;
		try {
			fileContent = fileStorageServiceImpl.readUserFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent;
	}
}
