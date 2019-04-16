package com.airlab.musicplayer.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

public final class FileTools {
    
	private static final String cachedUserDataDirecory = "tmp";
	
	private static final String FILE_ROOT_PATH = "/opt/airlab/musicplayer/data/";
	private static final String rootPath = "/opt/airlab/musicplayer/data/";
	private static final String RESOURCE_DOWNLOAD_ADDRESS = "/opt/airlab/musicplayer/data/";
	
    private FileTools() {
    }
    
    /**
     * 获得资源下载路径地址
     * @param filtPath
     * @return
     */
    public static String getResourceRealPath(String filtPath){
    	String prefix  = RESOURCE_DOWNLOAD_ADDRESS;
    	if(prefix.endsWith("/")){
    		return prefix + filtPath;
    	}else{
    		return prefix + File.separator + filtPath;
    	}
    	
    }

    /**
     * 妫�鏌ョ粰瀹氱殑鏂囦欢鍚嶆墍鍦ㄧ殑璺緞鏄惁瀛樺湪,濡傛灉涓嶅瓨鍦�,灏卞垱寤�
     *
     * @param fileName 甯﹁矾寰勭殑鏂囦欢鍚�
     */
    public static void checkMkdir(String fileName) {
        String path = dirName(fileName);
        if (path == null) {
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 鏍规嵁甯﹁矾寰勭殑鏂囦欢鍚嶏紝鑾峰緱璺緞鍚�. 
     * 鍩烘湰鎸夌収Unix鐨刣irname鐨勮鍒欙紝浣嗘湁涓�澶勪笉鍚岋紝瀵逛簬浠�'/'鎴�'\\'缁撳熬鐨勮矾寰勫悕
     *   濡�"/opt/"锛孶nix鐨刡asename杩斿洖"/"锛岃繖閲岃繑鍥�"/opt"
     * @param fileName 甯﹁矾寰勭殑鏂囦欢鍚�.
     * @return 涓嶅甫鏂囦欢鍚嶇殑璺緞鍚嶏紝闄や负鏍硅矾寰勭殑鎯呭喌澶栵紝涓嶅惈缁撳熬鐨� "/".
     */
    public static String dirName(String fileName) {
        return dirName(fileName, false);
    }

    /**
     * 鏍规嵁甯﹁矾寰勭殑鏂囦欢鍚嶏紝鑾峰緱璺緞鍚�. 
     * 鍩烘湰鎸夌収Unix鐨刣irname鐨勮鍒欙紝浣嗘湁涓�澶勪笉鍚岋紝瀵逛簬浠�'/'鎴�'\\'缁撳熬鐨勮矾寰勫悕
     *   濡�"/opt/"锛孶nix鐨刡asename杩斿洖"/"锛岃繖閲岃繑鍥�"/opt"
     * @param fileName 甯﹁矾寰勭殑鏂囦欢鍚�.
     * @param preserveTrailingSlash 鏄惁淇濈暀缁撳熬鐨勬枩绾裤��(鍗充娇鏄痜alse锛屽綋缁撴灉涓烘牴璺緞鏃讹紝
     *    缁撳熬浠嶇劧鍙兘鏄枩绾�)
     * @return 涓嶅甫鏂囦欢鍚嶇殑璺緞鍚�.
     */
    public static String dirName(String fileName, boolean preserveTrailingSlash) {
        if (StringTools.isNullOrWhiteSpace(fileName)) {
            return ".";
        }
        int index = fileName.lastIndexOf('/');
        if (index == -1) {
            index = fileName.lastIndexOf('\\');
        }
        switch (index) {
            case 0: 
                return "/";
            case -1:
                return ".";
            default:
                if (preserveTrailingSlash) {
                    return fileName.substring(0, index + 1);
                }
                return fileName.substring(0, index);
        }
    }

    /**
     * 寰楀埌涓�涓枃浠跺悕鐨勨�滃熀鏈枃浠跺悕閮ㄥ垎鈥濓紝灏嗗墠闈㈢殑璺緞閮ㄥ垎鍘绘帀. 
     * 鍩烘湰鎸夌収Unix鐨刡asename鐨勮鍒欙紝浣嗘湁涓�澶勪笉鍚岋紝瀵逛簬浠�'/'鎴�'\\'缁撳熬鐨勮矾寰勫悕
     *   濡�"/opt/"锛孶nix鐨刡asename杩斿洖"opt"锛岃繖閲岃繑鍥�""
     * @param fileName 锛堝彲鑳斤級甯﹁矾寰勭殑鏂囦欢鍚�
     * @return 涓嶅甫璺緞鐨勬枃浠跺悕锛屼笉鍚�"/"
     */
    public static String baseName(String fileName) {
        if (StringTools.isNullOrWhiteSpace(fileName)) {
            return "";
        }
        int index = fileName.lastIndexOf('/');
        if (index == -1) {
            index = fileName.lastIndexOf('\\');
        }
        // if index == -1, index + 1 will be 0
        return fileName.substring(index + 1);
    }

    /**
     * 灏唗ime浠ヨ拷鍔犵殑鏂瑰紡鍐欏叆filename
     *
     * @param timestr  : 鏃堕棿瀛楃涓�
     * @param filename : 鏂囨湰鏂囦欢鍚�
     * @return boolean : 鎴愬姛(true), 澶辫触(false)
     * @throws IOException
     */
    public static boolean appendFile(String timestr, String filename)
            throws IOException {
        FileOutputStream stream;
        OutputStreamWriter writer;
        File file = new File(filename);

        if (!file.exists()) {
            return false;
        }

        stream = new FileOutputStream(filename, true);
        writer = new OutputStreamWriter(stream, "gb2312");
        try {
            writer.write(timestr);
        } finally {
            writer.close();
            stream.close();
        }

        return true;
    }

    public RandomAccessFile openRandomFile(String fileName, String mode)
            throws IOException {
        File f;
        RandomAccessFile raf;
        f = new File(fileName);
        raf = new RandomAccessFile(f, mode);

        return raf;
    }

    /**
     * Create specified file.
     *
     * @param fileName File name.
     * @throws IOException When any IO error occurs.
     * @throws IllegalArgumentException If pass in empty fileName.
     */
    public static void createFile(String fileName) throws IOException {
        if (StringTools.isNullOrWhiteSpace(fileName)) {
            throw new IllegalArgumentException();
        }
        File file = new File(fileName);
        if (!file.exists()) {
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(file));
            writer.close();
        }
    }

    /**
     * Delete specified file.
     *
     * @param fileName File fileName.
     * @throws IOException When any IO error occurs.
     * @throws IllegalArgumentException If pass in empty fileName.
     */
    public static void deleteFile(String fileName) throws IOException {
        if (StringTools.isNullOrWhiteSpace(fileName)) {
            throw new IllegalArgumentException();
        }
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }
        if (!file.delete()) {
            throw new IOException();
        }
    }

