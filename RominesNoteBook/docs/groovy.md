# Groovy
version 3.0.3  
[Groovy 和 Java 的主要区别](groovy/1.md)  
[The Groovy Development Kit](groovy/2.md)  

---

### 安装  

Windows 安装可以使用 chocolate ，而在 Linux 上安装可以使用 sdkman  

sdkman 的安装  

```
$ curl -s "https://get.sdkman.io" | bash
# Follow the instructions on-screen to complete installation.
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk version
```

使用 sdkman 

```
# 安装 Gava 可以指定版本号
$ sdk install java x.y.z-open
# 安装 Groovy 3.0.4
$ sdk install groovy
# 安装 Maven 3.6.3
$ sdk install maven
# 安装 Gradle 6.4.1
$ sdk install gradle

# 卸载
$ sdk uninstall name version
```