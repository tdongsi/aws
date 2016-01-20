package com.tdongsi.aws;

/**
 * In this Amazon S3 application, it will:
 * Read a CSV file from one bucket.
 * Transform into another file, e.g. JSON file.
 * Upload the file into another bucket.
 * 
 */
public class App 
{
	public static final String INPUT_BUCKET_NAME = "cdongsi-practice-input";
	public static final String OUTPUT_BUCKET_NAME = "cdongsi-practice-output";
	
	public static final String INPUT_FILE_KEY = "state-capitals.csv";
	public static final String OUTPUT_FILE_KEY = "state-captials.json";
	
    public static void main( String[] args )
    {
    	S3FileTransformer transformer = new S3FileTransformer();
    	transformer.transformFile(INPUT_BUCKET_NAME, INPUT_FILE_KEY, OUTPUT_BUCKET_NAME, OUTPUT_FILE_KEY);
    }
}
