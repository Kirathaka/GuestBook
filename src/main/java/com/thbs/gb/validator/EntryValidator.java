package com.thbs.gb.validator;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.thbs.gb.model.Entry;
import com.thbs.gb.utils.AppConstants;

@Component
public class EntryValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Entry.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {

		Boolean isTextNull = true;

		if (o instanceof Entry) {
			Entry entry = (Entry) o;

			if ((entry.getTextMessage().length() != 0)
					&& (entry.getTextMessage().length() < 2 || entry.getTextMessage().length() > 50)) {

				errors.reject("Size.entryForm.entry.textMessage");
			}

			if (entry.getTextMessage().length() > 0) {
				isTextNull = false;
			}

		} else if (o instanceof MultipartFile) {

			MultipartFile multipartFile = (MultipartFile) o;

			if (multipartFile.getSize() == 0 && isTextNull) {

				errors.reject("missing.file");

			} else if (multipartFile.getSize() != 0) {
				if (!(multipartFile.getOriginalFilename().endsWith(AppConstants.PNG_FILE_FORMAT)
						|| multipartFile.getOriginalFilename().endsWith(AppConstants.JPEG_FILE_FORMAT)
						|| multipartFile.getOriginalFilename().endsWith(AppConstants.JPG_FILE_FORMAT))) {
					errors.reject("invalid.file");
				} else {
					
					if (multipartFile.getSize() > AppConstants.MAX_FILE_SIZE_IN_BYTES) {
						errors.reject("invalid.file.size");
					}

					try {
						BufferedImage bimg = ImageIO.read(multipartFile.getInputStream());
						Integer width = bimg.getWidth();
						Integer height = bimg.getHeight();

						if (width > AppConstants.FILE_DIMENSIONS_HEIGHT
								|| height > AppConstants.FILE_DIMENSIONS_WIDTH) {
							errors.reject("invalid.file.dimensions");
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}

}
