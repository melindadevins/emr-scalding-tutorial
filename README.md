### Command to get the size of the bucket

`aws s3api list-objects --bucket YOUR_BUCKET_NAME --output json --query "[sum(Contents[].Size), length(Contents[])]" | awk 'NR!=2 {print $0;next} NR==2 {print $0/1024/1024/1024" GB"}'`

### Run job locally:
`hadoop jar target/scala-2.11/emr-scalding-tutorial-assembly-0.1.jar com.softwaremill.AgeCounterJob — hdfs — input “data/*” — output data-output`
### or
`hadoop jar target/scala-2.11/emr-scalding-tutorial-assembly-0.1.jar com.softwaremill.AgeCounterJob — local — input “data/hello.txt” — output data-output.txt`

Once your task definition is ready and you have tested it locally, you can start pushing your assembled jar to the S3 service.
 
### Copy the jar file to S3 location:

`aws s3 cp target/scala-2.11/emr-scalding-tutorial-assembly-0.1.jar s3://grajo001log/emr-scalding-tutorial-assembly-0.1.jar`

### Run the cluster with the task

aws emr create-cluster --name "Scalding test cluster" --ami-version 3.9.0 --use-default-roles --instance-type m1.medium --instance-count 3 --log-uri s3://grajo001out/2 --steps Type=CUSTOM_JAR,Name="EMR Tutorial",ActionOnFailure=TERMINATE_CLUSTER,Jar=s3://grajo001log/emr-scalding-tutorial-assembly-0.1.jar
,Args=["com.softwaremill.AgeCounterJob","--hdfs","--input","s3n://grajo001log/users/*","--output","s3n://grajo001log/output"] --auto-terminate


### Push task to EMR
aws emr add-steps --cluster-id=YOUR-CLUSTER_ID --steps=file://./task.json
