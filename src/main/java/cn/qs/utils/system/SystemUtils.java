package cn.qs.utils.system;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.qs.bean.user.User;
import cn.qs.utils.UUIDUtils;
import cn.qs.utils.file.PropertiesFileUtils;

public class SystemUtils {
	private SystemUtils() {
	}

	public static String getProductName() {
		return StringUtils.defaultIfBlank(PropertiesFileUtils.getPropertyValue("settings.properties", "productName"),
				"管理网");
	}

	public static User getLoginUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		return user;
	}

	public static User getLoginUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return user;
	}

	public static String getLoginUsername(HttpServletRequest request) {
		return getLoginUser(request).getUsername();
	}

	public static String getLoginUsername() {
		return getLoginUser().getUsername();
	}

	public static File getTmpFile() {
		// 获取到当前系统的临时工作目录
		String tempDirectoryPath = FileUtils.getTempDirectoryPath();
		String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		String tmpFileDir = tempDirectoryPath + date;
		FileUtils.deleteQuietly(new File(tmpFileDir));

		// 创建目录(以日期格式命名)
		File file2 = new File(tmpFileDir);
		file2.mkdir();

		// 创建临时文件,UUID产生名称
		String fileName = UUIDUtils.getUUID2();
		String tmpFileName = (tmpFileDir + "/" + fileName).replace("\\", "/");
		File file = new File(tmpFileName);
		try {
			file.createNewFile();
		} catch (IOException ignore) {
			// ignore
		}

		return file;
	}
}
