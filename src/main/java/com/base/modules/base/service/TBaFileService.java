/**
 * Copyright &copy; 2019-2019 <a href="#">版权</a> All rights reserved.
 */
package com.base.modules.base.service;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lowagie.text.exceptions.BadPasswordException;
import com.lowagie.text.pdf.PdfReader;
import com.base.common.config.Global;
import com.base.common.persistence.Page;
import com.base.common.service.CrudService;
import com.base.common.utils.FTPUtil;
import com.base.common.utils.FileDownUtil;
import com.base.common.utils.StringUtils;
import com.base.modules.base.entity.TBaFile;
import com.base.modules.base.dao.TBaFileDao;
import com.base.modules.sys.entity.User;
import com.base.modules.sys.utils.UserUtils;

/**
 * 附件管理Service
 * @author handf
 * @version 2015-08-10
 */
@Service
@Transactional(readOnly = true)
public class TBaFileService extends CrudService<TBaFileDao, TBaFile> {

	public TBaFile get(String id) {
		return super.get(id);
	}
	
	public List<TBaFile> findList(TBaFile tBaFile) {
		return super.findList(tBaFile);
	}
	
	public Page<TBaFile> findPage(Page<TBaFile> page, TBaFile tBaFile) {
		return super.findPage(page, tBaFile);
	}
	
	@Transactional(readOnly = false)
	public void save(TBaFile tBaFile) {
		super.save(tBaFile);
	}
	
	/**
	 * 删除文件数据 注：此处做的是假删除
	 */
	@Transactional(readOnly = false)
	public void delete(TBaFile tBaFile) {
		super.delete(tBaFile);
	}