    /**
     * 妫�鏌ath鎵�鎸囧悜鐨勭洰褰曟槸鍚﹀瓨鍦紝濡傛灉write涓簍rue,鍒欒瘯鍥惧垱寤鸿鐩綍銆�
     *
     * @param path  鐩爣鐩綍
     * @param write 璇ユ枃浠舵墍鍦ㄧ殑鐩綍瀛樺湪锛屾垨鑰呬笉瀛樺湪锛屼絾鏄凡琚垱寤�(wirte涓簍rue鏃�)
     * @return false 璇ユ枃浠舵墍鍦ㄧ殑鐩綍涓嶅瓨鍦紝鎴栬�呯洰褰曞垱寤哄け璐ャ��
     */
    public static boolean checkDirExists(String path, boolean write) {
        File p = new File(path);

        if (p.exists() && p.isDirectory()) {
            return true;  //all dir exist, so return true;
        }

        if (!write) {
            return false;
        }

        if (!p.mkdirs()) {
            System.err.println("can't create dir: " + p);
            return false;
        }

        return true;
    }

    /**
     * 杩炴帴鍩虹鐩綍鍜屾枃浠跺悕锛岃繑鍥炵粷瀵规枃浠惰矾寰�
     * @param basedir 鍩虹鐩綍 濡傛灉鏄痭ull锛屽垯鐩稿綋浜庤皟鐢╢ormatFilePath(value)
     *          鍏佽鐩稿璺緞銆�
     * @param value   鏂囦欢 濡傛灉宸茬粡鏄粷瀵硅矾寰勶紝鍒欏拷鐣asedir
     * @return 缁濆鏂囦欢璺緞銆傚鏋滃嚭鐜伴敊璇紝杩斿洖缂虹渷缁撴灉
     */
//    public static String combineFileName(String basedir, String value) {
//        String rst = null;
//        // 鍏堝皢basedir杞崲涓虹粷瀵硅矾寰�
//        basedir = formatFilePath(basedir);
//        if (basedir != null && !basedir.endsWith("/")) {
//            basedir = basedir + '/';
//        }
//
//        if (value != null && value.trim().length() > 0) {
//            if (basedir == null || isAbsolutePath(value)) {
//                // 蹇界暐basedir
//                rst = value;
//            } else {
//                rst = basedir + value;
//            }
//        }
//
//        return formatFilePath(rst);
//    }
    
