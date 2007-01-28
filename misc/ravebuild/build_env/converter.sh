#!/bin/sh

if [ "$#" -lt 4 ]; then
        echo "\nUsage";
	echo "-s : source dir including file name to pick up the windows PE installer"
        echo "-d : destination dir to put the converted windows PE installer"
	echo "\n"
        exit 0;
fi

for i in $* ; do
  case $i in
    -s)
        ORIGINAL_DISTRIBUTION=$2
        shift 2
        ;;
    -d)
    	NEW_DISTRIBUTION_DIR=$2
    	shift 2
    	;;
    esac
done

if [ ! -f ${ORIGINAL_DISTRIBUTION} ]  ; then
	echo "\nSource File ${ORIGINAL_DISTRIBUTION} does not exists\n"
        exit 9
fi

if [ ! -d ${NEW_DISTRIBUTION_DIR} ]; then
	echo "\nDestination Dir ${NEW_DISTRIBUTION_DIR} does not exists\n"
        exit 9
fi

FILE_NAME=`basename ${ORIGINAL_DISTRIBUTION}`
NEW_DISTRIBUTION=${NEW_DISTRIBUTION_DIR}/${FILE_NAME}

mkdir tmp
cd tmp

#unzip the original distribution file, disregard warning 

/usr/bin/unzip $ORIGINAL_DISTRIBUTION

#create a new zip

/usr/bin/zip -r newzip.zip  appserv.class setup.exe setup.dos.exe 3RD-PARTY-LICENSE.txt LICENSE.txt package

#append new zip to setup.dos.exe

cat newzip.zip >> setup.dos.exe

#rename setup.dos.exe into new distribution file

mv setup.dos.exe $NEW_DISTRIBUTION
chmod 755 $NEW_DISTRIBUTION

#cleanup of intermediate files

rm -rf newzip.zip package appserv.class setup.exe 3RD-PARTY-LICENSE.txt LICENSE.txt

cd ../
rm -rf tmp