	//----------------------------------------------------FTP文件上传和下载START
	/**
	 * 保存文件到FTP上
	 * @param tBaFile
	 * @param request
	 * @param multipartHttpServletRequest
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveFTPFile(TBaFile tBaFile, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
	    StringBuilder result = new StringBuilder("{");
	    tBaFile.preInsert();
	    //1、校验文件的大小
	    String name = request.getParameter("name");
	    String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
	    String isFilterEncryption = request.getParameter("isFilterEncryption");
	    long maxM = 100;// 默认附件大小为100，单位：M
	    if(null != size && StringUtils.isNotBlank(size)){
	        maxM = Long.parseLong(size);
	    }
	    MultipartFile file = multipartHttpServletRequest.getFile("file" + name);// 获得上传的附件
	    
	    // 文件加密的校验（仅限于PDF的校验）
        String fileName = file.getOriginalFilename(); // 获得原始文件名
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
            try{
            	new PdfReader(file.getInputStream());
            }catch (BadPasswordException e){ 
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (NoClassDefFoundError e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (Exception e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
            }
        }
	    
        if(null != file && !file.isEmpty()){ 
            // 文件大小处理
            long maxSize = maxM * 1024 * 1024;
            long fileSize = file.getSize();
            if(fileSize > maxSize){
            	result = contactJson(result, "error", "文件大小超过上限");
            	result.append("}");
                return result.toString();
            }
            
            // 2、进行文件的上传操作
            try {
                upload(file, tBaFile.getId() + fileType);
            } catch (Exception e) {
                logger.error("文件上传时出现异常",e);
                result = contactJson(result, "error", "文件上传时出现异常");
            	result.append("}");
                return result.toString();
            }
            tBaFile.setFileSize("" + file.getSize());          // 文件大小
            tBaFile.setFileType(fileType);                     // 文件类型
            tBaFile.setFileName(fileName.replace(" ", ""));    // 原文件名
            tBaFile.setFolderPath("/" + UserUtils.getUser().getId());    // 文件夹路径
        } 
        
        // 3、优化页面展示的信息
        long fileSize = file.getSize();
        String sizeLabel = showSizeLabel(fileSize + "");
        
        dao.insert(tBaFile);
        //上传成功的文件ID
        result = contactJson(result, "fileId", tBaFile.getId());
    	result.append(",");
    	//上传成功后的文件大小
    	result = contactJson(result, "filesize", sizeLabel);
    	result.append("}");
        return result.toString();
    }
	
	/**
	 * 保存文件到FTP上
	 * @param tBaFile
	 * @param request
	 * @param multipartHttpServletRequest
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveFTPFileGz(TBaFile tBaFile, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
	    StringBuilder result = new StringBuilder("{");
	    User user = UserUtils.getUser();
	    tBaFile.preInsert();
	    //1、校验文件的大小
	    String name = request.getParameter("name");
	    String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
	    String isFilterEncryption = request.getParameter("isFilterEncryption");
	    long maxM = 100;// 默认附件大小为100，单位：M
	    if(null != size && StringUtils.isNotBlank(size)){
	        maxM = Long.parseLong(size);
	    }
	    MultipartFile file = multipartHttpServletRequest.getFile("file" + name);// 获得上传的附件
	    
	    // 文件加密的校验（仅限于PDF的校验）
        String fileName = file.getOriginalFilename(); // 获得原始文件名
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
            try{
            	new PdfReader(file.getInputStream());
            }catch (BadPasswordException e){ 
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (NoClassDefFoundError e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (Exception e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
            }
        }
	    
        if(null != file && !file.isEmpty()){ 
            // 文件大小处理
            long maxSize = maxM * 1024 * 1024;
            long fileSize = file.getSize();
            if(fileSize > maxSize){
            	result = contactJson(result, "error", "文件大小超过上限");
            	result.append("}");
                return result.toString();
            }
            
            // 2、进行文件的上传操作
            try {
                upload(file, tBaFile.getId() + fileType);
            } catch (Exception e) {
                logger.error("文件上传时出现异常",e);
                result = contactJson(result, "error", "文件上传时出现异常");
            	result.append("}");
                return result.toString();
            }
            tBaFile.setFileSize("" + file.getSize());          // 文件大小
            tBaFile.setFileType(fileType);                     // 文件类型
            tBaFile.setFileName(fileName.replace(" ", ""));    // 原文件名
            tBaFile.setFolderPath("/" + user.getId());    // 文件夹路径
        } 
        
        // 3、优化页面展示的信息
        long fileSize = file.getSize();
        String sizeLabel = showSizeLabel(fileSize + "");
        
        dao.insert(tBaFile);
        
        String path = Global.getConfig("FTP_PATH"); // FTP的根路径
        FTPClient ftp = new FTPClient(); 
        TBaFile tBaFileSmall = new TBaFile();
        try {
        	FTPUtil ftpUtil = new FTPUtil();
	    	ftp = ftpUtil.getFtpConnect(); // 获得FTP服务器的连接
	    	ftp.changeWorkingDirectory(path + "/" + user.getId());//跳转到FTP相应的路径
	    	tBaFileSmall.preInsert();
	    	tBaFileSmall.setFileType(tBaFile.getFileType());
	    	
	        OutputStream out = ftp.appendFileStream(tBaFileSmall.getId() + tBaFileSmall.getFileType()); // FTP存储的文件名称   
	    	
	        double minSize = 300 * 1024 ;
	        double tempFileSize = Float.parseFloat(tBaFile.getFileSize());
	        double proportion = Math.rint((minSize / tempFileSize) * 100000) / 100000.0;
	        if(Float.parseFloat(tBaFile.getFileSize()) <= minSize ){
	        	proportion = 1.0f;
	        }
	        
	        tBaFileSmall.setFileSize(Math.rint(Float.parseFloat(tBaFile.getFileSize()) * proportion) + "");
	        InputStream input = file.getInputStream();
	        Thumbnails.of(input).scale(1f).outputQuality(proportion).toOutputStream(out);
		    
			tBaFileSmall.setFileType(fileType);                     // 文件类型
			tBaFileSmall.setFileName(fileName.replace(" ", ""));    // 原文件名
			tBaFileSmall.setFolderPath("/" + user.getId());    // 文件夹路径
			dao.insert(tBaFileSmall);
			
			input.close();  
			out.close();
	        ftpUtil.logoutFtp(ftp);
			
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
        // 上传成功的文件ID
        result = contactJson(result, "fileId", tBaFile.getId());
    	result.append(",");
    	// 上传成功后的文件大小
    	result = contactJson(result, "filesize", sizeLabel);
    	result.append(",");
    	// 上传成功后的小文件
    	result = contactJson(result, "smallFileId", tBaFileSmall.getId());
    	result.append("}");
        return result.toString();
    }
	
    /**  
     * 保存主题图片到FTP
     * @param file 上传的文件 
     * @param filename 文件的名称
     * @throws Exception
     */
    public void uploadPic(MultipartFile file, String filename,String imgUrl) throws Exception{
    	String path = Global.getConfig("FTP_PATH");
        FTPUtil ftpUtil = new FTPUtil();
        FTPClient ftp = ftpUtil.getFtpConnect();
//        String userId = UserUtils.getUser().getId();
        boolean openRoot = ftp.changeWorkingDirectory(path);// 打开一级目录
        try{
	        if(openRoot == true){
	        	boolean openSecond = CreateDirecroty(imgUrl,ftp);
//	        	if(openSecond == false){
//	        		ftp.makeDirectory(userId);
//	        		ftp.changeWorkingDirectory(userId);
//	        	}
	        }else {
	        	logger.error("FTP服务器根目录打开失败！");
	        }
        }catch(Exception ex){
        	logger.error("创建二级文件夹失败！");
		}
			
        //2、上传文件
        InputStream input = file.getInputStream();        
        ftp.storeFile(filename, input);      
        input.close();  
        ftpUtil.logoutFtp(ftp);
    }


