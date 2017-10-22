#!/usr/bin/env bash

S3_DESTINATION_FOLDER="grajo001log/users"
SITES_FILE=sites.txt

IFS=$'\n'
for site in `cat $SITES_FILE`
do
    echo "$site"
    wget https://archive.org/download/stackexchange/$site.7z
    7z x $site.7z -otemp
    aws s3 cp temp/Users.xml s3://$S3_DESTINATION_FOLDER/$site.xml
    rm $site.7z
    rm -Rf temp
done

echo 'Finished!'
exit 0
