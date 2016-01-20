package com.tdongsi.aws;

import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

public class S3FileTransformer {

	private AmazonS3Client s3Client;

	public S3FileTransformer() {	
		init();
	}

	private void init() {
		AWSCredentials credentials = null;
		try {
			credentials = new ProfileCredentialsProvider("default")
					.getCredentials();
		} catch (Exception e) {
			throw new AmazonClientException( "Cannot load the credentials from "
					+ "the credential profiles file. ", e);
		}
		
		s3Client = new AmazonS3Client(credentials);
		// Use default region.
		s3Client.withRegion(Regions.DEFAULT_REGION);
	}
	
	public void transformFile(String inputBucket, String inputFilekey,
			String outputBucket, String outputFilekey) {
		
		try {
			checkAndCreateBucket(outputBucket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void checkAndCreateBucket(String outputBucket) throws Exception {
		if ( !s3Client.doesBucketExist(outputBucket) ) {
			System.out.println("Creating bucket " + outputBucket);
			s3Client.createBucket(outputBucket);
		} else {
			verifyBucketUsable(outputBucket);
		}
		
	}

	private void verifyBucketUsable(String outputBucket) throws Exception {
		// Verify if it is accessible
		boolean isOk = false;
		List<Bucket> buckets = s3Client.listBuckets();
		for (Bucket bucket : buckets) {
			if (bucket.getName().equals(outputBucket)) {
				isOk = true;
				break;
			}
		}
		if (!isOk) {
			throw new Exception("Output bucket name is owned by another account");
		}
	}
}