    /**
     * 杩炴帴鍩虹鐩綍鍜屾枃浠跺悕锛岃繑鍥炵粷瀵规枃浠惰矾寰�
     * @param basedir 鍩虹鐩綍蹇呴』涓烘枃浠剁郴缁熶腑鐨勭粷瀵硅矾寰�
     * @param value  鏂囦欢 璺緞锛岀浉瀵逛簬缁濆璺緞鐨�
     * @return 缁濆鏂囦欢璺緞銆傚鏋滃嚭鐜伴敊璇紝杩斿洖缂虹渷缁撴灉
     */
    public static String combineFileName(String basedir, String value) {
        String rst = null;
        if(StringUtils.isEmpty(basedir)){
        	throw new IllegalArgumentException("basedir 涓嶈兘涓虹┖");
        }
        // 鍏堝皢basedir杞崲涓虹粷瀵硅矾寰�
        basedir = formatFilePath(basedir);
        if (!basedir.endsWith("/")) {
            basedir = basedir + '/';
        }
        
        //鏃犺濡備綍锛岄兘浼氫互basedir寮�澶�
        if (StringUtils.isNotEmpty(value)) {
            rst = basedir + value;
        }
        
        return formatFilePath(rst);
    }
    
    

    /**
     * 杩炴帴鍩虹URL鍜屾枃浠跺悕锛岃繑鍥炵粨鏋淯RL
     * @param baseURL 鍩虹URL锛屼笉鍏佽null
     * @param value   鏂囦欢鍚嶏紝鐩稿浜庡熀纭�URL
     * @return 缁撴灉URL
     */
    public static URL combineURL(URL baseURL, String value) 
            throws MalformedURLException {
        String baseURLFile = baseURL.getFile();
        if (!baseURLFile.endsWith("/")) {
            baseURLFile += '/';
        }
        return new URL(baseURL.getProtocol(), baseURL.getHost(), baseURL.getPort(),
                baseURLFile + value);
    }
    
    /**
     * 瀵规寚瀹氱殑鏂囦欢鍚嶏紝杩斿洖涓�涓鑼冪殑鏂囦欢鍚嶈〃绀烘柟寮忥紝鍖呮嫭锛�
     * 1. 灏嗙浉瀵硅矾寰勫彉鎴愮粷瀵硅矾寰�
     * 2. 灏�'\'鏇挎崲涓�'/'
     *
     * @param file 鏂囦欢鍚�
     * @return 瑙勮寖鐨勬枃浠跺悕
     */
    public static String formatFilePath(String file) {
        if (file == null) {
            return null;
        }
        file = removeIllegalPath(file);
        return new File(file).getAbsolutePath().replace('\\', '/');
    }

    //绉婚櫎闈炴硶璺緞
	public static String removeIllegalPath(String file) {
		file=file.replaceAll("\\.\\./", "");
		return file;
	}

