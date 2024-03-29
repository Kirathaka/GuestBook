package com.thbs.gb.utils;

public class AppConstants {

	public static final String FILE_SEPERATOR = "_";
	public static final String DOWNLOAD_PATH = "/downloadFile/";
	public static final String DOWNLOAD_URI = "/downloadFile/{fileName:.+}";
	public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
	public static final String FILE_DOWNLOAD_HTTP_HEADER = "attachment; filename=\"%s\"";
	public static final String FILE_PROPERTIES_PREFIX = "file";
	public static final String FILE_STORAGE_EXCEPTION_PATH_NOT_FOUND = "Could not create the directory to store the uploaded files";
	public static final String INVALID_FILE_PATH_NAME = "Sorry! Filename contains invalid path sequence";
	public static final String FILE_NOT_FOUND = "File not found ";
	public static final String FILE_STORAGE_EXCEPTION = "Could not store file %s !! Please try again!";
	public static final CharSequence INVALID_FILE_DELIMITER = "..";
	public static final String TEMP_DIR = "./uploads/tmp/";
	public static final int FILE_DIMENSIONS_HEIGHT = 300;
	public static final int FILE_DIMENSIONS_WIDTH = 300;
	public static final int MAX_FILE_SIZE_IN_BYTES = 314572800;
	public static final String INVALID_FILE_DIMENSIONS = "Invalid file dimensions. File dimension should not be more than 300 X 300";
	public static final String INVALID_FILE_FORMAT = "Only PNG, JPEG and JPG images are allowed";
	public static final String PNG_FILE_FORMAT = ".png";
	public static final String JPEG_FILE_FORMAT = ".jpeg";
	public static final String JPG_FILE_FORMAT = ".jpg";
	public static final String SUCCESS_MESSAGE = "Your Feedback has been sent Successfully.";
	public static final String MULTIPLE_ENTRY_ERROR_MESSAGE = "You have already submitted your Feedback. Please wait until the administrator deletes the previous one.";
	
}
