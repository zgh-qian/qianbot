package com.qian.qianbotbackend.constant;

import java.util.Arrays;
import java.util.List;

public interface OjConstant {
    // region 返回信息
    Integer OJ_QUESTION_TITLE_LENGTH = 200;
    String OJ_QUESTION_TITLE_LENGTH_TOO_LONG = "题目标题过长";
    Integer OJ_TAGS_LENGTH = 5;
    String OJ_TAGS_LENGTH_TOO_LONG = "标签数量过多";
    String OJ_QUESTION_DIFFICULTY_ERROR = "题目难度只能是简单、中等、困难";
    Integer OJ_QUESTION_CONTENT_LENGTH = 10000;
    String OJ_QUESTION_CONTENT_LENGTH_TOO_LONG = "题目内容过长";
    Integer OJ_QUESTION_TEMPLATE_LENGTH = 10000;
    String OJ_QUESTION_TEMPLATE_LENGTH_TOO_LONG = "题目模板过长";
    Integer OJ_QUESTION_ANSWER_LENGTH = 10000;
    String OJ_QUESTION_ANSWER_LENGTH_TOO_LONG = "题目回答过长";
    Integer OJ_TIPS_LENGTH = 3;
    String OJ_TIPS_LENGTH_TOO_LONG = "提示数量过多";
    // endregion

    // region codesandbox
    /**
     * 成功返回码
     */
    Integer CODE_SUCCESS = 0;

    /**
     * 错误返回码
     */
    Integer CODE_ERROR = 1;

    /**
     * 错误消息返回码
     */
    Integer CODE_ERROR_MESSAGE = 2;

    /**
     * 用户目录
     */
    String USER_DIR_NAME = "user.dir";

    /**
     * 用户提交代码临时目录
     */
    String CODE_DIR_NAME = "tmpCode";

    // endregion

    // region java

    /**
     * 编译前的Java文件目录的Java类名
     */
    String JAVA_CLASS_NAME = "Main.java";

    /**
     * 编译后的Java文件目录的Java类名
     */
    String JAVA_CLASS_NAME_WITHOUT_SUFFIX = "Main";

    /**
     * 编译命令 javac -encoding utf-8 %s
     */
    String COMPILE_COMMAND = "javac -encoding utf-8 %s";

    /**
     * 运行命令
     * java -Dfile.encoding=UTF-8 -cp %s Main
     * java -Dfile.encoding=UTF-8 -cp %s Main %s
     * java -Xmx256m -Dfile.encoding=UTF-8 -cp %s;%s -Djava.security.manager=%s Main %s
     */
    String RUN_COMMAND_INTERPRETER = "java -Dfile.encoding=UTF-8 -cp %s Main";

    String RUN_COMMAND_ARGUMENTS = "java -Dfile.encoding=UTF-8 -cp %s Main %s";

    /**
     * Java镜像
     */
    String JAVA_IMAGE = "openjdk:8-alpine";

    /**
     * Java容器路径
     */
    String JAVA_VOLUME_PATH = "/app";

