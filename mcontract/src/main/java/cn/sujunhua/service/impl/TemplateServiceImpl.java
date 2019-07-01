package cn.sujunhua.service.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.sujunhua.dao.TemplateDao;
import cn.sujunhua.pojo.Template;
import cn.sujunhua.service.TemplateService;

@Service
@Scope("prototype")
public class TemplateServiceImpl implements TemplateService{
	// tomcat管理的文件夹    路径读取设为/images
	private final String TOMCATFILE = "/images/";
	@Autowired
	private TemplateDao templateDao;

	@Override
	public List<Template> list() {
		return templateDao.list();
	}

	@Override
	public String add(MultipartFile template_file, HttpServletRequest request) {
		// 文件的原始名称
		String originalFilename = "";
		// 新的文件名
		String newfileName = "";
		// 判断所上传文件是否存在
		if (!template_file.isEmpty() && template_file.getSize() > 0) {
			// 获取上传文件的原始名称
			originalFilename = template_file.getOriginalFilename();
			// 获取后缀名
			String extension = FilenameUtils.getExtension(originalFilename);
			// UUID创建随机String 用于作为新的文件名
			String string = UUID.randomUUID().toString();
			// 新的文件名
			newfileName = string.replaceAll("-", "") + "." + extension;
			// 设置上传文件的保存地址目录
			String dirPath = request.getServletContext().getRealPath(TOMCATFILE);
			File filePath = new File(dirPath);
			// 如果目录不存在就创建目录
			if (!filePath.exists())
				filePath.mkdirs();
			try {
				template_file.transferTo(new File(dirPath + newfileName));
			} catch (Exception e) {
				e.printStackTrace();
				return "no";
			}
		}
		Template template = new Template();
		template.setTemplate_file(TOMCATFILE+newfileName);
		template.setTemplate_filename(originalFilename);
		Integer row = templateDao.add(template);
		if (row > 0) {
			return "yes";
		} else
			return "no";
	}

	@Override
	public String delete(Integer template_id,HttpServletRequest request) {
		Template template = templateDao.findByid(template_id);
		File file = new File(request.getServletContext().getRealPath(TOMCATFILE), 
				template.getTemplate_file());
		file.delete();
		Integer row = templateDao.delete(template_id);
		if (row > 0) {
			return "yes";
		} else
			return "no";
	}

	@Override
	public Template findByid(Integer template_id) {
		return templateDao.findByid(template_id);
	}
}
