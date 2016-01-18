import boto3
from botocore.exceptions import ClientError,NoCredentialsError
import sys

def getS3BucketNumber():

    try:
        s3 = boto3.resource('s3')
        buckets = []
    except NoCredentialsError:
        print "No AWS Credentials"
        sys.exit()

    try:
        bucket_num = len(list(s3.buckets.all()))
        print "Number of buckets: " + str(bucket_num)
        return bucket_num
    except ClientError as ex:
        print(ex)
        return 0

if __name__ == '__main__':
    getS3BucketNumber()
