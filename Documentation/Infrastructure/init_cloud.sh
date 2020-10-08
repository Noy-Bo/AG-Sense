#!/bin/bash

echo UPDATING YUM PACKAGES
sudo yum update

# DOWNLOAD FAIL2BAN
# https://www.hostinger.com/tutorials/secure-vps-fail2ban-centos
echo DOWNLOADING FAIL2BAN

sudo yum install epel-release
sudo yum install fail2ban
sudo systemctl enable fail2ban
sudo systemctl start fail2ban
sudo cp /etc/fail2ban/jail.conf /etc/fail2ban/jail.local


#sudo vim /etc/fail2ban/jail.local  -- use this to configure 
#sudo systemctl restart fail2ban


# DOWNLOAD POSTGRESQL
echo DOWNLOADING POSTGRES...
sudo yum install postgresql-server postgresql-contrib
sudo postgresql-setup initdb
sudo systemctl start postgresql
sudo systemctl enable postgresql

echo do you want to config postgresql connection?'('y/n')'
read varname

if [ $varname = "y" ];then
	echo do you want to change the default postgrespath?'('y/n')'
	read varname1
	if [ $varname1 = "y" ];then
		echo ENTER THE NEW PATH:
		read varname1
		vim $varname1/data/postgresql.conf
		vim $varname1/data/pg_hba.conf
	else
		vim /var/lib/pgsql/data/postgresql.conf
		vim /var/lib/pgsql/data/pg_hba.conf
	fi
fi

#configure the postgres user
#set the password
#sudo passwd postgres

# to configure the PSQL
# https://www.hostinger.com/tutorials/how-to-install-postgresql-on-centos-7/ 

#INSTALL JAVA
echo installing JAVA...
sudo yum -y install curl
curl -O https://download.oracle.com/otn-pub/java/jdk/14.0.2+12/205943a0976c4ed48cb16f1043c5c647/jdk-14.0.2_linux-x64_bin.tar.gz?xd_co_f=c551c681-903c-4182-ae47-3ee46aabe8ae

tar xvf openjdk-14_linux-x64_bin.tar.gz
sudo mv jdk-14 /opt/

sudo tee /etc/profile.d/jdk14.sh <<EOF
export JAVA_HOME=/opt/jdk-14
export PATH=\$PATH:\$JAVA_HOME/bin
EOF

source /etc/profile.d/jdk14.sh

#confirm that its installed
echo $JAVA_HOME
java -version

#DOWNLOAD TOMCAT 9
#https://www.linuxtechi.com/install-apache-tomcat9-centos7-rhel7-ubuntu16-04/
echo DOWNLOADING TOMCAT...
wget http://downloads.apache.org/tomcat/tomcat-9/v9.0.37/bin/apache-tomcat-9.0.37.tar.gz
tar -zxpvf apache-tomcat-9.0.37.tar.gz -C /opt/
cd /opt/
mv apache-tomcat-9.0.37.tar.gz tomcat
echo "export CATALINA_HOME='/opt/tomcat/'" >> ~/.bashrc
source ~/.bashrc

cd /opt/tomcat/bin/
./startup.sh

# TO OPEN PORTS IN FIRE WALL USE:
#firewall-cmd --permanent --zone=public --add-port=8080/tcp
#firewall-cmd --permanent --zone=public --add-port=5432/tcp
#sudo firewall-cmd –add-service=postgresql –permanent
#firewall-cmd --reload
#lsof -i -P |grep http