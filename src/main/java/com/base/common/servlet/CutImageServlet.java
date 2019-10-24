package com.base.common.servlet;

import com.base.common.config.Global;
import com.base.common.utils.ImagesUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * 图片上传后进行，处理的裁剪类
 */
/*@WebServlet("/uploadarea")*/
@SuppressWarnings("serial")
public class CutImageServlet extends HttpServlet {
	private File tempDir;   //临时路径
	private File saveDir;   //保存路径
	private File cutDir;    //裁剪图片保存的路径

	/**
	 * servlet初始化事件
	 *
	 * @param config
	 * @throws ServletException
	 */
	public void init(ServletConfig config) throws ServletException {
		String uploadPath = config.getServletContext().getRealPath("/");
		StringBuffer sb = new StringBuffer(uploadPath);
		saveDir = new File(sb.append("\\upload").toString());
		tempDir = new File(sb.append("\\temp").toString());
//		cutDir = new File(uploadPath + "\\upload\\cut");
		cutDir = new File(Global.getConfig("DEFINE_ATTACHMENT"));//放到D:/upload目录下
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		if (!cutDir.exists()) {
			cutDir.mkdir();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int operate = request.getParameter("operate").isEmpty() ? 0 : Integer.valueOf(request.getParameter("operate"));

		//方法操作
		switch (operate) {
			case 1:             //上传操作
				uploadImg(request, response);
				break;
			case 2:             //截图操作
				cutImg(request, response);
				break;
		}
	}

	//上传图片
	private void uploadImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1 * 1024 * 1024);      //设置缓冲区大小
		factory.setRepository(tempDir);             //设置临时保存目录
		ServletFileUpload sfu = new ServletFileUpload(factory); //Servelt文件上传对象
		sfu.setFileSizeMax(200 * 1024 * 1024);  //200M 文件上传大小
		sfu.setHeaderEncoding("utf-8");
		List<FileItem> list = null;
		try {
			list = sfu.parseRequest(request);       //得到文件
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		int size = list.size();
		StringBuffer json = new StringBuffer("{\"success\":true,\"imgs\":[");      //输出到页面的json
		PrintWriter out = response.getWriter();
		for (int i = 0; i < size; i++) { //循环保存文件
			FileItem file = list.get(i);
			if (file.isFormField()) {   //如果是表单字段
				String name = file.getFieldName();  // 获得该字段名称
				String value = file.getString("utf-8"); //获得该字段值
			} else {
				String extName = file.getName().substring(      //得到扩展名
						file.getName().lastIndexOf("."));
				String fname = UUID.randomUUID() + extName;
				try {
					file.write(new File(saveDir, fname));  // 写入文件
					String path = request.getSession().getServletContext().getRealPath("/");
					if (i == size) {
						json.append(saveDir.toString() + "\\" + fname + "]}");    //回传上传图片路径
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		out.flush();
		out.print(json);
		out.close();
	}

	//裁剪图片
	private void cutImg(HttpServletRequest request, HttpServletResponse response)  {
		StringBuffer json = new StringBuffer("{\"success\":true,\"imgs\":");      //输出到页面的json
		response.setContentType("text/plain;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//得到坐标
		Integer x1 = Integer.parseInt(request.getParameter("x1"));
		Integer y1 = Integer.parseInt(request.getParameter("y1"));
		Integer x2 = Integer.parseInt(request.getParameter("x2"));
		Integer y2 = Integer.parseInt(request.getParameter("y2"));
		String basePath = request.getSession().getServletContext().getRealPath("/");    //项目物理路径
		String path = request.getParameter("path");             //原图片路径
		String extName = path.substring(path.lastIndexOf(".")); //文件扩展名
		int width = x2 - x1; //图像的宽度
		int height = y2 - y1;//图像的高度
		String cutFileName ="\\cut_" + UUID.randomUUID() + extName;
		ImagesUtils.cut(basePath + path,  cutDir.toString() + cutFileName, x1, y1, width, height);
		json.append("\"upload\\cut"+cutFileName+"\"]}");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.flush();
		out.print(json);
		out.close();

	}
}