    /**
     * 黑名单
     */
    List<String> BLACK_LIST = Arrays.asList(
            "File",
            "Files",
            "Runtime",
            "Process",
            "exec",
            "SecurityManager",
            "ClassLoader",
            "Reflection",
            "Unsafe",
            "Socket",
            "ServerSocket",
            "SocketChannel",
            "DatagramSocket",
            "HttpURLConnection",
            "HttpsURLConnection",
            "URL",
            "URI",
            "InetAddress",
            "DNS",
            "Proxy",
            "ProxySelector",
            "PrintWriter",
            "BufferedReader",
            "InputStream",
            "OutputStream",
            "Cipher",
            "SecretKey",
            "TrustManager",
            "KeyStore",
            "FileInputStream",
            "FileOutputStream",
            "ObjectInputStream",
            "ObjectOutputStream",
            "PipedInputStream",
            "PipedOutputStream",
            "ZipInputStream",
            "ZipOutputStream",
            "GZIPInputStream",
            "GZIPOutputStream",
            "JarFile",
            "ClassLoader",
            "ProcessBuilder",
            "ProcessImpl",
            "Native",
            "JNI",
            "Reflection",
            "MethodHandle",
            "sun.misc",
            "sun.reflect",
            "java.lang.reflect",
            "Runtime.getRuntime()",
            "ProcessBuilder.start()",
            "Runtime.exec()",
            "ClassLoader.getSystemClassLoader()",
            "System.load()",
            "System.loadLibrary()",
            "SecurityManager.check*",
            "FileDescriptor",
            "RandomAccessFile",
            "Channel",
            "FileLock",
            "Unsafe.allocateMemory()",
            "Unsafe.freeMemory()",
            "Unsafe.throwException()",
            "Thread.stop()",
            "Thread.suspend()",
            "Thread.resume()",
            "Thread.destroy()",
            "Thread.setContextClassLoader()",
            "ThreadGroup.allowThreadSuspension()",
            "ThreadGroup.stop()",
            "ThreadGroup.suspend()",
            "ThreadGroup.resume()",
            "ThreadGroup.destroy()",
            "ThreadGroup.setDaemon()",
            "ThreadGroup.setMaxPriority()",
            "ThreadGroup.checkAccess()",
            "ThreadLocal",
            "Instrumentation",
            "Class.get*Method",
            "Class.getDeclared*Method",
            "Method.invoke()",
            "MethodHandle.invoke()",
            "AccessibleObject.setAccessible()",
            "SecurityManager.checkPermission()",
            "SecurityManager.checkAccept()",
            "SecurityManager.checkConnect()",
            "SecurityManager.checkCreateClassLoader()",
            "SecurityManager.checkExec()",
            "SecurityManager.checkLink()",
            "SecurityManager.checkListen()",
            "SecurityManager.checkMemberAccess()",
            "SecurityManager.checkMulticast()",
            "SecurityManager.checkPackageAccess()",
            "SecurityManager.checkPackageDefinition()",
            "SecurityManager.checkPermission()",
            "SecurityManager.checkPropertiesAccess()",
            "SecurityManager.checkPropertyAccess()",
            "SecurityManager.checkRead()",
            "SecurityManager.checkSecurityAccess()",
            "SecurityManager.checkWrite()",
            "ClassLoader.defineClass()",
            "ClassLoader.findClass()",
            "ClassLoader.loadClass()",
            "ClassLoader.resolveClass()",
            "ClassLoader.definePackage()",
            "ClassLoader.findLibrary()",
            "ClassLoader.getResource()",
            "ClassLoader.getResources()",
            "ClassLoader.getSystemResource()",
            "ClassLoader.getSystemResources()",
            "ClassLoader.getPackage()",
            "ClassLoader.getPackages()",
            "ClassLoader.getSystemPackage()",
            "ClassLoader.getSystemPackages()",
            "Class.forName()",
            "ClassLoader.registerAsParallelCapable()",
            "URLClassLoader.newInstance()",
            "URLClassLoader.newInstance()",
            "URLClassLoader.addURL()",
            "URLClassLoader.findClass()",
            "URLClassLoader.getURLs()",
            "URLClassLoader.getResource()",
            "URLClassLoader.getResources()",
            "URLClassLoader.loadClass()",
            "URLClassLoader.newInstance()",
            "URLClassLoader.newInstance()",
            "URLClassLoader.resolveClass()",
            "URLClassLoader.setDefaultAssertionStatus()",
            "URLClassLoader.setPackageAssertionStatus()",
            "URLClassLoader.setClassAssertionStatus()",
            "URLClassLoader.clearAssertionStatus()",
            "URLClassLoader.definePackage()",
            "URLClassLoader.getPermissions()",
            "URLClassLoader.close()",
            "URLClassLoader.getParent()",
            "URLClassLoader.getSystemClassLoader()",
            "URLClassLoader.getSystemResources()",
            "URLClassLoader.getSystemResource()",
            "URLClassLoader.getSystemPackages()",
            "URLClassLoader.getSystemPackage()",
            "URLClassLoader.setPackageAssertionStatus()",
            "URLClassLoader.setClassAssertionStatus()",
            "URLClassLoader.setDefaultAssertionStatus()",
            "InetAddress.getAllByName()",
            "InetAddress.getByName()",
            "InetAddress.getLoopbackAddress()",
            "InetAddress.getLocalHost()",
            "InetAddress.getByName()",
            "InetAddress.getByAddress()",
            "InetAddress.getByAddress()",
            "InetAddress.getByAddress()",
            "InetAddress.getByName()",
            "InetAddress.getLoopbackAddress()",
            "InetAddress.getLocalHost()",
            "InetAddress.getByName()",
            "InetAddress.getByAddress()",
            "InetAddress.getByAddress()",
            "InetAddress.getByAddress()",
            "InetAddress.getByName()",
            "InetAddress.getLoopbackAddress()",
            "InetAddress.getLocalHost()",
            "InetAddress.getByName()",
            "InetAddress.getByAddress()",
            "InetAddress.getByAddress()",
            "InetAddress.getByAddress()",
            "InetAddress.getByName()",
            "InetAddress.getLoopbackAddress()"
    );
    // endregion
}
