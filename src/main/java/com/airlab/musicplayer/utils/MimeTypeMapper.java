package com.airlab.musicplayer.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MimeTypeMapper {
	private static final Map<String,String> mimeTypeMap = new HashMap<String,String>();
	private static final Map<String,String> extensionMap = new HashMap<String,String>();
	public static final List<String> imageMimeTypeList = new ArrayList<String>();
	public static final List<String> docMimeTypeList = new ArrayList<String>();
	
	public static final String MIME_TYPE_IMAGE_JPEG = "image/jpeg";
	public static final String MIME_TYPE_TEXT_HTML = "text/html";
	public static final String MIME_TYPE_BINARY = "application/octet-stream";
	public static String getMimeTypeNameByExtName(String extName){
		return extensionMap.get(extName);
	}
	
	public static String getExtNameByMimeTypeName(String mimeType){
		return mimeTypeMap.get(mimeType);
	}
	
	public static boolean isImage(String mimeType){
		if ( mimeType == null || mimeType.equals("") ) {
			return false;
		}
		if ( mimeType.startsWith("image/") ) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isHtml(String mimeType){
		if ( mimeType == null || mimeType.equals("") ) {
			return false;
		}		
		if ( mimeType.toLowerCase().contains("html") ) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isVoice(String mimeType){
		if ( mimeType == null || mimeType.equals("") ) {
			return false;
		}
		if ( mimeType.toLowerCase().equals("voice") ) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isDoc(String mimeType){
		if ( mimeType == null || mimeType.equals("") ) {
			return false;
		}
		if ( docMimeTypeList.contains(mimeType.toLowerCase()) ) {
			return true;
		} else {
			return false;
		}
	}
	
	static {
		/**
		 * mime类型变化可能性很小，使用常量，不使用资源文件方式
		 */
		extensionMap.put("3gp","video/3gpp");
		extensionMap.put("aac","audio/3gpp");
		extensionMap.put("aab","application/x-authoware-bin");
		extensionMap.put("aam","application/x-authoware-map");
		extensionMap.put("aas","application/x-authoware-seg");
		extensionMap.put("ai","application/postscript");
		extensionMap.put("aif","audio/x-aiff");
		extensionMap.put("aifc","audio/x-aiff");
		extensionMap.put("aiff","audio/x-aiff");
		extensionMap.put("als","audio/X-Alpha5");
		extensionMap.put("amc","application/x-mpeg");
		extensionMap.put("ani","application/octet-stream");
		extensionMap.put("asc","text/plain");
		extensionMap.put("asd","application/astound");
		extensionMap.put("asf","video/x-ms-asf");
		extensionMap.put("asn","application/astound");
		extensionMap.put("asp","application/x-asap");
		extensionMap.put("asx","video/x-ms-asf");
		extensionMap.put("au","audio/basic");
		extensionMap.put("avb","application/octet-stream");
		extensionMap.put("avi","video/x-msvideo");
		extensionMap.put("awb","audio/amr-wb");
		extensionMap.put("bcpio","application/x-bcpio");
		extensionMap.put("bin","application/octet-stream");
		extensionMap.put("bld","application/bld");
		extensionMap.put("bld2","application/bld2");
		extensionMap.put("bmp","application/x-MS-bmp");
		extensionMap.put("bpk","application/octet-stream");
		extensionMap.put("bz2","application/x-bzip2");
		extensionMap.put("cal","image/x-cals");
		extensionMap.put("ccn","application/x-cnc");
		extensionMap.put("cco","application/x-cocoa");
		extensionMap.put("cdf","application/x-netcdf");
		extensionMap.put("cgi","magnus-internal/cgi");
		extensionMap.put("chat","application/x-chat");
		extensionMap.put("class","application/octet-stream");
		extensionMap.put("clp","application/x-msclip");
		extensionMap.put("cmx","application/x-cmx");
		extensionMap.put("co","application/x-cult3d-object");
		extensionMap.put("cod","image/cis-cod");
		extensionMap.put("cpio","application/x-cpio");
		extensionMap.put("cpt","application/mac-compactpro");
		extensionMap.put("crd","application/x-mscardfile");
		extensionMap.put("csh","application/x-csh");
		extensionMap.put("csm","chemical/x-csml");
		extensionMap.put("csml","chemical/x-csml");
		extensionMap.put("css","text/css");
		extensionMap.put("cur","application/octet-stream");
		extensionMap.put("dcm","x-lml/x-evm");
		extensionMap.put("dcr","application/x-director");
		extensionMap.put("dcx","image/x-dcx");		
		extensionMap.put("dir","application/x-director");
		extensionMap.put("dll","application/octet-stream");
		extensionMap.put("dmg","application/octet-stream");
		extensionMap.put("dms","application/octet-stream");
		extensionMap.put("doc","application/msword");
		extensionMap.put("docx","application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		extensionMap.put("dot","application/x-dot");
		extensionMap.put("dvi","application/x-dvi");
		extensionMap.put("dwf","drawing/x-dwf");
		extensionMap.put("dwg","application/x-autocad");
		extensionMap.put("dxf","application/x-autocad");
		extensionMap.put("dxr","application/x-director");
		extensionMap.put("ebk","application/x-expandedbook");
		extensionMap.put("emb","chemical/x-embl-dl-nucleotide");
		extensionMap.put("embl","chemical/x-embl-dl-nucleotide");
		extensionMap.put("eps","application/postscript");
		extensionMap.put("epub","application/epub+zip");
		extensionMap.put("eri","image/x-eri");
		extensionMap.put("es","audio/echospeech");
		extensionMap.put("esl","audio/echospeech");
		extensionMap.put("etc","application/x-earthtime");
		extensionMap.put("etx","text/x-setext");
		extensionMap.put("evm","x-lml/x-evm");
		extensionMap.put("evy","application/x-envoy");
		extensionMap.put("exe","application/octet-stream");
		extensionMap.put("fh4","image/x-freehand");
		extensionMap.put("fh5","image/x-freehand");
		extensionMap.put("fhc","image/x-freehand");
		extensionMap.put("fif","image/fif");
		extensionMap.put("fm","application/x-maker");
		extensionMap.put("fpx","image/x-fpx");
		extensionMap.put("fvi","video/isivideo");
		extensionMap.put("gau","chemical/x-gaussian-input");
		extensionMap.put("gca","application/x-gca-compressed");
		extensionMap.put("gdb","x-lml/x-gdb");		
		extensionMap.put("gps","application/x-gps");
		extensionMap.put("gtar","application/x-gtar");
		extensionMap.put("gz","application/x-gzip");
		extensionMap.put("hdf","application/x-hdf");
		extensionMap.put("hdm","text/x-hdml");
		extensionMap.put("hdml","text/x-hdml");
		extensionMap.put("hlp","application/winhlp");
		extensionMap.put("hqx","application/mac-binhex40");		
		extensionMap.put("htm","text/html");		
		extensionMap.put("hts","text/html");
		extensionMap.put("dhtml","text/html");
		extensionMap.put("html","text/html");
		extensionMap.put("ice","x-conference/x-cooltalk");
		extensionMap.put("ico","application/octet-stream");
		extensionMap.put("ief","image/ief");
		extensionMap.put("ifm","image/gif");
		extensionMap.put("gif","image/gif");
		extensionMap.put("ifs","image/ifs");
		extensionMap.put("imy","audio/melody");
		extensionMap.put("ins","application/x-NET-Install");
		extensionMap.put("ips","application/x-ipscript");
		extensionMap.put("ipx","application/x-ipix");
		extensionMap.put("it","audio/x-mod");
		extensionMap.put("itz","audio/x-mod");
		extensionMap.put("ivr","i-world/i-vrml");
		extensionMap.put("j2k","image/j2k");
		extensionMap.put("jad","text/vnd.sun.j2me.app-descriptor");
		extensionMap.put("jam","application/x-jam");
		extensionMap.put("jar","application/java-archive");
		extensionMap.put("jnlp","application/x-java-jnlp-file");
		extensionMap.put("jpe", MIME_TYPE_IMAGE_JPEG);
		extensionMap.put("jpeg", MIME_TYPE_IMAGE_JPEG);
		extensionMap.put("jpz", MIME_TYPE_IMAGE_JPEG);
		extensionMap.put("jpg", MIME_TYPE_IMAGE_JPEG);
		extensionMap.put("js","application/x-javascript");
		extensionMap.put("jwc","application/jwc");
		extensionMap.put("kjx","application/x-kjx");
		extensionMap.put("lak","x-lml/x-lak");
		extensionMap.put("latex","application/x-latex");
		extensionMap.put("lcc","application/fastman");
		extensionMap.put("lcl","application/x-digitalloca");
		extensionMap.put("lcr","application/x-digitalloca");
		extensionMap.put("lgh","application/lgh");
		extensionMap.put("lha","application/octet-stream");
		extensionMap.put("lml","x-lml/x-lml");
		extensionMap.put("lmlpack","x-lml/x-lmlpack");
		extensionMap.put("lsf","video/x-ms-asf");
		extensionMap.put("lsx","video/x-ms-asf");
		extensionMap.put("lzh","application/x-lzh");
		extensionMap.put("m13","application/x-msmediaview");
		extensionMap.put("m14","application/x-msmediaview");
		extensionMap.put("m15","audio/x-mod");
		extensionMap.put("m3u","audio/x-mpegurl");
		extensionMap.put("m3url","audio/x-mpegurl");
		extensionMap.put("ma1","audio/ma1");
		extensionMap.put("ma2","audio/ma2");
		extensionMap.put("ma3","audio/ma3");
		extensionMap.put("ma5","audio/ma5");
		extensionMap.put("man","application/x-troff-man");
		extensionMap.put("map","magnus-internal/imagemap");
		extensionMap.put("mbd","application/mbedlet");
		extensionMap.put("mct","application/x-mascot");
		extensionMap.put("mdb","application/x-msaccess");
		extensionMap.put("mdz","audio/x-mod");
		extensionMap.put("me","application/x-troff-me");
		extensionMap.put("mel","text/x-vmel");
		extensionMap.put("mi","application/x-mif");
		extensionMap.put("mid","audio/midi");
		extensionMap.put("midi","audio/midi");
		extensionMap.put("mif","application/x-mif");
		extensionMap.put("mil","image/x-cals");
		extensionMap.put("mio","audio/x-mio");
		extensionMap.put("mmf","application/x-skt-lbs");
		extensionMap.put("mng","video/x-mng");
		extensionMap.put("mny","application/x-msmoney");
		extensionMap.put("moc","application/x-mocha");
		extensionMap.put("mocha","application/x-mocha");
		extensionMap.put("mod","audio/x-mod");
		extensionMap.put("mof","application/x-yumekara");
		extensionMap.put("mol","chemical/x-mdl-molfile");
		extensionMap.put("mop","chemical/x-mopac-input");
		extensionMap.put("mov","video/quicktime");
		extensionMap.put("movie","video/x-sgi-movie");
		extensionMap.put("mp2","audio/x-mpeg");
		extensionMap.put("mp3","audio/x-mpeg");
		extensionMap.put("mp4","video/mp4");
		extensionMap.put("mpc","application/vnd.mpohun.certificate");
		extensionMap.put("mpe","video/mpeg");
		extensionMap.put("mpeg","video/mpeg");
		extensionMap.put("mpg","video/mpeg");
		extensionMap.put("mpg4","video/mp4");
		extensionMap.put("mpga","audio/mpeg");
		extensionMap.put("mpn","application/vnd.mophun.application");
		extensionMap.put("mpp","application/vnd.ms-project");
		extensionMap.put("mps","application/x-mapserver");
		extensionMap.put("mrl","text/x-mrml");
		extensionMap.put("mrm","application/x-mrm");
		extensionMap.put("ms","application/x-troff-ms");
		extensionMap.put("mts","application/metastream");
		extensionMap.put("mtx","application/metastream");
		extensionMap.put("mtz","application/metastream");
		extensionMap.put("mzv","application/metastream");
		extensionMap.put("nar","application/zip");
		extensionMap.put("nbmp","image/nbmp");
		extensionMap.put("nc","application/x-netcdf");
		extensionMap.put("ndb","x-lml/x-ndb");
		extensionMap.put("ndwn","application/ndwn");
		extensionMap.put("nif","application/x-nif");
		extensionMap.put("nmz","application/x-scream");
		extensionMap.put("nokia-op-logo);","image/vnd.nok-oplogo-color");
		extensionMap.put("npx","application/x-netfpx");
		extensionMap.put("nsnd","audio/nsnd");
		extensionMap.put("nva","application/x-neva1");
		extensionMap.put("oda","application/oda");
		extensionMap.put("oom","application/x-AtlasMate-Plugin");
		extensionMap.put("pac","audio/x-pac");
		extensionMap.put("pae","audio/x-epac");
		extensionMap.put("pan","application/x-pan");
		extensionMap.put("pbm","image/x-portable-bitmap");
		extensionMap.put("pcx","image/x-pcx");
		extensionMap.put("pda","image/x-pda");
		extensionMap.put("pdb","chemical/x-pdb");
		extensionMap.put("pdf","application/pdf");
		extensionMap.put("pfr","application/font-tdpfr");
		extensionMap.put("pgm","image/x-portable-graymap");
		extensionMap.put("pict","image/x-pict");
		extensionMap.put("pm","application/x-perl");
		extensionMap.put("pmd","application/x-pmd");
		extensionMap.put("png","image/png");
		extensionMap.put("pnm","image/x-portable-anymap");
		extensionMap.put("pnz","image/png");
		extensionMap.put("pot","application/vnd.ms-powerpoint");
		extensionMap.put("ppm","image/x-portable-pixmap");
		extensionMap.put("pps","application/vnd.ms-powerpoint");
		extensionMap.put("ppt","application/vnd.ms-powerpoint");
		extensionMap.put("pptx","application/vnd.openxmlformats-officedocument.presentationml.presentation");
		extensionMap.put("pqf","application/x-cprplayer");
		extensionMap.put("pqi","application/cprplayer");
		extensionMap.put("prc","application/x-prc");
		extensionMap.put("proxy","application/x-ns-proxy-autoconfig");
		extensionMap.put("ps","application/postscript");
		extensionMap.put("ptlk","application/listenup");
		extensionMap.put("pub","application/x-mspublisher");
		extensionMap.put("pvx","video/x-pv-pvx");
		extensionMap.put("qcp","audio/vnd.qcelp");
		extensionMap.put("qt","video/quicktime");
		extensionMap.put("qti","image/x-quicktime");
		extensionMap.put("qtif","image/x-quicktime");
		extensionMap.put("r3t","text/vnd.rn-realtext3d");
		extensionMap.put("ra","audio/x-pn-realaudio");
		extensionMap.put("ram","audio/x-pn-realaudio");
		extensionMap.put("rar","application/x-rar-compressed");
		extensionMap.put("ras","image/x-cmu-raster");
		extensionMap.put("rdf","application/rdf+xml");
		extensionMap.put("rf","image/vnd.rn-realflash");
		extensionMap.put("rgb","image/x-rgb");
		extensionMap.put("rlf","application/x-richlink");
		extensionMap.put("rm","audio/x-pn-realaudio");
		extensionMap.put("rmf","audio/x-rmf");
		extensionMap.put("rmm","audio/x-pn-realaudio");
		extensionMap.put("rmvb","audio/x-pn-realaudio");
		extensionMap.put("rnx","application/vnd.rn-realplayer");
		extensionMap.put("roff","application/x-troff");
		extensionMap.put("rp","image/vnd.rn-realpix");
		extensionMap.put("rpm","audio/x-pn-realaudio-plugin");
		extensionMap.put("rt","text/vnd.rn-realtext");
		extensionMap.put("rte","x-lml/x-gps");
		extensionMap.put("rtf","application/rtf");
		extensionMap.put("rtg","application/metastream");
		extensionMap.put("rtx","text/richtext");
		extensionMap.put("rv","video/vnd.rn-realvideo");
		extensionMap.put("rwc","application/x-rogerwilco");
		extensionMap.put("s3m","audio/x-mod");
		extensionMap.put("s3z","audio/x-mod");
		extensionMap.put("sca","application/x-supercard");
		extensionMap.put("scd","application/x-msschedule");
		extensionMap.put("sdf","application/e-score");
		extensionMap.put("sea","application/x-stuffit");
		extensionMap.put("sgm","text/x-sgml");
		extensionMap.put("sgml","text/x-sgml");
		extensionMap.put("sh","application/x-sh");
		extensionMap.put("shar","application/x-shar");
		extensionMap.put("shtml","magnus-internal/parsed-html");
		extensionMap.put("shw","application/presentations");
		extensionMap.put("si6","image/si6");
		extensionMap.put("si7","image/vnd.stiwap.sis");
		extensionMap.put("si9","image/vnd.lgtwap.sis");
		extensionMap.put("sis","application/vnd.symbian.install");
		extensionMap.put("sit","application/x-stuffit");
		extensionMap.put("skd","application/x-Koan");
		extensionMap.put("skm","application/x-Koan");
		extensionMap.put("skp","application/x-Koan");
		extensionMap.put("skt","application/x-Koan");
		extensionMap.put("slc","application/x-salsa");
		extensionMap.put("smd","audio/x-smd");
		extensionMap.put("smi","application/smil");
		extensionMap.put("smil","application/smil");
		extensionMap.put("smp","application/studiom");
		extensionMap.put("smz","audio/x-smd");
		extensionMap.put("snd","audio/basic");
		extensionMap.put("spc","text/x-speech");
		extensionMap.put("spl","application/futuresplash");
		extensionMap.put("spr","application/x-sprite");
		extensionMap.put("sprite","application/x-sprite");
		extensionMap.put("sdp","application/sdp");
		extensionMap.put("spt","application/x-spt");
		extensionMap.put("src","application/x-wais-source");
		extensionMap.put("stk","application/hyperstudio");
		extensionMap.put("stm","audio/x-mod");
		extensionMap.put("sv4cpio","application/x-sv4cpio");
		extensionMap.put("sv4crc","application/x-sv4crc");
		extensionMap.put("svf","image/vnd");
		extensionMap.put("svg","image/svg-xml");
		extensionMap.put("svh","image/svh");
		extensionMap.put("svr","x-world/x-svr");
		extensionMap.put("swf","application/x-shockwave-flash");
		extensionMap.put("swfl","application/x-shockwave-flash");
		extensionMap.put("t","application/x-troff");
		extensionMap.put("tad","application/octet-stream");
		extensionMap.put("talk","text/x-speech");
		extensionMap.put("tar","application/x-tar");
		extensionMap.put("taz","application/x-tar");
		extensionMap.put("tbp","application/x-timbuktu");
		extensionMap.put("tbt","application/x-timbuktu");
		extensionMap.put("tcl","application/x-tcl");
		extensionMap.put("tex","application/x-tex");
		extensionMap.put("texi","application/x-texinfo");
		extensionMap.put("texinfo","application/x-texinfo");
		extensionMap.put("tgz","application/x-tar");
		extensionMap.put("thm","application/vnd.eri.thm");
		extensionMap.put("tif","image/tiff");
		extensionMap.put("tiff","image/tiff");
		extensionMap.put("tki","application/x-tkined");
		extensionMap.put("tkined","application/x-tkined");
		extensionMap.put("toc","application/toc");
		extensionMap.put("toy","image/toy");
		extensionMap.put("tr","application/x-troff");
		extensionMap.put("trk","x-lml/x-gps");
		extensionMap.put("trm","application/x-msterminal");
		extensionMap.put("tsi","audio/tsplayer");
		extensionMap.put("tsp","application/dsptype");
		extensionMap.put("tsv","text/tab-separated-values");
		extensionMap.put("tsv","text/tab-separated-values");
		extensionMap.put("ttf","application/octet-stream");
		extensionMap.put("ttz","application/t-time");
		extensionMap.put("txt","text/plain");
		extensionMap.put("ult","audio/x-mod");
		extensionMap.put("ustar","application/x-ustar");
		extensionMap.put("uu","application/x-uuencode");
		extensionMap.put("uue","application/x-uuencode");
		extensionMap.put("vcd","application/x-cdlink");
		extensionMap.put("vcf","text/x-vcard");
		extensionMap.put("vdo","video/vdo");
		extensionMap.put("vib","audio/vib");
		extensionMap.put("viv","video/vivo");
		extensionMap.put("vivo","video/vivo");
		extensionMap.put("vmd","application/vocaltec-media-desc");
		extensionMap.put("vmf","application/vocaltec-media-file");
		extensionMap.put("vmi","application/x-dreamcast-vms-info");
		extensionMap.put("vms","application/x-dreamcast-vms");
		extensionMap.put("vox","audio/voxware");
		extensionMap.put("vqe","audio/x-twinvq-plugin");
		extensionMap.put("vqf","audio/x-twinvq");
		extensionMap.put("vql","audio/x-twinvq");
		extensionMap.put("vre","x-world/x-vream");
		extensionMap.put("vrml","x-world/x-vrml");
		extensionMap.put("vrt","x-world/x-vrt");
		extensionMap.put("vrw","x-world/x-vream");
		extensionMap.put("vts","workbook/formulaone");
		extensionMap.put("wav","audio/x-wav");
		extensionMap.put("wax","audio/x-ms-wax");
		extensionMap.put("wbmp","image/vnd.wap.wbmp");
		extensionMap.put("web","application/vnd.xara");
		extensionMap.put("wi","image/wavelet");
		extensionMap.put("wis","application/x-InstallShield");
		extensionMap.put("wm","video/x-ms-wm");
		extensionMap.put("wma","audio/x-ms-wma");
		extensionMap.put("wmd","application/x-ms-wmd");
		extensionMap.put("wmf","application/x-msmetafile");
		extensionMap.put("wml","text/vnd.wap.wml");
		extensionMap.put("wmlc","application/vnd.wap.wmlc");
		extensionMap.put("wmls","text/vnd.wap.wmlscript");
		extensionMap.put("wmlsc","application/vnd.wap.wmlscriptc");
		extensionMap.put("wmlscript","text/vnd.wap.wmlscript");
		extensionMap.put("wmv","video/x-ms-wmv");
		extensionMap.put("wmx","video/x-ms-wmx");
		extensionMap.put("wmz","application/x-ms-wmz");
		extensionMap.put("wpng","image/x-up-wpng");
		extensionMap.put("wpt","x-lml/x-gps");
		extensionMap.put("wri","application/x-mswrite");
		extensionMap.put("wrl","x-world/x-vrml");
		extensionMap.put("wrz","x-world/x-vrml");
		extensionMap.put("ws","text/vnd.wap.wmlscript");
		extensionMap.put("wsc","application/vnd.wap.wmlscriptc");
		extensionMap.put("wv","video/wavelet");
		extensionMap.put("wvx","video/x-ms-wvx");
		extensionMap.put("wxl","application/x-wxl");
		extensionMap.put("x-gzip","application/x-gzip");
		extensionMap.put("xar","application/vnd.xara");
		extensionMap.put("xbm","image/x-xbitmap");
		extensionMap.put("xdm","application/x-xdma");
		extensionMap.put("xdma","application/x-xdma");
		extensionMap.put("xdw","application/vnd.fujixerox.docuworks");
		extensionMap.put("xht","application/xhtml+xml");
		extensionMap.put("xhtm","application/xhtml+xml");
		extensionMap.put("xhtml","application/xhtml+xml");
		extensionMap.put("xla","application/vnd.ms-excel");
		extensionMap.put("xlc","application/vnd.ms-excel");
		extensionMap.put("xll","application/x-excel");
		extensionMap.put("xlm","application/vnd.ms-excel");
		extensionMap.put("xls","application/vnd.ms-excel");
		extensionMap.put("xlsx","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		extensionMap.put("xlt","application/vnd.ms-excel");
		extensionMap.put("xlw","application/vnd.ms-excel");
		extensionMap.put("xm","audio/x-mod");
		extensionMap.put("xml","text/xml");
		extensionMap.put("xmz","audio/x-mod");
		extensionMap.put("xpi","application/x-xpinstall");
		extensionMap.put("xpm","image/x-xpixmap");
		extensionMap.put("xsit","text/xml");
		extensionMap.put("xsl","text/xml");
		extensionMap.put("xul","text/xul");
		extensionMap.put("xwd","image/x-xwindowdump");
		extensionMap.put("xyz","chemical/x-pdb");
		extensionMap.put("yz1","application/x-yz1");
		extensionMap.put("z","application/x-compress");
		extensionMap.put("zac","application/x-zaurus-zac");
		extensionMap.put("zip","application/zip");
		extensionMap.put("mmap","application/mindmanager");
		extensionMap.put("xmmap","application/mindmanager");
		extensionMap.put("mmp","application/mindmanager");	
		extensionMap.put("csv","text/csv");	
		extensionMap.put("apk","application/vnd.android.package-archive");	
		extensionMap.put("ipa","application/x-itunes-ipa");	
		extensionMap.put("cer","application/x-x509-ca-cert");
		extensionMap.put("crt","application/x-x509-ca-cert");
		extensionMap.put("der","application/x-x509-ca-cert");
		extensionMap.put("p12","application/x-pkcs12");
		extensionMap.put("pfx","application/x-pkcs12");
		extensionMap.put("p10","application/pkcs10");
		extensionMap.put("crl","application/pkix-crl");
		extensionMap.put("p7b","application/x-pkcs7-certificates");
		extensionMap.put("spc","application/x-pkcs7-certificates");
		extensionMap.put("p7r","application/x-pkcs7-certreqresp");
		extensionMap.put("p7c","application/x-pkcs7-mime");
		extensionMap.put("p7m","application/x-pkcs7-mime");
		extensionMap.put("p7s","application/x-pkcs7-signature");		
		extensionMap.put("pko","application/ynd.ms-pkipko");
		extensionMap.put("ts","video/MP2T");
		extensionMap.put("m3u8","application/x-mpegURL");
		extensionMap.put("webp","image/webp");
		extensionMap.put("flv","flv-application/octet-stream");
		extensionMap.put("flv","video/x-flv");
		extensionMap.put("m4v","video/x-m4v");
		extensionMap.put("m4v","audio/mp4a-latm");
		extensionMap.put("m4v","video/ogg");
		
		//mimeType与扩展名映射转化
		for(Entry<String,String> entry : extensionMap.entrySet()){
			mimeTypeMap.put(entry.getValue(), entry.getKey());
			mimeTypeMap.put("image/gif","gif");
			mimeTypeMap.put("text/html","html");
			mimeTypeMap.put("application/mindmanager","mmap");
			mimeTypeMap.put("application/octet-stream","bin");
			mimeTypeMap.put("application/x-x509-ca-cert","cer");
		}
		
		//图片类型
		imageMimeTypeList.add("image/jpeg");
		imageMimeTypeList.add("image/png");
		imageMimeTypeList.add("image/jpg");
		imageMimeTypeList.add("image/gif");
		imageMimeTypeList.add("image/x-ms-bmp");
		imageMimeTypeList.add("image/webp");
//		imageMimeTypeList.add("application/x-MS-bmp");
//		imageMimeTypeList.add("image/nbmp");
//		imageMimeTypeList.add("image/tiff");
//		imageMimeTypeList.add("image/toy");
//		imageMimeTypeList.add("image/x-pcx");
//		imageMimeTypeList.add("image/x-pda");
//		imageMimeTypeList.add("image/x-portable-bitmap");
//		imageMimeTypeList.add("image/vnd");
//		imageMimeTypeList.add("image/svg-xml");
//		imageMimeTypeList.add("image/svh");
//		imageMimeTypeList.add("image/fif");
//		imageMimeTypeList.add("image/x-fpx");
		
		//文档类型 （2014.11.01佟总要求，除图片外都定义到文档中（在群文档显示））
		for(Entry<String,String> entry : extensionMap.entrySet()){
			String mimeType = entry.getValue();
			if (!imageMimeTypeList.contains(mimeType)) {
				docMimeTypeList.add(mimeType);
			}
		}
		
		/**
		 * 2014.11.01 注释
		//文档类型
		docMimeTypeList.add("application/pdf");
		docMimeTypeList.add("application/msword");
		docMimeTypeList.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		docMimeTypeList.add("application/vnd.ms-excel");
		docMimeTypeList.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		docMimeTypeList.add("application/vnd.ms-powerpoint");
		docMimeTypeList.add("application/vnd.openxmlformats-officedocument.presentationml.presentation");
		docMimeTypeList.add("text/html");
		docMimeTypeList.add("application/xhtml+xml");
		docMimeTypeList.add("magnus-internal/parsed-html");
		docMimeTypeList.add("text/plain");		
		docMimeTypeList.add("text/xml");
		docMimeTypeList.add("application/mindmanager");
		
		//音频、视频
		docMimeTypeList.add("video/3gpp");
		docMimeTypeList.add("audio/x-aiff");
		docMimeTypeList.add("audio/x-aiff");
		docMimeTypeList.add("audio/x-aiff");
		docMimeTypeList.add("audio/X-Alpha5");
		docMimeTypeList.add("application/x-mpeg");
		docMimeTypeList.add("video/x-ms-asf");
		docMimeTypeList.add("video/x-ms-asf");
		docMimeTypeList.add("audio/basic");
		docMimeTypeList.add("video/x-msvideo");
		docMimeTypeList.add("audio/amr-wb");
		docMimeTypeList.add("video/x-ms-asf");
		docMimeTypeList.add("video/x-ms-asf");
		docMimeTypeList.add("audio/ma1");
		docMimeTypeList.add("audio/ma2");
		docMimeTypeList.add("audio/ma3");
		docMimeTypeList.add("audio/ma5");
		docMimeTypeList.add("audio/x-mod");
		docMimeTypeList.add("audio/midi");
		docMimeTypeList.add("audio/midi");
		docMimeTypeList.add("audio/x-mio");
		docMimeTypeList.add("video/x-mng");
		docMimeTypeList.add("audio/x-mod");
		docMimeTypeList.add("video/quicktime");
		docMimeTypeList.add("video/x-sgi-movie");
		docMimeTypeList.add("audio/x-mpeg");
		docMimeTypeList.add("audio/x-mpeg");
		docMimeTypeList.add("video/mp4");
		docMimeTypeList.add("video/mpeg");
		docMimeTypeList.add("video/mpeg");
		docMimeTypeList.add("video/mpeg");
		docMimeTypeList.add("video/mp4");
		docMimeTypeList.add("audio/mpeg");
		docMimeTypeList.add("audio/nsnd");
		docMimeTypeList.add("audio/x-pac");
		docMimeTypeList.add("audio/x-epac");
		docMimeTypeList.add("video/x-pv-pvx");
		docMimeTypeList.add("audio/vnd.qcelp");
		docMimeTypeList.add("video/quicktime");
		docMimeTypeList.add("audio/x-pn-realaudio");
		docMimeTypeList.add("audio/x-rmf");
		docMimeTypeList.add("audio/x-pn-realaudio");
		docMimeTypeList.add("audio/x-pn-realaudio");
		docMimeTypeList.add("application/vnd.rn-realplayer");
		docMimeTypeList.add("video/vnd.rn-realvideo");
		docMimeTypeList.add("audio/x-mod");
		docMimeTypeList.add("audio/x-mod");
		docMimeTypeList.add("audio/x-smd");
		docMimeTypeList.add("audio/x-smd");
		docMimeTypeList.add("audio/basic");
		docMimeTypeList.add("audio/tsplayer");
		docMimeTypeList.add("video/vdo");
		docMimeTypeList.add("audio/vib");
		docMimeTypeList.add("video/vivo");
		docMimeTypeList.add("video/vivo");
		docMimeTypeList.add("audio/x-wav");
		docMimeTypeList.add("audio/x-ms-wax");
		docMimeTypeList.add("video/x-ms-wm");
		docMimeTypeList.add("audio/x-ms-wma");
		docMimeTypeList.add("video/x-ms-wmv");
		docMimeTypeList.add("video/x-ms-wmx");
		docMimeTypeList.add("video/wavelet");
		docMimeTypeList.add("video/x-ms-wvx");
		docMimeTypeList.add("audio/x-mod");
		docMimeTypeList.add("audio/3gpp");
		*/
	}
}
