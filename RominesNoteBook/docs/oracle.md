# oracle

trunc(date:DATE,fmt:VARCHAR)  
fmt:  

```
WW: 以1月1日开始算起的周
IW: 星期一
W: 以本月1号算起的周
```

---

创建表空间和用户

```
create tablespace tbs_cds
datafile '/u01/app/oracle/tablespaces/tbs_cds.dbf'
size 12m autoextend on next 12m maxsize unlimited ;

create temporary tablespace temp_tbs_cds
tempfile '/u01/app/oracle/tablespaces/temp_tbs_cds.dbf'
size 12m autoextend on next 12m maxsize unlimited ;

create user cds identified by qweruiop
default tablespace tbs_cds
temporary tablespace temp_tbs_cds
profile DEFAULT;

grant dba,connect,resource to cds;
```

----------

ID 自增长  

```
-- 先创建一个序列
create sequence TEST_S
start with 1
maxvalue 999999999
nocycle;

-- 将这个序列绑定到要插入表的 trigger
create or replace trigger TEST_T_ID_TRIGGER
    before insert on TEST_T for each row when (new.ID is null)
    declare
        m number;
    begin
        select MAX(ID) into m from TEST_T;
        loop
            select TEST_S.nextval into :new.ID from dual;
            exit when m is null or :new.ID > m ;
        end loop;
    end;
```

---

字符串分割

```
create or replace type str_split_rtn is object
(
    item varchar2(100)
);
create or replace type str_split_rtn_tb is table of str_split_rtn;
create or replace function str_split(str in varchar2,separator in varchar2)
return str_split_rtn_tb pipelined
as
    item str_split_rtn;
begin
for i in (SELECT REGEXP_SUBSTR (str, '[^'||separator||']+', 1,rownum) it from dual
connect by rownum<=LENGTH (str) - LENGTH (regexp_replace(str, separator, ''))+1) loop
        item := str_split_rtn(i.it);
        pipe row(item);
    end loop;
return;
end;
```

---

- Database Readme
    - [Section 1](oracle/8.md), "Nomenclature Changes`术语的变化`"
    - [Section 2](oracle/9.md), "Compatibility, Upgrading, Downgrading, and Installation`兼容性，升级，降级和安装`"
    - [Section 3](oracle/10.md), "Features Not Available or Restricted in This Release`在此版本中不可用或受限制的特性`"
    - [Section 4](oracle/11.md), "Default Behavior Changes`默认的行为变化`"
    - [Section 5](oracle/12.md), "Automatic Storage Management`自动存储管理ASM`"
    - [Section 6](oracle/13.md), "Oracle Enterprise Manager Database Control"
    - [Section 7](oracle/14.md), "Database Security"
    - [Section 8](oracle/15.md), "Encryption and Integrity`加密和完整性`"
    - [Section 9](oracle/16.md), "Java and Web Services"
    - [Section 10](oracle/17.md), "Media Management Software`媒体管理软件`"
    - [Section 11](oracle/18.md), "Oracle Application Express"
    - [Section 12](oracle/19.md), "Oracle Client Applications"
    - [Section 13](oracle/20.md), "Oracle Configuration Manager"
    - [Section 14](oracle/21.md), "Oracle Data Mining`数据挖掘`"
    - [Section 15](oracle/22.md), "Oracle Internet Directory`互联网目录`"
    - [Section 16](oracle/23.md), "Oracle Multimedia"
    - [Section 17](oracle/24.md), "Oracle Net Services"
    - [Section 18](oracle/25.md), "Oracle Real Application Clusters`真实应用程序集群`"
    - [Section 19](oracle/26.md), "Oracle Grid Infrastructure for a Cluster`用于集群的网格基础设施`"
    - [Section 20](oracle/27.md), "Oracle Real Application Testing"
    - [Section 21](oracle/28.md), "Oracle ODBC Driver"
    - [Section 22](oracle/29.md), "Oracle OLAP"
    - [Section 23](oracle/30.md), "Oracle Spatial`空间`"
    - [Section 24](oracle/31.md), "Oracle SQL Developer"
    - [Section 25](oracle/32.md), "Oracle Text"
    - [Section 26](oracle/33.md), "Oracle Ultra Search"
    - [Section 27](oracle/34.md), "Oracle Warehouse Builder`OWB`"
    - [Section 28](oracle/35.md), "Oracle Workflow"
    - [Section 29](oracle/36.md), "Oracle XML DB"
    - [Section 30](oracle/37.md), "PL/SQL"
    - Section 31, "Pro*C"
    - Section 32, "Pro*COBOL"
    - Section 33, "SQLJ"
    - Section 34, "SQL*Plus"
    - Section 35, "Summary Management`摘要管理`"
    - Section 36, "Oracle Streams"
    - Section 37, "Documentation Addendum`附录`"
    - Section 38, "Open Bugs"
    - Section 39, "Documentation Accessibility"

