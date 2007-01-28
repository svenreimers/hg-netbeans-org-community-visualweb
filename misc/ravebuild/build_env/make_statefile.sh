#!/bin/sh
#
# This script generates a statefile for the Sun App Server (personal edition)
# based on existing standards.  Fucntionality has now been added to extract
# the wizard IDs from the installers themselves

ErrorExit()
{
	echo ""
	echo ${p}: ERROR: $*
	Usage
	exit 1
}

GetOptions()
{
	# Set defaults

	#JAVA_HOME is expected to be set in environment

	PLATFORM="$FORTE_PORT"
	if [ "${PLATFORM}" = "cygwin" ]; then
	    	PLATFORM="nt"
	fi
	INSTALL_DIR="$SRCROOT/SUNWappserver"
	STATEFILE="sunappserver_statefile"
	STOREAUTH="FALSE"
	ENCRYPT="TRUE"
	PASSWORD="npoacvubuubealduopqzvanqzva"
	macarg=""

	# Parse arguments

	while [ $# -gt 0 -a "$1" != "" ]
	do
		arg=$1; shift

		case $arg in
		-platform)
			if [ $# -eq 0 ]; then
			ErrorExit "-platform requires an argument (solsparc, nt, linux)"
			fi
			PLATFORM="$1"
			shift
			;;
		-wizard_id)
			if [ $# -eq 0 ]; then
			ErrorExit "-wizard_id requires an argument"
			fi
			WIZARD_ID="$1"
			shift
			;;
		-java_home)
			if [ $# -eq 0 ]; then
			ErrorExit "-java_home requires an argument"
			fi
			JDK_LOC="$1"
			shift
			;;
		-install_dir)
			if [ $# -eq 0 ]; then
			ErrorExit "-install_dir requires an argument"
			fi
			INSTALL_DIR="$1"
			shift
			;;
		-password)
			if [ $# -eq 0 ]; then
			ErrorExit "-password requires an argument"
			fi
			PASSWORD="$1"
			shift
			;;
		-statefile)
			if [ $# -eq 0 ]; then
			ErrorExit "-statefile requires an argument"
			fi
			STATEFILE="$1"
			shift
			;;
		-storeauth)
			STOREAUTH=TRUE
			;;
		-no_encrypt)
			ENCRYPT=FALSE
			;;
		*)
			ErrorExit "Unknown option: $arg"
			;;
		esac
	done

}

GetWizardID()
{
	# This function extracts the WizardID from the installer
	# If CACHEROOT is set it will use that location, but if not
    # it will use the APP_SERVER_LOCATION from create_installers

	if [ "${CACHEROOT}x" != "x" ]; then
		if [ "${LOCALE_EXT}" = "ml" ]; then	
			APP_SERVER_LOCATION=$CACHEROOT/SunAppServerML
		else
			APP_SERVER_LOCATION=$CACHEROOT/SunAppServer
		fi

		APP_SERVER_SPARC_NAME=`cd $APP_SERVER_LOCATION; echo *solaris-sparc*`
		APP_SERVER_X86_NAME=`cd $APP_SERVER_LOCATION; echo *solaris-i586*`
		APP_SERVER_WINDOWS_NAME=`cd $APP_SERVER_LOCATION; echo *windows*`
		APP_SERVER_LINUX_NAME=`cd $APP_SERVER_LOCATION; echo *linux*`
		APP_SERVER_MAC_NAME=`cd $APP_SERVER_LOCATION; echo *mac*`
	fi

	case $PLATFORM in 
		solsparc)
			ASINSTALLER=`echo $APP_SERVER_LOCATION/$APP_SERVER_SPARC_NAME`
			;;
		solx86)
			ASINSTALLER=`echo $APP_SERVER_LOCATION/$APP_SERVER_X86_NAME`
			;;
		nt)
			ASINSTALLER=`echo $APP_SERVER_LOCATION/$APP_SERVER_WINDOWS_NAME`
			;;
		linux)
			ASINSTALLER=`echo $APP_SERVER_LOCATION/$APP_SERVER_LINUX_NAME`
			;;
		macosx)
			macarg="-Dwizard.NoNativeAbort=true"
			ASINSTALLER=`echo $APP_SERVER_LOCATION/$APP_SERVER_MAC_NAME`
			;;
	esac

	if [ "${ASINSTALLER}x" = "x" ]; then
		ErrorExit "Unable to find PE installer from $APP_SERVER_LOCATION"
	fi

	CURRDIR=`pwd`
	TMPDIR=/tmp/$USER/${p}.$$
	mkdir -p $TMPDIR
	cd $TMPDIR
	unzip -q $ASINSTALLER appserv.class > /dev/null 2>&1
	# unzip -q $ASINSTALLER appserv.class
	
	WIZARD_ID=`$JAVA_HOME/bin/java $macarg -cp . appserv -id`
	cd $CURRDIR
	rm -rf $TMPDIR
}

Usage()
{

  cat <<EOF

Usage: sh $p

Options:  -wizard_id <wizard_id> - Sets the Wizard ID to the specified value
          -platform <platform>   - Sets the desired platform statefile (solsparc, nt, linux)
          -java_home <java_home> - Sets the JDK_LOCATION to the specified location
          -install_dir <dir>     - Sets the location to install the server
          -statefile <file>      - Sets the filename to use for the statefile
          -password <pass>       - Sets the encrypted ASADMIN password
          -storeauth             - Sets STORE_AUTH_ADMIN to true (defaults to FALSE)
          -no_encrypt            - Sets ADMIN_PASSWORD_ENCRYPTED to false (defaults to TRUE)

Description:

This script will generate a statefile for use with automated
installations of the Sun application server.  The hard-coded
default values are expected to change relatively frequently
as the version of the app server changes.

If using the default values, this script assumes that SRCROOT
is set.  If you override with the -install_dir argument,
you do not need to set SRCROOT.

EOF
}


p=`basename $0`

GetOptions "$@"

# If the wizard_id isn't passed on the ocmmand line, go figure it out
if [ "${WIZARD_ID}x" = "x" ]; then
	GetWizardID 
fi

cat << EOF > $STATEFILE
#
# Wizard Statefile generatd by ${p}
#              
# Wizard Statefile section for Sun Java System Application Server 
#
[STATE_BEGIN Sun Java System Application Server ${WIZARD_ID}]
defaultInstallDirectory = $INSTALL_DIR
currentInstallDirectory = $INSTALL_DIR
JDK_LOCATION = ${JDK_LOC}
INST_ASADMIN_USERNAME = admin
INST_ASADMIN_PASSWORD = ${PASSWORD}
INST_ASADMIN_PORT = 14848
INST_ASWEB_PORT = 18080
INST_HTTPS_PORT = 11043
STORE_ADMIN_AUTH = ${STOREAUTH}
ADMIN_PASSWORD_ENCRYPTED = ${ENCRYPT}
CREATE_SAMPLES_DOMAIN = FALSE
CREATE_DESKTOP_SHORTCUT = FALSE
[STATE_DONE Sun Java System Application Server ${WIZARD_ID}]
EOF
