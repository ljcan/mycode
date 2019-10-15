package cn.shinelon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.shinelon.vo.FileInfo;

@RestController
@RequestMapping("/file_upload")
public class FileController {
	String realPath="E:/STS/shinelon_demo/src/main/java/cn/shinelon/vo";
	//文件上传
	@PostMapping
	@ResponseBody
	public FileInfo file(MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println();
		File RealFile=new File(realPath,new Date().getTime()+".txt");
		file.transferTo(RealFile);
		return new FileInfo(RealFile.getAbsolutePath());
	}
	//文件下载
	@GetMapping("/{id}")
	public void downLoad(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) 
			throws FileNotFoundException, IOException {
		try(
				FileInputStream inputStream=new FileInputStream(new File(realPath,id+".txt"));
				OutputStream outputStream=response.getOutputStream();
				){		//jdk1.7新特性。IO放在try语句的括号中就不用在finally中关闭。它会自动关闭 
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
		
	}
}