    /**
     * 鍒ゆ柇涓�涓枃浠跺悕鎴栬矾寰勫悕鏄惁涓虹粷瀵规枃浠跺悕鎴栬矾寰勫悕
     *
     * @param path 鏂囦欢鍚�
     * @return true 濡傛灉鏄粷瀵硅矾寰勫悕
     */
    public static boolean isAbsolutePath(String path) {
        return new File(path).isAbsolute();
    }
    /**
     * 鍒ゆ柇鏂囦欢鏄惁瀛樺湪
     * @param fileName
     * @return
     */
    public static boolean exist(String fileName){
    	if (StringTools.isNullOrWhiteSpace(fileName)) {
            throw new IllegalArgumentException();
        }
    	File file = new File(fileName);
    	return file.exists();
    }
    
	/**
	 * 鏍煎紡鍖栨枃浠跺ぇ灏忔樉绀�
	 * 
	 * @param f
	 * @return
	 */
	public static String formartFileSize(long length) {
		String show = "";
		DecimalFormat df = new DecimalFormat("#.##");
		Double lengthDouble = new Double(length);
		if (length < 1024) {
			show = String.valueOf(length) + "B";
		} else if (length >= 1024*1024*1024) {
			show = df.format(lengthDouble / new Double(1024*1024*1024)) + "GB";
		} else if (length >= 1024*1024) {
			show = df.format(lengthDouble / new Double(1024*1024)) + "MB";			
		} else if (length >= 1024) {			
			show = df.format(lengthDouble / 1024.0) + "KB";
		}		
		return show;
	}
	
	
	public static byte[] toByteArray(File f) throws IOException {  
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());  
        BufferedInputStream in = null;  
        try {  
            in = new BufferedInputStream(new FileInputStream(f));  
            int buf_size = 1024;  
            byte[] buffer = new byte[buf_size];  
            int len = 0;  
            while (-1 != (len = in.read(buffer, 0, buf_size))) {  
                bos.write(buffer, 0, len);  
            }  
            return bos.toByteArray();  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        } finally {  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            bos.close();  
        }  
    }  
	
	public static File createTmpFile(){
		String filePath = mkCachedDir();
		String fileName = createRandomFileName()+".zip";
		
		File path = new File(filePath);
		if(!path.exists()){
			path.mkdirs();
		}
		File file = new File(path,fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static String mkCachedDir() {
		Date now = new Date();
		String root = rootPath;
		String level_0 = cachedUserDataDirecory;
		
		String level_1 = DateFormatUtils.format(now, "yyyy");
		String level_2 = DateFormatUtils.format(now, "MM");
		String level_3 = DateFormatUtils.format(now, "dd");
		StringBuffer buffer = new StringBuffer();
		buffer.append(root).append(level_0).append(File.separator).append(level_1).append(File.separator).append(level_2)
				.append(File.separator).append(level_3).append(File.separator).append(StringTools.getUUID()).append(File.separator);
		String filePath = buffer.toString();

		// 得到文件的全路径
		String fileFullPath = getFullFileName(filePath);
		File file = new File(fileFullPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return filePath;
	}
	
	public static String getFullFileName(String fileNameWithPath) {
		// 若是全路径名，直接返回
		if (FileTools.isAbsolutePath(fileNameWithPath)) {
			return fileNameWithPath;
		}
		// 得到上级目录的全路径
		String fileRootPathAbsolute = FILE_ROOT_PATH;
		// // 得到文件全路径
		return FileTools.combineFileName(fileRootPathAbsolute, fileNameWithPath);
	}
	
	public static String createRandomFileName() {
		String randomFileName = DigestUtils
				.md5Hex(RandomStringUtils.randomAlphanumeric(16) + System.currentTimeMillis());
		return randomFileName;
	}
	
	public static String getFileNameWithoutSuffix(String fileName){
		if (StringUtils.isNotEmpty(fileName)) {
			int index =fileName.lastIndexOf(".");
			if(index >0) {
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
			}
		}
		
		return fileName;
	}
}