---

- Oracle® Database
    - 1 Certification Information`认证信息`
    - 2 Unsupported Products  
    see ["Features Not Available or Restricted in This Release"](oracle/9.md)  
    - 3 Preinstallation Requirements`安装需求`
    - 4 Installation, Configuration, and Upgrade Issues  
        - Latest Upgrade Information
        - Oracle Automatic Storage Management Cluster File System and Oracle Automatic Storage Management Dynamic Volume Manager Support
        - Connection Load Balancing Issue`连接负载均衡`  
        Connect to a database using the following configuration, specifying `LOAD_BALANCE=on`, in the `tnsnames.ora` file:  

                ORCL =
                (DESCRIPTION =
                    (LOAD_BALANCE=on)
                    (ADDRESS = (PROTOCOL = TCP)(HOST = stscan1)(PORT = 1521))
                    (CONNECT_DATA =
                    (SERVER = DEDICATED)
                    (SERVICE_NAME = srv.world)
                    )
                )

        - Oracle Real Application Clusters Upgrade Error When Upgrading from Release 10.1.0.5 to 11.2
        - Cluster Verification Utility Issue During Oracle Clusterware Upgrade
        - RPM Errors with Oracle VM
        - C++ XDK Demos and Pro*C++ Static Demos Linking Error
        - Oracle RAC Database Installation Error on SUSE Linux Enterprise Server 11
        - Error When Installing Oracle RAC Database on Oracle Enterprise Linux 5.0 Update 3
        - Oracle Grid Infrastructure for a Standalone Server Installation Error on SUSE Linux Enterprise Server 10
        - Oracle Database Reupgrade Issue on Linux x86-64
        -
    - 5 Other Known Issues
    - 6 Documentation Corrections and Additions
    - 7 Documentation Accessibility

---

- Oracle® Application Express
    - 1 Configuration Requirements
    - 2 Checking for the Most Current Release
    - 3 New Features
    - 4 Components and Features Not Supported
    - 5 Open Bugs and Known Issues
    - 6 Documentation Accessibility

---

- Oracle® Warehouse Builder
    - Documentation Accessibility
    - Related Publications
    - Requirements
    - Known Issues and Limitations
    - Documentation Addendum
    - Resolved Issues

---

