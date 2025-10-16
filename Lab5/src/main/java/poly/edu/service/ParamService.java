package poly.edu.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null) ? value : defaultValue;
    }

    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        try {
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        try {
            return (value != null) ? Double.parseDouble(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value == null) return defaultValue;
        return Boolean.parseBoolean(value);
    }

    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value == null) return null;
        try {
            return new SimpleDateFormat(pattern).parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) return null;
        try {
            File dir = new File(request.getServletContext().getRealPath(path));
            if (!dir.exists()) dir.mkdirs();
            File saveFile = new File(dir, file.getOriginalFilename());
            file.transferTo(saveFile);
            return saveFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public File save1(MultipartFile file, String path) {
	    try {
	        if (!file.isEmpty()) {
	            // Lấy đường dẫn tuyệt đối tới thư mục static/images
	            String basePath = System.getProperty("user.dir") 
	                    + "/src/main/resources/static" + path;

	            File dir = new File(basePath);
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }

	            File savedFile = new File(dir, file.getOriginalFilename());
	            file.transferTo(savedFile);
	            return savedFile;
	        }
	        return null;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
