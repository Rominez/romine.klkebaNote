package priv.ftp;

import org.apache.ftpserver.ConnectionConfig;
import org.apache.ftpserver.ConnectionConfigFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.Md5PasswordEncryptor;
import org.apache.ftpserver.usermanager.impl.PropertiesUserManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FtpServerStarter {

    public static void main(String[] args) {
        String configFile = null;
        String serverAddress = "0.0.0.0";
        int port = 15021;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]){
                case "-c":
                case "--configFile":{
                    configFile = args[++i];
                    break;
                }
                case "-s":
                case "--serverAddress":{
                    serverAddress = args[++i];
                    break;
                }
                case "-p":
                case "--port":{
                    port = Integer.parseInt(args[++i]);
                    break;
                }
            }
        }
        if(configFile == null){
            configFile = "/home/romine/Desktop/PROJECT/localsvn/project/ftp/src/main/resources/config.properties";
        }
        FtpServerFactory factory = new FtpServerFactory();

//        设置ftp命令
//        CommandFactoryFactory commandFactoryFactory = new CommandFactoryFactory();
//        CommandFactory commandFactory = commandFactoryFactory.createCommandFactory();
//        factory.setCommandFactory(commandFactory);

        ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
//        禁止匿名登录
        connectionConfigFactory.setAnonymousLoginEnabled(false);
//        登录失败延时
        connectionConfigFactory.setLoginFailureDelay(1000 * 60);
        ConnectionConfig connectionConfig = connectionConfigFactory.createConnectionConfig();
        factory.setConnectionConfig(connectionConfig);

//        FileSystemFactory fileSystemFactory = new NativeFileSystemFactory();
//        factory.setFileSystem(fileSystemFactory);

        Md5PasswordEncryptor encryptor = new Md5PasswordEncryptor();
        PropertiesUserManager userManager = new PropertiesUserManager(encryptor, new File(configFile), "admin");
        factory.setUserManager(userManager);

//        Map<String, Ftplet> ftpLets = new HashMap<>();
//        factory.setFtplets(ftpLets);

        Map<String, Listener> listeners = new HashMap<>();
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setServerAddress(serverAddress);
        listenerFactory.setPort(port);
        Listener listener = listenerFactory.createListener();
        listeners.put("default",listener);
        factory.setListeners(listeners);

        FtpServer server = factory.createServer();
        try {
            server.start();
        } catch (FtpException e) {
            e.printStackTrace();
        }
    }
}