- Database Quick Installation Guide
    - 1 Reviewing Information About This Guide 
    For more information about installing Oracle Database, including information about the tasks not described in this guide, refer to one of the following guides:  
        - If you want to install the software on a single system, then refer to Oracle Database Installation Guide for Linux.  
        - If you want to install Oracle grid infrastructure for a standalone server, then refer to the "Oracle Grid Infrastructure" chapter in Oracle Database Installation Guide for Linux.  
        - If you want to perform a Oracle Real Application Clusters installation, then refer to Oracle Grid Infrastructure Installation Guide for Linux and Oracle Real Application Clusters Installation Guide for Linux and UNIX. These guides describe how to install Oracle Clusterware and Oracle Real Application Clusters. Oracle clusterware is a prerequisite for Oracle Real Application Clusters installations.  
    - 2 Logging In to the System as root  
    ubuntu 可以使用 `su`
    - 3 Checking the Hardware Requirements  
    At least 1 GB of RAM  
    检查系统架构 `uname -m`
    硬盘 /tmp 1GB `df -h /tmp`  
    - 4 Checking the Software Requirements `dpkg -l`  
        - binutils-2.17.50.0.6  `ld -v`  
        - compat-libstdc++-33-3.2.3  
        - compat-libstdc++-33-3.2.3 (32 bit)  
        - elfutils-libelf-0.125  
        - elfutils-libelf-devel-0.125  
        - gcc-4.1.2 `gcc -v`  
        - gcc-c++-4.1.2  
        - glibc-2.5-24 `ldd --version`  
        - glibc-2.5-24 (32 bit)  
        - glibc-common-2.5  
        - glibc-devel-2.5  
        - glibc-devel-2.5 (32 bit)  
        - glibc-headers-2.5  
        - ksh-20060214  
        - libaio-0.3.106  
        - libaio-0.3.106 (32 bit)  
        - libaio-devel-0.3.106  
        - libaio-devel-0.3.106 (32 bit)  
        - libgcc-4.1.2  
        - libgcc-4.1.2 (32 bit)  
        - libstdc++-4.1.2  
        - libstdc++-4.1.2 (32 bit)  
        - libstdc++-devel 4.1.2  
        - make-3.81  
        - sysstat-7.0.2  
        - unixODBC-2.2.11  
        - unixODBC-2.2.11 (32 bit)  
        - unixODBC-devel-2.2.11  
        - unixODBC-devel-2.2.11 (32 bit)  
    - 5 Creating Required Operating System Groups and Users  

        - The Oracle Inventory`库存` group(`oinstall`)  
        - The OSDBA group(`dba`)
        - The Oracle software owner(`oracle`)
        - The OSOPER group(`oper`, 可选)  
        创建组和用户  

                $ /usr/sbin/groupadd oinstall  
                $ /usr/sbin/groupadd dba  
                # 新建 oracle 用户，主用户组为 oinstall，第二用户组为 dba  
                $ /usr/sbin/useradd -g oinstall -G dba -m oracle  
                # 给 oracle 用户设置密码  
                $ passwd oracle  
                # 修改默认的 bash  
                $ chsh -s /bin/bash newUserName  
                # 将 oracle 用户加入 sudoer   
                $ vim /etc/sudoer  
                # 复制 root (ALL ... 那一行，粘贴到下面一行，再将这行的 root 改为 oracle  

    - 6 Configuring Kernel Parameters
    - 7 Creating Required Directories  

                $ mkdir -p /app/
                $ chown -R oracle:oinstall /app/
                $ chmod -R 775 /app/

    - 8 Configuring the oracle User's Environment  

                # 用 oracle 账号登录 linux 系统
                $ vi .bash_profile (或者.profile)
                # 添加下面一行
                umask 022
                # 如果检查时发现 /tmp 文件夹空间不足，则需要指定新的挂载点，格式如下
                export TMP=/mount_point/tmp
                export TMPDIR=/mount_point/tmp
                # 添加全局变量
                export ORACLE_BASE=/app/oracle
                export ORACLE_SID=orcl
                # 使设置生效
                . ./.bash_profile  

    - 9 Mounting the Product Disc
    - 10 Installing Oracle Database
    - 11 Installing Oracle Database Examples
    - 12 What to Do Next?
    - 13 Additional Information
    - 14 Documentation Accessibility

---