	//创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
	public boolean CreateDirecroty(String remote,FTPClient ftp) throws IOException {
		boolean success = true;
		String directory = remote + "/";
//        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		// 如果远程目录不存在，则递归创建远程服务器目录
		if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory),ftp)) {
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			String path = "";
			String paths = "";
			while (true) {

				String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
				path = path + "/" + subDirectory;
				if (!existFile(path,ftp)) {
					if (makeDirectory(subDirectory,ftp)) {
						changeWorkingDirectory(subDirectory,ftp);
					} else {
						logger.debug("创建目录[" + subDirectory + "]失败");
						changeWorkingDirectory(subDirectory,ftp);
					}
				} else {
					changeWorkingDirectory(subDirectory,ftp);
				}

				paths = paths + "/" + subDirectory;
				start = end + 1;
				end = directory.indexOf("/", start);
				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return success;
	}

	//判断ftp服务器文件是否存在
	public boolean existFile(String path,FTPClient ftp) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = ftp.listFiles(path);
		if (ftpFileArr.length > 0) {
			flag = true;
		}
		return flag;
	}


	//创建目录
	public boolean makeDirectory(String dir,FTPClient ftp) {
		boolean flag = true;
		try {
			flag = ftp.makeDirectory(dir);
			if (flag) {
				logger.debug("创建文件夹" + dir + " 成功！");

			} else {
				logger.debug("创建文件夹" + dir + " 失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	//改变目录路径
	public boolean changeWorkingDirectory(String directory,FTPClient ftp) {
		boolean flag = true;
		try {
			flag = ftp.changeWorkingDirectory(directory);
			if (flag) {
				logger.debug("进入文件夹" + directory + " 成功！");

			} else {
				logger.debug("进入文件夹" + directory + " 失败！");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return flag;
	}


	public void upload(MultipartFile file, String filename) throws Exception{
		String path = Global.getConfig("FTP_PATH");
		FTPUtil ftpUtil = new FTPUtil();
		FTPClient ftp = ftpUtil.getFtpConnect();
		String userId = UserUtils.getUser().getId();
		boolean openRoot = ftp.changeWorkingDirectory(path);// 打开一级目录
		try{
			if(openRoot == true){
				boolean openSecond = ftp.changeWorkingDirectory(userId);
				if(openSecond == false){
					ftp.makeDirectory(userId);
					ftp.changeWorkingDirectory(userId);
				}
			}else {
				logger.error("FTP服务器根目录打开失败！");
			}
		}catch(Exception ex){
			logger.error("创建二级文件夹失败！");
		}

		//2、上传文件
		InputStream input = file.getInputStream();
		ftp.storeFile(filename, input);
		input.close();
		ftpUtil.logoutFtp(ftp);
	}

	/**
	 * FTP服务器附件的下载
	 * @param request
	 * @param response
	 */
	public void downFTPFile(TBaFile tBaFile, HttpServletRequest request, HttpServletResponse response) {
	    try {
            downFTP(tBaFile, response, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    /**  
     * 下载FTP服务器上的文件
     * @param
     * @throws Exception  
     */    
    public void downFTP(TBaFile tBaFile, HttpServletResponse response, HttpServletRequest request) throws Exception{
    	String path = Global.getConfig("FTP_PATH");
        FTPUtil ftpUtil = new FTPUtil();
        FTPClient ftp = ftpUtil.getFtpConnect();
        //2、下载文件
        String folderPath = tBaFile.getFolderPath();
        boolean isChangeWork = true;
        if(tBaFile.getFolderPath() != null && StringUtils.isNotBlank(folderPath)){
        	ftp.changeWorkingDirectory(path + tBaFile.getFolderPath());  
        }else {
        	ftp.changeWorkingDirectory(path); 
        }
        if (!isChangeWork) {  
            throw new IOException("ftp 目录不存在");  
        } 
        response.reset();//清空response
        response.setCharacterEncoding("UTF-8"); 
        //名称两边的双引号不能省略 兼容火狐 文件名中的空格
        response.addHeader("Content-Disposition", "attachment;filename=\""
                + FileDownUtil.encodeFilename(request, tBaFile.getFileName()) + "\"");
        response.addHeader("Content-Length", "" + tBaFile.getFileSize());
        InputStream input = ftp.retrieveFileStream(tBaFile.getId() + tBaFile.getFileType());
        OutputStream out = response.getOutputStream();
        int buf = -1;  
        while ((buf = input.read()) != -1) {  
            out.write(buf);  
        }  
        out.flush();  
        input.close();  
        ftpUtil.logoutFtp(ftp);
    } 
    
    /**
     * 查询信息集合
     * @param request
     * @return
     */
	@Transactional(readOnly = false)
	public List<TBaFile> queryFile(HttpServletRequest request) {
	    String value = request.getParameter("value");
	    String[] fileIds = value.split(",");
	    List<String> list = new ArrayList<String>();
	    for(int i = 0; i < fileIds.length; i++){
	        list.add(fileIds[i]);
	    }
	    Map<String, List<String>> condition = new HashMap<String, List<String>>();
	    condition.put("fileIds", list);
	    List<TBaFile> fileList = new ArrayList<TBaFile>();
	    List<TBaFile> files = dao.queryFile(condition);
	    if(null != files){
	        for (TBaFile tBaFile : files) {
	        	String sizeLabel = showSizeLabel(tBaFile.getFileSize());
	            tBaFile.setFileSize(sizeLabel);
	            fileList.add(tBaFile);
	        }
	    }
	    return fileList;
	}
    
	//----------------------------------------------------FTP文件上传和下载END
    
	//----------------------------------------------------服务器的文件上传和下载START
	
	/**
	 * 保存文件到项目服务器
	 * @param tBaFile
	 * @param request
	 * @param multipartHttpServletRequest
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveLocationFile(TBaFile tBaFile, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
	    StringBuilder result = new StringBuilder("{");
	    tBaFile.preInsert();
	    //1、校验文件的大小
	    String name = request.getParameter("name");
	    String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
	    String isFilterEncryption = request.getParameter("isFilterEncryption");
	    long maxM = 100;// 默认附件大小为100，单位：M
	    if(null != size && StringUtils.isNotBlank(size)){
	        maxM = Long.parseLong(size);
	    }
	    MultipartFile file = multipartHttpServletRequest.getFile("file" + name);// 获得上传的附件
	    
	    // 文件加密的校验（仅限于PDF的校验）
        String fileName = file.getOriginalFilename(); // 获得原始文件名
        String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
	    if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
            try{
            	new PdfReader(file.getInputStream());
            }catch (BadPasswordException e){ 
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (NoClassDefFoundError e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
            }catch (Exception e){
            	return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
            }
        }
	    
	    if(null != file && !file.isEmpty()){ 
            // 文件大小处理
            long maxSize = maxM * 1024 * 1024;
            long fileSize = file.getSize();
            if(fileSize > maxSize){
            	result = contactJson(result, "error", "文件大小超过上限");
            	result.append("}");
                return result.toString();
            }
            
            // 2、进行文件的上传操作
            String path = Global.getConfig("DEFINE_ATTACHMENT") ;//上传路径
            User user = UserUtils.getUser();
            if(user != null && StringUtils.isNotBlank(user.getId())){
            	path = path + File.separator + user.getId();
            }
            // 注：该配置在config.properties文件里。
            //在windows系统配置下，双斜杠（例如：DEFINE_ATTACHMENT=D\:\\temp\\）。
            //linicx系统，单斜杠（例如：#DEFINE_ATTACHMENT=opt/attachment/define/）。
            try {
            	uploadLocation(file, tBaFile.getId() + fileType, path);
            } catch (Exception e) {
                logger.error("文件上传时出现异常",e);
                result = contactJson(result, "error", "文件上传时出现异常");
            	result.append("}");
                return result.toString();
            }
            tBaFile.setFileSize("" + file.getSize());          // 文件大小
            tBaFile.setFileType(fileType);                     // 文件类型
            tBaFile.setFileName(fileName.replace(" ", ""));    // 原文件名
            tBaFile.setFolderPath("/" + UserUtils.getUser().getId());    // 文件夹路径
        } 
        
        // 3、优化页面展示的信息
        long fileSize = file.getSize();
        String sizeLabel = showSizeLabel(fileSize + "");
        
        dao.insert(tBaFile);
        //上传成功的文件ID
        result = contactJson(result, "fileId", tBaFile.getId());
    	result.append(",");
    	//上传成功后的文件大小
    	result = contactJson(result, "filesize", sizeLabel);
    	result.append("}");
        return result.toString();
    }
	
	/**  
     * 上传文件
     * @param file 上传的文件
     * @throws Exception  
     */    
    public void uploadLocation(MultipartFile file, String filename, String path) throws Exception{  
    	if (!file.isEmpty()) {  
	        File fs = new File(path);
			if(!fs.exists()){
				fs.mkdir();
			}
	        if(!file.isEmpty()){
	            try {
	                FileOutputStream fos = new FileOutputStream(path + File.separator + filename);
	                InputStream in = file.getInputStream();
	                int b = 0;
	                while((b=in.read())!=-1){
	                    fos.write(b);
	                }
	                fos.close();
	                in.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
    	}
    }   
	
	//----------------------------------------------------服务器的文件上传和下载END
	
    //----------------------------------------------------FTP文件图片上传和下载START
    /**
     * 获取展示FTP服务器上的图片
     * @param
     * @throws Exception
     */
    public void showFTPImage(TBaFile tBaFile, HttpServletResponse response,
        HttpServletRequest request){
        // 1、连接到FTP服务器上
    	String path = Global.getConfig("FTP_PATH");
        FTPUtil ftpUtil = new FTPUtil();
        FTPClient ftp = ftpUtil.getFtpConnect();
        // 2、下载文件
        String folderPath = "";
        if(StringUtils.isNotBlank(tBaFile.getFolderPath())){
        	folderPath = tBaFile.getFolderPath();
        }
        boolean isChangeWork;
		try {
			isChangeWork = ftp.changeWorkingDirectory(path + folderPath);
			if (!isChangeWork) {
	            throw new IOException("ftp 目录不存在");
	        }
			// 清空response
	        response.reset();
	        response.setCharacterEncoding("utf-8");
	        response.setContentType("image/*"); // 设置返回的文件类型
	        if (tBaFile.getFileName() != null && !"".equals(tBaFile.getFileName())) {
	            response.addHeader("Content-Disposition", "attachment;filename=\""
	                + FileDownUtil.encodeFilename(request, tBaFile.getFileName())
	                + "\"");// 名称两边的双引号不能省略 兼容火狐 文件名中的空格
	            // response.addHeader("Content-Length", "" + tBaFile.getFileSize());
	            ServletOutputStream out = response.getOutputStream();
	            InputStream input = ftp.retrieveFileStream(tBaFile.getId()
	                + tBaFile.getFileType());
	            byte[] b = new byte[1024 * 1024];
	            int len;
	            while ((len = input.read(b)) > 0)
	                out.write(b, 0, len);
	            out.flush();
	            out.close();
	            input.close();
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ftpUtil.logoutFtp(ftp);
    }
    
    /**
     * 获取展示项目服务器上的图片
     * @param
     * @throws Exception
     */
    public HttpServletResponse showLocationImage(TBaFile tBaFile, String path, HttpServletResponse response,
        HttpServletRequest request){
    	try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
                if(!file.exists()) {  
                    response.setContentType("text/html;charset=GBK"); 
                    response.getWriter().print("指定文件不存在！");        
                    return response;           
            }
            // 清空response
            response.reset();
            response.setCharacterEncoding("UTF-8"); 
            // 设置response的Header
            // realname = URLEncoder.encode(realname, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=\""+ FileDownUtil.encodeFilename(request,tBaFile.getFileName())+"\"");//名称两边的双引号不能省略 兼容火狐 文件名中的空格
            response.addHeader("Content-Length", "" + file.length());
            ServletOutputStream out = response.getOutputStream(); 
            InputStream input = new FileInputStream(file); 
            byte[] b = new byte[1024 * 1024];
            int len;
            while ((len = input.read(b)) > 0)
                out.write(b, 0, len);
            out.flush();
            out.close();
            input.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
    //----------------------------------------------------FTP文件图片上传和下载END
    
	/**
	 * 静态附件的下载
	 * @param fileName 传递文件名
	 * @param request
	 * @param response
	 */
	public void downStaticFile(String fileName, HttpServletRequest request, HttpServletResponse response) {
	    if("drys.xlsx".equals(fileName)){
	        fileName = "导入演示.xlsx";
	    }

	    String path = request.getSession().getServletContext().getRealPath("")+"/static/files/"+fileName;
	    downloadLocationFile(fileName,path,request,response);
	}	
	
	/**
	 * 下载附件响应方法。
	 * @param realname
	 * @param path
	 * @param request
	 * @param response
	 * @return
	 */
	public HttpServletResponse downloadLocationFile(String realname,String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
                if(!file.exists()) {  
                    response.setContentType("text/html;charset=GBK"); 
                    response.getWriter().print("指定文件不存在！");        
                    return response;           
            }
            // 清空response
            response.reset();
            response.setCharacterEncoding("UTF-8"); 
            // 设置response的Header
            // realname = URLEncoder.encode(realname, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=\""+ FileDownUtil.encodeFilename(request,realname)+"\"");//名称两边的双引号不能省略 兼容火狐 文件名中的空格
            response.addHeader("Content-Length", "" + file.length());
            ServletOutputStream out = response.getOutputStream(); 
            InputStream inStream=new FileInputStream(file); 
            byte[] b = new byte[1024]; 
            int len; 
            while((len=inStream.read(b)) >0) 
            out.write(b,0,len); 
            out.flush();
            out.close();
            inStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
	
	//----------------------------------------------------封装的工具类START
	
	/**
	 * 拼接JSON格式字符
	 * @param sb 字符容器
	 * @param key 键
	 * @param value 值
	 * @return
	 */
    public StringBuilder contactJson(StringBuilder sb, String key, String value){
    	sb.append("\"");
    	sb.append(key);
    	sb.append("\"");
    	sb.append(":" );
    	sb.append("\"");
    	sb.append(value);
    	sb.append("\"");
    	return sb;
    }
    
    /**
     * 展现文件的大小
     * @param fileSize
     * @return
     */
    public String showSizeLabel(String fileSize){
    	String sizeLabel = "";
        String sizeLevel = "B";
        double tempFile = 0;
        if(StringUtils.isNotBlank(fileSize)){
        	tempFile = Double.parseDouble(fileSize);
        }
        if(tempFile > 1024){
            tempFile = tempFile / 1024.0;
            sizeLevel = "K";
        }
        if(tempFile > 1024){
            tempFile = tempFile / 1024;
            sizeLevel = "M";
        }
        DecimalFormat df = new DecimalFormat("######0.00"); 
        sizeLabel = df.format(tempFile);
    	return sizeLabel + sizeLevel;
    }
    
    
    /**
    * @Title:
    * @Description:保存主题图片到本地目录
    * @param  
    * @author yanmingyue
    * @return     返回类型
    * @Date  2019-04-1916:43
    * @throws 
    */
    public String savePic(TBaFile tBaFile, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest)
	{
		String result = "0";// 返回处理结果 0 正常  1 失败：文件大于设定大小 2 文件大小是0kb
		tBaFile.preInsert();

		//1、校验文件的大小
		String name = request.getParameter("name");
		String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
		String imgUrl = request.getParameter("imgUrl");// 获取文件设置的文件大小上限
		String isFilterEncryption = request.getParameter("isFilterEncryption");
		long maxM = 100;// 默认附件大小为100，单位：M
		if(null != size && StringUtils.isNotBlank(size)){
			maxM = Long.parseLong(size);
		}
		MultipartFile file = multipartHttpServletRequest.getFile("file" + name);// 获得上传的附件

		// 文件加密的校验（仅限于PDF的校验）
		String fileName = file.getOriginalFilename(); // 获得原始文件名
		String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
			try{
				new PdfReader(file.getInputStream());
			}catch (BadPasswordException e){
				return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
			}catch (NoClassDefFoundError e){
				return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
			}catch (Exception e){
				return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
			}
		}

		if(null != file && !file.isEmpty()){
			// 文件大小处理
			long maxSize = maxM * 1024 * 1024;
			long fileSize = file.getSize();
			if(fileSize > maxSize){
				result = "1";
				return result;
			}
			if(fileSize == 0){
				result = "2";
				return result;
			}
			imgUrl.replaceAll("/", File.separator);
			// 2、进行文件的上传操作
//			String path = request.getServletContext().getRealPath(imgUrl);;//上传路径
//			User user = UserUtils.getUser();
//			if(user != null && StringUtils.isNotBlank(user.getId())){
//				path = path + File.separator + user.getId();
//			}
			// 注：该配置在config.properties文件里。
			//在windows系统配置下，双斜杠（例如：DEFINE_ATTACHMENT=D\:\\temp\\）。
			//linicx系统，单斜杠（例如：#DEFINE_ATTACHMENT=opt/attachment/define/）。
//			try {
//				uploadLocation(file, name + fileType, path);
//			} catch (Exception e) {
//				logger.error("文件上传时出现异常",e);
//				result = "2";
//				return result;
//			}
			//2.1 把文件上传到项目本地目录
			String localPath = Global.getConfig("DEFINE_ATTACHMENT").replaceAll("/", File.separator)+imgUrl;//上传路径
			try {
				uploadLocation(file, name + fileType, localPath);
			} catch (Exception e) {
				logger.error("文件上传时出现异常",e);
				result = "2";
				return result;
			}

		}

		// 3、优化页面展示的信息
		return result;
	}
	
    /**
    * @Title:
    * @Description: 保存主题图片到ftp
    * @param  
    * @author yanmingyue
    * @return     返回类型
    * @Date  2019-04-1916:43
    * @throws 
    */
	public String saveFTPPic(TBaFile tBaFile, HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest) {
		String result = "0";// 返回处理结果 0 正常  1 失败：文件大于设定大小 2 文件大小是0kb
		tBaFile.preInsert();
		//1、校验文件的大小
		String name = request.getParameter("name");
		String size = request.getParameter("filesize");// 获取文件设置的文件大小上限
		String imgUrl = request.getParameter("imgUrl");// 获取文件设置的文件大小上限
		String isFilterEncryption = request.getParameter("isFilterEncryption");
		long maxM = 100;// 默认附件大小为100，单位：M
		if(null != size && StringUtils.isNotBlank(size)){
			maxM = Long.parseLong(size);
		}
		MultipartFile file = multipartHttpServletRequest.getFile("file" + name);// 获得上传的附件

		// 文件加密的校验（仅限于PDF的校验）
		String fileName = file.getOriginalFilename(); // 获得原始文件名
		String fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		if("yes".equals(isFilterEncryption) && ".pdf".equals(fileType)){
			try{
				new PdfReader(file.getInputStream());
			}catch (BadPasswordException e){
				return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
			}catch (NoClassDefFoundError e){
				return "{\"result\": \"error\"," + "\"messageError\":\"havePassword\"}";
			}catch (Exception e){
				return "{\"result\": \"error\"," + "\"messageError\":\"haveProblem\"}";
			}
		}

		if(null != file && !file.isEmpty()){
			// 文件大小处理
			long maxSize = maxM * 1024 * 1024;
			long fileSize = file.getSize();
			if(fileSize > maxSize){
				result = "1";
				return result;
			}
			if(fileSize == 0){
				result = "2";
				return result;
			}

			// 2、进行文件的上传操作
			try {
				uploadPic(file, name + fileType,imgUrl);
			} catch (Exception e) {
				logger.error("文件上传时出现异常",e);
				result = "2";
				return result;
			}
		}

		return result;
	}

	public String switchOnFtp(String imgPath,String id)
	{
		String result="0";
		List<String>imgUrls=new ArrayList<String>();
		//登录页LOGO
		String logo1="logo1";
		imgUrls.add(logo1);
		//首页背景左上角
		String backgroundimage1="backgroundimage1";
		imgUrls.add(backgroundimage1);
		//首页背景右上角
		String backgroundimage2="backgroundimage2";
		imgUrls.add(backgroundimage2);
		//首页背景左下角
		String backgroundimage3="backgroundimage3";
		imgUrls.add(backgroundimage3);
		//首页背景右下角
		String backgroundimage4="backgroundimage4";
		imgUrls.add(backgroundimage4);
		//登录页电话图标
		String icon5="icon5";
		imgUrls.add(icon5);
		//登录页QQ图标
		String icon6="icon6";
		imgUrls.add(icon6);
		//登录页二维码图标
//		String icon7="icon7";
//		imgUrls.add(icon7);

		//首页LOGO
		String logo="logo";
		imgUrls.add(logo);
		//首页抬头
		String titletop="titletop";
		imgUrls.add(titletop);

		for (String s:imgUrls){
			// 1、连接到FTP服务器上
			String path = Global.getConfig("FTP_PATH");
			FTPUtil ftpUtil = new FTPUtil();
			FTPClient ftp = ftpUtil.getFtpConnect();
			// 2、下载文件
			String folderPath = "";
			if(StringUtils.isNotBlank("/theme"+id)){
				folderPath = "/theme"+id;
			}
			boolean isChangeWork;
			try {
				isChangeWork = ftp.changeWorkingDirectory(path + folderPath);
				if (!isChangeWork) {
					throw new IOException("ftp 目录不存在");
				}
				if (s != null && !"".equals(s)) {
					InputStream input = ftp.retrieveFileStream(s
							+ ".png");
					MultipartFile multi = new MockMultipartFile(s, input);
					uploadLocation(multi,s+ ".png",imgPath);
				}

				result="1";

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="0";
			}
			ftpUtil.logoutFtp(ftp);
		}

		return result;

	}

	public String switchOnLocation(String imgPath,String id)
	{

     if(readfile(Global.getConfig("DEFINE_ATTACHMENT")+"theme"+id+File.separator,imgPath)){
          return "1";
	}else{
     	return "0";
	 }
	}


	/**
	 * 读取某个文件夹下的所有文件
	 */
	public  boolean readfile(String filepath,String path)
	{
		try {

			File file = new File(filepath);
			if (!file.isDirectory()) {
//				System.out.println("文件");
//				System.out.println("path=" + file.getPath());
//				System.out.println("absolutepath=" + file.getAbsolutePath());
//				System.out.println("name=" + file.getName());

			} else if (file.isDirectory()) {
//				System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + File.separator + filelist[i]);
					if (!readfile.isDirectory()) {
//						System.out.println("path=" + readfile.getPath());
//						System.out.println("absolutepath="
//								+ readfile.getAbsolutePath());
//						System.out.println("name=" + readfile.getName());
						FileInputStream fileInputStream = new FileInputStream(readfile);
						// 转 MultipartFile
						MultipartFile multi = new MockMultipartFile(readfile.getName(), fileInputStream);

						uploadLocation(multi,readfile.getName(),path);
//						uploadLocation(multi,readfile.getName(),localPath);

					} else if (readfile.isDirectory()) {
						readfile(filepath + "\\" + filelist[i], path);
					}
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
			return false;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//----------------------------------------------------封装的工具类END
}