- Oracle Database Installation Guide for Linux
    - ...
    - [1. Overview of Oracle Database Installation](oracle/2.md)
    - ...
    - [4. Installing Oracle Database](oracle/3.md)
    - ...
    - [7. Remoing Oracle Database Software](oracle/1.md)
    - ...
    - [A. Installing and Configuring Oracle Database Using Response Files](oracle/4.md)
    - ...

---

- Universal Installer and OPatch User's Guide
    - [Preface](oracle/5.md)
    - [Introduction to Oracle Universal Installer](oracle/6.md)
    - ...
    - [Customizing and Creating Response Files](oracle/7.md)


---

静默安装成功    

```
oracle@ubuntu:~/database/database$ ./runInstaller -silent -ignoreSysPrereqs -force -ignorePrereq -responseFile ~/db_install.rsp 
Starting Oracle Universal Installer...

Checking Temp space: must be greater than 120 MB.   Actual 9553 MB    Passed
Checking swap space: must be greater than 150 MB.   Actual 3906 MB    Passed
Preparing to launch Oracle Universal Installer from /tmp/OraInstall2020-06-29_07-58-13AM. Please wait ...
oracle@ubuntu:~/database/database$ You can find the log of this install session at:
 /app/ora_inventory/logs/installActions2020-06-29_07-58-13AM.log
The following configuration scripts need to be executed as the "root" user. 
 #!/bin/sh 
 #Root scripts to run

/app/ora_inventory/orainstRoot.sh
/app/oracle/product/11.2.0/db_1/root.sh
To execute the configuration scripts:
         1. Open a terminal window 
         2. Log in as "root" 
         3. Run the scripts 
         4. Return to this window and hit "Enter" key to continue 

Successfully Setup Software.

```

db_install.rsp

```
oracle.install.responseFileVersion=/oracle/install/rspfmt_dbinstall_response_schema_v11_2_0
oracle.install.option=INSTALL_DB_SWONLY
ORACLE_HOSTNAME=ubuntu
UNIX_GROUP_NAME=oinstall
INVENTORY_LOCATION=/app/ora_inventory
SELECTED_LANGUAGES=en,zh_CN
ORACLE_HOME=/app/oracle/product/11.2.0/db_1
ORACLE_BASE=/app/oracle
oracle.install.db.InstallEdition=SE
oracle.install.db.isCustomInstall=false
oracle.install.db.customComponents=oracle.server:11.2.0.1.0,oracle.sysman.ccr:10.2.7.0.0,oracle.xdk:11.2.0.1.0,oracle.rdbms.oci:11.2.0.1.0,oracle.network:11.2.0.1.0,oracle.network.listener:11.2.0.1.0,oracle.rdbms:11.2.0.1.0,oracle.options:11.2.0.1.0,oracle.rdbms.partitioning:11.2.0.1.0,oracle.oraolap:11.2.0.1.0,oracle.rdbms.dm:11.2.0.1.0,oracle.rdbms.dv:11.2.0.1.0,orcle.rdbms.lbac:11.2.0.1.0,oracle.rdbms.rat:11.2.0.1.0
oracle.install.db.DBA_GROUP=dba
oracle.install.db.OPER_GROUP=oinstall
oracle.install.db.CLUSTER_NODES=
oracle.install.db.config.starterdb.type=GENERAL_PURPOSE
oracle.install.db.config.starterdb.globalDBName=ora11g
oracle.install.db.config.starterdb.SID=orcl
oracle.install.db.config.starterdb.characterSet=AL32UTF8
oracle.install.db.config.starterdb.memoryOption=true
oracle.install.db.config.starterdb.memoryLimit=512
oracle.install.db.config.starterdb.installExampleSchemas=false
oracle.install.db.config.starterdb.enableSecuritySettings=true
oracle.install.db.config.starterdb.password.ALL=qweruiop
oracle.install.db.config.starterdb.password.SYS=
oracle.install.db.config.starterdb.password.SYSTEM=
oracle.install.db.config.starterdb.password.SYSMAN=
oracle.install.db.config.starterdb.password.DBSNMP=
oracle.install.db.config.starterdb.control=DB_CONTROL
oracle.install.db.config.starterdb.gridcontrol.gridControlServiceURL=
oracle.install.db.config.starterdb.dbcontrol.enableEmailNotification=false
oracle.install.db.config.starterdb.dbcontrol.emailAddress=
oracle.install.db.config.starterdb.dbcontrol.SMTPServer=
oracle.install.db.config.starterdb.automatedBackup.enable=false
oracle.install.db.config.starterdb.automatedBackup.osuid=
oracle.install.db.config.starterdb.automatedBackup.ospwd=
oracle.install.db.config.starterdb.storageType=
oracle.install.db.config.starterdb.fileSystemStorage.dataLocation=
oracle.install.db.config.starterdb.fileSystemStorage.recoveryLocation=
oracle.install.db.config.asm.diskGroup=
oracle.install.db.config.asm.ASMSNMPPassword=
MYORACLESUPPORT_USERNAME=
MYORACLESUPPORT_PASSWORD=
SECURITY_UPDATES_VIA_MYORACLESUPPORT=
DECLINE_SECURITY_UPDATES=true
PROXY_HOST=
PROXY_PORT=
PROXY_USER=
PROXY_PWD=
```

netca.rsp  

```
[GENERAL]
RESPONSEFILE_VERSION="11.2"
CREATE_TYPE="CUSTOM"
SHOW_GUI=false
LOG_FILE=""/home/oracle/oracle11gLogs/network/tools/log/netca.log""
INSTALLED_COMPONENTS={"server","net8","javavm"}
INSTALL_TYPE=""typical""
LISTENER_NUMBER=1
LISTENER_NAMES={"LISTENER"}
LISTENER_PROTOCOLS={"TCP;1521"}
LISTENER_START=""LISTENER""
NAMING_METHODS={"TNSNAMES","ONAMES","HOSTNAME"}
NSN_NUMBER=1
NSN_NAMES={"EXTPROC_CONNECTION_DATA"}
NSN_SERVICE={"PLSExtProc"}
NSN_PROTOCOLS={"TCP;HOSTNAME;1521"}
```

配置监听

```
# 如果出现错误 loading native library: njni11
cd $ORACLE_HOME/inventory/Scripts/ext/lib/
cp libclntsh.so.11.1  $ORACLE_HOME/lib/
```

```
root@ubuntu:/home/oracle# /app/oracle/product/11.2.0/db_1/bin/netca /silent /responseFile /home/oracle/netca.rsp

Warning: Failed to create log file:
    /home/oracle/oracle11gLogs/network/tools/log/netca.log
Mon Jun 29 08:14:15 UTC 2020 Oracle Net Configuration Assistant
Parsing command line arguments:
    Parameter "silent" = true
    Parameter "responsefile" = /home/oracle/netca.rsp
    Parameter "log" = /home/oracle/oracle11gLogs/network/tools/log/netca.log
Done parsing command line arguments.
Oracle Net Services Configuration:
Profile configuration complete.
Oracle Net Listener Startup:
    Running Listener Control: 
      /app/oracle/product/11.2.0/db_1/bin/lsnrctl start LISTENER
    Listener Control complete.
    Listener started successfully.
Listener configuration complete.
Oracle Net Services configuration successful. The exit code is 0

```

dbca.rsp

```
[GENERAL]
RESPONSEFILE_VERSION = "11.2.0"
OPERATION_TYPE = "createDatabase"
[CREATEDATABASE]
GDBNAME = "orcl11g.us.oracle.com"
SID = "orcl"
TEMPLATENAME = "General_Purpose.dbc"
LISTENERS = "LISTENER"
```

```
/app/oracle/product/11.2.0/db_1/bin/dbca -silent -responseFile  /usr/local/src/database/response/dbca.rsp
```